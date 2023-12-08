package org.allaymc.server.network.processor;

import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityVillagerV2;
import org.allaymc.server.network.DataPacketProcessor;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class TextPacketProcessor extends DataPacketProcessor<TextPacket> {
    @Override
    public void handle(EntityPlayer player, TextPacket pk) {
        if (pk.getType() == TextPacket.Type.CHAT) {
            var message = pk.getMessage();
            Server.getInstance().broadcastChat(player, message);
            //TODO: will be remove,debug only
            if (message.equals("spawn v")) {
                var loc = player.getLocation();
                for (var i = 0; i <= 0; i++) {
                    var entity = EntityVillagerV2.VILLAGER_V2_TYPE.createEntity(
                            SimpleEntityInitInfo
                                    .builder()
                                    .pos(loc.x() + i, loc.y(), loc.z() + i)
                                    .dimension(loc.dimension())
                                    .build()
                    );
                    loc.dimension().getEntityUpdateService().addEntity(entity);
                }
                player.sendRawMessage("TPS: " + loc.dimension().getWorld().getTps() + ", Entity Count: " + loc.dimension().getEntities().size());
            }
            if (message.startsWith("gb_")) {
                var blockStateHash = Integer.parseInt(message.substring(3));
                var blockState = BlockStateHashPalette.getRegistry().get(blockStateHash);
                if (blockState == null) {
                    player.sendRawMessage("unknown hash!");
                    return;
                }
                player.getContainer(FullContainerType.PLAYER_INVENTORY).setItemInHand(blockState.toItemStack());
            }
            if (message.equals("rfinv")) {
                player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), FixedContainerId.PLAYER_INVENTORY);
                player.sendRawMessage("Inventory is refreshed!");
            }
            if (message.equals("tps")) {
                player.sendRawMessage("TPS: " + player.getLocation().dimension().getWorld().getTps());
            }
            if (message.equals("tps20")) {
                AtomicInteger count = new AtomicInteger(1);
                player.getLocation().dimension().getWorld().getScheduler().scheduleRepeating(() -> {
                    count.getAndIncrement();
                    player.sendRawMessage("TPS: " + player.getLocation().dimension().getWorld().getTps());
                    return count.get() <= 20;
                }, 20);
            }
            if (message.equals("testjoin")) {
                var pk2 = new TextPacket();
                pk.setType(TextPacket.Type.TRANSLATION);
                pk.setMessage("§e%multiplayer.player.joined");
                pk.setParameters(List.of("liulihaocai"));
                pk.setNeedsTranslation(true);
                player.sendPacket(pk);
            }
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TEXT;
    }
}
