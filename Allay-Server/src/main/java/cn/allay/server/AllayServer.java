package cn.allay.server;

import cn.allay.api.client.Client;
import cn.allay.api.client.info.DeviceInfo;
import cn.allay.api.client.skin.Skin;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.server.Server;
import cn.allay.api.server.ServerSettings;
import cn.allay.api.world.World;
import cn.allay.api.world.WorldPool;
import cn.allay.api.world.storage.ClientStorage;
import cn.allay.server.client.AllayClient;
import cn.allay.server.network.AllayNetworkServer;
import cn.allay.server.terminal.AllayTerminalConsole;
import cn.allay.server.world.AllayWorld;
import cn.allay.server.world.AllayWorldPool;
import cn.allay.server.world.generator.flat.FlatWorldGenerator;
import cn.allay.server.world.storage.nonpersistent.AllayNonPersistentClientStorage;
import cn.allay.server.world.storage.nonpersistent.AllayNonPersistentWorldStorage;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public final class AllayServer implements Server {
    private final boolean DEBUG;
    private final Map<String, Client> clients;
    @Getter
    private final WorldPool worldPool;
    private final AtomicBoolean isRunning;
    private final Object2ObjectMap<UUID, PlayerListPacket.Entry> playerListEntryMap;
    @Getter
    private final ClientStorage clientStorage;
    //执行CPU密集型任务的线程池
    @Getter
    private final ForkJoinPool computeThreadPool;
    //执行IO密集型任务的线程池
    @Getter
    private final ExecutorService virtualThreadPool;
    private final GameLoop MAIN_THREAD_GAME_LOOP;
    @Getter
    private ServerSettings serverSettings;
    @Getter
    private NetworkServer networkServer;
    private Thread terminalConsoleThread;
    private AllayTerminalConsole terminalConsole;
    @Getter
    private long ticks;
    private static volatile AllayServer instance;

    private AllayServer() {
        DEBUG = false;
        clients = new ConcurrentHashMap<>();
        worldPool = new AllayWorldPool();
        isRunning = new AtomicBoolean(true);
        playerListEntryMap = new Object2ObjectOpenHashMap<>();
        //TODO: client storage
        clientStorage = new AllayNonPersistentClientStorage();
        computeThreadPool = new ForkJoinPool(
                Runtime.getRuntime().availableProcessors() + 1,
                pool -> {
                    ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
                    worker.setName("computation-thread-" + worker.getPoolIndex());
                    return worker;
                },
                null, true);
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
    public void start() {
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
        log.info("Network server started.");
        loadWorlds();
        MAIN_THREAD_GAME_LOOP.startLoop();
    }

    private void initTerminalConsole() {
        terminalConsole = new AllayTerminalConsole(this);
        terminalConsoleThread = new AllayTerminalConsoleThread();
        terminalConsoleThread.start();
    }

    private void loadWorlds() {
        worldPool.setDefaultWorld(AllayWorld
                .builder()
                .setWorldGenerator(new FlatWorldGenerator())
                .setWorldStorage(new AllayNonPersistentWorldStorage()/*new RocksDBWorldStorage(Path.of("output/新的世界"))*/)
                .build());
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }

    private void onTick() {
        ticks++;
    }

    @Override
    public int getOnlineClientCount() {
        return clients.size();
    }

    @Override
    @UnmodifiableView
    public Map<String, Client> getOnlineClients() {
        return Collections.unmodifiableMap(clients);
    }

    private ServerSettings readServerSettings() {
        //TODO
        return ServerSettings
                .builder()
                .ip("0.0.0.0")
                .port(19132)
                .motd("Allay Server")
                .subMotd("Powered by Allay")
                .maxClientCount(20)
                .gameType(GameType.CREATIVE)
                .xboxAuth(false)
                .defaultTickingRadius(8)
                .defaultViewDistance(48)
                .enableNetworkEncryption(true)
                .build();
    }

    private NetworkServer initNetwork() {
        return new AllayNetworkServer(this);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void onClientConnect(BedrockServerSession session) {
        AllayClient.hold(session, this);
    }

    @Override
    public void onLoginFinish(Client client) {
        clients.put(client.getName(), client);
        networkServer.setPlayerCount(clients.size());
    }

    @Override
    public void onClientDisconnect(Client client) {
        clients.remove(client.getName());
        networkServer.setPlayerCount(clients.size());
    }

    @Override
    public void addToPlayerList(Client client) {
        addToPlayerList(
                client.getLoginData().getUuid(),
                client.getPlayerEntity().getUniqueId(),
                client.getName(),
                client.getLoginData().getDeviceInfo(),
                client.getLoginData().getXuid(),
                client.getLoginData().getSkin()
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

    public void removeFromPlayerList(Client client) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.REMOVE);
        playerListPacket.getEntries().add(new PlayerListPacket.Entry(client.getLoginData().getUuid()));
        broadcastPacket(playerListPacket);
        playerListEntryMap.remove(client.getLoginData().getUuid());
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
        for (var client : clients.values()) {
            client.sendPacket(packet);
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
