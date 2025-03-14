package org.allaymc.server;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.service.PlayerService;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.event.server.ServerStopEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.permission.DefaultPermissions;
import org.allaymc.api.permission.tree.PermissionTree;
import org.allaymc.api.plugin.PluginManager;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scoreboard.ScoreboardService;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.GameLoop;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.server.client.service.AllayPlayerService;
import org.allaymc.server.client.storage.AllayEmptyPlayerStorage;
import org.allaymc.server.client.storage.AllayNBTFilePlayerStorage;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.metrics.Metrics;
import org.allaymc.server.network.AllayNetworkInterface;
import org.allaymc.server.plugin.AllayPluginManager;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.scroreboard.storage.JsonScoreboardStorage;
import org.allaymc.server.terminal.AllayTerminalConsole;
import org.allaymc.server.utils.AllayForkJoinWorkerThreadFactory;
import org.allaymc.server.utils.SignalUtils;
import org.allaymc.server.world.AllayWorldPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;

import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class AllayServer implements Server {

    private static final AllayServer INSTANCE = new AllayServer();
    private static final CommandOriginData SERVER_COMMAND_ORIGIN_DATA = new CommandOriginData(CommandOriginType.DEDICATED_SERVER, UUID.randomUUID(), "", 0);

    // TODO: replaced with ServerStatus
    private final AtomicBoolean isRunning;
    private final AtomicBoolean isStarting;
    private final AtomicBoolean isFullyStopped;
    @Getter
    private final AllayWorldPool worldPool;
    @Getter
    private final PlayerService playerService;
    @Getter
    private final ExecutorService computeThreadPool;
    @Getter
    private final ExecutorService virtualThreadPool;
    @Getter
    private final EventBus eventBus;
    @Getter
    private final ScoreboardService scoreboardService;
    @Getter
    private final PluginManager pluginManager;
    @Getter
    private final Scheduler scheduler;
    private final AllayTerminalConsole terminalConsole;
    private final GameLoop gameLoop;

    @Getter
    private long startTime;

    private AllayServer() {
        this.isRunning = new AtomicBoolean(true);
        this.isStarting = new AtomicBoolean(true);
        this.isFullyStopped = new AtomicBoolean(false);
        this.playerService = new AllayPlayerService(Server.SETTINGS.storageSettings().savePlayerData() ? new AllayNBTFilePlayerStorage(Path.of("players")) : AllayEmptyPlayerStorage.INSTANCE, new AllayNetworkInterface(this));
        this.worldPool = new AllayWorldPool();
        this.computeThreadPool = createComputeThreadPool();
        this.virtualThreadPool = Executors.newVirtualThreadPerTaskExecutor();
        this.eventBus = new AllayEventBus(virtualThreadPool);
        this.scoreboardService = new ScoreboardService(this, new JsonScoreboardStorage(Path.of("command_data/scoreboards.json")));
        this.pluginManager = new AllayPluginManager();
        this.scheduler = new AllayScheduler(virtualThreadPool);
        this.terminalConsole = new AllayTerminalConsole(AllayServer.this);
        this.gameLoop = GameLoop.builder()
                .loopCountPerSec(20)
                .onStart(this::onServerStart)
                .onTick(this::serverThreadMain)
                .onStop(this::onServerStop)
                .build();
    }

    public static AllayServer getInstance() {
        return INSTANCE;
    }

    private void serverThreadMain(GameLoop gameLoop) {
        if (!isRunning.get()) {
            gameLoop.stop();
            return;
        }

        try {
            tick(gameLoop.getTick());
        } catch (Throwable throwable) {
            log.error("Error while ticking the server", throwable);
        }
    }

    private void onServerStart() {
        this.isStarting.set(false);
    }

    private void onServerStop() {
        try {
            shutdownReally();
        } catch (Throwable throwable) {
            log.error("Error while stopping the server", throwable);
        }
    }

    private ExecutorService createComputeThreadPool() {
        return new ForkJoinPool(
                Server.SETTINGS.genericSettings().maxComputeThreadCount() <= 0 ?
                        Runtime.getRuntime().availableProcessors() :
                        Server.SETTINGS.genericSettings().maxComputeThreadCount(),
                new AllayForkJoinWorkerThreadFactory(), null, true
        );
    }

    @SneakyThrows
    public void start(long initialTime) {
        var ctx = (LoggerContext) LogManager.getContext(false);
        var log4jConfig = ctx.getConfiguration();
        var loggerConfig = log4jConfig.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        if (Server.SETTINGS.genericSettings().debug() && Level.TRACE.isLessSpecificThan(loggerConfig.getLevel())) {
            loggerConfig.setLevel(Level.TRACE);
            ctx.updateLoggers();
        }

        SignalUtils.addTask(() -> {
            if (!isRunning.get()) {
                return;
            }

            shutdown();
            while (!isFullyStopped.get()) {
                Thread.yield();
            }
        });

        if (System.console() != null) {
            Thread.ofVirtual().name("Console Thread").start(terminalConsole::start);
        }

        ((AllayPluginManager) this.pluginManager).loadPlugins();
        this.worldPool.loadWorlds();
        this.scoreboardService.read();
        ((AllayPluginManager) this.pluginManager).enablePlugins();

        sendTr(TrKeys.A_NETWORK_INTERFACE_STARTING);
        ((AllayPlayerService) this.playerService).startNetworkInterface();
        this.startTime = System.currentTimeMillis();
        sendTr(
                TrKeys.A_NETWORK_INTERFACE_STARTED,
                SETTINGS.networkSettings().ip(),
                String.valueOf(SETTINGS.networkSettings().port()),
                String.valueOf(startTime - initialTime)
        );

        Metrics.AllayMetrics.startMetrics();
        if (SETTINGS.genericSettings().enableGui()) {
            Allay.DASHBOARD.afterServerStarted();
        }
        this.gameLoop.startLoop();
    }

    private void tick(long currentTick) {
        this.scheduler.tick();
        ((AllayPlayerService) this.playerService).tick(currentTick);
    }

    @Override
    public long getTick() {
        return gameLoop.getTick();
    }

    @Override
    public void shutdown() {
        // Mark the server as "not running"
        // The real shutdown logic is in shutdownReally() method
        // and will be called after all players are disconnected
        if (!isRunning.compareAndSet(true, false)) {
            return;
        }
        playerService.disconnectAllPlayers(TrKeys.A_SERVER_STOPPED);
    }

    private void shutdownReally() {
        // Shutdown network server to prevent new client connecting to the server
        ((AllayPlayerService) this.playerService).shutdownNetworkInterface();
        this.scheduler.shutdown();

        new ServerStopEvent().call();

        // Disable all plugins firstly
        ((AllayPluginManager) this.pluginManager).disablePlugins();

        // Save all configurations & data
        Server.SETTINGS.save();
        this.scoreboardService.save();
        ((AllayPlayerService) this.playerService).shutdown();

        // Shutdown all worlds
        this.worldPool.shutdown();

        // Shutdown thread pools
        this.virtualThreadPool.shutdown();
        this.computeThreadPool.shutdown();

        this.isFullyStopped.set(true);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void broadcastTr(@MayContainTrKey String tr, Object... args) {
        playerService.getPlayers().values().forEach(player -> player.sendTr(tr, args));
        sendTr(tr, args);
    }

    @Override
    public void sendText(String text) {
        log.info(text);
    }

    @Override
    public void sendTr(String key, boolean forceTranslatedByClient, Object... args) {
        // forceTranslatedByClient is unused
        log.info(I18n.get().tr(key, args));
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        for (var output : outputs) {
            log.info("[{}] {}{}", sender.getCommandSenderName(), status <= 0 ? TextFormat.RED : "", I18n.get().tr(output.str(), output.args()));
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }

    @Override
    public boolean isStarting() {
        return isStarting.get();
    }

    @Override
    public String getCommandSenderName() {
        return "Server";
    }

    @Override
    public CommandOriginData getCommandOriginData() {
        return SERVER_COMMAND_ORIGIN_DATA;
    }

    @Override
    public Location3dc getCmdExecuteLocation() {
        return new Location3d(0, 0, 0, getWorldPool().getDefaultWorld().getOverWorld());
    }

    @Override
    public PermissionTree getPermissionTree() {
        return DefaultPermissions.OPERATOR;
    }
}
