package cn.allay.server;

import cn.allay.block.registry.VanillaBlockDataRegistry;
import cn.allay.network.AllayNetwork;
import cn.allay.network.Network;
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
        VanillaBlockDataRegistry.init(new VanillaBlockDataRegistry.Loader());
    }

    @Override
    public void startMainLoop() {
        //TODO
        while (true) {

        }
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

    @Override
    public boolean isValid() {
        return true;
    }
}
