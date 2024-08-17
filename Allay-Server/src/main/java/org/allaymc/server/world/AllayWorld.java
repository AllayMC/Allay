package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.world.WorldDataSaveEvent;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.GameLoop;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.NativeFileWorldStorage;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.scheduler.AllayScheduler;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Allay Project 2023/7/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorld implements World {
    public static final long TIME_SENDING_INTERVAL = 12 * 20;

    public static final int MAX_PACKETS_HANDLE_COUNT_AT_ONCE = Server.SETTINGS.networkSettings().maxSyncedPacketsHandleCountAtOnce();

    protected final Queue<PacketQueueEntry> packetQueue = PlatformDependent.newMpscQueue();
    protected final AtomicBoolean networkLock = new AtomicBoolean(false);
    protected final AtomicBoolean isRunning = new AtomicBoolean(true);
    @Getter
    protected final WorldStorage worldStorage;
    @Getter
    protected final WorldData worldData;
    @Getter
    protected final Int2ObjectOpenHashMap<Dimension> dimensionMap = new Int2ObjectOpenHashMap<>(3);
    @Getter
    protected final Scheduler scheduler = new AllayScheduler(Server.getInstance().getVirtualThreadPool());
    protected final GameLoop gameLoop;
    @Getter
    protected final Thread thread;
    protected final Thread networkThread;
    protected long nextTimeSendTick;

    @Getter
    protected int internalSkyLight;

    public AllayWorld(WorldStorage worldStorage) {
        this.worldStorage = worldStorage;
        this.worldData = worldStorage.getWorldDataCache();
        this.worldData.setWorld(this);

        if (worldStorage instanceof NativeFileWorldStorage nativeFileWorldStorage)
            this.worldData.setName(nativeFileWorldStorage.getWorldFolderPath().toFile().getName());

        this.gameLoop = GameLoop.builder().onTick(gameLoop -> {
            if (!isRunning.get()) {
                gameLoop.stop();
                return;
            }

            //noinspection StatementWithEmptyBody
            while (!networkLock.compareAndSet(false, true)) {
                // Spin
                // We don't use Thread.yield() here, because we don't want to block the world main thread
            }

            try {
                tick(gameLoop.getTick());
            } catch (Throwable throwable) {
                log.error("Error while ticking level {}", this.getWorldData().getName(), throwable);
            } finally {
                networkLock.set(false);
            }
        }).build();
        this.thread = Thread.ofPlatform()
                .name("World Thread - " + this.getWorldData().getName())
                .unstarted(gameLoop::startLoop);
        this.networkThread = Thread.ofVirtual()
                .name("World Network Thread - " + this.getWorldData().getName())
                .unstarted(this::networkTick);
        
        this.internalSkyLight = worldData.calculInternalSkyLight(1);
    }

    protected void networkTick() {
        while (isRunning.get()) {
            if (!packetQueue.isEmpty()) {
                while (!networkLock.compareAndSet(false, true)) {
                    // Spin
                    Thread.yield();
                }
            } else {
                Thread.yield();
                continue;
            }

            try {
                PacketQueueEntry entry;
                int count = 0;
                while (count < MAX_PACKETS_HANDLE_COUNT_AT_ONCE && (entry = packetQueue.poll()) != null) {
                    entry.player().handleDataPacket(entry.packet(), entry.time());
                    count++;
                }
            } catch (Throwable throwable) {
                log.error("Error while handling sync packet in world {}", this.getWorldData().getName(), throwable);
            } finally {
                networkLock.set(false);
            }
        }
    }

    @Override
    public void addSyncPacketToQueue(EntityPlayer player, BedrockPacket packet, long time) {
        packetQueue.add(new PacketQueueEntry(player, packet, time));
    }

    @Override
    public long getTick() {
        return gameLoop.getTick();
    }

    @Override
    public float getTps() {
        return gameLoop.getTps();
    }

    @Override
    public float getMSPT() {
        return gameLoop.getMSPT();
    }

    @Override
    public float getTickUsage() {
        return gameLoop.getTickUsage();
    }

    @Override
    public void tick(long currentTick) {
        syncData();
        tickTime(currentTick);
        scheduler.tick();
        getDimensions().values().forEach(d -> d.tick(currentTick));
        worldStorage.tick(currentTick);
    }

    protected void syncData() {
        worldData.getGameRules().sync(this);
    }

    @Override
    public void startTick() {
        if (thread.getState() != Thread.State.NEW) {
            throw new IllegalStateException("World is already start ticking!");
        } else {
            thread.start();
            networkThread.start();
        }
    }

    protected void tickTime(long currentTick) {
        if (worldData.getGameRule(GameRule.DO_DAYLIGHT_CYCLE)) {
            if (currentTick >= nextTimeSendTick) {
                worldData.setTime(worldData.getTime() + TIME_SENDING_INTERVAL);
                this.internalSkyLight = worldData.calculInternalSkyLight(currentTick);

                nextTimeSendTick = currentTick + TIME_SENDING_INTERVAL; // Send the time to client every 12 seconds
            }
        }
    }

    @Override
    public Dimension getDimension(int dimensionId) {
        return dimensionMap.get(dimensionId);
    }

    @Override
    public @UnmodifiableView Map<Integer, Dimension> getDimensions() {
        return Collections.unmodifiableMap(dimensionMap);
    }

    @Override
    public Collection<EntityPlayer> getPlayers() {
        return Collections.unmodifiableCollection(
                getDimensions().values().stream()
                        .map(Dimension::getPlayers)
                        .reduce(new HashSet<>(), (entityPlayers, entityPlayers2) -> {
                            entityPlayers.addAll(entityPlayers2);
                            return entityPlayers;
                        })
        );
    }

    @Override
    public void setDimension(Dimension dimension) {
        Preconditions.checkArgument(!this.dimensionMap.containsKey(dimension.getDimensionInfo().dimensionId()));
        this.dimensionMap.put(dimension.getDimensionInfo().dimensionId(), dimension);
    }

    @Override
    public void saveWorldData() {
        var event = new WorldDataSaveEvent(this);
        event.call();
        getWorldStorage().writeWorldData(worldData);
    }

    @Override
    public void shutdown() {
        isRunning.set(false);
        dimensionMap.values().forEach(Dimension::shutdown);
        saveWorldData();
        getWorldStorage().shutdown();
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }

    protected record PacketQueueEntry(EntityPlayer player, BedrockPacket packet, long time) {}
}
