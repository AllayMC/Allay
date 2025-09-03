package org.allaymc.server;

import com.google.common.base.Suppliers;
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
import org.allaymc.api.permission.PermissionGroup;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scoreboard.ScoreboardService;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerState;
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
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class AllayServer implements Server {

    private static final AllayServer INSTANCE = new AllayServer();

    private static final CommandOriginData SERVER_COMMAND_ORIGIN_DATA = new CommandOriginData(CommandOriginType.DEDICATED_SERVER, UUID.randomUUID(), "", 0);

    private final AtomicReference<ServerState> state;
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
    private final AllayPluginManager pluginManager;
    @Getter
    private final Scheduler scheduler;
    private final Supplier<PermissionGroup> permissionGroup;
    private final AllayTerminalConsole terminalConsole;
    private final GameLoop gameLoop;

    @Getter
    private long startTime;

    private AllayServer() {
        this.state = new AtomicReference<>(ServerState.STARTING);
        this.playerService = new AllayPlayerService(Server.SETTINGS.storageSettings().savePlayerData() ? new AllayNBTFilePlayerStorage(Path.of("players")) : AllayEmptyPlayerStorage.INSTANCE, new AllayNetworkInterface(this));
        this.worldPool = new AllayWorldPool();
        this.computeThreadPool = createComputeThreadPool();
        this.virtualThreadPool = Executors.newVirtualThreadPerTaskExecutor();
        this.eventBus = new AllayEventBus(virtualThreadPool);
        this.scoreboardService = new ScoreboardService(this, new JsonScoreboardStorage(Path.of("command_data/scoreboards.json")));
        this.pluginManager = new AllayPluginManager();
        this.scheduler = new AllayScheduler(virtualThreadPool);
        // Initialize the permission group with a memoized supplier to avoid NPE during server startup
        this.permissionGroup = Suppliers.memoize(() -> PermissionGroup.create("Permission group for server instance", Set.of(), PermissionGroups.OPERATOR, false));
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
        if (getState() != ServerState.RUNNING) {
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
        state.set(ServerState.RUNNING);

        if (System.console() != null) {
            Thread.ofVirtual().name("Console Thread").start(terminalConsole::start);
        }
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
        this.scoreboardService.read();
        this.pluginManager.enablePlugins();

        sendTr(TrKeys.ALLAY_NETWORK_INTERFACE_STARTING);
        ((AllayPlayerService) this.playerService).startNetworkInterface();

        this.startTime = System.currentTimeMillis();
        if (SETTINGS.networkSettings().enablev6()) {
            sendTr(
                    TrKeys.ALLAY_NETWORK_INTERFACE_STARTED,
                    SETTINGS.networkSettings().ip(),
                    String.valueOf(SETTINGS.networkSettings().port()),
                    SETTINGS.networkSettings().ipv6(),
                    String.valueOf(SETTINGS.networkSettings().portv6()),
                    String.valueOf(startTime - initialTime)
            );
        } else {
            sendTr(
                    TrKeys.ALLAY_NETWORK_INTERFACE_STARTED_V4ONLY,
                    SETTINGS.networkSettings().ip(),
                    String.valueOf(SETTINGS.networkSettings().port()),
                    String.valueOf(startTime - initialTime)
            );
        }

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
        // Mark the server as STOPPING, the real shutdown logic is in shutdownReally() method
        state.compareAndSet(ServerState.RUNNING, ServerState.STOPPING);
    }

    @Override
    public ServerState getState() {
        return state.get();
    }

    @SneakyThrows
    private void shutdownReally() {
        // Disconnect all players
        playerService.disconnectAllPlayers(TrKeys.ALLAY_SERVER_STOPPED);
        // Shutdown network server to prevent new client connecting to the server
        ((AllayPlayerService) this.playerService).shutdownNetworkInterface();
        this.scheduler.shutdown();

        new ServerStopEvent().call();

        // Disable all plugins
        this.pluginManager.disablePlugins();

        // Save all configurations & data
        Server.SETTINGS.save();
        this.scoreboardService.save();
        ((AllayPlayerService) this.playerService).shutdown();

        // Shutdown all worlds
        this.worldPool.shutdown();

        // Shutdown all thread pools
        this.virtualThreadPool.shutdown();
        this.computeThreadPool.shutdown();

        this.state.set(ServerState.STOPPED);
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
    public String getCommandSenderName() {
        return "Server";
    }

    @Override
    public CommandOriginData getCommandOriginData() {
        return SERVER_COMMAND_ORIGIN_DATA;
    }

    @Override
    public Location3dc getCommandExecuteLocation() {
        return new Location3d(0, 0, 0, getWorldPool().getDefaultWorld().getOverWorld());
    }

    @Override
    public PermissionGroup getPermissionGroup() {
        return permissionGroup.get();
    }
}
