package cn.allay.server;

import cn.allay.api.math.location.Loc;
import cn.allay.api.network.Client;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.server.Server;
import cn.allay.api.server.ServerSettings;
import cn.allay.api.world.World;
import cn.allay.api.world.WorldPool;
import cn.allay.api.world.WorldType;
import cn.allay.server.network.AllayNetworkServer;
import cn.allay.server.player.AllayClient;
import cn.allay.server.terminal.AllayTerminalConsole;
import cn.allay.server.utils.GameLoop;
import cn.allay.server.world.AllayWorld;
import cn.allay.server.world.AllayWorldPool;
import cn.allay.server.world.generator.flat.FlatWorldGenerator;
import cn.allay.server.world.storage.nonpersistent.AllayNonPersistentWorldStorage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public final class AllayServer implements Server {

    @Getter
    private final Map<String, Client> clients = new ConcurrentHashMap<>();
    @Getter
    private ServerSettings serverSettings;
    @Getter
    private NetworkServer networkServer;
    @Getter
    private final WorldPool worldPool = new AllayWorldPool();

    private Thread terminalConsoleThread;
    private AllayTerminalConsole terminalConsole;
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    @Getter
    private long ticks = 0;

    @Override
    public void start() {
        initTerminalConsole();
        this.serverSettings = readServerSettings();
        this.networkServer = initNetwork();
        log.info("Starting up network server...");
        this.networkServer.start();
        log.info("Network server started.");
        loadWorlds();

        loop();
    }

    private void loop() {
        GameLoop.builder()
                .loopCountPerSec(20)
                .onTick(loop -> {
                    if (isRunning.get())
                        onTick();
                    else loop.stop();
                })
                .onStop(() -> isRunning.set(false))
                .build()
                .startLoop();
    }

    private void initTerminalConsole() {
        terminalConsole = new AllayTerminalConsole(this);
        terminalConsoleThread = new AllayTerminalConsoleThread();
        terminalConsoleThread.start();
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

    private void loadWorlds() {
        //TODO: Remove this hack
        worldPool.setDefaultWorld(AllayWorld
                .builder()
                .setServer(this)
                .setName("Test Flat World")
                .setWorldGenerator(new FlatWorldGenerator())
                .setWorldStorage(new AllayNonPersistentWorldStorage())
                .setWorldType(WorldType.FLAT)
                .setSpawnLocation(Loc.of(0f, 0f, 0f, null))
                .build());
    }

    @Override
    public void shutdown() {
        isRunning.compareAndSet(true, false);
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
                .defaultViewDistance(8)
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
    }

    @Override
    public void onClientDisconnect(Client client) {
        clients.remove(client.getName());
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }
}
