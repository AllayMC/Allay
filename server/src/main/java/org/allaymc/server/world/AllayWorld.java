package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.world.WeatherChangeEvent;
import org.allaymc.api.eventbus.event.world.WorldDataSaveEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.chunk.FakeChunkLoader;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.AllayServer;
import org.allaymc.server.datastruct.collections.queue.BlockingQueueWrapper;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.utils.GameLoop;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3i;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorld implements World {

    protected static final int TIME_SENDING_INTERVAL = 12 * 20;
    protected static final int MAX_PACKETS_HANDLE_COUNT_AT_ONCE = AllayServer.getSettings().networkSettings().maxSyncedPacketsHandleCountAtOnce();
    protected static final boolean ENABLE_INDEPENDENT_NETWORK_THREAD = AllayServer.getSettings().networkSettings().enableIndependentNetworkThread();
    protected static final boolean TICK_DIMENSION_IN_PARALLEL = AllayServer.getSettings().worldSettings().tickDimensionInParallel();

    @Getter
    protected final String name;
    @Getter
    protected final WorldStorage worldStorage;
    @Getter
    protected final AllayWorldData worldData;

    protected final BlockingQueueWrapper<PacketQueueEntry> packetQueue;
    protected final Semaphore networkSemaphore;

    protected final AtomicReference<WorldState> state;
    protected final Int2ObjectOpenHashMap<Dimension> dimensionMap;
    @Getter
    protected final Scheduler scheduler;
    protected final GameLoop gameLoop;
    @Getter
    protected final Thread worldThread, networkThread;

    protected long nextTimeSendTick;

    @Getter
    protected Weather weather;
    protected int rainTimer, thunderTimer;
    protected boolean raining, thundering;

    protected boolean firstTick;
    @Getter
    @Setter
    protected boolean runtimeOnly;

    public AllayWorld(String name, WorldStorage worldStorage) {
        this.name = name;
        this.worldStorage = worldStorage;
        this.worldStorage.setWorld(this);
        this.worldData = (AllayWorldData) worldStorage.readWorldData();
        this.worldData.setWorld(this);
        this.worldData.increaseWorldStartCount();
        this.packetQueue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        this.networkSemaphore = ENABLE_INDEPENDENT_NETWORK_THREAD ? new Semaphore(1) : null;
        this.state = new AtomicReference<>(WorldState.STARTING);
        this.dimensionMap = new Int2ObjectOpenHashMap<>(3);
        this.scheduler = new AllayScheduler(Server.getInstance().getVirtualThreadPool());
        this.gameLoop = GameLoop.builder()
                .currentTick(this.worldData.getTotalTime())
                .onStart(this::onWorldStart)
                .onTick(this::worldThreadMain)
                .onStop(this::shutdownReally)
                .build();
        this.worldThread = Thread.ofPlatform()
                .name("World Thread #" + this.getName())
                .unstarted(gameLoop::startLoop);
        this.networkThread = ENABLE_INDEPENDENT_NETWORK_THREAD ? Thread.ofPlatform()
                .name("World Network Thread #" + this.getName())
                .unstarted(this::networkThreadMain) : null;
        this.weather = Weather.CLEAR;
        this.rainTimer = Weather.CLEAR.generateRandomTimeLength();
        this.thunderTimer = Weather.CLEAR.generateRandomTimeLength();
        this.firstTick = true;
    }

    protected void onWorldStart() {
        this.state.set(WorldState.RUNNING);
    }

    @SneakyThrows
    protected void networkThreadMain() {
        while (getState() == WorldState.RUNNING) {
            // Block until there are packets to handle (up to 1 second)
            var firstEntry = packetQueue.tryPoll(1, TimeUnit.SECONDS);
            if (firstEntry == null) {
                continue;
            }
            // Get the permit to handle packets
            networkSemaphore.acquire();
            handleSyncPackets(firstEntry);
            // Return the permit so that the world thread can run
            networkSemaphore.release();
        }
    }

    @SneakyThrows
    private void worldThreadMain(GameLoop gameLoop) {
        this.worldData.setTotalTime(gameLoop.getTick());
        if (getState() != WorldState.RUNNING) {
            gameLoop.stop();
            return;
        }

        if (ENABLE_INDEPENDENT_NETWORK_THREAD) {
            networkSemaphore.acquire();
        }

        try {
            tick(gameLoop.getTick());
        } catch (Throwable throwable) {
            log.error("Error while ticking world {}", name, throwable);
        } finally {
            if (ENABLE_INDEPENDENT_NETWORK_THREAD) {
                networkSemaphore.release();
            }
        }
    }

    public void addSyncPacketToQueue(Player player, BedrockPacket packet, long time) {
        this.packetQueue.offer(new PacketQueueEntry(player, packet, time));
    }

    protected void handleSyncPackets(PacketQueueEntry firstEntry) {
        try {
            PacketQueueEntry entry = firstEntry;
            int count = 0;
            do {
                // The first entry can be null, for why we do that see the callers
                if (entry == null) {
                    continue;
                }

                // The player should still in the same world
                if (entry.player.getControlledEntity().getWorld() != this) {
                    log.error("Trying to handle sync packet in world {} which the player {} is not in!", name, entry.player.getOriginName());
                    continue;
                }

                // The player may have been disconnected,
                // which is possible because this is a synced packet
                if (!entry.player.getClientState().canHandlePackets()) {
                    continue;
                }

                ((AllayPlayer) entry.player).handlePacketSync(entry.packet(), entry.time());
                count++;
            } while (count < MAX_PACKETS_HANDLE_COUNT_AT_ONCE && (entry = packetQueue.pollNow()) != null);
        } catch (Throwable throwable) {
            log.error("Error while handling sync packet in world {}", this.getWorldData().getDisplayName(), throwable);
        }
    }

    protected void tick(long currentTick) {
        checkFirstTick();
        if (!ENABLE_INDEPENDENT_NETWORK_THREAD) {
            handleSyncPackets(null);
        }

        tickTime(currentTick);
        tickWeather();
        scheduler.tick();

        var dimensions = dimensionMap.values();
        if (TICK_DIMENSION_IN_PARALLEL && dimensions.size() > 1) {
            Utils.forEachInParallel(
                    dimensions, Server.getInstance().getComputeThreadPool(),
                    dimension -> ((AllayDimension) dimension).tick(currentTick)
            ).join();
        } else {
            for (var dimension : dimensions) {
                ((AllayDimension) dimension).tick(currentTick);
            }
        }

        worldStorage.tick(currentTick);
    }

    protected void checkFirstTick() {
        if (!this.firstTick) {
            return;
        }
        this.firstTick = false;

        var overworld = getOverWorld();
        if (AllayServer.getSettings().worldSettings().loadSpawnPointChunks()) {
            // Add spawn point chunk loader
            overworld.getChunkManager().addChunkLoader(new FakeChunkLoader(() -> {
                var spawnPoint = this.worldData.getSpawnPoint();
                return new Location3d(spawnPoint.x(), spawnPoint.y(), spawnPoint.z(), getOverWorld());
            }, AllayServer.getSettings().worldSettings().spawnPointChunkRadius()));
        }

        // Find the spawn point only the first time the world is loaded
        if (this.worldData.getWorldStartCount() == 1 && !isSafeStandingPos(new Position3i(worldData.getSpawnPoint(), overworld))) {
            Thread.ofVirtual().name("Spawn Point Finding Thread #" + name).start(() -> {
                var newSpawnPoint = overworld.findSuitableGroundPosAround(this::isSafeStandingPos, 0, 0, 32);
                if (newSpawnPoint == null) {
                    log.warn("Cannot find a safe spawn point in the overworld dimension of world {}", name);
                    overworld.getChunkManager().getOrLoadChunk(0, 0);
                    newSpawnPoint = new Vector3i(0, overworld.getHeight(0, 0) + 1, 0);
                }
                var finalNewSpawnPoint = newSpawnPoint;
                overworld.getWorld().getScheduler().runLater(this, () -> {
                    // Set new spawn point in world thread as world data object is not thread-safe
                    this.worldData.setSpawnPoint(finalNewSpawnPoint);
                    log.info("Spawn point for world {} is set to {}, {}, {}", name, finalNewSpawnPoint.x(), finalNewSpawnPoint.y(), finalNewSpawnPoint.z());
                });
            });
        }
    }

    protected boolean isSafeStandingPos(Position3ic pos) {
        var blockUnder = pos.dimension().getBlockState(pos.x(), pos.y() - 1, pos.z());
        if (!blockUnder.getBlockStateData().isSolid()) {
            return false;
        }
        return pos.dimension().getBlockState(pos.x(), pos.y(), pos.z()).getBlockType() == BlockTypes.AIR &&
               pos.dimension().getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockType() == BlockTypes.AIR;
    }

    protected void tickTime(long currentTick) {
        if (currentTick < this.nextTimeSendTick) {
            return;
        }

        if (!this.worldData.<Boolean>getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)) {
            // Client will always keep time flowing, so we still need to send the
            // same time uninterruptedly if the daylight cycle is disabled
            getPlayers().forEach(player -> player.viewTime(this.worldData.getTimeOfDay()));
        } else {
            this.worldData.addTimeOfDay(TIME_SENDING_INTERVAL);
        }

        this.nextTimeSendTick = currentTick + TIME_SENDING_INTERVAL;
    }

    protected void tickWeather() {
        if (!worldData.<Boolean>getGameRuleValue(GameRule.DO_WEATHER_CYCLE)) {
            return;
        }

        var newWeather = this.weather;
        if (--this.rainTimer == 0) {
            if (this.raining) {
                newWeather = Weather.CLEAR;
                this.rainTimer = Weather.CLEAR.generateRandomTimeLength();
            } else {
                newWeather = thundering ? Weather.THUNDER : Weather.RAIN;
                this.rainTimer = Weather.RAIN.generateRandomTimeLength();
            }
            this.raining = !this.raining;
        }

        if (--this.thunderTimer == 0) {
            if (this.thundering) {
                newWeather = raining ? Weather.RAIN : Weather.CLEAR;
                this.thunderTimer = Weather.CLEAR.generateRandomTimeLength();
            } else {
                newWeather = raining ? Weather.THUNDER : Weather.CLEAR;
                this.thunderTimer = Weather.THUNDER.generateRandomTimeLength();
            }
            this.thundering = !this.thundering;
        }

        setWeather(newWeather);
    }

    @Override
    public long getTick() {
        return this.gameLoop.getTick();
    }

    @Override
    public float getTPS() {
        return this.gameLoop.getTPS();
    }

    @Override
    public float getMSPT() {
        return this.gameLoop.getMSPT();
    }

    @Override
    public float getTickUsage() {
        return this.gameLoop.getTickUsage();
    }

    public void startTick() {
        if (this.worldThread.getState() != Thread.State.NEW) {
            throw new IllegalStateException("World " + this.name + " is already ticking!");
        }

        this.worldThread.start();
        this.dimensionMap.values().forEach(dimension -> ((AllayDimension) dimension).startTick());
        if (ENABLE_INDEPENDENT_NETWORK_THREAD) {
            this.networkThread.start();
        }
    }

    @Override
    public Dimension getDimension(int dimensionId) {
        return this.dimensionMap.get(dimensionId);
    }

    @Override
    @UnmodifiableView
    public Map<Integer, Dimension> getDimensions() {
        return Collections.unmodifiableMap(this.dimensionMap);
    }

    @Override
    @UnmodifiableView
    public Collection<Player> getPlayers() {
        return this.dimensionMap.values().stream()
                .flatMap(dimension -> dimension.getPlayers().stream())
                .toList();
    }

    public void addDimension(Dimension dimension) {
        Preconditions.checkArgument(!this.dimensionMap.containsKey(dimension.getDimensionInfo().dimensionId()));
        this.dimensionMap.put(dimension.getDimensionInfo().dimensionId(), dimension);
    }

    @Override
    public void saveWorldData() {
        new WorldDataSaveEvent(this).call();
        getWorldStorage().writeWorldData(worldData);
    }

    public void shutdown() {
        // Mark the world as STOPPING, the real shutdown logic is in shutdownReally() method
        state.set(WorldState.STOPPING);
    }

    protected void shutdownReally() {
        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_UNLOADING, name));
        getPlayers().forEach(Player::disconnect);
        this.scheduler.shutdown();
        this.dimensionMap.values().forEach(dimension -> ((AllayDimension) dimension).shutdown());
        saveWorldData();
        this.worldStorage.shutdown();
        this.state.set(WorldState.STOPPED);
        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_UNLOADED, name));
    }

    @Override
    public WorldState getState() {
        return state.get();
    }

    @Override
    public void setWeather(Weather weather) {
        if (this.weather == weather) {
            return;
        }

        var event = new WeatherChangeEvent(this, this.weather, weather);
        if (!event.call()) {
            return;
        }

        this.weather = weather;
        getPlayers().forEach(player -> player.viewWeather(this.weather));
    }

    protected record PacketQueueEntry(Player player, BedrockPacket packet, long time) {
    }
}
