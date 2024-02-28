package org.allaymc.exampleplugin;

import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.server.player.PlayerInitializedEvent;
import org.allaymc.api.form.Forms;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.server.Server;

import java.util.ArrayList;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class ServerEventListener {

    @EventHandler
    private void onPlayerInitialized(PlayerInitializedEvent event) {
        var player = event.getPlayer();
        player.sendText("allay-chan: welcome to the server");
        player.addServerSettingForm(
                Forms.custom()
                        .title("title of this custom form")
                        .dropdown("a dropdown", "a", "b", "c")
                        .input("an input", "a placeholder", "a default text")
                        .label("a label")
                        .slider("a slider", 0f, 100f)
                        .stepSlider("a step slider", "a", "b", "c")
                        .toggle("a toggle", false)
                        .onResponse(responses -> {
                            player.sendText("dropdown: " + responses.get(0));
                            player.sendText("input: " + responses.get(1));
                            player.sendText("label: " + responses.get(2));
                            player.sendText("slider: " + responses.get(3));
                            player.sendText("step slider: " + responses.get(4));
                            player.sendText("toggle: " + responses.get(5));
                        })
        );
        Scoreboard scoreboard = new Scoreboard("Dashboard");
        scoreboard.addViewer(player, DisplaySlot.SIDEBAR);
        Server.getInstance().getScheduler().scheduleRepeating(() -> {
            if (player.isDisconnected()) return false;
            var list = new ArrayList<String>();
            list.add("Online: §a" + Server.getInstance().getOnlinePlayerCount() + "/" + Server.getInstance().getNetworkServer().getMaxPlayerCount());
            list.add("Time: §a" + player.getWorld().getWorldData().getTime());
            list.add("World: §a" + player.getWorld().getWorldData().getName());
            list.add("DimId: §a" + player.getDimension().getDimensionInfo().dimensionId());
            int cx = ((int)player.getLocation().x()) >> 4;
            int cz = ((int)player.getLocation().z()) >> 4;
            list.add("Chunk: §a" + cx + ", " + cz);
            scoreboard.setLines(list);
            return true;
        }, 20);
    }
}
