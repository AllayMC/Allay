package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.eventbus.event.player.PlayerBlockPickEvent;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessor;
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
    public void handleSync(Player player, BlockPickRequestPacket packet, long receiveTime) {
        var blockPos = NetworkHelper.fromNetwork(packet.getBlockPosition());
        var entity = player.getControlledEntity();
        if (!entity.canReachBlock(blockPos) || entity.getGameMode() != GameMode.CREATIVE) {
            return;
        }

        var block = new Block(entity.getDimension(), blockPos);
        if (block.getBlockType() == BlockTypes.AIR) {
            log.warn("Player {} tried to pick air!", player.getOriginName());
            return;
        }

        var item = block.toItemStack();

        var event = new PlayerBlockPickEvent(entity, block, packet.isAddUserData(), item);
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

        var inventory = entity.getContainer(ContainerTypes.INVENTORY);
        // Step 1: Search for an existing item in the hotbar
        for (int slot = 0; slot < 9; slot++) {
            var hotBarItem = inventory.getItemStack(slot);
            if (hotBarItem.getItemType() == item.getItemType()) {
                entity.setHandSlot(slot);
                return;
            }
        }

        // Step 2: Search in the main inventory and swap
        for (int slot = 9; slot < ContainerTypes.INVENTORY.getSize(); slot++) {
            var hotBarItem = inventory.getItemStack(slot);
            if (hotBarItem.getItemType() == item.getItemType()) {
                var emptySlot = findFirstEmptyHotbarSlot(inventory);
                if (emptySlot != -1) {
                    inventory.setItemStack(emptySlot, hotBarItem);
                    inventory.clearSlot(slot);
                    entity.setHandSlot(emptySlot);
                } else {
                    var handSlot = entity.getHandSlot();
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
            entity.setHandSlot(emptySlot);
        } else {
            var handSlot = entity.getHandSlot();
            inventory.setItemStack(handSlot, item);
        }
    }

    private int findFirstEmptyHotbarSlot(Container container) {
        return IntStream.range(0, 9).filter(container::isEmpty).findFirst().orElse(-1);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_PICK_REQUEST;
    }
}
