package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerBlockPickEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.network.processor.PacketProcessor;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.MathUtils;
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
    public void handleSync(EntityPlayer player, BlockPickRequestPacket packet, long receiveTime) {
        var blockPos = MathUtils.CBVecToJOMLVec(packet.getBlockPosition());
        var addUserData = packet.isAddUserData();
        if (!player.canReachBlock(blockPos) || player.getGameType() != GameType.CREATIVE) return;

        var block = player.getLocation().dimension().getBlockState(blockPos);
        if (block.getBlockType() == BlockTypes.AIR) {
            log.warn("Player {} tried to pick air!", player.getOriginName());
            return;
        }

        var item = block.toItemStack();
        // TODO: UserData
        item.setCount(item.getItemData().maxStackSize());

        var event = new PlayerBlockPickEvent(
                player,
                new BlockStateWithPos(block, new Position3i(blockPos, player.getDimension()), 0),
                addUserData,
                item
        );
        Server.getInstance().getEventBus().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        item = event.getItemBlock();

        var inventory = player.getContainer(FullContainerType.PLAYER_INVENTORY);
        // Foreach hot bar
        var minEmptySlot = -1;
        var success = false;
        for (int slot = 0; slot <= 9; slot++) {
            if (inventory.isEmpty(slot) && minEmptySlot == -1) {
                minEmptySlot = slot;
                continue;
            }

            var hotBarItem = inventory.getItemStack(slot);
            if (hotBarItem.canMerge(item)) {
                hotBarItem.setCount(hotBarItem.getItemData().maxStackSize());
                inventory.onSlotChange(slot);
                success = true;
            }
        }

        if (success) return;

        if (minEmptySlot != -1) inventory.setItemStack(minEmptySlot, item);
        else inventory.setItemInHand(item); // Hot bar is full
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_PICK_REQUEST;
    }
}
