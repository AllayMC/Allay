package org.allaymc.server;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.client.info.DeviceInfo;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.I18nTranslator;
import org.allaymc.api.network.NetworkServer;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerSettings;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.allaymc.api.world.storage.PlayerStorage;
import org.allaymc.server.i18n.AllayI18NTranslator;
import org.allaymc.server.i18n.AllayI18nLoader;
import org.allaymc.server.network.AllayNetworkServer;
import org.allaymc.server.terminal.AllayTerminalConsole;
import org.allaymc.server.world.AllayDimension;
import org.allaymc.server.world.AllayWorld;
import org.allaymc.server.world.AllayWorldPool;
import org.allaymc.server.world.generator.jegen.JeGeneratorLoader;
import org.allaymc.server.world.storage.leveldb.AllayLevelDBWorldStorage;
import org.allaymc.server.world.storage.nonpersistent.AllayNonPersistentPlayerStorage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.allaymc.api.AllayAPI.API_VERSION;

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
    private ServerSettings serverSettings;
    private I18nTranslator serverI18nTranslator;
    @Getter
    private NetworkServer networkServer;
    private Thread terminalConsoleThread;
    private AllayTerminalConsole terminalConsole;
    private static volatile AllayServer instance;

    private AllayServer() {
        DEBUG = false;
        players = new ConcurrentHashMap<>();
        worldPool = new AllayWorldPool();
        isRunning = new AtomicBoolean(true);
        playerListEntryMap = new Object2ObjectOpenHashMap<>();
        //TODO: client storage
        playerStorage = new AllayNonPersistentPlayerStorage();
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
                isRunning.compareAndSet(true, false);
                getWorldPool().getWorlds().values().forEach(World::close);
                virtualThreadPool.shutdownNow();
                computeThreadPool.shutdownNow();
                System.out.println("Server ShutDown Over.");
            }
        });
        initTerminalConsole();
        this.serverSettings = readServerSettings();
        this.serverI18nTranslator = new AllayI18NTranslator(new AllayI18nLoader(), serverSettings.genericSettings().language());
        sendTr("allay:lang.set", serverSettings.genericSettings().language().toString());
        loadWorlds();
        this.networkServer = initNetwork();
        sendTr("allay:network.server.starting");
        this.networkServer.start();
        sendTr("allay:network.server.started", serverSettings.networkSettings().ip(), String.valueOf(serverSettings.networkSettings().port()), String.valueOf(System.currentTimeMillis() - timeMillis));
        sendTr("allay:api.implemented", "Allay", API_VERSION);
    }

    private void initTerminalConsole() {
        terminalConsole = new AllayTerminalConsole(this);
        terminalConsoleThread = new AllayTerminalConsoleThread();
        terminalConsoleThread.start();
    }

    private void loadWorlds() {
        JeGeneratorLoader.setup();
        JeGeneratorLoader.waitStart();
        AllayWorld defaultWorld = new AllayWorld(new AllayLevelDBWorldStorage(Path.of("worlds/Bedrock Level")));
        defaultWorld.setDimension(new AllayDimension(defaultWorld, JeGeneratorLoader.getJeGenerator(DimensionInfo.OVERWORLD), DimensionInfo.OVERWORLD));

//        FLAT WORLD
//        AllayWorld defaultWorld = new AllayWorld(new AllayLevelDBWorldStorage(Path.of("worlds/Bedrock Level")));
//        AllayWorld defaultWorld = new AllayWorld(new AllayNonPersistentWorldStorage());
//        defaultWorld.setDimension(new AllayDimension(defaultWorld, new FlatWorldGenerator(), DimensionInfo.OVERWORLD));

        worldPool.setDefaultWorld(defaultWorld);
    }

    @Override
    public void shutdown() {
        System.exit(0);
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

    private ServerSettings readServerSettings() {
        return ConfigManager.create(ServerSettings.class, it -> {
            it.withConfigurer(new YamlSnakeYamlConfigurer()); // specify configurer implementation, optionally additional serdes packages
            it.withBindFile("server-settings.yml"); // specify Path, File or pathname
            it.withRemoveOrphans(true); // automatic removal of undeclared keys
            it.saveDefaults(); // save file if does not exists
            it.load(true); // load and save to update comments/new fields
        });
    }

    private NetworkServer initNetwork() {
        return new AllayNetworkServer(this);
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
        player.setClientSession(session);
    }

    @Override
    public void onLoggedIn(EntityPlayer player) {
        players.put(player.getUUID(), player);
        networkServer.setPlayerCount(players.size());
    }

    @Override
    public void onDisconnect(EntityPlayer player) {
        if (player.isInitialized()) {
            this.getPlayerStorage().writePlayerData(player);
        }
        player.getDimension().removePlayer(player);
        players.remove(player.getUUID());
        networkServer.setPlayerCount(players.size());
        var playerListEntry = playerListEntryMap.remove(player.getUUID());
        var pk = new PlayerListPacket();
        pk.setAction(PlayerListPacket.Action.REMOVE);
        pk.getEntries().add(playerListEntry);
        broadcastPacket(pk);
        broadcastTr("§e%minecraft:multiplayer.player.left", player.getName());
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
    public void sendTr(String tr) {
        log.info(getI18nTranslator().tr(tr));
    }

    @Override
    public void sendTr(String tr, String... args) {
        log.info(getI18nTranslator().tr(tr, args));
    }

    @Override
    public I18nTranslator getI18nTranslator() {
        return serverI18nTranslator;
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
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
