package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
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
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.WorldState;
import org.allaymc.api.world.chunk.FakeChunkLoader;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.AllayServer;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.utils.GameLoop;
import org.allaymc.server.world.manager.AllayEntityManager;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3i;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorld implements World {

    protected static final int TIME_SENDING_INTERVAL = 12 * 20;
    protected static final int MAX_PACKETS_HANDLE_COUNT_AT_ONCE = AllayServer.getSettings().networkSettings().maxSyncedPacketsHandleCountAtOnce();
    protected static final boolean TICK_DIMENSION_IN_PARALLEL = AllayServer.getSettings().worldSettings().tickDimensionInParallel();

    @Getter
    protected final String name;
    @Getter
    protected final WorldStorage worldStorage;
    @Getter
    protected final boolean virtualTickingThread;
    @Getter
    protected final AllayWorldData worldData;

    protected final Queue<PacketQueueEntry> packetQueue;

    protected final AtomicReference<WorldState> state;
    protected final Int2ObjectOpenHashMap<Dimension> dimensionMap;
    @Getter
    protected final Scheduler scheduler;
    protected final GameLoop gameLoop;
    @Getter
    protected final Thread worldThread;

    protected long nextTimeSendTick;

    @Getter
    protected Weather weather;
    protected int rainTimer, thunderTimer;
    protected boolean raining, thundering;

    protected boolean firstTick;
    @Getter
    @Setter
    protected boolean runtimeOnly;

    // Number of ticks remaining before day advancement is attempted
    @Setter
    protected int requiredSleepTicks;

    public AllayWorld(String name, WorldStorage worldStorage, boolean virtualTickingThread) {
        this.name = name;
        this.worldStorage = worldStorage;
        this.worldStorage.setWorld(this);
        this.virtualTickingThread = virtualTickingThread;
        this.worldData = (AllayWorldData) worldStorage.readWorldData();
        this.worldData.setWorld(this);
        this.worldData.increaseWorldStartCount();
        this.packetQueue = PlatformDependent.newMpscQueue();
        this.state = new AtomicReference<>(WorldState.STARTING);
        this.dimensionMap = new Int2ObjectOpenHashMap<>(3);
        this.scheduler = new AllayScheduler(Server.getInstance().getVirtualThreadPool());
        this.gameLoop = GameLoop.builder()
                .currentTick(this.worldData.getTotalTime())
                .onStart(this::onWorldStart)
                .onTick(this::worldThreadMain)
                .onIdle(this::idle)
                .onStop(this::shutdownReally)
                .build();
        this.worldThread = (virtualTickingThread ? Thread.ofVirtual() : Thread.ofPlatform())
                .name("World Thread #" + this.getName())
                .unstarted(gameLoop::startLoop);
        this.weather = Weather.CLEAR;
        this.rainTimer = Weather.CLEAR.generateRandomTimeLength();
        this.thunderTimer = Weather.CLEAR.generateRandomTimeLength();
        this.firstTick = true;
    }

    protected void onWorldStart() {
        this.state.set(WorldState.RUNNING);
    }

    private void worldThreadMain(GameLoop gameLoop) {
        this.worldData.setTotalTime(gameLoop.getTick());
        if (getState() != WorldState.RUNNING) {
            gameLoop.stop();
            return;
        }

        try {
            tick(gameLoop.getTick());
        } catch (Throwable throwable) {
            log.error("Error while ticking world {}", name, throwable);
        }
    }

    private void idle() {
        handleSyncPackets();
        for (var dimension : this.dimensionMap.values()) {
            ((AllayEntityManager) dimension.getEntityManager()).idle();
        }
    }

    public void wakeUp() {
        this.gameLoop.wakeUp();
    }

    public void addSyncPacketToQueue(Player player, BedrockPacket packet, long time) {
        this.packetQueue.offer(new PacketQueueEntry(player, packet, time));
        this.gameLoop.wakeUp();
    }

    protected void handleSyncPackets() {
        try {
            PacketQueueEntry entry;
            int count = 0;
            while (count < MAX_PACKETS_HANDLE_COUNT_AT_ONCE && (entry = packetQueue.poll()) != null) {
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
            }
        } catch (Throwable throwable) {
            log.error("Error while handling sync packet in world {}", this.getWorldData().getDisplayName(), throwable);
        }
    }

    protected void tick(long currentTick) {
        checkFirstTick();
        handleSyncPackets();

        tickTime(currentTick);
        tickWeather();
        tickSleep();
        scheduler.tick();

        var dimensions = dimensionMap.values();
        if (TICK_DIMENSION_IN_PARALLEL && dimensions.size() > 1) {
            var server = Server.getInstance();
            Utils.forEachInParallel(
                    dimensions, virtualTickingThread ? server.getVirtualThreadPool() : server.getComputeThreadPool(),
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

    protected void tickSleep() {
        var players = getPlayers();
        if (players.isEmpty()) {
            return;
        }

        // Only overworld players can sleep
        var overworldPlayers = players.stream()
                .filter(p -> p.getControlledEntity().getDimension().getDimensionInfo().dimensionId() == 0)
                .toList();

        if (overworldPlayers.isEmpty()) {
            return;
        }

        var sleepingCount = 0;
        for (var player : overworldPlayers) {
            if (player.getControlledEntity().isSleeping()) {
                sleepingCount++;
            }
        }

        // Send sleeping indicator to all sleeping players
        if (sleepingCount > 0) {
            for (var player : overworldPlayers) {
                if (player.getControlledEntity().isSleeping()) {
                    player.viewSleepingIndicator(sleepingCount, overworldPlayers.size());
                }
            }
        }

        // Countdown mechanism: only attempt day advancement after the countdown expires
        if (this.requiredSleepTicks > 0) {
            this.requiredSleepTicks--;
            if (this.requiredSleepTicks > 0) {
                return;
            }
        } else {
            // No countdown active, nothing to do
            return;
        }

        // Countdown just reached zero — try to advance day
        tryAdvanceDay(overworldPlayers);
    }

    protected void tryAdvanceDay(java.util.List<Player> overworldPlayers) {
        // Check time - ensure it's actually night
        int time = this.worldData.getTimeOfDay() % WorldData.TIME_FULL;
        if (this.weather != Weather.THUNDER) {
            if (this.weather != Weather.RAIN && (time <= WorldData.TIME_SLEEP || time >= WorldData.TIME_WAKE)) {
                return;
            }
            if (time <= WorldData.TIME_SLEEP_WITH_RAIN || time >= WorldData.TIME_WAKE_WITH_RAIN) {
                return;
            }
        }

        // Check if all sleeping players are still sleeping
        for (var player : overworldPlayers) {
            if (!player.getControlledEntity().isSleeping()) {
                return;
            }
        }

        // Advance day: wake all players, set time to day, stop raining
        for (var player : overworldPlayers) {
            player.getControlledEntity().wake();
        }

        if (this.worldData.<Boolean>getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)) {
            int newTime = this.worldData.getTimeOfDay() + (WorldData.TIME_FULL - time);
            this.worldData.setTimeOfDay(newTime);
        }

        setWeather(Weather.CLEAR);
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
        gameLoop.wakeUp(); // Wake up the thread if it's parked so it can detect the state change
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
