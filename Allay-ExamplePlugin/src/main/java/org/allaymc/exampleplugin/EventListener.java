package org.allaymc.exampleplugin;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
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
public class EventListener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
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
        Server.getInstance().getScheduler().scheduleRepeating(ExamplePlugin.INSTANCE, () -> {
            if (player.isDisconnected()) return false;
            var list = new ArrayList<String>();
            list.add("Online: §a" + Server.getInstance().getOnlinePlayerCount() + "/" + Server.getInstance().getNetworkServer().getMaxPlayerCount());
            list.add("Time: §a" + player.getWorld().getWorldData().getTime());
            list.add("World: §a" + player.getWorld().getWorldData().getName());
            if (!player.isInWorld()) return true;
            var loc = player.getLocation();
            var chunk = player.getCurrentChunk();
            int cx = chunk.getX();
            int cz = chunk.getZ();
            var blockUnder = player.getDimension().getBlockState(BlockFace.DOWN.offsetPos((int) loc.x(), (int) loc.y(), (int) loc.z()));
            list.add("BlockUnder: §a" + blockUnder.getBlockType().getIdentifier().path());
            list.add("Chunk: §a" + cx + ", " + cz);
            list.add("Loaded: §a" + player.getDimension().getChunkService().getLoadedChunks().size());
            list.add("Loading: §a" + player.getDimension().getChunkService().getLoadingChunks().size());
            try {
                list.add("Biome: §a" + player.getCurrentChunk().getBiome((int) loc.x() & 15, (int) loc.y(), (int) loc.z() & 15));
            } catch (IllegalArgumentException e) {
                // y坐标超出范围了
                list.add("Biome: §aN/A");
            }
            list.add("Ping: " + player.getPing());
            scoreboard.setLines(list);
            return true;
        }, 20);
    }
}
