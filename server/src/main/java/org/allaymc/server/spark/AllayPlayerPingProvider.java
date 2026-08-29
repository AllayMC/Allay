package org.allaymc.server.spark;

import me.lucko.spark.common.monitor.ping.PlayerPingProvider;
import org.allaymc.api.server.Server;

import java.util.HashMap;
import java.util.Map;

public final class AllayPlayerPingProvider implements PlayerPingProvider {

    @Override
    public Map<String, Integer> poll() {
        Map<String, Integer> pings = new HashMap<>();
        Server.getInstance().getPlayerManager().forEachPlayer(player -> pings.put(player.getOriginName(), player.getPing()));
        return pings;
    }
}