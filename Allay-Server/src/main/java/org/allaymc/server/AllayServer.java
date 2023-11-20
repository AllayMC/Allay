package org.allaymc.server;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.info.DeviceInfo;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.network.NetworkServer;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.ServerSettings;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.allaymc.api.world.storage.PlayerStorage;
import org.allaymc.server.network.AllayNetworkServer;
import org.allaymc.server.scheduler.AllayScheduler;
import org.allaymc.server.terminal.AllayTerminalConsole;
import org.allaymc.server.world.AllayDimension;
import org.allaymc.server.world.AllayWorld;
import org.allaymc.server.world.AllayWorldPool;
import org.allaymc.server.world.generator.flat.FlatWorldGenerator;
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
    private final GameLoop MAIN_THREAD_GAME_LOOP;
    @Getter
    private ServerSettings serverSettings;
    @Getter
    private NetworkServer networkServer;
    @Getter
    private long ticks;
    @Getter
    private final Scheduler serverScheduler;
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
        ticks = 0;
        MAIN_THREAD_GAME_LOOP = GameLoop.builder()
                .loopCountPerSec(20)
                .onTick(loop -> {
                    if (isRunning.get())
                        onTick();
                    else loop.stop();
                })
                .onStop(() -> isRunning.set(false))
                .build();
        serverScheduler = new AllayScheduler(virtualThreadPool);
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
                while (MAIN_THREAD_GAME_LOOP.isRunning()) {
                    MAIN_THREAD_GAME_LOOP.stop();
                }
                System.out.println("Server ShutDown Over.");
            }
        });
        initTerminalConsole();
        this.serverSettings = readServerSettings();
        this.networkServer = initNetwork();
        log.info("Starting up network server...");
        this.networkServer.start();
        log.info("Network server started  ( " + (System.currentTimeMillis() - timeMillis) + "ms )");
        loadWorlds();
        MAIN_THREAD_GAME_LOOP.startLoop();
    }

    private void initTerminalConsole() {
        terminalConsole = new AllayTerminalConsole(this);
        terminalConsoleThread = new AllayTerminalConsoleThread();
        terminalConsoleThread.start();
    }

    private void loadWorlds() {
        //AllayWorld defaultWorld = new AllayWorld(new AllayLevelDBWorldStorage(Path.of("world/Bedrock Level1")));
        AllayWorld defaultWorld = new AllayWorld(new AllayLevelDBWorldStorage(Path.of("worlds/Cordoba")));
        defaultWorld.setDimension(new AllayDimension(defaultWorld, new FlatWorldGenerator(), DimensionInfo.OVERWORLD));
        worldPool.setDefaultWorld(defaultWorld);
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }

    private void onTick() {
        ticks++;
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
        players.put(player.getUuid(), player);
        networkServer.setPlayerCount(players.size());
    }

    @Override
    public void onDisconnect(EntityPlayer player) {
        if (!player.isLoggedIn()) return;
        players.remove(player.getUuid());
        networkServer.setPlayerCount(players.size());
        var playerListEntry = playerListEntryMap.remove(player.getUuid());
        var pk = new PlayerListPacket();
        pk.setAction(PlayerListPacket.Action.REMOVE);
        pk.getEntries().add(playerListEntry);
        broadcastPacket(pk);
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
