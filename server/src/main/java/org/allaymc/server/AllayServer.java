package org.allaymc.server;

import eu.okaeri.configs.ConfigManager;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.data.DeviceInfo;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.client.storage.PlayerStorage;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.event.network.IPBanEvent;
import org.allaymc.api.eventbus.event.network.IPUnbanEvent;
import org.allaymc.api.eventbus.event.player.PlayerBanEvent;
import org.allaymc.api.eventbus.event.player.PlayerUnbanEvent;
import org.allaymc.api.eventbus.event.server.ServerStopEvent;
import org.allaymc.api.eventbus.event.server.WhitelistAddPlayerEvent;
import org.allaymc.api.eventbus.event.server.WhitelistRemovePlayerEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.network.NetworkInterface;
import org.allaymc.api.permission.DefaultPermissions;
import org.allaymc.api.permission.tree.PermissionTree;
import org.allaymc.api.plugin.PluginManager;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scoreboard.ScoreboardService;
import org.allaymc.api.server.BanInfo;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.Whitelist;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.GameLoop;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.client.storage.AllayEmptyPlayerStorage;
import org.allaymc.server.client.storage.AllayNBTFilePlayerStorage;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.metrics.Metrics;
import org.allaymc.server.network.AllayNetworkInterface;
import org.allaymc.server.plugin.AllayPluginManager;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.scroreboard.storage.JsonScoreboardStorage;
import org.allaymc.server.terminal.AllayTerminalConsole;
import org.allaymc.server.utils.AllayComputeThread;
import org.allaymc.server.utils.SignalUtils;
import org.allaymc.server.world.AllayWorldPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class AllayServer implements Server {

    private static final AllayServer INSTANCE = new AllayServer();
    private static final String BAN_INFO_FILE_NAME = "ban-info.yml";
    private static final String WHITELIST_FILE_NAME = "whitelist.yml";
    private static final CommandOriginData SERVER_COMMAND_ORIGIN_DATA = new CommandOriginData(CommandOriginType.DEDICATED_SERVER, UUID.randomUUID(), "", 0);

    private final AtomicBoolean isRunning;
    private final AtomicBoolean isStarting;
    private final AtomicBoolean isFullyStopped;
    private final Map<UUID, EntityPlayer> players;
    private final Map<UUID, PlayerListPacket.Entry> playerListEntryMap;
    @Getter
    private final AllayWorldPool worldPool;
    @Getter
    private final PlayerStorage playerStorage;
    @Getter
    private final ThreadPoolExecutor computeThreadPool;
    @Getter
    private final ExecutorService virtualThreadPool;
    @Getter
    private final EventBus eventBus;
    @Getter
    private final ScoreboardService scoreboardService;
    private final BanInfo banInfo;
    private final Whitelist whitelist;
    @Getter
    private final PluginManager pluginManager;
    @Getter
    private final Scheduler scheduler;
    @Getter
    private final NetworkInterface networkInterface;
    private final AllayTerminalConsole terminalConsole;
    private final GameLoop gameLoop;

    @Getter
    private long startTime;

    public static AllayServer getInstance() {
        return INSTANCE;
    }

    private AllayServer() {
        this.isRunning = new AtomicBoolean(true);
        this.isStarting = new AtomicBoolean(true);
        this.isFullyStopped = new AtomicBoolean(false);
        this.players = new ConcurrentHashMap<>();
        this.playerListEntryMap = new Object2ObjectOpenHashMap<>();
        this.worldPool = new AllayWorldPool();
        this.playerStorage = Server.SETTINGS.storageSettings().savePlayerData() ? new AllayNBTFilePlayerStorage(Path.of("players")) : AllayEmptyPlayerStorage.INSTANCE;
        this.computeThreadPool = createComputeThreadPool();
        this.virtualThreadPool = Executors.newVirtualThreadPerTaskExecutor();
        this.eventBus = new AllayEventBus(virtualThreadPool);
        this.scoreboardService = new ScoreboardService(this, new JsonScoreboardStorage(Path.of("command_data/scoreboards.json")));
        this.banInfo = ConfigManager.create(BanInfo.class, Utils.createConfigInitializer(Path.of(BAN_INFO_FILE_NAME)));
        this.whitelist = ConfigManager.create(Whitelist.class, Utils.createConfigInitializer(Path.of(WHITELIST_FILE_NAME)));
        this.pluginManager = new AllayPluginManager();
        this.scheduler = new AllayScheduler(virtualThreadPool);
        this.networkInterface = new AllayNetworkInterface(this);
        this.terminalConsole = new AllayTerminalConsole(AllayServer.this);
        this.gameLoop = GameLoop.builder()
                .loopCountPerSec(20)
                .onStart(this::onServerStart)
                .onTick(this::serverThreadMain)
                .onStop(this::onServerStop)
                .build();
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

    private ThreadPoolExecutor createComputeThreadPool() {
        return new ThreadPoolExecutor(
                Server.SETTINGS.genericSettings().maxComputeThreadCount() <= 0 ? Runtime.getRuntime().availableProcessors() : Server.SETTINGS.genericSettings().maxComputeThreadCount(),
                // maximumPoolSize and keepAliveTime are both meaningless, because we are using LinkedBlockingQueue
                Server.SETTINGS.genericSettings().maxComputeThreadCount() <= 0 ? Runtime.getRuntime().availableProcessors() : Server.SETTINGS.genericSettings().maxComputeThreadCount(), 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), AllayComputeThread::new);
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

        ((AllayPluginManager) pluginManager).loadPlugins();
        this.worldPool.loadWorlds();
        this.scoreboardService.read();
        ((AllayPluginManager) pluginManager).enablePlugins();

        sendTr(TrKeys.A_NETWORK_INTERFACE_STARTING);
        ((AllayNetworkInterface) this.networkInterface).start();
        this.startTime = System.currentTimeMillis();
        sendTr(
                TrKeys.A_NETWORK_INTERFACE_STARTED,
                SETTINGS.networkSettings().ip(),
                String.valueOf(SETTINGS.networkSettings().port()),
                String.valueOf(startTime - initialTime)
        );

        Metrics.AllayMetrics.startMetrics();
        if (SETTINGS.genericSettings().enableGui()) {
            Allay.DASHBOARD.serverStarted();
        }
        this.gameLoop.startLoop();
    }

    private void tick(long currentTick) {
        this.scheduler.tick();
        this.playerStorage.tick(currentTick);
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
        disconnectAllPlayers(TrKeys.A_SERVER_STOPPED);
    }

    private void shutdownReally() {
        // Shutdown network server to prevent new client connecting to the server
        ((AllayNetworkInterface) this.networkInterface).shutdown();
        this.scheduler.shutdown();

        new ServerStopEvent().call();

        // Disable all plugins firstly
        ((AllayPluginManager) this.pluginManager).disablePlugins();

        // Save all configurations & data
        Server.SETTINGS.save();
        this.banInfo.save();
        this.whitelist.save();
        this.scoreboardService.save();
        this.playerStorage.shutdown();

        // Shutdown all worlds
        this.worldPool.shutdown();

        // Shutdown thread pools
        this.virtualThreadPool.shutdown();
        this.computeThreadPool.shutdown();

        this.isFullyStopped.set(true);
    }

    @Override
    @UnmodifiableView
    public Map<UUID, EntityPlayer> getOnlinePlayers() {
        return Collections.unmodifiableMap(players);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public void onLoggedIn(EntityPlayer player) {
        players.put(player.getUUID(), player);
        ((AllayNetworkInterface) networkInterface).setPlayerCount(players.size());
        Server.getInstance().broadcastTr(TextFormat.YELLOW + "%" + TrKeys.M_MULTIPLAYER_PLAYER_JOINED, player.getOriginName());
    }

    public void onDisconnect(EntityPlayer player) {
        sendTr(TrKeys.A_NETWORK_CLIENT_DISCONNECTED, player.getClientSession().getSocketAddress().toString());

        if (player.isLoggedIn()) {
            broadcastTr(TextFormat.YELLOW + "%" + TrKeys.M_MULTIPLAYER_PLAYER_LEFT, player.getOriginName());
            players.remove(player.getUUID());
        }

        if (player.isSpawned()) {
            player.getDimension().removePlayer(player);
            playerStorage.savePlayerData(player);

            removeFromPlayerList(player);
        }

        ((AllayNetworkInterface) networkInterface).setPlayerCount(players.size());
    }

    public void addToPlayerList(EntityPlayer player) {
        addToPlayerList(
                player.getLoginData().getUuid(),
                player.getRuntimeId(),
                player.getOriginName(),
                player.getLoginData().getDeviceInfo(),
                player.getLoginData().getXuid(),
                player.getLoginData().getSkin()
        );
    }

    private void addToPlayerList(UUID uuid, long entityId, String name, DeviceInfo deviceInfo, String xuid, Skin skin) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.ADD);

        var entry = new PlayerListPacket.Entry(uuid);
        entry.setEntityId(entityId);
        entry.setName(name);
        entry.setXuid(xuid);
        entry.setPlatformChatId(deviceInfo.deviceName());
        entry.setBuildPlatform(deviceInfo.device().getId());
        entry.setSkin(skin.toNetwork());
        entry.setTrustedSkin(skin.isTrusted());

        playerListPacket.getEntries().add(entry);
        playerListEntryMap.put(uuid, entry);

        broadcastPacket(playerListPacket);
    }

    public void removeFromPlayerList(EntityPlayer player) {
        removeFromPlayerList(player.getLoginData().getUuid());
    }

    private void removeFromPlayerList(UUID uuid) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.REMOVE);
        playerListPacket.getEntries().add(playerListEntryMap.remove(uuid));
        broadcastPacket(playerListPacket);
    }

    public void sendFullPlayerListInfoTo(EntityPlayer player) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.ADD);
        playerListEntryMap.forEach((uuid, entry) -> {
            if (uuid != player.getUUID()) {
                playerListPacket.getEntries().add(entry);
            }
        });
        player.sendPacket(playerListPacket);
    }

    public void onSkinUpdate(EntityPlayer player) {
        this.playerListEntryMap.get(player.getUUID()).setSkin(player.getSkin().toNetwork());
    }

    @Override
    public void broadcastPacket(BedrockPacket packet) {
        players.values().forEach(player -> player.sendPacket(packet));
    }

    @Override
    public void broadcastTr(@MayContainTrKey String tr) {
        getOnlinePlayers().values().forEach(player -> player.sendTr(tr));
        sendTr(tr);
    }

    @Override
    public void broadcastTr(@MayContainTrKey String tr, String... args) {
        getOnlinePlayers().values().forEach(player -> player.sendTr(tr, args));
        sendTr(tr, args);
    }

    @Override
    public void savePlayerData() {
        players.values().stream().filter(EntityPlayer::isInitialized).forEach(playerStorage::savePlayerData);
    }

    @Override
    public boolean isBanned(String uuidOrName) {
        return banInfo.bannedPlayers().contains(uuidOrName);
    }

    @Override
    public boolean ban(String uuidOrName) {
        if (isBanned(uuidOrName)) return false;

        var event = new PlayerBanEvent(uuidOrName);
        event.call();
        if (event.isCancelled()) return false;

        banInfo.bannedPlayers().add(uuidOrName);
        players.values().stream()
                .filter(player -> player.getUUID().toString().equals(uuidOrName) || player.getOriginName().equals(uuidOrName))
                .forEach(player -> player.disconnect("You are banned!"));
        return true;
    }

    @Override
    public boolean unban(String uuidOrName) {
        if (!isBanned(uuidOrName)) return false;

        var event = new PlayerUnbanEvent(uuidOrName);
        event.call();
        if (event.isCancelled()) return false;

        banInfo.bannedPlayers().remove(uuidOrName);
        return true;
    }

    @Override
    public Set<String> getBannedPlayers() {
        return Collections.unmodifiableSet(banInfo.bannedPlayers());
    }

    @Override
    public boolean isIPBanned(String ip) {
        return banInfo.bannedIps().contains(ip);
    }

    @Override
    public boolean banIP(String ip) {
        if (isIPBanned(ip)) return false;

        var event = new IPBanEvent(ip);
        event.call();
        if (event.isCancelled()) return false;

        banInfo.bannedIps().add(ip);
        players.values().stream()
                .filter(player -> AllayStringUtils.fastTwoPartSplit(player.getClientSession().getSocketAddress().toString().substring(1), ":", "")[0].equals(ip))
                .forEach(player -> player.disconnect("Your IP is banned!"));
        return true;
    }

    @Override
    public boolean unbanIP(String ip) {
        if (!isIPBanned(ip)) return false;

        var event = new IPUnbanEvent(ip);
        event.call();
        if (event.isCancelled()) return false;

        banInfo.bannedIps().remove(ip);
        return true;
    }

    @Override
    public Set<String> getBannedIPs() {
        return Collections.unmodifiableSet(banInfo.bannedIps());
    }

    @Override
    public boolean isWhitelisted(String uuidOrName) {
        return whitelist.whitelist().contains(uuidOrName);
    }

    @Override
    public boolean addToWhitelist(String uuidOrName) {
        if (isWhitelisted(uuidOrName)) return false;

        var event = new WhitelistAddPlayerEvent(uuidOrName);
        event.call();
        if (event.isCancelled()) return false;

        return whitelist.whitelist().add(uuidOrName);
    }

    @Override
    public boolean removeFromWhitelist(String uuidOrName) {
        if (!isWhitelisted(uuidOrName)) return false;

        var event = new WhitelistRemovePlayerEvent(uuidOrName);
        event.call();
        if (event.isCancelled()) return false;

        whitelist.whitelist().remove(uuidOrName);
        players.values().stream()
                .filter(player -> player.getUUID().toString().equals(uuidOrName) || player.getOriginName().equals(uuidOrName))
                .forEach(player -> player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOTALLOWED));
        return true;
    }

    @Override
    public @UnmodifiableView Set<String> getWhitelistedPlayers() {
        return Collections.unmodifiableSet(whitelist.whitelist());
    }

    @Override
    public void sendText(String text) {
        log.info(text);
    }

    @Override
    public void sendTr(String key, boolean forceTranslatedByClient, String... args) {
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
    public Location3fc getCmdExecuteLocation() {
        return new Location3f(0, 0, 0, getWorldPool().getDefaultWorld().getOverWorld());
    }

    @Override
    public PermissionTree getPermissionTree() {
        return DefaultPermissions.OPERATOR;
    }
}
