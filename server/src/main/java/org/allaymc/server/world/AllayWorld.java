package org.allaymc.server.world;

import com.google.common.base.Preconditions;
import io.netty.util.internal.PlatformDependent;
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
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.utils.GameLoop;
import org.allaymc.server.world.manager.AllayEntityManager;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3i;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * {@code AllayWorld} coordinates one world's ticking, queued packet
 * processing, dimensions, clocks, and shutdown. Network receipt publishes
 * packet work into a single-consumer queue; the world loop handles that work
 * during ticks and idle callbacks. Each packet retains the processor selected
 * at receipt, while admission checks the player's current world and client
 * state before invoking its synchronous phase. This preserves dispatch
 * identity without treating queued membership as permission to handle a
 * disconnected or transferred player.
 *
 * <p>
 * Dimension ticks can run in parallel, but the world joins them before storage
 * maintenance and the next idle phase. Shared metadata and lifecycle cleanup
 * belong to world execution; the atomic lifecycle value does not make every
 * field independently thread-safe. Shutdown publishes {@code STOPPING} and
 * wakes the loop, whose next tick stops admission before the stop callback
 * performs cleanup. {@code STOPPED} is published only after that cleanup
 * returns normally; an uncaught idle or cleanup failure can prevent
 * completion.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayWorld implements World {

    /**
     * {@code TIME_SENDING_INTERVAL} controls how many world ticks separate
     * time broadcasts and how far daylight advances on each broadcast.
     */
    protected static final int TIME_SENDING_INTERVAL = 12 * 20;
    /**
     * {@code MAX_PACKETS_HANDLE_COUNT_AT_ONCE} caps successfully invoked
     * packet handlers per drain; skipped entries do not consume this
     * configured processing budget.
     */
    protected static final int MAX_PACKETS_HANDLE_COUNT_AT_ONCE = AllayServer.getSettings().networkSettings().maxSyncedPacketsHandleCountAtOnce();
    /**
     * {@code TICK_DIMENSION_IN_PARALLEL} selects concurrent dimension tick
     * submission when a world contains multiple dimensions; storage
     * maintenance follows the joined results.
     */
    protected static final boolean TICK_DIMENSION_IN_PARALLEL = AllayServer.getSettings().worldSettings().tickDimensionInParallel();

    /**
     * Stable key used by the world pool.
     */
    @Getter
    protected final String name;
    /**
     * {@code worldStorage} retains the owned persistence backend, which the
     * constructor binds before reading metadata and shutdown closes after
     * dimension cleanup.
     */
    @Getter
    protected final WorldStorage worldStorage;
    /**
     * Thread selection for world and lighting workers.
     */
    @Getter
    protected final boolean virtualTickingThread;
    /**
     * {@code worldData} retains mutable metadata shared by dimensions and
     * storage; callers coordinate changes with world execution rather than
     * relying on its generated getter.
     */
    @Getter
    protected final AllayWorldData worldData;

    /**
     * {@code packetQueue} accepts work from multiple receipt threads and is
     * drained by world execution during tick and idle callbacks.
     */
    protected final Queue<PacketQueueEntry> packetQueue;

    /**
     * {@code state} publishes lifecycle decisions across threads; {@code
     * STOPPED} appears only after the normal cleanup sequence reaches its
     * final publication.
     */
    protected final AtomicReference<WorldState> state;
    /**
     * {@code dimensionMap} retains dimensions in insertion order; callers
     * populate it before ticking because iteration and insertion have no
     * shared lock.
     */
    protected final Map<DimensionType, Dimension> dimensionMap;
    /**
     * Scheduler advanced by this world's tick callback.
     */
    @Getter
    protected final Scheduler scheduler;
    /**
     * Loop coordinating tick callbacks and idle work.
     */
    protected final GameLoop gameLoop;
    /**
     * {@code worldThread} starts unstarted and runs {@code gameLoop}; {@code
     * startTick} rejects a thread that has already left {@code NEW}.
     */
    @Getter
    protected final Thread worldThread;

    /**
     * World tick threshold for the next time broadcast.
     */
    protected long nextTimeSendTick;

    /**
     * Weather currently stored and presented to players.
     */
    @Getter
    protected Weather weather;
    // rainTimer and thunderTimer count separate cycle intervals; raining and
    // thundering record their phases. tickWeather combines these phases to select
    // a proposed weather without rolling them back when listeners cancel it.
    /**
     * Remaining ticks until rain and thunder toggle decisions.
     */
    protected int rainTimer, thunderTimer;
    /**
     * Internal rain and thunder cycle phase flags.
     */
    protected boolean raining, thundering;

    /**
     * {@code firstTick} permits one spawn initialization attempt and is
     * cleared before loader installation or asynchronous spawn search begins.
     */
    protected boolean firstTick;
    /**
     * {@code runtimeOnly} controls whether the pool includes this world in
     * saved startup settings; ordinary world persistence remains enabled.
     */
    @Getter
    @Setter
    protected boolean runtimeOnly;

    /**
     * {@code requiredSleepTicks} counts eligible sleep ticks before one
     * day-advancement attempt; nonpositive values leave no active countdown
     * until a caller resets it.
     */
    @Setter
    protected int requiredSleepTicks;

    /**
     * Creates an unstarted world bound to the supplied storage backend.
     * Construction reads mutable metadata, increments its initialization
     * count, and prepares loop callbacks; callers attach dimensions before
     * invoking {@code startTick} and retain responsibility for failures during
     * initialization.
     */
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
        this.dimensionMap = new LinkedHashMap<>(3);
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

    /**
     * Publishes {@code RUNNING} when the loop enters its start callback. This
     * unconditional publication replaces the previous lifecycle value, so
     * callers must not rely on a pre-start shutdown request surviving loop
     * startup.
     */
    protected void onWorldStart() {
        this.state.set(WorldState.RUNNING);
    }

    /**
     * Updates persisted total time and runs one admitted world tick. A
     * lifecycle state other than {@code RUNNING} stops the loop before world
     * simulation, allowing the loop's normal exit to invoke cleanup. This
     * check does not interrupt a tick already admitted before a concurrent
     * shutdown request.
     *
     * <p>
     * Simulation failures are logged and suppressed so later loop ticks can
     * proceed after partially completed work. Total time is stored before the
     * state check, while failures outside this callback, including idle work,
     * remain subject to the loop's uncaught-failure behavior.
     */
    private void worldThreadMain(GameLoop gameLoop) {
        this.worldData.setTotalTime(gameLoop.getTick());
        if (getState() != WorldState.RUNNING) {
            gameLoop.stop();
            return;
        }

        try {
            tick(gameLoop.getTick());
        } catch (Throwable throwable) {
            // A failed tick leaves completed mutations intact. Suppress its failure
            // so the loop can attempt later ticks rather than bypassing all
            // subsequent world processing through an uncaught callback exception.
            log.error("Error while ticking world {}", name, throwable);
        }
    }

    /**
     * Handles queued synchronous packets and entity lifecycle requests between
     * world ticks. The loop invokes this callback after joined dimension work,
     * allowing entity managers to drain their nonconcurrent membership
     * operations without overlapping a normal dimension tick.
     */
    private void idle() {
        handleSyncPackets();
        for (var dimension : this.dimensionMap.values()) {
            ((AllayEntityManager) dimension.getEntityManager()).idle();
        }
    }

    /**
     * Supplies a wake-up permit to this world's loop thread. The request can
     * shorten parking and permit idle processing, but it does not interrupt
     * running work or guarantee one callback per wakeup.
     */
    public void wakeUp() {
        this.gameLoop.wakeUp();
    }

    /**
     * Queues a packet's selected synchronous processor and wakes world
     * execution. Receipt transfers the retained packet context to this queue;
     * callers supply the processor already selected by the asynchronous phase
     * rather than requesting selection again.
     *
     * <p>
     * The queue accepts work independently of lifecycle state, and draining
     * rechecks the player's current world and packet-capable client state.
     * Those checks may discard queued work after a transfer or disconnect.
     * Shutdown does not promise that every accepted entry will be drained
     * before cleanup.
     */
    public void addSyncPacketToQueue(
            Player player,
            BedrockPacket packet,
            long time,
            PacketProcessor<BedrockPacket> processor
    ) {
        this.packetQueue.offer(new PacketQueueEntry(player, packet, time, processor));
        this.gameLoop.wakeUp();
    }

    /**
     * Drains queued packet contexts in world execution up to the configured
     * handler budget. Only invoked handlers increase the count; entries
     * rejected for a changed world or client state are discarded without
     * consuming that budget. The retained processor runs without repeating
     * state-based processor selection.
     *
     * <p>
     * A thrown lookup or handler failure stops this drain and is logged. The
     * polled entry is already consumed, while later entries remain queued for
     * another drain. Callers serialize drains because the queue is designed
     * for one consumer.
     */
    protected void handleSyncPackets() {
        try {
            PacketQueueEntry entry;
            int count = 0;
            while (count < MAX_PACKETS_HANDLE_COUNT_AT_ONCE && (entry = packetQueue.poll()) != null) {
                // A transfer can move the controlled entity after receipt. Discard
                // the old world's entry instead of executing its processor against
                // a player now owned by another world.
                if (entry.player.getControlledEntity().getWorld() != this) {
                    log.error("Trying to handle sync packet in world {} which the player {} is not in!", name, entry.player.getOriginName());
                    continue;
                }

                // Receipt admission can outlive the connection's usable state;
                // rechecking here avoids synchronous handling after disconnect begins
                // while retaining the processor chosen during receipt.
                if (!entry.player.getClientState().canHandlePackets()) {
                    continue;
                }

                entry.processor().handleSync(entry.player(), entry.packet(), entry.time());
                count++;
            }
        } catch (Throwable throwable) {
            // The failing polled entry is already consumed; later entries remain
            // queued for another drain, and completed handler side effects are
            // not rolled back by this error boundary.
            log.error("Error while handling sync packet in world {}", this.getWorldData().getDisplayName(), throwable);
        }
    }

    /**
     * Advances world phases and dimensions for one admitted world tick. Packet
     * handling precedes time, weather, sleep, and scheduler work, so those
     * phases observe earlier mutations from the same tick. Callers keep this
     * operation serialized with idle and shutdown callbacks.
     *
     * <p>
     * When parallel dimension ticking is enabled for multiple dimensions,
     * every submitted tick joins before storage maintenance. The executor
     * follows the world's thread selection. A failed dimension join propagates
     * to the surrounding tick error boundary and skips storage maintenance
     * without undoing dimensions that already completed.
     */
    protected void tick(long currentTick) {
        checkFirstTick();
        handleSyncPackets();

        tickTime(currentTick);
        tickWeather();
        tickSleep();
        scheduler.tick();

        var dimensions = dimensionMap.values();
        // Join every submitted dimension tick before storage maintenance and the
        // next idle drain; entity membership operations rely on these phases
        // sharing one serialized execution interval per dimension.
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

    /**
     * Performs the world's one-time spawn initialization and optional
     * persistent spawn loading. The flag is cleared before either operation,
     * so a later failure does not automatically repeat initialization. Spawn
     * discovery is attempted only for the first persisted world initialization
     * when the existing position is unsafe.
     *
     * <p>
     * A virtual search thread finds ground and falls back to the origin column
     * if no candidate passes the standing test. The selected coordinates are
     * then submitted to the world scheduler instead of mutating world metadata
     * from the search thread; later task execution still depends on scheduler
     * lifecycle.
     */
    protected void checkFirstTick() {
        if (!this.firstTick) {
            return;
        }
        this.firstTick = false;

        var overworld = getOverWorld();
        if (AllayServer.getSettings().worldSettings().loadSpawnPointChunks()) {
            // Keep spawn chunks referenced through a loader that reads the current
            // spawn each time, so a later accepted spawn change moves its loading center.
            overworld.getChunkManager().addChunkLoader(new FakeChunkLoader(() -> {
                var spawnPoint = this.worldData.getSpawnPoint();
                return new Location3d(spawnPoint.x(), spawnPoint.y(), spawnPoint.z(), getOverWorld());
            }, AllayServer.getSettings().worldSettings().spawnPointChunkRadius()));
        }

        // The persisted initialization count limits discovery to the first load.
        // Search outside world execution because suitable-ground lookup can load
        // chunks before its result is scheduled for metadata mutation.
        if (this.worldData.getWorldStartCount() == 1 && !isSafeStandingPos(new Position3i(worldData.getSpawnPoint(), overworld))) {
            Thread.ofVirtual().name("Spawn Point Finding Thread #" + name).start(() -> {
                var newSpawnPoint = overworld.findSuitableGroundPosAround(this::isSafeStandingPos, 0, 0, 32);
                // No suitable candidate still needs a usable spawn proposal. Load
                // the origin column before choosing the height above its surface.
                if (newSpawnPoint == null) {
                    log.warn("Cannot find a safe spawn point in the overworld dimension of world {}", name);
                    overworld.getChunkManager().getOrLoadChunk(0, 0);
                    newSpawnPoint = new Vector3i(0, overworld.getHeight(0, 0) + 1, 0);
                }
                var finalNewSpawnPoint = newSpawnPoint;
                overworld.getWorld().getScheduler().runLater(this, () -> {
                    // Publish the search result through world scheduling because
                    // worldData setters dispatch events against shared mutable world
                    // state rather than providing independent synchronization.
                    this.worldData.setSpawnPoint(finalNewSpawnPoint);
                    log.info("Spawn point for world {} is set to {}, {}, {}", name, finalNewSpawnPoint.x(), finalNewSpawnPoint.y(), finalNewSpawnPoint.z());
                });
            });
        }
    }

    /**
     * Returns whether a position has solid support and two air blocks above.
     */
    protected boolean isSafeStandingPos(Position3ic pos) {
        var blockUnder = pos.dimension().getBlockState(pos.x(), pos.y() - 1, pos.z());
        if (!blockUnder.getBlockStateData().isSolid()) {
            return false;
        }
        return pos.dimension().getBlockState(pos.x(), pos.y(), pos.z()).getBlockType() == BlockTypes.AIR &&
               pos.dimension().getBlockState(pos.x(), pos.y() + 1, pos.z()).getBlockType() == BlockTypes.AIR;
    }

    /**
     * Broadcasts or advances time when the next send threshold is reached.
     * Disabled daylight sends the unchanged value because clients continue
     * their own clock; enabled daylight adds the broadcast interval through
     * the event-aware metadata setter.
     */
    protected void tickTime(long currentTick) {
        if (currentTick < this.nextTimeSendTick) {
            return;
        }

        if (!this.worldData.<Boolean>getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)) {
            // Clients advance their displayed clock between packets. Repeatedly send
            // the retained time when daylight cycling is disabled so client-side
            // interpolation does not permanently advance the visible world time.
            getPlayers().forEach(player -> player.viewTime(this.worldData.getTimeOfDay()));
        } else {
            this.worldData.addTimeOfDay(TIME_SENDING_INTERVAL);
        }

        this.nextTimeSendTick = currentTick + TIME_SENDING_INTERVAL;
    }

    /**
     * Advances the internal rain and thunder cycles when weather cycling is
     * enabled. The two countdowns toggle independent phase flags, and their
     * combination selects clear, rainy, or thunder weather rather than
     * allowing thunder to imply rain unconditionally.
     *
     * <p>
     * The selected enum passes through {@code setWeather}, which can be
     * cancelled. Timer resets and phase toggles have already happened at that
     * point and are not rolled back by cancellation, so a rejected weather
     * proposal does not freeze or restore the internal cycle state.
     */
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

    /**
     * Updates sleep presentation and advances an active sleep countdown. Only
     * overworld players participate, and worlds without participating players
     * leave the countdown unchanged. The indicator includes the currently
     * sleeping count and eligible overworld total.
     *
     * <p>
     * A positive countdown decrements once per eligible tick and attempts day
     * advancement exactly when it reaches zero. A nonpositive value makes no
     * attempt, and a failed eligibility check at expiry is not retried until
     * another caller establishes a new countdown.
     */
    protected void tickSleep() {
        var players = getPlayers();
        if (players.isEmpty()) {
            return;
        }

        var overworldPlayers = players.stream()
                .filter(p -> p.getControlledEntity().getDimension().getDimensionType() == DimensionTypes.OVERWORLD)
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

        if (sleepingCount > 0) {
            for (var player : overworldPlayers) {
                if (player.getControlledEntity().isSleeping()) {
                    player.viewSleepingIndicator(sleepingCount, overworldPlayers.size());
                }
            }
        }

        // An attempt is tied to countdown expiry, not simply to sleeping membership.
        // Leaving zero inactive prevents repeated attempts after a failed eligibility
        // check until another sleep request resets the countdown.
        if (this.requiredSleepTicks > 0) {
            this.requiredSleepTicks--;
            if (this.requiredSleepTicks > 0) {
                return;
            }
        } else {
            return;
        }

        tryAdvanceDay(overworldPlayers);
    }

    /**
     * Attempts day advancement when every supplied overworld player is still
     * sleeping. Clear and rainy weather use their respective time windows,
     * while thunder permits the attempt without a time-window check. Callers
     * pass the current eligible overworld players after the countdown expires.
     *
     * <p>
     * The method wakes players before proposing the time and weather changes.
     * Daylight advancement obeys {@code DO_DAYLIGHT_CYCLE}, but weather
     * clearing is still requested; cancellations or failures in those later
     * setters do not put already awakened players back to sleep. The operation
     * does not consult the sleeping-percentage rule.
     */
    protected void tryAdvanceDay(java.util.List<Player> overworldPlayers) {
        int time = this.worldData.getTimeOfDay() % WorldData.TIME_FULL;
        if (this.weather != Weather.THUNDER) {
            if (this.weather != Weather.RAIN && (time <= WorldData.TIME_SLEEP || time >= WorldData.TIME_WAKE)) {
                return;
            }
            if (time <= WorldData.TIME_SLEEP_WITH_RAIN || time >= WorldData.TIME_WAKE_WITH_RAIN) {
                return;
            }
        }

        for (var player : overworldPlayers) {
            if (!player.getControlledEntity().isSleeping()) {
                return;
            }
        }

        // Wake before proposing time and weather changes; event cancellation can
        // reject either later proposal without restoring the players' earlier
        // sleep state, so this sequence is not transactional.
        for (var player : overworldPlayers) {
            player.getControlledEntity().wake();
        }

        if (this.worldData.<Boolean>getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE)) {
            int newTime = this.worldData.getTimeOfDay() + (WorldData.TIME_FULL - time);
            this.worldData.setTimeOfDay(newTime);
        }

        setWeather(Weather.CLEAR);
    }

    /**
     * Returns the world loop's current persisted-origin tick counter value.
     */
    @Override
    public long getTick() {
        return this.gameLoop.getTick();
    }

    /**
     * Returns the loop's recent callback-capacity estimate capped at its
     * target tick rate. The estimate derives from tick callback durations
     * rather than completed ticks per elapsed wall-clock second.
     */
    @Override
    public float getTPS() {
        return this.gameLoop.getTPS();
    }

    /**
     * Returns the loop's mean tick callback duration in milliseconds. Parking
     * and idle work are excluded, and concurrent readers can observe a sample
     * window while its entries change.
     */
    @Override
    public float getMSPT() {
        return this.gameLoop.getMSPT();
    }

    /**
     * Returns mean tick callback duration divided by the world loop's
     * configured tick budget. Parking and idle work are excluded, and
     * overloaded callbacks can produce a ratio above {@code 1}.
     */
    @Override
    public float getTickUsage() {
        return this.gameLoop.getTickUsage();
    }

    /**
     * Starts the prepared world thread and then starts dimension lighting
     * workers. Callers attach every dimension before this operation and invoke
     * it only once; a world thread outside {@code NEW} is rejected before
     * startup.
     *
     * <p>
     * The world thread can enter its loop before the dimension worker starts
     * complete, so startup provides no barrier that holds world ticks until
     * every light worker is running. A later dimension-start failure does not
     * stop or roll back a world thread already started.
     */
    public void startTick() {
        if (this.worldThread.getState() != Thread.State.NEW) {
            throw new IllegalStateException("World " + this.name + " is already ticking!");
        }

        this.worldThread.start();
        this.dimensionMap.values().forEach(dimension -> ((AllayDimension) dimension).startTick());
    }

    /**
     * Returns the dimension currently mapped to the supplied type, or {@code
     * null} when absent. Registry membership does not create an entry, and
     * this lookup never attaches a new dimension.
     */
    @Override
    public Dimension getDimension(DimensionType dimensionType) {
        return this.dimensionMap.get(dimensionType);
    }

    /**
     * Returns an unmodifiable view backed by the dimension mapping. The view
     * reflects later attachments and retains dimension references; callers
     * must not attach dimensions concurrently with unsynchronized map
     * iteration.
     */
    @Override
    @UnmodifiableView
    public Map<DimensionType, Dimension> getDimensions() {
        return Collections.unmodifiableMap(this.dimensionMap);
    }

    /**
     * Returns an unmodifiable snapshot of player references across current
     * dimensions. The collection does not track subsequent membership changes,
     * and the player instances themselves remain shared with dimension
     * managers.
     */
    @Override
    @UnmodifiableView
    public Collection<Player> getPlayers() {
        return this.dimensionMap.values().stream()
                .flatMap(dimension -> dimension.getPlayers().stream())
                .toList();
    }

    /**
     * Attaches a dimension under its type before world ticking starts.
     * Duplicate types are rejected with {@code IllegalArgumentException}; this
     * method neither validates the dimension's owning world nor synchronizes
     * concurrent map mutation.
     */
    public void addDimension(Dimension dimension) {
        Preconditions.checkArgument(!this.dimensionMap.containsKey(dimension.getDimensionType()));
        this.dimensionMap.put(dimension.getDimensionType(), dimension);
    }

    /**
     * Dispatches a metadata save notification before invoking the storage
     * writer. Listeners can update the shared metadata for that write, while
     * backend behavior determines persistence completion and any write failure
     * propagation.
     */
    @Override
    public void saveWorldData() {
        new WorldDataSaveEvent(this).call();
        getWorldStorage().writeWorldData(worldData);
    }

    /**
     * Requests stopping by publishing {@code STOPPING} and waking the world
     * loop. Cleanup runs from the loop's normal stop callback after its next
     * lifecycle check, so this request returns without joining ticks or
     * resource closure.
     *
     * <p>
     * Callers issue the request after startup and do not treat it as
     * idempotent completion: the state assignment is unconditional, so it can
     * overwrite {@code STOPPED}, and the start callback can overwrite a
     * request issued before startup. A queued request does not guarantee
     * cleanup after an uncaught loop failure.
     */
    public void shutdown() {
        // Publish the stop decision before waking the loop so a returning park
        // can lead to a tick that observes STOPPING and exits into shutdownReally.
        state.set(WorldState.STOPPING);
        gameLoop.wakeUp();
    }

    /**
     * Disconnects players, stops scheduling and dimensions, saves metadata,
     * and closes world storage. The loop invokes this sequence after normal
     * exit, when its world tick and idle callbacks have stopped. Dimension
     * cleanup submits its own ordered chunk and entity persistence before the
     * metadata write.
     *
     * <p>
     * Only the successful end publishes {@code STOPPED}. There is no
     * encompassing recovery or finally block, so an earlier failure leaves
     * later cleanup and final publication unperformed. Light engine shutdown
     * requests do not themselves join every calculation thread before the
     * world sequence continues.
     */
    protected void shutdownReally() {
        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_UNLOADING, name));
        getPlayers().forEach(Player::disconnect);
        // Finish dimension cleanup and its persistence requests before writing world
        // metadata and closing the backend; a failure here deliberately prevents
        // the later STOPPED publication in this uncaught sequence.
        this.scheduler.shutdown();
        this.dimensionMap.values().forEach(dimension -> ((AllayDimension) dimension).shutdown());
        saveWorldData();
        this.worldStorage.shutdown();
        this.state.set(WorldState.STOPPED);
        log.info(I18n.get().tr(TrKeys.ALLAY_WORLD_UNLOADED, name));
    }

    /**
     * Returns the lifecycle value published through the world's atomic
     * reference.
     */
    @Override
    public WorldState getState() {
        return state.get();
    }

    /**
     * Proposes weather through the cancellable world event when the requested
     * enum differs. Accepted dispatch stores the requested weather and
     * broadcasts it; cancellation keeps the prior value without changing
     * internal weather-cycle timers.
     */
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

    /**
     * {@code PacketQueueEntry} retains receipt context for one deferred
     * synchronous packet operation. The {@code player}, {@code packet}, and
     * {@code processor} objects are shared receipt-time references. {@code
     * packetQueue} retains this entry until a drain consumes it, so waiting
     * work also keeps those objects reachable.
     *
     * @param player receiving player reference
     * @param packet packet awaiting synchronous handling
     * @param time tick recorded during packet receipt
     * @param processor processor selected by receipt-time asynchronous
     *     dispatch
     */
    protected record PacketQueueEntry(
            Player player,
            BedrockPacket packet,
            long time,
            PacketProcessor<BedrockPacket> processor
    ) {
    }
}
