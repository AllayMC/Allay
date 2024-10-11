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
import org.allaymc.api.world.Weather;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.allaymc.server.scheduler.AllayScheduler;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorld implements World {
    // Send the time to client every 12 seconds
    protected static final long TIME_SENDING_INTERVAL = 12 * 20;

    protected static final int MAX_PACKETS_HANDLE_COUNT_AT_ONCE = Server.SETTINGS.networkSettings().maxSyncedPacketsHandleCountAtOnce();
    protected static final boolean ENABLE_INDEPENDENT_NETWORK_THREAD = Server.SETTINGS.networkSettings().enableIndependentNetworkThread();

    protected final Queue<PacketQueueEntry> packetQueue = PlatformDependent.newMpscQueue();
    protected final AtomicBoolean networkLock = ENABLE_INDEPENDENT_NETWORK_THREAD ? new AtomicBoolean(false) : null;
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
    protected int rainTimer = Weather.CLEAR.generateRandomTimeLength();
    protected int thunderTimer = Weather.CLEAR.generateRandomTimeLength();
    protected boolean isRaining = false;
    protected boolean isThundering = false;
    protected Set<Weather> effectiveWeathers = new HashSet<>();

    public AllayWorld(WorldStorage worldStorage) {
        this.worldStorage = worldStorage;
        this.worldData = worldStorage.readWorldData();
        this.worldData.setWorld(this);

        this.gameLoop = GameLoop.builder().onTick(gameLoop -> {
            if (!isRunning.get()) {
                gameLoop.stop();
                return;
            }

            if (ENABLE_INDEPENDENT_NETWORK_THREAD) {
                //noinspection StatementWithEmptyBody
                while (!networkLock.compareAndSet(false, true)) {
                    // Spin
                    // We don't use Thread.yield() here, because we don't want to block the world main thread
                }
            }

            try {
                tick(gameLoop.getTick());
            } catch (Throwable throwable) {
                log.error("Error while ticking level {}", this.getWorldData().getName(), throwable);
            } finally {
                if (ENABLE_INDEPENDENT_NETWORK_THREAD) {
                    networkLock.set(false);
                }
            }
        }).build();
        this.thread = Thread.ofPlatform()
                .name("World Thread - " + this.getWorldData().getName())
                .unstarted(gameLoop::startLoop);
        if (ENABLE_INDEPENDENT_NETWORK_THREAD) {
            this.networkThread = Thread.ofPlatform()
                    .name("World Network Thread - " + this.getWorldData().getName())
                    .unstarted(() -> {
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
                            handleSyncPackets();
                        }
                    });
        } else {
            this.networkThread = null;
        }
    }

    protected void handleSyncPackets() {
        try {
            PacketQueueEntry entry;
            int count = 0;
            while (count < MAX_PACKETS_HANDLE_COUNT_AT_ONCE && (entry = packetQueue.poll()) != null) {
                entry.player().getManager().<EntityPlayerNetworkComponentImpl>getComponent(EntityPlayerNetworkComponentImpl.IDENTIFIER).handleDataPacket(entry.packet(), entry.time());
                count++;
            }
        } catch (Throwable throwable) {
            log.error("Error while handling sync packet in world {}", this.getWorldData().getName(), throwable);
        } finally {
            if (ENABLE_INDEPENDENT_NETWORK_THREAD) {
                networkLock.set(false);
            }
        }
    }

    protected void tick(long currentTick) {
        if (!ENABLE_INDEPENDENT_NETWORK_THREAD) {
            handleSyncPackets();
        }
        syncData();
        tickTime(currentTick);
        tickWeather();
        scheduler.tick();
        getDimensions().values().forEach(d -> ((AllayDimension) d).tick(currentTick));
        worldStorage.tick(currentTick);
    }


    public void addSyncPacketToQueue(EntityPlayer player, BedrockPacket packet, long time) {
        packetQueue.add(new PacketQueueEntry(player, packet, time));
    }

    protected void tickTime(long currentTick) {
        if (!worldData.<Boolean>getGameRule(GameRule.DO_DAYLIGHT_CYCLE)) return;

        if (currentTick >= nextTimeSendTick) {
            worldData.addTime(TIME_SENDING_INTERVAL);
            nextTimeSendTick = currentTick + TIME_SENDING_INTERVAL;
        }
    }

    protected void tickWeather() {
        if (!worldData.<Boolean>getGameRule(GameRule.DO_WEATHER_CYCLE)) {
            return;
        }

        Set<Weather> weatherAdded = new HashSet<>();
        Set<Weather> weatherRemoved = new HashSet<>();
        rainTimer--;
        if (rainTimer == 0) {
            if (isRaining) {
                isRaining = false;
                rainTimer = Weather.CLEAR.generateRandomTimeLength();
                weatherRemoved.add(Weather.RAIN);
                effectiveWeathers.remove(Weather.RAIN);
            } else {
                isRaining = true;
                rainTimer = Weather.RAIN.generateRandomTimeLength();
                weatherAdded.add(Weather.RAIN);
                effectiveWeathers.add(Weather.RAIN);
            }
        }

        thunderTimer--;
        if (thunderTimer == 0) {
            if (isThundering) {
                isThundering = false;
                thunderTimer = Weather.CLEAR.generateRandomTimeLength();
                weatherRemoved.add(Weather.THUNDER);
                effectiveWeathers.remove(Weather.THUNDER);
            } else {
                isThundering = true;
                thunderTimer = Weather.THUNDER.generateRandomTimeLength();
                weatherAdded.add(Weather.THUNDER);
                effectiveWeathers.add(Weather.THUNDER);
            }
        }

        onWeatherUpdate(weatherRemoved, weatherAdded);
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

    protected void syncData() {
        worldData.getGameRules().sync(this);
    }

    public void startTick() {
        if (thread.getState() != Thread.State.NEW) {
            throw new IllegalStateException("World is already start ticking!");
        } else {
            thread.start();
            if (ENABLE_INDEPENDENT_NETWORK_THREAD) {
                networkThread.start();
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

    public void shutdown() {
        isRunning.set(false);
        dimensionMap.values().forEach(dimension -> ((AllayDimension) dimension).shutdown());
        saveWorldData();
        worldStorage.shutdown();
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }

    @Override
    public Set<Weather> getWeathers() {
        return effectiveWeathers.isEmpty() ?
                Set.of(Weather.CLEAR) :
                Collections.unmodifiableSet(effectiveWeathers);
    }

    @Override
    public void addWeather(Weather weather) {
        if (weather == Weather.CLEAR) {
            throw new IllegalArgumentException("Weather.CLEAR shouldn't be used here.");
        }
        if (effectiveWeathers.contains(weather)) {
            return;
        }
        effectiveWeathers.add(weather);
        onWeatherUpdate(Set.of(), Set.of(weather));
    }

    @Override
    public void removeWeather(Weather weather) {
        if (weather == Weather.CLEAR) {
            throw new IllegalArgumentException("Weather.CLEAR shouldn't be used here.");
        }
        if (!effectiveWeathers.contains(weather)) {
            return;
        }
        effectiveWeathers.remove(weather);
        onWeatherUpdate(Set.of(weather), Set.of());
    }

    @Override
    public void clearWeather() {
        onWeatherUpdate(effectiveWeathers, Set.of());
        effectiveWeathers.clear();
    }

    public void clearWeather(EntityPlayer player) {
        effectiveWeathers.forEach(weather -> player.sendPacket(weather.createStopLevelEventPacket()));
    }

    public void sendWeather(EntityPlayer player) {
        effectiveWeathers.forEach(weather -> player.sendPacket(weather.createStartLevelEventPacket()));
    }

    protected void onWeatherUpdate(Set<Weather> weatherRemoved, Set<Weather> weatherAdded) {
        getPlayers().forEach(player -> {
            weatherRemoved.forEach(weather -> player.sendPacket(weather.createStopLevelEventPacket()));
            weatherAdded.forEach(weather -> player.sendPacket(weather.createStartLevelEventPacket()));
        });
    }

    protected record PacketQueueEntry(EntityPlayer player, BedrockPacket packet, long time) {}
}
