package cn.allay.server;

import cn.allay.api.network.Client;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.server.Server;
import cn.allay.api.server.ServerSettings;
import cn.allay.api.world.GameMode;
import cn.allay.server.network.AllayNetworkServer;
import cn.allay.server.player.AllayClient;
import cn.allay.server.utils.GameLoop;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Getter
public final class AllayServer implements Server {

    private final Map<String, Client> clients = new ConcurrentHashMap<>();
    private ServerSettings serverSettings;
    private NetworkServer networkServer;

    @Override
    public void initServer() {
        this.serverSettings = readServerSettings();
        this.networkServer = initNetwork();
        log.info("Starting up network...");
        this.networkServer.start();
    }

    @Override
    public void startMainLoop() {
        GameLoop.builder()
                .loopCountPerSec(20)
                .onTick(loop -> onTick())
                .build()
                .startLoop();
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
        if (disconnected != null) {
            throw new IllegalArgumentException("Client is not connected: " + client.getName());
        }
    }
}
