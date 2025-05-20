package org.allaymc.server.network.processor.impl.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerBlockPickEvent;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.BlockPickRequestPacket;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Cool_Loong
 */
@Slf4j
public class BlockPickRequestPacketProcessor extends PacketProcessor<BlockPickRequestPacket> {
    @Override
    public void handleSync(EntityPlayer player, BlockPickRequestPacket packet, long receiveTime) {
        var blockPos = MathUtils.CBVecToJOMLVec(packet.getBlockPosition());
        if (!player.canReachBlock(blockPos) || player.getGameType() != GameType.CREATIVE) {
            return;
        }

        var block = new BlockStateWithPos(blockPos, player.getDimension());
        if (block.getBlockType() == BlockTypes.AIR) {
            log.warn("Player {} tried to pick air!", player.getOriginName());
            return;
        }

        var item = block.toItemStack();

        var event = new PlayerBlockPickEvent(player, block, packet.isAddUserData(), item);
        if (!event.call()) {
            return;
        }

        item = event.getItemBlock();
        if (event.isIncludeBlockEntity()) {
            var blockEntity = block.getBlockEntity();
            if (blockEntity != null) {
                item.setBlockEntityNBT(blockEntity.saveNBT());
                item.setLore(List.of("+(DATA)"));
            }
        }

        var inventory = player.getContainer(FullContainerType.PLAYER_INVENTORY);
        // Step 1: Search for an existing item in the hotbar
        for (int slot = 0; slot < 9; slot++) {
            var hotBarItem = inventory.getItemStack(slot);
            if (hotBarItem.getItemType() == item.getItemType()) {
                player.setHandSlot(slot);
                return;
            }
        }

        // Step 2: Search in the main inventory and swap
        for (int slot = 9; slot < FullContainerType.PLAYER_INVENTORY.size(); slot++) {
            var hotBarItem = inventory.getItemStack(slot);
            if (hotBarItem.getItemType() == item.getItemType()) {
                var emptySlot = findFirstEmptyHotbarSlot(inventory);
                if (emptySlot != -1) {
                    inventory.setItemStack(emptySlot, hotBarItem);
                    inventory.setItemStack(slot, ItemAirStack.AIR_STACK);
                    player.setHandSlot(emptySlot);
                } else {
                    var handSlot = player.getHandSlot();
                    inventory.setItemStack(slot, inventory.getItemStack(handSlot));
                    inventory.setItemStack(handSlot, hotBarItem);
                }
                return;
            }
        }

        // Step 3: If the item is not in the inventory — place it
        var emptySlot = findFirstEmptyHotbarSlot(inventory);
        if (emptySlot != -1) {
            inventory.setItemStack(emptySlot, item);
            player.setHandSlot(emptySlot);
        } else {
            var handSlot = player.getHandSlot();
            inventory.setItemStack(handSlot, item);
        }
    }

    private int findFirstEmptyHotbarSlot(BaseContainer container) {
        return IntStream.range(0, 9).filter(container::isEmpty).findFirst().orElse(-1);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_PICK_REQUEST;
    }
}
