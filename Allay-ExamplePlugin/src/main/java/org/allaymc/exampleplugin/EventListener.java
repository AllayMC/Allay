package org.allaymc.exampleplugin;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
import org.allaymc.api.form.Forms;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.MathUtils;

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

        // World info
        var worldInfo = "World: §a" + player.getWorld().getWorldData().getName() + "\n§f" +
                        "Time: §a" + player.getWorld().getWorldData().getTime() + "\n§f" +
                        "TPS: §a" + MathUtils.round(player.getWorld().getTps(), 2) + "\n§f" +
                        "MSPT: §a" + MathUtils.round(player.getWorld().getMSPT(), 2);
        lines.add(worldInfo);

        var loc = player.getLocation();
        var chunk = player.getCurrentChunk();
        var itemInHand = player.getItemInHand();
        var blockUnder = player.getDimension().getBlockState(BlockFace.DOWN.offsetPos((int) loc.x(), (int) loc.y(), (int) loc.z()));
        lines.add(
                "ItemInHand:\n§a" + itemInHand.getItemType().getIdentifier().path() + (itemInHand.getMeta() != 0 ? ":" + itemInHand.getMeta() : "") + "\n§f" +
                "BlockUnder:\n§a" + blockUnder.getBlockType().getIdentifier().path()
        );
        var chunkInfo =
                "Chunk: §a" + chunk.getX() + "," + chunk.getZ() + "\n§f" +
                "Loaded: §a" + player.getDimension().getChunkService().getLoadedChunks().size() + "\n§f" +
                "Loading: §a" + player.getDimension().getChunkService().getLoadingChunks().size() + "\n§f";
        try {
            chunkInfo += "Biome:\n§a" + player.getCurrentChunk().getBiome((int) loc.x() & 15, (int) loc.y(), (int) loc.z() & 15).toString().toLowerCase();
        } catch (IllegalArgumentException e) {
            // y coordinate is out of range
            chunkInfo += "Biome: §aN/A";
        }
        lines.add(chunkInfo);

        // Player info
        var playerInfo = "Ping: §a" + player.getPing() + "\n§f" +
                         "Food: §a" + player.getFoodLevel() + "/" + (int) AttributeType.PLAYER_HUNGER.getMaxValue() + "\n§f" +
                         "Exhaustion: §a" + MathUtils.round(player.getFoodExhaustionLevel(), 2) + "/" + (int) AttributeType.PLAYER_EXHAUSTION.getMaxValue() + "\n§f" +
                         "Saturation: §a" + MathUtils.round(player.getFoodSaturationLevel(), 2) + "/" + (int) AttributeType.PLAYER_SATURATION.getMaxValue() + "\n§f" +
                         "Exp: §a" + player.getExperienceInCurrentLevel() + "/" + player.getRequireExperienceForCurrentLevel();
        lines.add(playerInfo);

        scoreboard.setLines(lines);
    }
}
