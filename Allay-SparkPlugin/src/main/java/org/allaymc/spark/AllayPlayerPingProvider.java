package org.allaymc.spark;

import lombok.AllArgsConstructor;
import me.lucko.spark.common.monitor.ping.PlayerPingProvider;
import org.allaymc.api.server.Server;

import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 08/02/2024
 *
 * @author IWareQ
 */
@AllArgsConstructor
public class AllayPlayerPingProvider implements PlayerPingProvider {

    private final Server server;

    @Override
    public Map<String, Integer> poll() {
        Map<String, Integer> builder = new HashMap<>();
        for (var player : this.server.getOnlinePlayers().values())
            builder.put(player.getName(), 20); // TODO
        return builder;
    }
}
