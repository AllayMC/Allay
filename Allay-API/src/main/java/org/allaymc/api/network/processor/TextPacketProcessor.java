package org.allaymc.api.network.processor;

import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.entity.interfaces.villagerv2.EntityVillagerV2;
import org.allaymc.api.network.DataPacketProcessor;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

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
            Server.getInstance().broadcastChat(player, pk.getMessage());
            //TODO: will be remove,debug only
            if (pk.getMessage().equals("spawn v")) {
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
            if (pk.getMessage().startsWith("gb_")) {
                var blockStateHash = Integer.parseInt(pk.getMessage().substring(3));
                var blockState = BlockStateHashPalette.getRegistry().get(blockStateHash);
                if (blockState == null) {
                    player.sendRawMessage("unknown hash!");
                    return;
                }
                player.getContainer(FullContainerType.PLAYER_INVENTORY).setItemInHand(blockState.toItemStack());
            }
            if (pk.getMessage().equals("rfinv")) {
                player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), FixedContainerId.PLAYER_INVENTORY);
                player.sendRawMessage("Inventory is refreshed!");
            }
            if (pk.getMessage().equals("tps")) {
                player.sendRawMessage("TPS: " + player.getLocation().dimension().getWorld().getTps());
            }
            if (pk.getMessage().equals("tps20")) {
                AtomicInteger count = new AtomicInteger(1);
                player.getLocation().dimension().getWorld().getScheduler().scheduleRepeating(() -> {
                    count.getAndIncrement();
                    player.sendRawMessage("TPS: " + player.getLocation().dimension().getWorld().getTps());
                    return count.get() <= 20;
                }, 20);
            }
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.TEXT;
    }
}
