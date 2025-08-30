package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.world.WeatherChangeEvent;
import org.allaymc.api.eventbus.event.world.WorldDataSaveEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.GameLoop;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.Weather;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.chunk.FakeChunkLoader;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.datastruct.collections.queue.BlockingQueueWrapper;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.scheduler.AllayScheduler;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3i;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorld implements World {

    protected static final int TIME_SENDING_INTERVAL = 12 * 20;
    protected static final int MAX_PACKETS_HANDLE_COUNT_AT_ONCE = Server.SETTINGS.networkSettings().maxSyncedPacketsHandleCountAtOnce();
    protected static final boolean ENABLE_INDEPENDENT_NETWORK_THREAD = Server.SETTINGS.networkSettings().enableIndependentNetworkThread();
    protected static final boolean TICK_DIMENSION_IN_PARALLEL = Server.SETTINGS.worldSettings().tickDimensionInParallel();

    @Getter
    protected final String name;
    @Getter
    protected final WorldStorage worldStorage;
    @Getter
    protected final AllayWorldData worldData;
    protected final BlockingQueueWrapper<PacketQueueEntry> packetQueue;
    protected final Semaphore networkSemaphore;
    protected final AtomicReference<WorldState> state;
    @Getter
    protected final Int2ObjectOpenHashMap<Dimension> dimensionMap;
    @Getter
    protected final Scheduler scheduler;
    protected final GameLoop gameLoop;
    @Getter
    protected final Thread worldThread;
    protected final Thread networkThread;

    protected final Set<Weather> effectiveWeathers;

    protected long nextTimeSendTick;
    protected int rainTimer;
    protected int thunderTimer;

    protected boolean isRaining = false;
    protected boolean isThundering = false;
    protected boolean isFirstTick = true;

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
        this.rainTimer = Weather.CLEAR.generateRandomTimeLength();
        this.thunderTimer = Weather.CLEAR.generateRandomTimeLength();
        this.effectiveWeathers = new HashSet<>();
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
            // Return the permit, so that the world thread can run
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

    protected void handleSyncPackets(PacketQueueEntry firstEntry) {
        try {
            PacketQueueEntry entry = firstEntry;
            int count = 0;
            do {
                // The first entry can be null, for why we do that see the callers
                if (entry == null) {
                    continue;
                }

                if (entry.player.getWorld() != this) {
                    log.warn("Trying to handle sync packet in world {} which the player {} is not in!", name, entry.player.getOriginName());
                }
                // The player may have been disconnected,
                // which is possible because this is a synced packet
                if (!entry.player.getClientStatus().canHandlePackets()) {
                    continue;
                }

                var playerImpl = (EntityPlayerImpl) entry.player;
                var networkComponent = (EntityPlayerNetworkComponentImpl) playerImpl.getPlayerNetworkComponent();
                networkComponent.handleDataPacket(entry.packet(), entry.time());
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

        if (TICK_DIMENSION_IN_PARALLEL) {
            dimensionMap.values().parallelStream().forEach(d -> ((AllayDimension) d).tick(currentTick));
        } else {
            for (var dimension : dimensionMap.values()) {
                ((AllayDimension) dimension).tick(currentTick);
            }
        }

        worldStorage.tick(currentTick);
    }

    protected void checkFirstTick() {
        if (!isFirstTick) {
            return;
        }
        isFirstTick = false;

        var overworld = getOverWorld();
        if (Server.SETTINGS.worldSettings().loadSpawnPointChunks()) {
            // Add spawn point chunk loader
            overworld.getChunkService().addChunkLoader(new FakeChunkLoader(() -> {
                var spawnPoint = worldData.getSpawnPoint();
                return new Location3d(spawnPoint.x(), spawnPoint.y(), spawnPoint.z(), getOverWorld());
            }, Server.SETTINGS.worldSettings().spawnPointChunkRadius()));
        }

        // Find the spawn point only the first time the world is loaded
        if (worldData.getWorldStartCount() == 1 && !isSafeStandingPos(new Position3i(worldData.getSpawnPoint(), overworld))) {
            Thread.ofVirtual().name("Spawn Point Finding Thread #" + name).start(() -> {
                var newSpawnPoint = overworld.findSuitableGroundPosAround(this::isSafeStandingPos, 0, 0, 32);
                if (newSpawnPoint == null) {
                    log.warn("Cannot find a safe spawn point in the overworld dimension of world {}", name);
                    overworld.getChunkService().getOrLoadChunk(0, 0);
                    newSpawnPoint = new Vector3i(0, overworld.getHeight(0, 0) + 1, 0);
                }
                var finalNewSpawnPoint = newSpawnPoint;
                overworld.getWorld().getScheduler().runLater(this, () -> {
                    // Set new spawn point in world thread as world data object is not thread-safe
                    worldData.setSpawnPoint(finalNewSpawnPoint);
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

    public void addSyncPacketToQueue(EntityPlayer player, BedrockPacket packet, long time) {
        packetQueue.offer(new PacketQueueEntry(player, packet, time));
    }

    protected void tickTime(long currentTick) {
        if (currentTick < nextTimeSendTick) {
            return;
        }

        if (!worldData.<Boolean>getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)) {
            // Client will always keep time flowing, so we still need to send the
            // same time uninterruptedly if the daylight cycle is disabled
            worldData.sendTimeOfDay(getPlayers());
        } else {
            worldData.addTimeOfDay(TIME_SENDING_INTERVAL);
        }

        nextTimeSendTick = currentTick + TIME_SENDING_INTERVAL;
    }

    protected void tickWeather() {
        if (!worldData.<Boolean>getGameRuleValue(GameRule.DO_WEATHER_CYCLE)) {
            return;
        }

        Set<Weather> added = new HashSet<>();
        Set<Weather> removed = new HashSet<>();
        boolean changed = false;

        rainTimer--;
        if (rainTimer == 0) {
            if (isRaining) {
                isRaining = false;
                rainTimer = Weather.CLEAR.generateRandomTimeLength();
                removed.add(Weather.RAIN);
                effectiveWeathers.remove(Weather.RAIN);
            } else {
                isRaining = true;
                rainTimer = Weather.RAIN.generateRandomTimeLength();
                added.add(Weather.RAIN);
                effectiveWeathers.add(Weather.RAIN);
            }

            changed = true;
        }

        thunderTimer--;
        if (thunderTimer == 0) {
            if (isThundering) {
                isThundering = false;
                thunderTimer = Weather.CLEAR.generateRandomTimeLength();
                removed.add(Weather.THUNDER);
                effectiveWeathers.remove(Weather.THUNDER);
            } else {
                isThundering = true;
                thunderTimer = Weather.THUNDER.generateRandomTimeLength();
                added.add(Weather.THUNDER);
                effectiveWeathers.add(Weather.THUNDER);
            }

            changed = true;
        }

        if (changed) {
            applyWeatherChange(removed, added);
        }
    }

    @Override
    public long getTick() {
        return gameLoop.getTick();
    }

    @Override
    public float getTPS() {
        return gameLoop.getTPS();
    }

    @Override
    public float getMSPT() {
        return gameLoop.getMSPT();
    }

    @Override
    public float getTickUsage() {
        return gameLoop.getTickUsage();
    }

    public void startTick() {
        if (worldThread.getState() != Thread.State.NEW) {
            throw new IllegalStateException("World " + name + " is already ticking!");
        }

        worldThread.start();
        dimensionMap.values().forEach(dimension -> ((AllayDimension) dimension).startTick());
        if (ENABLE_INDEPENDENT_NETWORK_THREAD) {
            networkThread.start();
        }
    }

    @Override
    public Dimension getDimension(int dimensionId) {
        return dimensionMap.get(dimensionId);
    }

    @Override
    @UnmodifiableView
    public Map<Integer, Dimension> getDimensions() {
        return Collections.unmodifiableMap(dimensionMap);
    }

    @Override
    @UnmodifiableView
    public Collection<EntityPlayer> getPlayers() {
        return dimensionMap.values().stream()
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
        getPlayers().forEach(EntityPlayer::disconnect);
        scheduler.shutdown();
        dimensionMap.values().forEach(dimension -> ((AllayDimension) dimension).shutdown());
        saveWorldData();
        worldStorage.shutdown();
        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_UNLOADED, name));
        state.set(WorldState.STOPPED);
    }

    @Override
    public WorldState getState() {
        return state.get();
    }

    @Override
    public Set<Weather> getWeathers() {
        return effectiveWeathers.isEmpty() ? Set.of(Weather.CLEAR) : Collections.unmodifiableSet(effectiveWeathers);
    }

    @Override
    public void addWeather(Weather weather) {
        if (weather == Weather.CLEAR) {
            throw new IllegalArgumentException("Weather.CLEAR shouldn't be used here.");
        }
        if (effectiveWeathers.contains(weather)) {
            return;
        }

        applyWeatherChange(Collections.emptySet(), Set.of(weather));
    }

    @Override
    public void removeWeather(Weather weather) {
        if (weather == Weather.CLEAR) {
            throw new IllegalArgumentException("Weather.CLEAR shouldn't be used here.");
        }
        if (!effectiveWeathers.contains(weather)) {
            return;
        }

        applyWeatherChange(Set.of(weather), Collections.emptySet());
    }

    @Override
    public void clearWeather() {
        applyWeatherChange(Set.copyOf(effectiveWeathers), Collections.emptySet());
    }

    private void applyWeatherChange(Set<Weather> toRemove, Set<Weather> toAdd) {
        var newEffective = new HashSet<>(effectiveWeathers);
        newEffective.removeAll(toRemove);
        newEffective.addAll(toAdd);

        var event = new WeatherChangeEvent(this, Collections.unmodifiableSet(effectiveWeathers), Collections.unmodifiableSet(newEffective));
        if (!event.call()) {
            return;
        }

        effectiveWeathers.clear();
        effectiveWeathers.addAll(newEffective);

        onWeatherUpdate(toRemove, toAdd);
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

    protected record PacketQueueEntry(EntityPlayer player, BedrockPacket packet, long time) {
    }
}
