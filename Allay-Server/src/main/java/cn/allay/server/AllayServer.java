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

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
public final class AllayServer implements Server {

    private final Set<Client> clients = new HashSet<>();
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
        clients.add(AllayClient.hold(session, this));
    }

    @Override
    public void onClientDisconnect(Client client) {
        var disconnected = clients.remove(client);
        if (!disconnected) {
            throw new IllegalArgumentException("Client is not connected: " + client);
        }
    }
}
