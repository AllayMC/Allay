package cn.allay.server;

import cn.allay.network.Network;
import cn.allay.network.AllayNetwork;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public final class AllayServer implements Server {

    private ServerSettings serverSettings;
    private Network network;

    @Override
    public void initServer() {
        this.serverSettings = readServerSettings();
        this.network = initNetwork();
        log.info("Starting up network...");
        this.network.start();
    }

    @Override
    public void startMainLoop() {
        //TODO
        while (true){}
    }

    @Override
    public int getOnlinePlayerCount() {
        //TODO
        return 0;
    }

    private ServerSettings readServerSettings() {
        //TODO
        return ServerSettings
                .builder()
                .ip("0.0.0.0")
                .port(19132)
                .motd("Spray Server")
                .subMotd("Powered by Spray")
                .maxPlayerCount(20)
                .build();
    }

    private Network initNetwork() {
        return new AllayNetwork(this);
    }
}
