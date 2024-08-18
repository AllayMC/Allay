package org.allaymc.exampleplugin;

import org.allaymc.api.data.BlockFace;
import org.allaymc.api.entity.interfaces.EntityPlayer;
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
            updateScoreboard(player, scoreboard);
            return true;
        }, 20);
    }

    private void updateScoreboard(EntityPlayer player, Scoreboard scoreboard) {
        if (!player.isInWorld()) return;

        var lines = new ArrayList<String>();
        lines.add("Online: §a" + Server.getInstance().getOnlinePlayerCount() + "/" + Server.getInstance().getNetworkServer().getMaxPlayerCount());
        lines.add("Time: §a" + player.getWorld().getWorldData().getTime());
        lines.add("World: §a" + player.getWorld().getWorldData().getName());
        var loc = player.getLocation();
        var chunk = player.getCurrentChunk();
        var blockUnder = player.getDimension().getBlockState(BlockFace.DOWN.offsetPos((int) loc.x(), (int) loc.y(), (int) loc.z()));
        lines.add("BlockUnder: §a" + blockUnder.getBlockType().getIdentifier().path());
        var itemInHand = player.getItemInHand();
        lines.add("ItemInHand: §a" + itemInHand.getItemType().getIdentifier() + (itemInHand.getMeta() != 0 ? ":" + itemInHand.getMeta() : ""));
        lines.add("Chunk: §a" + chunk.getX() + ", " + chunk.getZ());
        lines.add("Loaded: §a" + player.getDimension().getChunkService().getLoadedChunks().size());
        lines.add("Loading: §a" + player.getDimension().getChunkService().getLoadingChunks().size());
        try {
            lines.add("Biome: §a" + player.getCurrentChunk().getBiome((int) loc.x() & 15, (int) loc.y(), (int) loc.z() & 15));
        } catch (IllegalArgumentException e) {
            // y坐标超出范围了
            lines.add("Biome: §aN/A");
        }
        lines.add("Ping: " + player.getPing());
        lines.add("ItlSkyLight: " + player.getWorld().getInternalSkyLight());
        scoreboard.setLines(lines);
    }
}
