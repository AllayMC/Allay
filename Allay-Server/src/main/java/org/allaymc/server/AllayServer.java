package org.allaymc.server;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.data.DeviceInfo;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.client.storage.PlayerStorage;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.event.network.ClientConnectEvent;
import org.allaymc.api.eventbus.event.network.IPBanEvent;
import org.allaymc.api.eventbus.event.network.IPUnbanEvent;
import org.allaymc.api.eventbus.event.player.PlayerBanEvent;
import org.allaymc.api.eventbus.event.player.PlayerQuitEvent;
import org.allaymc.api.eventbus.event.player.PlayerUnbanEvent;
import org.allaymc.api.eventbus.event.server.ServerStopEvent;
import org.allaymc.api.eventbus.event.server.WhitelistAddPlayerEvent;
import org.allaymc.api.eventbus.event.server.WhitelistRemovePlayerEvent;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.network.NetworkServer;
import org.allaymc.api.perm.DefaultPermissions;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.plugin.PluginManager;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scoreboard.ScoreboardService;
import org.allaymc.api.scoreboard.storage.JsonScoreboardStorage;
import org.allaymc.api.server.BanInfo;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.Whitelist;
import org.allaymc.api.utils.AllayComputeThread;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.GameLoop;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldPool;
import org.allaymc.server.client.storage.AllayEmptyPlayerStorage;
import org.allaymc.server.client.storage.AllayNBTFilePlayerStorage;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.network.AllayNetworkServer;
import org.allaymc.server.plugin.AllayPluginManager;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.terminal.AllayTerminalConsole;
import org.allaymc.server.world.AllayWorldPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.allaymc.api.entity.type.EntityTypes.PLAYER;

@Slf4j
public final class AllayServer implements Server {

    private static final CommandOriginData SERVER_COMMAND_ORIGIN_DATA = new CommandOriginData(CommandOriginType.DEDICATED_SERVER, UUID.randomUUID(), "", 0);
    private static volatile AllayServer INSTANCE;

    private final boolean debug = Server.SETTINGS.genericSettings().debug();
    private final Map<UUID, EntityPlayer> players = new ConcurrentHashMap<>();
    @Getter
    private final WorldPool worldPool = new AllayWorldPool();
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    private final Object2ObjectMap<UUID, PlayerListPacket.Entry> playerListEntryMap = new Object2ObjectOpenHashMap<>();
    @Getter
    private final PlayerStorage playerStorage =
            Server.SETTINGS.storageSettings().savePlayerData() ?
                    new AllayNBTFilePlayerStorage(Path.of("players")) :
                    AllayEmptyPlayerStorage.INSTANCE;
    // Thread pool for executing CPU-intensive tasks
    @Getter
    private final ThreadPoolExecutor computeThreadPool = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            0,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            AllayComputeThread::new);
    // Thread pool for executing I/O-intensive tasks
    @Getter
    private final ExecutorService virtualThreadPool = Executors.newVirtualThreadPerTaskExecutor();
    @Getter
    private final EventBus eventBus = new AllayEventBus(Executors.newVirtualThreadPerTaskExecutor());
    private final BanInfo banInfo = ConfigManager.create(BanInfo.class, it -> {
        it.withConfigurer(new YamlSnakeYamlConfigurer()); // specify configurer implementation, optionally additional serdes packages
        it.withBindFile("ban-info.yml"); // specify Path, File or pathname
        it.withRemoveOrphans(true); // automatic removal of undeclared keys
        it.saveDefaults(); // save file if it does not exist
        it.load(true); // load and save to update comments/new fields
    });
    private final Whitelist whitelist = ConfigManager.create(Whitelist.class, it -> {
        it.withConfigurer(new YamlSnakeYamlConfigurer()); // specify configurer implementation, optionally additional serdes packages
        it.withBindFile("whitelist.yml"); // specify Path, File or pathname
        it.withRemoveOrphans(true); // automatic removal of undeclared keys
        it.saveDefaults(); // save file if it does not exist
        it.load(true); // load and save to update comments/new fields
    });

    @Getter
    private PluginManager pluginManager;
    @Getter
    private Scheduler scheduler;
    @Getter
    private NetworkServer networkServer;

    private Thread terminalConsoleThread;
    private AllayTerminalConsole terminalConsole;

    private long nextPlayerDataAutoSaveTime = 0;

    private final GameLoop gameLoop = GameLoop.builder()
            .loopCountPerSec(20)
            .onTick(gameLoop -> {
                try {
                    tick(gameLoop.getTick());
                } catch (Throwable throwable) {
                    log.error("Error while ticking server", throwable);
                }
            }).build();

    @Getter
    private ScoreboardService scoreboardService;
    @Getter
    private long startTime;

    public static AllayServer getInstance() {
        if (INSTANCE == null) {
            synchronized (AllayServer.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AllayServer();
                }
            }
        }

        return INSTANCE;
    }

    @SneakyThrows
    @Override
    public void start(long timeMillis) {
        var ctx = (LoggerContext) LogManager.getContext(false);
        var log4jConfig = ctx.getConfiguration();
        var loggerConfig = log4jConfig.getLoggerConfig(org.apache.logging.log4j.LogManager.ROOT_LOGGER_NAME);
        if (debug && Level.TRACE.isLessSpecificThan(loggerConfig.getLevel())) {
            loggerConfig.setLevel(Level.TRACE);
            ctx.updateLoggers();
        }

        Runtime.getRuntime().addShutdownHook(new Thread("ShutDownHookThread") {
            @Override
            public void run() {
                // TODO: it seems not works
                shutdown();
            }
        });

        initTerminalConsole();

        pluginManager = new AllayPluginManager();
        pluginManager.loadPlugins();

        worldPool.loadWorlds();
        var cmdDataPath = Path.of("command_data");
        if (!Files.exists(cmdDataPath)) Files.createDirectory(cmdDataPath);

        scoreboardService = new ScoreboardService(
                this,
                new JsonScoreboardStorage(Path.of("command_data/scoreboards.json"))
        );
        networkServer = new AllayNetworkServer(this);
        scheduler = new AllayScheduler();

        pluginManager.enablePlugins();

        sendTr(TrKeys.A_NETWORK_SERVER_STARTING);
        networkServer.start();
        startTime = System.currentTimeMillis();
        sendTr(
                TrKeys.A_NETWORK_SERVER_STARTED,
                SETTINGS.networkSettings().ip(),
                String.valueOf(SETTINGS.networkSettings().port()),
                String.valueOf(startTime - timeMillis)
        );

        if (SETTINGS.genericSettings().enableGui()) Allay.DASHBOARD.serverStarted();
        gameLoop.startLoop();
    }

    @Override
    public void disconnectAllPlayersBlocking() {
        players.values().forEach(player -> player.disconnect(TrKeys.M_DISCONNECT_CLOSED));
        while (!players.isEmpty()) {
            Thread.yield();
            // Spin until all players are disconnected
        }
    }

    @Override
    public void tick(long currentTick) {
        this.scheduler.tick();
        playerStorage.tick(currentTick);
        autoSavePlayerData(currentTick);
    }

    private void autoSavePlayerData(long currentTick) {
        if (currentTick >= nextPlayerDataAutoSaveTime) {
            savePlayerData();
            nextPlayerDataAutoSaveTime = currentTick + Server.SETTINGS.storageSettings().playerDataAutoSaveCycle();
        }
    }

    @Override
    public long getTick() {
        return gameLoop.getTick();
    }

    private void initTerminalConsole() {
        terminalConsole = new AllayTerminalConsole(this);
        terminalConsoleThread = new AllayTerminalConsoleThread();
        terminalConsoleThread.start();
    }

    @Override
    public void shutdown() {
        var event = new ServerStopEvent();
        event.call();
        if (event.isCancelled()) return;

        pluginManager.disablePlugins();
        // TODO: check the reason
        // Do not move this line down
        // Otherwise server-settings.yml will be blank after shutdown
        SETTINGS.save();
        banInfo.save();
        whitelist.save();
        scoreboardService.save();

        // Start a thread to handle server shutdown
        Thread.ofPlatform().start(() -> {
            try {
                disconnectAllPlayersBlocking();
            } finally {
                isRunning.compareAndSet(true, false);
                worldPool.close();
                playerStorage.close();
                virtualThreadPool.shutdownNow();
                computeThreadPool.shutdownNow();
            }

            System.exit(0);
        });
    }

    @Override
    public int getOnlinePlayerCount() {
        return players.size();
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

    @Override
    public void onConnect(BedrockServerSession session) {
        if (Server.getInstance().isIPBanned(AllayStringUtils.fastTwoPartSplit(session.getSocketAddress().toString().substring(1), ":", "")[0])) {
            // TODO: I18n
            session.disconnect("Your IP is banned!");
            return;
        }

        var event = new ClientConnectEvent(session);
        event.call();
        if (event.isCancelled()) {
            session.disconnect();
            return;
        }

        var player = PLAYER.createEntity(
                SimpleEntityInitInfo
                        .builder()
                        .build()
        );
        sendTr(TrKeys.A_NETWORK_CLIENT_CONNECTED, session.getSocketAddress().toString());
        player.setClientSession(session);
    }

    @Override
    public void onLoggedIn(EntityPlayer player) {
        players.put(player.getUUID(), player);
        networkServer.setPlayerCount(players.size());
    }

    @Override
    public void onDisconnect(EntityPlayer player, String reason) {
        sendTr(TrKeys.A_NETWORK_CLIENT_DISCONNECTED, player.getClientSession().getSocketAddress().toString());
        if (player.isInitialized()) {
            broadcastTr(TextFormat.YELLOW + "%" + TrKeys.M_MULTIPLAYER_PLAYER_LEFT, player.getOriginName());
        }
        if (player.isSpawned()) {
            this.getPlayerStorage().savePlayerData(player);
            player.getDimension().removePlayer(player);
            var playerListEntry = playerListEntryMap.remove(player.getUUID());
            var pk = new PlayerListPacket();
            pk.setAction(PlayerListPacket.Action.REMOVE);
            pk.getEntries().add(playerListEntry);
            broadcastPacket(pk);
        }
        players.remove(player.getUUID());
        networkServer.setPlayerCount(players.size());
        var event = new PlayerQuitEvent(player, reason);
        eventBus.callEvent(event);
    }

    @Override
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

    @Override
    public void addToPlayerList(
            UUID uuid, long entityId,
            String name, DeviceInfo deviceInfo,
            String xuid, Skin skin
    ) {
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

    @Override
    public void removeFromPlayerList(EntityPlayer player) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.REMOVE);
        playerListPacket.getEntries().add(new PlayerListPacket.Entry(player.getLoginData().getUuid()));
        broadcastPacket(playerListPacket);

        playerListEntryMap.remove(player.getLoginData().getUuid());
    }

    @Override
    public void removeFromPlayerList(UUID uuid) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.REMOVE);
        playerListPacket.getEntries().add(new PlayerListPacket.Entry(uuid));
        broadcastPacket(playerListPacket);

        playerListEntryMap.remove(uuid);
    }

    @Override
    public Map<UUID, PlayerListPacket.Entry> getPlayerListEntryMap() {
        return Collections.unmodifiableMap(this.playerListEntryMap);
    }

    @Override
    public void onSkinUpdate(EntityPlayer player) {
        this.playerListEntryMap.get(player.getUUID()).setSkin(player.getSkin().toNetwork());
    }

    @Override
    public void broadcastPacket(BedrockPacket packet) {
        players.values().forEach(player -> player.sendPacket(packet));
    }

    @Override
    public void broadcastTr(String tr) {
        getOnlinePlayers().values().forEach(player -> player.sendTr(tr));
        sendTr(tr);
    }

    @Override
    public void broadcastTr(String tr, String... args) {
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
                .filter(player -> player.getUUID().toString().equals(uuidOrName) ||
                                  player.getOriginName().equals(uuidOrName))
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
                .filter(player -> player.getUUID().toString().equals(uuidOrName) ||
                                  player.getOriginName().equals(uuidOrName))
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
    public String getCommandSenderName() {
        return "Server";
    }

    @Override
    public CommandOriginData getCommandOriginData() {
        return SERVER_COMMAND_ORIGIN_DATA;
    }

    @Override
    public Location3fc getCmdExecuteLocation() {
        return new Location3f(0, 0, 0, getDefaultWorld().getDimension(DimensionInfo.OVERWORLD.dimensionId()));
    }

    @Override
    public PermTree getPermTree() {
        return DefaultPermissions.OPERATOR;
    }

    private class AllayTerminalConsoleThread extends Thread {
        public AllayTerminalConsoleThread() {
            super("Console Thread");
        }

        @Override
        public void run() {
            terminalConsole.start();
        }
    }
}
