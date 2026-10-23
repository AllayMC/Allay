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
 * {@code BlockPickRequestPacketProcessor} selects or creates a picked block item
 * for a creative player within block reach. Air targets are rejected, and a
 * cancellable block-pick event may
 * replace the candidate item or request block-entity data. When requested data
 * exists, it is attached to the candidate with a data marker in its lore.
 *
 * <p>
 * Inventory selection compares item types only, preferring a matching hotbar
 * slot before searching the main inventory. Existing matches keep their current
 * item data even if the event-created candidate carries different metadata.
 * Main-inventory matches move into an empty hotbar slot or swap with the
 * current hand; only a missing type causes the candidate item to be inserted.
 * Multi-slot changes are ordered ordinary container operations without
 * rollback, and a full hotbar replaces the selected hand item when no existing
 * match is found.
 *
 * @author Cool_Loong
 */
@Slf4j
public class BlockPickRequestPacketProcessor extends PacketProcessor<BlockPickRequestPacket> {
    /**
     * Picks a reachable nonair block for a creative player after event
     * approval. The event's candidate item receives optional block-entity NBT
     * before inventory selection, but existing item-type matches take
     * precedence over that candidate's other data.
     *
     * <p>
     * The hotbar is searched first, then main inventory. A main-inventory match
     * moves to an empty hotbar slot or swaps with the current hand; no match
     * inserts the candidate into an empty slot or replaces the hand. These
     * container writes occur sequentially without compensation if a later
     * operation fails.
     */
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
        // Resolve the event before copying block-entity data or changing
        // inventory, so cancellation prevents this handler from applying either
        // operation.
        if (!event.call()) {
            return;
        }

        item = event.getItemBlock();
        if (event.isIncludeBlockEntity()) {
            var blockEntity = block.getBlockEntity();
            if (blockEntity != null) {
                item.setBlockEntityNBT(blockEntity.saveCleanNBT());
                item.setLore(List.of("+(DATA)"));
            }
        }

        var inventory = entity.getContainer(ContainerTypes.INVENTORY);
        // Reuse an existing hotbar item by type before inserting the candidate;
        // its current metadata remains authoritative even if the picked
        // candidate includes different block-entity data.
        for (int slot = 0; slot < 9; slot++) {
            var hotBarItem = inventory.getItemStack(slot);
            if (hotBarItem.getItemType() == item.getItemType()) {
                entity.setHandSlot(slot);
                return;
            }
        }

        // Reuse a matching main-inventory stack before creating another item;
        // move it into an empty hotbar slot or exchange it with the current
        // hand when every hotbar slot is occupied.
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

        // Only a missing item type uses the event-selected candidate; with no
        // empty hotbar position, this path replaces the current hand rather
        // than adding a slot elsewhere.
        var emptySlot = findFirstEmptyHotbarSlot(inventory);
        if (emptySlot != -1) {
            inventory.setItemStack(emptySlot, item);
            entity.setHandSlot(emptySlot);
        } else {
            var handSlot = entity.getHandSlot();
            inventory.setItemStack(handSlot, item);
        }
    }

    /**
     * Returns the first empty slot among the nine hotbar positions. A full
     * hotbar returns minus one, letting the caller choose its current-hand
     * replacement or swap path.
     */
    private int findFirstEmptyHotbarSlot(Container container) {
        return IntStream.range(0, 9).filter(container::isEmpty).findFirst().orElse(-1);
    }

    /**
     * Returns the block pick request packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.BLOCK_PICK_REQUEST;
    }
}
