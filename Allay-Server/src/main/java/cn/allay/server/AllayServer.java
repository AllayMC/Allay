package cn.allay.server;

import cn.allay.api.network.Network;
import cn.allay.api.server.Server;
import cn.allay.api.server.ServerSettings;
import cn.allay.server.network.AllayNetwork;
import cn.allay.server.utils.GameLoop;
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
                .motd("Allay Server")
                .subMotd("Powered by Allay")
                .maxPlayerCount(20)
                .build();
    }

    private Network initNetwork() {
        return new AllayNetwork(this);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
