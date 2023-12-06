package org.allaymc.server.network.processor;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.network.DataPacketProcessor;
import org.allaymc.api.utils.MathUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventorySource;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class InventoryTransactionPacketProcessor extends DataPacketProcessor<InventoryTransactionPacket> {
    protected long spamCheckTime;

    @Override
    public void handle(EntityPlayer player, InventoryTransactionPacket pk) {
        if (pk.getTransactionType() == InventoryTransactionType.ITEM_USE) {
            Vector3ic blockPos = MathUtils.cbVecToJOMLVec(pk.getBlockPosition());
            Vector3fc clickPos = MathUtils.cbVecToJOMLVec(pk.getClickPosition());
            BlockFace blockFace = BlockFace.fromId(pk.getBlockFace());
            var inv = player.getContainer(FullContainerType.PLAYER_INVENTORY);
            var itemStack = inv.getItemInHand();
            var world = player.getLocation().dimension();
            switch (pk.getActionType()) {
                case 0 -> {
                    var placePos = blockFace.offsetPos(blockPos);
                    if (!canInteract()) {
                        var blockState = world.getBlockState(placePos.x(), placePos.y(), placePos.z());
                        world.sendBlockUpdateTo(blockState, placePos.x(), placePos.y(), placePos.z(), 0, player);
                        return;
                    }
                    this.spamCheckTime = System.currentTimeMillis();

                    if (!useItemOn(player, itemStack, blockPos, placePos, clickPos, blockFace)) {
                        //Failed to use the item, send back origin block state to client
                        var w = player.getLocation().dimension();
                        var blockStateClicked = w.getBlockState(blockPos.x(), blockPos.y(), blockPos.z());
                        w.sendBlockUpdateTo(blockStateClicked, blockPos.x(), blockPos.y(), blockPos.z(), 0, player);

                        var blockStateReplaced = w.getBlockState(placePos.x(), placePos.y(), placePos.z());
                        w.sendBlockUpdateTo(blockStateReplaced, placePos.x(), placePos.y(), placePos.z(), 0, player);
                    } else {
                        //Used! Update item slot to client
                        if (itemStack.getCount() != 0) {
                            inv.onSlotChange(inv.getHandSlot());
                        } else {
                            inv.setItemInHand(Container.EMPTY_SLOT_PLACE_HOLDER);
                        }
                    }
                }
            }
        } else if (pk.getTransactionType() == InventoryTransactionType.NORMAL) {
            for (var action : pk.getActions()) {
                if (action.getSource().getType().equals(InventorySource.Type.WORLD_INTERACTION)) {
                    if (action.getSource().getFlag().equals(InventorySource.Flag.DROP_ITEM)) {
                        //Do not ask me why mojang still use the old item transaction pk even the server-auth inv was enabled
                        var count = action.getToItem().getCount();
                        player.tryDropItemInHand(count);
                    }
                }
            }
        }
    }

    protected boolean canInteract() {
        return System.currentTimeMillis() - this.spamCheckTime >= 100;
    }

    private static boolean useItemOn(EntityPlayer player, ItemStack itemStack, Vector3ic blockPos, Vector3ic placePos, Vector3fc clickPos, BlockFace blockFace) {
        var dimension = player.getLocation().dimension();
        var blockStateClicked = dimension.getBlockState(blockPos.x(), blockPos.y(), blockPos.z());
        if (!blockStateClicked.getBehavior().onInteract(player, itemStack, dimension, blockPos, placePos, clickPos, blockFace))
            return itemStack.useItemOn(player, itemStack, dimension, blockPos, placePos, clickPos, blockFace);
        else return true;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INVENTORY_TRANSACTION;
    }
}
