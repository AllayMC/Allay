package org.allaymc.server;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.data.DeviceInfo;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.client.storage.PlayerStorage;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.network.NetworkServer;
import org.allaymc.api.perm.DefaultPermissions;
import org.allaymc.api.perm.tree.PermTree;
import org.allaymc.api.plugin.PluginManager;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.GameLoop;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.allaymc.server.client.storage.AllayEmptyPlayerStorage;
import org.allaymc.server.client.storage.AllayNBTFilePlayerStorage;
import org.allaymc.server.command.AllayCommandRegistry;
import org.allaymc.server.network.AllayNetworkServer;
import org.allaymc.server.plugin.AllayPluginManager;
import org.allaymc.server.terminal.AllayTerminalConsole;
import org.allaymc.server.world.AllayWorldPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public final class AllayServer implements Server {
    private final boolean DEBUG;
    private final Map<UUID, EntityPlayer> players;
    @Getter
    private final WorldPool worldPool;
    private final AtomicBoolean isRunning;
    //TODO: skin update
    private final Object2ObjectMap<UUID, PlayerListPacket.Entry> playerListEntryMap;
    @Getter
    private final PlayerStorage playerStorage;
    //执行CPU密集型任务的线程池
    @Getter
    private final ThreadPoolExecutor computeThreadPool;
    //执行IO密集型任务的线程池
    @Getter
    private final ExecutorService virtualThreadPool;
    @Getter
    private CommandRegistry commandRegistry;
    @Getter
    private PluginManager pluginManager;
    @Getter
    private NetworkServer networkServer;
    private Thread terminalConsoleThread;
    private AllayTerminalConsole terminalConsole;
    private static volatile AllayServer instance;
    private int nextPlayerDataAutoSaveTime = 0;

    private final GameLoop gameLoop = GameLoop.builder()
            .loopCountPerSec(20)
            .onTick(gameLoop -> {
                try {
                    tick(gameLoop.getTick());
                } catch (Throwable throwable) {
                    log.error("Error while ticking server", throwable);
                }
            })
            .build();

    private AllayServer() {
        DEBUG = Server.SETTINGS.genericSettings().debug();
        players = new ConcurrentHashMap<>();
        worldPool = new AllayWorldPool();
        isRunning = new AtomicBoolean(true);
        playerListEntryMap = new Object2ObjectOpenHashMap<>();
        playerStorage = Server.SETTINGS.storageSettings().savePlayerData() ?
                new AllayNBTFilePlayerStorage(Path.of("players")) :
                AllayEmptyPlayerStorage.INSTANCE;
        computeThreadPool = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("computation-thread-" + thread.threadId());
                    return thread;
                });
        virtualThreadPool = Executors.newVirtualThreadPerTaskExecutor();
    }

    public static AllayServer getInstance() {
        if (instance == null) {
            synchronized (AllayServer.class) {
                if (instance == null) {
                    instance = new AllayServer();
                }
            }
        }
        return instance;
    }

    @Override
    public void start(long timeMillis) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration log4jConfig = ctx.getConfiguration();
        LoggerConfig loggerConfig = log4jConfig.getLoggerConfig(org.apache.logging.log4j.LogManager.ROOT_LOGGER_NAME);
        if (DEBUG && Level.TRACE.isLessSpecificThan(loggerConfig.getLevel())) {
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
        worldPool.loadWorlds();
        this.commandRegistry = new AllayCommandRegistry();
        this.commandRegistry.registerDefaultCommands();
        this.networkServer = new AllayNetworkServer(this);
        this.pluginManager = new AllayPluginManager();
        pluginManager.loadPlugins();
        pluginManager.enablePlugins();
        // TODO: plugin
        sendTr(TrKeys.A_NETWORK_SERVER_STARTING);
        this.networkServer.start();
        sendTr(TrKeys.A_NETWORK_SERVER_STARTED, SETTINGS.networkSettings().ip(), String.valueOf(SETTINGS.networkSettings().port()), String.valueOf(System.currentTimeMillis() - timeMillis));
        gameLoop.startLoop();
    }

    @Override
    public void kickAllPlayersAndBlock() {
        players.values().forEach(player -> player.disconnect(TrKeys.M_DISCONNECT_CLOSED));
        while (!players.isEmpty()) {
            Thread.yield();
            // Spin until all players are disconnected
        }
    }

    @Override
    public void tick(long currentTick) {
        playerStorage.tick(currentTick);
        autoSavePlayerData(currentTick);
    }

    private void autoSavePlayerData(long currentTick) {
        if (currentTick >= nextPlayerDataAutoSaveTime) {
            savePlayerData();
            nextPlayerDataAutoSaveTime += Server.SETTINGS.storageSettings().playerDataAutoSaveCycle();
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
        // Start a thread to handle server shutdown
        Thread.ofPlatform().start(() -> {
            SETTINGS.save();
            kickAllPlayersAndBlock();
            isRunning.compareAndSet(true, false);
            worldPool.close();
            playerStorage.close();
            virtualThreadPool.shutdownNow();
            computeThreadPool.shutdownNow();
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
        var player = EntityPlayer.PLAYER_TYPE.createEntity(
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
            broadcastTr("§e%" + TrKeys.M_MULTIPLAYER_PLAYER_LEFT, player.getOriginName());
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
    }

    @Override
    public void addToPlayerList(EntityPlayer player) {
        addToPlayerList(
                player.getLoginData().getUuid(),
                player.getUniqueId(),
                player.getOriginName(),
                player.getLoginData().getDeviceInfo(),
                player.getLoginData().getXuid(),
                player.getLoginData().getSkin()
        );
    }

    @Override
    public void addToPlayerList(UUID uuid, long entityId,
                                String name, DeviceInfo deviceInfo,
                                String xuid, Skin skin) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.ADD);
        PlayerListPacket.Entry entry = new PlayerListPacket.Entry(uuid);
        entry.setEntityId(entityId);
        entry.setName(name);
        entry.setXuid(xuid);
        entry.setPlatformChatId(deviceInfo.getDeviceName());
        entry.setBuildPlatform(deviceInfo.getDevice().getId());
        entry.setSkin(skin.toNetwork());
        entry.setTrustedSkin(skin.isTrusted());
        playerListPacket.getEntries().add(entry);
        playerListEntryMap.put(uuid, entry);
        broadcastPacket(playerListPacket);
    }

    public void removeFromPlayerList(EntityPlayer player) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.REMOVE);
        playerListPacket.getEntries().add(new PlayerListPacket.Entry(player.getLoginData().getUuid()));
        broadcastPacket(playerListPacket);
        playerListEntryMap.remove(player.getLoginData().getUuid());
    }

    public void removeFromPlayerList(UUID uuid) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.REMOVE);
        playerListPacket.getEntries().add(new PlayerListPacket.Entry(uuid));
        broadcastPacket(playerListPacket);
        playerListEntryMap.remove(uuid);
    }

    public Map<UUID, PlayerListPacket.Entry> getPlayerListEntryMap() {
        return this.playerListEntryMap;
    }

    @Override
    public void broadcastPacket(BedrockPacket packet) {
        for (var player : players.values()) {
            player.sendPacket(packet);
        }
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
            log.info("[" + sender.getName() + "] " + (status <= 0 ? "§c" : "") + I18n.get().tr(output.str(), output.args()));
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }

    @Override
    public String getName() {
        return "Server";
    }

    private static final CommandOriginData SERVER_COMMAND_ORIGIN_DATA = new CommandOriginData(CommandOriginType.DEDICATED_SERVER, UUID.randomUUID(), "", 0);

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
