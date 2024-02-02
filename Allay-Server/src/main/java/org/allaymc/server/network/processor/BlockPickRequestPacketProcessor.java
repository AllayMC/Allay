package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.interfaces.BlockAirBehavior;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.BlockPickRequestPacket;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class BlockPickRequestPacketProcessor extends PacketProcessor<BlockPickRequestPacket> {
    @Override
    public void handle(EntityPlayer player, BlockPickRequestPacket pk) {
        if (player.getGameType() != GameType.CREATIVE) {
            log.warn("Player " + player.getOriginName() + " tried to pick block in non-creative mode!");
            return;
        }
        var pos = pk.getBlockPosition();
        // TODO: includeBlockEntityData
        var includeBlockEntityData = pk.isAddUserData();
        var block = player.getLocation().dimension().getBlockState(pos.getX(), pos.getY(), pos.getZ());
        if (block.getBlockType() == BlockAirBehavior.AIR_TYPE) {
            log.warn("Player " + player.getOriginName() + " tried to pick air!");
            return;
        }
        var item = block.toItemStack();
        item.setCount(item.getItemAttributes().maxStackSize());
        var inventory = player.getContainer(FullContainerType.PLAYER_INVENTORY);
        // Foreach hot bar
        int minEmptySlot = -1;
        boolean success = false;
        for (int slot = 0; slot <= 9; slot++) {
            if (inventory.isEmpty(slot) && minEmptySlot == -1) {
                minEmptySlot = slot;
                continue;
            }
            var hotBarItem = inventory.getItemStack(slot);
            if (hotBarItem.canMerge(item)) {
                hotBarItem.setCount(hotBarItem.getItemAttributes().maxStackSize());
                inventory.onSlotChange(slot);
                success = true;
            }
        }
        if (!success) {
            if (minEmptySlot != -1) {
                inventory.setItemStack(minEmptySlot, item);
            } else {
                // Hot bar is full
                inventory.setItemInHand(item);
            }
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_PICK_REQUEST;
    }
}
