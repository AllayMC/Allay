package cn.allay.server;

import cn.allay.api.network.Client;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.server.Server;
import cn.allay.api.server.ServerSettings;
import cn.allay.api.world.GameMode;
import cn.allay.api.world.World;
import cn.allay.server.network.AllayNetworkServer;
import cn.allay.server.player.AllayClient;
import cn.allay.server.terminal.AllayTerminalConsole;
import cn.allay.server.utils.GameLoop;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
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
    private final Map<String, World> worlds = new ConcurrentHashMap<>();
    @Getter
    private World defaultWorld;

    private Thread terminalConsoleThread;
    private AllayTerminalConsole terminalConsole;
    private final AtomicBoolean isRunning = new AtomicBoolean(true);

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
        //TODO: World loading
    }

    @Override
    public void shutdown() {
        isRunning.compareAndSet(true, false);
    }

    private void onTick() {
        //TODO
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
                .gameType(GameMode.CREATIVE)
                .tickingRadius(6)
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
        var disconnected = clients.remove(client.getName());
        if (disconnected == null) {
            throw new IllegalArgumentException("Client is not connected: " + client.getName());
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }
}
