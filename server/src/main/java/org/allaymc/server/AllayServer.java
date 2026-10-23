package org.allaymc.server;

import eu.okaeri.configs.ConfigManager;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.event.server.ServerStopEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.MessageChannel;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.ConstantPermissionCalculator;
import org.allaymc.api.permission.PermissionCalculator;
import org.allaymc.api.permission.Tristate;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scoreboard.ScoreboardManager;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerState;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.network.AllayNetworkManager;
import org.allaymc.server.network.AllayRakNetInterface;
import org.allaymc.server.network.protocol.ProtocolRegistry;
import org.allaymc.server.player.AllayEmptyPlayerStorage;
import org.allaymc.server.player.AllayNBTFilePlayerStorage;
import org.allaymc.server.player.AllayPlayerManager;
import org.allaymc.server.plugin.AllayPluginManager;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.scroreboard.JsonScoreboardStorage;
import org.allaymc.server.terminal.AllayTerminalConsole;
import org.allaymc.server.utils.AllayForkJoinWorkerThreadFactory;
import org.allaymc.server.utils.GameLoop;
import org.allaymc.server.utils.SignalUtils;
import org.allaymc.server.utils.Utils;
import org.allaymc.server.world.AllayWorldPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicReference;

/**
 * {@code AllayServer} owns shared server services and drives the main server
 * lifecycle.
 * The constructor prepares service objects while {@code state} remains
 * {@code STARTING}; {@code start}
 * loads plugins and worlds, freezes protocol data, opens networking, and enters
 * the tick loop on its calling thread. The loop advances the scheduler before
 * player-manager work, while worlds manage their own ticking.
 *
 * <p>
 * Shutdown requests atomically change {@code RUNNING} to {@code STOPPING}, and the next loop
 * callback stops ticking so cleanup runs in order. Cleanup closes network
 * admission and scheduling before plugin disable callbacks, persistence, world closure,
 * and executor shutdown. Failures are logged by the stop callback without
 * resuming the remaining cleanup steps; {@code STOPPED} is published only if the full
 * sequence returns. Executor shutdown does not await all submitted tasks, and
 * the atomic lifecycle state does not make unrelated service operations atomic.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayServer implements Server {

    // SETTINGS must be loaded before INSTANCE construction because the constructor
    // reads storage, executor, and network configuration while creating shared services.
    /** Loaded mutable server configuration. */
    private static final ServerSettings SETTINGS = ConfigManager.create(
            ServerSettings.class,
            Utils.createConfigInitializer(Path.of("server-settings.yml"))
    );
    /** Process-wide server service owner. */
    private static final AllayServer INSTANCE = new AllayServer();

    /**
     * {@code state} publishes lifecycle transitions independently of service
     * internals. A shutdown request changes {@code RUNNING} to {@code STOPPING}
     * without performing cleanup on its caller.
     */
    private final AtomicReference<ServerState> state;
    /** Shared loaded-world service. */
    @Getter
    private final AllayWorldPool worldPool;
    /** Shared connection and player service. */
    @Getter
    private final AllayPlayerManager playerManager;
    /**
     * {@code computeThreadPool} runs compute tasks, and {@code virtualThreadPool}
     * creates virtual task threads. The server owns both
     * lifetimes and requests shutdown after world cleanup, without awaiting termination.
     */
    @Getter
    private final ExecutorService computeThreadPool, virtualThreadPool;
    /** Shared server event dispatcher. */
    @Getter
    private final EventBus eventBus;
    /** Persistent scoreboard service. */
    @Getter
    private final ScoreboardManager scoreboardManager;
    /** Shared plugin lifecycle manager. */
    @Getter
    private final AllayPluginManager pluginManager;
    /**
     * {@code protocolRegistry} holds the frozen protocols available to network
     * connections. It remains {@code null} until {@code start} creates the default
     * registry after plugin enablement.
     */
    @Getter
    private ProtocolRegistry protocolRegistry;
    /** Main server tick scheduler. */
    @Getter
    private final Scheduler scheduler;
    /** Interactive console command source. */
    private final AllayTerminalConsole terminalConsole;
    /** Main thread timing loop. */
    private final GameLoop gameLoop;

    /**
     * {@code messageChannel} holds the shared broadcast channel, initially
     * containing this server. Replacement through the generated setter does not
     * transfer receivers or add this server.
     */
    @Getter
    @Setter
    private MessageChannel messageChannel;
    /**
     * {@code permissionCalculator} supplies the console permission policy and
     * initially allows every permission. The generated setter replaces the shared
     * calculator used by command permission checks.
     */
    @Getter
    @Setter
    private PermissionCalculator permissionCalculator;
    /**
     * {@code startTime} records wall-clock milliseconds after network startup.
     * It remains zero until {@code start} reaches that point rather than recording
     * object construction time.
     */
    @Getter
    private long startTime;

    /**
     * Creates shared services from the already loaded configuration in {@code STARTING}.
     * Service constructors may open storage or create directories; networking
     * and the main tick loop begin only in {@code start}. The initial broadcast channel
     * includes the server console, whose default permission policy allows everything.
     */
    private AllayServer() {
        this.state = new AtomicReference<>(ServerState.STARTING);
        var rakNetInterface = new AllayRakNetInterface(this);
        var networkManager = new AllayNetworkManager(rakNetInterface);
        this.playerManager = new AllayPlayerManager(SETTINGS.storageSettings().savePlayerData() ? new AllayNBTFilePlayerStorage(Path.of("players")) : AllayEmptyPlayerStorage.INSTANCE, networkManager);
        this.worldPool = new AllayWorldPool();
        this.computeThreadPool = createComputeThreadPool();
        this.virtualThreadPool = Executors.newVirtualThreadPerTaskExecutor();
        this.eventBus = new AllayEventBus(virtualThreadPool);
        this.scoreboardManager = new ScoreboardManager(this, new JsonScoreboardStorage(Path.of("command_data/scoreboards.json")));
        this.pluginManager = new AllayPluginManager();
        this.scheduler = new AllayScheduler(virtualThreadPool);
        this.terminalConsole = new AllayTerminalConsole(AllayServer.this);
        this.gameLoop = GameLoop.builder()
                .loopCountPerSec(20)
                .onStart(this::onServerStart)
                .onTick(this::serverThreadMain)
                .onStop(this::onServerStop)
                .build();
        this.messageChannel = new MessageChannel();
        this.messageChannel.addReceiver(this);
        this.permissionCalculator = new ConstantPermissionCalculator(Tristate.TRUE);
    }

    /**
     * Returns the process-wide implementation created during server class initialization.
     */
    public static AllayServer getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the shared mutable configuration loaded from {@code server-settings.yml}.
     * Consumers may capture settings during construction, so later changes do
     * not necessarily reconfigure services that have already been initialized.
     */
    public static ServerSettings getSettings() {
        return SETTINGS;
    }

    /**
     * Advances one main-loop tick while the server remains {@code RUNNING}.
     * Other states stop the loop so its stop callback performs cleanup;
     * failures during tick work are logged and allow later ticks to continue.
     *
     * <p>
     * The state check does not lock the following tick: a shutdown request
     * arriving after the check may allow that tick to finish before the next
     * callback observes {@code STOPPING}. Resource cleanup therefore belongs to the
     * loop's stop callback rather than the thread requesting shutdown.
     */
    private void serverThreadMain(GameLoop gameLoop) {
        if (getState() != ServerState.RUNNING) {
            gameLoop.stop();
            return;
        }

        try {
            tick(gameLoop.getTick());
        } catch (Throwable throwable) {
            // A failed scheduler or player update does not terminate the loop;
            // later ticks remain available to process work and observe shutdown requests.
            log.error("Error while ticking the server", throwable);
        }
    }

    /**
     * Publishes {@code RUNNING} when the main loop starts and optionally starts console
     * input. A virtual console thread is created only when {@code System.console()} is
     * available; no separate console thread is tracked for joining here.
     */
    private void onServerStart() {
        this.state.set(ServerState.RUNNING);

        if (System.console() != null) {
            // Console input runs independently so blocking reads do not stall
            // server ticks; console shutdown is not joined by this startup callback.
            Thread.ofVirtual().name("Console Thread").start(terminalConsole::start);
        }
    }

    /**
     * Runs shutdown cleanup and logs any uncaught failure from the sequence.
     * A failure aborts the remaining cleanup rather than retrying it, and may
     * leave state at {@code STOPPING} instead of reaching {@code STOPPED}.
     */
    private void onServerStop() {
        try {
            shutdownReally();
        } catch (Throwable throwable) {
            // The loop is already exiting, so report cleanup failure without
            // restarting it; unexecuted cleanup stages and lifecycle state remain unchanged.
            log.error("Error while stopping the server", throwable);
        }
    }

    /**
     * Creates the server's asynchronous-mode fork-join executor for compute work.
     * Nonpositive configured parallelism selects the available processor count;
     * the server later owns shutdown of the returned executor.
     */
    private ExecutorService createComputeThreadPool() {
        return new ForkJoinPool(
                SETTINGS.genericSettings().maxComputeThreadCount() <= 0 ?
                        Runtime.getRuntime().availableProcessors() :
                        SETTINGS.genericSettings().maxComputeThreadCount(),
                new AllayForkJoinWorkerThreadFactory(), null, true
        );
    }

    /**
     * Starts server services and runs the main loop on the calling thread.
     * Callers invoke this once after API and registry bootstrap; {@code initialTime} is the
     * earlier wall-clock timestamp used for startup-duration logging. Plugins
     * load before worlds, then enable before protocol snapshots freeze and
     * network interfaces open. Startup failures propagate without rolling back
     * already started services.
     *
     * <p>
     * The installed signal callback requests shutdown
     * and waits for {@code STOPPED}, which can remain unreachable if cleanup fails.
     */
    @SneakyThrows
    public void start(long initialTime) {
        var ctx = (LoggerContext) LogManager.getContext(false);
        var log4jConfig = ctx.getConfiguration();
        var loggerConfig = log4jConfig.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        if (SETTINGS.genericSettings().debug() && Level.DEBUG.isLessSpecificThan(loggerConfig.getLevel())) {
            loggerConfig.setLevel(Level.DEBUG);
            ctx.updateLoggers();
        }

        // Signal handling requests cleanup on the server loop and waits for its
        // completion marker; a failed cleanup that never publishes STOPPED keeps this wait active.
        SignalUtils.addTask(() -> {
            if (getState() != ServerState.RUNNING) {
                return;
            }

            shutdown();
            while (getState() != ServerState.STOPPED) {
                Thread.yield();
            }
        });

        this.pluginManager.loadPlugins();
        this.worldPool.loadWorlds();
        this.scoreboardManager.read();
        this.pluginManager.enablePlugins();

        // Plugins register source data before protocol snapshots freeze it;
        // only then may networking accept clients that consume those immutable snapshots.
        this.protocolRegistry = ProtocolRegistry.createDefault();
        ProtocolRegistry.installDefault(protocolRegistry);

        sendTranslatable(TrKeys.ALLAY_NETWORK_INTERFACE_STARTING);
        this.playerManager.startNetworkInterfaces();

        this.startTime = System.currentTimeMillis();
        if (SETTINGS.networkSettings().enablev6()) {
            sendTranslatable(
                    TrKeys.ALLAY_NETWORK_INTERFACE_STARTED,
                    SETTINGS.networkSettings().ip(),
                    String.valueOf(SETTINGS.networkSettings().port()),
                    SETTINGS.networkSettings().ipv6(),
                    String.valueOf(SETTINGS.networkSettings().portv6()),
                    String.valueOf(startTime - initialTime)
            );
        } else {
            sendTranslatable(
                    TrKeys.ALLAY_NETWORK_INTERFACE_STARTED_V4ONLY,
                    SETTINGS.networkSettings().ip(),
                    String.valueOf(SETTINGS.networkSettings().port()),
                    String.valueOf(startTime - initialTime)
            );
        }

        AllayMetrics.start();
        if (SETTINGS.genericSettings().enableGui()) {
            Allay.DASHBOARD.afterServerStarted();
        }

        this.gameLoop.startLoop();
    }

    /**
     * Advances scheduled work before player-manager work for one server tick.
     * A scheduler failure prevents the player-manager update for this tick;
     * the enclosing loop callback logs that failure and continues later ticks.
     */
    private void tick(long currentTick) {
        this.scheduler.tick();
        this.playerManager.tick(currentTick);
    }

    /**
     * Returns the main loop's current tick counter for server services.
     */
    @Override
    public long getTick() {
        return gameLoop.getTick();
    }

    /**
     * Requests cleanup by atomically changing {@code RUNNING} to {@code STOPPING}.
     * Calls in other states have no effect, and this method does not wait;
     * the main loop observes the change and runs {@code shutdownReally} after exiting.
     * A tick already admitted by the loop's state check may still finish.
     * Repeated requests cannot move {@code STOPPING} backward or start another cleanup
     * sequence, while requests during {@code STARTING} do not cancel startup. Completion
     * is represented separately by {@code STOPPED} after successful synchronous cleanup.
     */
    @Override
    public void shutdown() {
        // Only the running-to-stopping transition wins admission to cleanup;
        // actual resource shutdown remains on the loop thread rather than this caller.
        this.state.compareAndSet(ServerState.RUNNING, ServerState.STOPPING);
    }

    /**
     * Returns the currently published atomic server lifecycle state value.
     */
    @Override
    public ServerState getState() {
        return this.state.get();
    }

    /**
     * Performs the ordered resource cleanup after the main loop exits.
     * Stop listeners run before player disconnection and network closure, then
     * scheduling is cancelled before plugin disable callbacks and persistence.
     * Worlds close before executors reject further submissions.
     *
     * <p>
     * Each stage
     * depends on earlier stages returning: an exception aborts later cleanup,
     * and {@code STOPPED} is published only after the full sequence succeeds. Executor
     * shutdown requests do not await task termination, so that marker describes
     * this sequence rather than completion of all submitted background work.
     */
    @SneakyThrows
    private void shutdownReally() {
        new ServerStopEvent().call();

        // Close client admission and scheduling before plugin teardown and
        // persistence, while keeping executors available until world cleanup has finished.
        this.playerManager.disconnectAllPlayers(TrKeys.ALLAY_SERVER_STOPPED);
        this.playerManager.shutdownNetworkInterfaces();
        this.scheduler.shutdown();

        this.pluginManager.disablePlugins();

        SETTINGS.save();
        this.scoreboardManager.save();
        this.playerManager.shutdown();

        this.worldPool.shutdown();

        this.virtualThreadPool.shutdown();
        this.computeThreadPool.shutdown();

        this.state.set(ServerState.STOPPED);
    }

    /**
     * Returns {@code true} so server-owned tasks are not invalidated by creator checks.
     * This result does not follow lifecycle state; scheduler shutdown handles
     * queued task cancellation separately during the server's cleanup sequence.
     */
    @Override
    public boolean isValid() {
        return true;
    }

    /**
     * Writes the supplied console message at the logger's info level.
     */
    @Override
    public void sendMessage(String message) {
        log.info(message);
    }

    /**
     * Logs translated console text using the configured default language service.
     */
    @Override
    public void sendTranslatable(String key, Object... args) {
        log.info(I18n.get().tr(key, args));
    }

    /**
     * Logs translated command outputs when the console passes the supplied
     * permission check. Nonpositive status prefixes each output with red text,
     * and every line identifies the originating sender's command name.
     */
    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        if (!hasPermissions(permissions)) {
            return;
        }

        for (var output : outputs) {
            log.info("[{}] {}{}", sender.getCommandSenderName(), status <= 0 ? TextFormat.RED : "", I18n.get().tr(output.str(), output.args()));
        }
    }

    /**
     * Returns the fixed {@code "Server"} label identifying console-originated command messages.
     */
    @Override
    public String getCommandSenderName() {
        return "Server";
    }

    /**
     * Creates a command execution origin at zero in the default overworld.
     * The default world must already be available; the returned location is
     * a new object rather than a shared mutable position.
     */
    @Override
    public Location3dc getCommandExecuteLocation() {
        return new Location3d(0, 0, 0, getWorldPool().getDefaultWorld().getOverWorld());
    }
}
