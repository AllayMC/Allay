package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.container.ContainerItemMoveEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.TransferItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.List;
import java.util.Map;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * Allay Project 2023/7/28
 *
 * @author daoge_cmd
 */
@Slf4j
public abstract class TransferItemActionProcessor<T extends TransferItemStackRequestAction> implements ContainerActionProcessor<T> {

    @Override
    public ActionResponse handle(T action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<Object, Object> dataPool) {
        var source = player.getReachableContainerBySlotType(action.getSource().getContainerName().getContainer());
        var destination = player.getReachableContainerBySlotType(action.getDestination().getContainerName().getContainer());

        int sourceSlot = source.fromNetworkSlotIndex(action.getSource().getSlot());
        int sourceStackNetworkId = action.getSource().getStackNetworkId();

        int destinationSlot = destination.fromNetworkSlotIndex(action.getDestination().getSlot());
        int destinationStackNetworkId = action.getDestination().getStackNetworkId();

        var sourItem = source.getItemStack(sourceSlot);
        if (sourItem.getItemType() == AIR) {
            log.warn("pick an air item is not allowed!");
            return error();
        }

        if (failToValidateStackNetworkId(sourItem.getStackNetworkId(), sourceStackNetworkId)) {
            log.warn("mismatch source stack network id!");
            return error();
        }

        var count = action.getCount();
        if (sourItem.getCount() < count) {
            log.warn("place an item that has not enough count is not allowed");
            return error();
        }

        var destItem = destination.getItemStack(destinationSlot);
        if (destItem.getItemType() != AIR && destItem.getItemType() != sourItem.getItemType()) {
            log.warn("place an item to a slot that has a different item is not allowed");
            return error();
        }

        if (failToValidateStackNetworkId(destItem.getStackNetworkId(), destinationStackNetworkId)) {
            log.warn("mismatch destination stack network id!");
            return error();
        }

        if (destItem.getCount() + count > destItem.getItemData().maxStackSize()) {
            log.warn("destination stack size bigger than the max stack size!");
            return error();
        }

        var event = new ContainerItemMoveEvent(
                source, sourceSlot,
                destination, destinationSlot,
                sourItem.getItemType(), count
        );
        event.call();
        if (event.isCancelled()) {
            return error();
        }

        ItemStack resultSourItem;
        ItemStack resultDestItem;
        if (sourItem.getCount() == count) {
            // Case 1: Take all
            resultSourItem = ItemAirStack.AIR_STACK;
            source.setItemStack(sourceSlot, resultSourItem);
            if (destItem.getItemType() != AIR) {
                resultDestItem = destItem;
                // Destination item is not empty, just add count, and keep the same stack network id
                resultDestItem.setCount(destItem.getCount() + count);
                destination.onSlotChange(destinationSlot);
            } else {
                // Destination item is empty, move the original stack to the new position, and use the source item's stack network id (like changing positions)
                if (source.getContainerType() == FullContainerType.CREATED_OUTPUT) {
                    // HACK: If taken from CREATED_OUTPUT, the server needs to create a new stack network id
                    sourItem = sourItem.copy(true);
                }
                resultDestItem = sourItem;
                destination.setItemStack(destinationSlot, resultDestItem);
            }
        } else {
            // Case 2: Take part of the item
            resultSourItem = sourItem;
            resultSourItem.setCount(resultSourItem.getCount() - count);
            source.onSlotChange(sourceSlot);
            if (destItem.getItemType() != AIR) {
                // Destination item is not empty
                resultDestItem = destItem;
                resultDestItem.setCount(destItem.getCount() + count);
                destination.onSlotChange(destinationSlot);
            } else {
                // Destination item is empty, create a new stack network id for the separated sub-item stack
                resultDestItem = sourItem.copy(true);
                resultDestItem.setCount(count);
                destination.setItemStack(destinationSlot, resultDestItem);
            }
        }

        var destItemStackResponseSlot = new ItemStackResponseContainer(
                destination.getSlotType(destinationSlot),
                List.of(
                        new ItemStackResponseSlot(
                                destination.toNetworkSlotIndex(destinationSlot),
                                destination.toNetworkSlotIndex(destinationSlot),
                                resultDestItem.getCount(),
                                resultDestItem.getStackNetworkId(),
                                resultDestItem.getCustomName(),
                                resultDestItem.getDurability()
                        )
                ),
                new FullContainerName(destination.getSlotType(destinationSlot), 0)
        );
        // No need to respond to CREATED_OUTPUT (mj's strange hack)
        if (source.getContainerType() != FullContainerType.CREATED_OUTPUT) {
            return new ActionResponse(
                    true,
                    List.of(new ItemStackResponseContainer(
                            source.getSlotType(sourceSlot),
                            List.of(
                                    new ItemStackResponseSlot(
                                            source.toNetworkSlotIndex(sourceSlot),
                                            source.toNetworkSlotIndex(sourceSlot),
                                            resultSourItem.getCount(),
                                            resultSourItem.getStackNetworkId(),
                                            resultSourItem.getCustomName(),
                                            resultSourItem.getDurability()
                                    )
                            ),
                            new FullContainerName(source.getSlotType(sourceSlot), 0)
                    ), destItemStackResponseSlot)
            );
        } else {
            return new ActionResponse(true, List.of(destItemStackResponseSlot));
        }
    }
}
