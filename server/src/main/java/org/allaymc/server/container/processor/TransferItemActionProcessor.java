package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.eventbus.event.container.ContainerItemMoveEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.TransferItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.List;
import java.util.Map;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * @author daoge_cmd
 */
@Slf4j
public abstract class TransferItemActionProcessor<T extends TransferItemStackRequestAction> implements ContainerActionProcessor<T> {

    @Override
    public ActionResponse handle(T action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var sourceContainer = ContainerActionProcessor.getContainerFrom(player, action.getSource().getContainerName());
        var destinationContainer = ContainerActionProcessor.getContainerFrom(player, action.getDestination().getContainerName());

        int sourceSlot = ContainerActionProcessor.fromNetworkSlotIndex(sourceContainer, action.getSource().getSlot());
        int sourceStackNetworkId = action.getSource().getStackNetworkId();

        int destinationSlot = ContainerActionProcessor.fromNetworkSlotIndex(destinationContainer, action.getDestination().getSlot());
        int destinationStackNetworkId = action.getDestination().getStackNetworkId();

        if (ContainerActionProcessor.tryHandleFakeContainer(sourceContainer, sourceSlot, destinationContainer, destinationSlot)) {
            return error();
        }

        var sourItem = sourceContainer.getItemStack(sourceSlot);
        if (sourItem.getItemType() == AIR) {
            log.warn("pick an air item is not allowed!");
            return error();
        }

        if (failToValidateStackUniqueId(sourItem.getUniqueId(), sourceStackNetworkId)) {
            log.warn("mismatch source stack unique id!");
            return error();
        }

        var count = action.getCount();
        if (sourItem.getCount() < count) {
            log.warn("place an item that has not enough count is not allowed");
            return error();
        }

        var destItem = destinationContainer.getItemStack(destinationSlot);
        if (destItem.getItemType() != AIR && destItem.getItemType() != sourItem.getItemType()) {
            log.warn("place an item to a slot that has a different item is not allowed");
            return error();
        }

        if (failToValidateStackUniqueId(destItem.getUniqueId(), destinationStackNetworkId)) {
            log.warn("mismatch destination stack unique id!");
            return error();
        }

        if (destItem.getCount() + count > destItem.getItemType().getItemData().maxStackSize()) {
            log.warn("destination stack size bigger than the max stack size!");
            return error();
        }

        var event = new ContainerItemMoveEvent(
                sourceContainer, sourceSlot,
                destinationContainer, destinationSlot,
                sourItem.getItemType(), count
        );
        if (!event.call()) {
            return error();
        }

        ItemStack resultSourItem;
        ItemStack resultDestItem;
        if (sourItem.getCount() == count) {
            // Case 1: Take all
            resultSourItem = ItemAirStack.AIR_STACK;
            sourceContainer.setItemStack(sourceSlot, resultSourItem, false);
            if (destItem.getItemType() != AIR) {
                resultDestItem = destItem;
                // Destination item is not empty, just add count, and keep the same stack unique id
                resultDestItem.setCount(destItem.getCount() + count);
                destinationContainer.notifySlotChange(destinationSlot, false);
            } else {
                // Destination item is empty, move the original stack to the new position, and use the source item's stack unique id (like changing positions)
                if (sourceContainer.getContainerType() == ContainerTypes.CREATED_OUTPUT) {
                    // HACK: If taken from CREATED_OUTPUT, the server needs to create a new stack unique id
                    sourItem = sourItem.copy(true);
                }
                resultDestItem = sourItem;
                destinationContainer.setItemStack(destinationSlot, resultDestItem, false);
            }
        } else {
            // Case 2: Take part of the item
            resultSourItem = sourItem;
            resultSourItem.setCount(resultSourItem.getCount() - count);
            sourceContainer.notifySlotChange(sourceSlot, false);
            if (destItem.getItemType() != AIR) {
                // Destination item is not empty
                resultDestItem = destItem;
                resultDestItem.setCount(destItem.getCount() + count);
                destinationContainer.notifySlotChange(destinationSlot, false);
            } else {
                // Destination item is empty, create a new stack unique id for the separated sub-item stack
                resultDestItem = sourItem.copy(true);
                resultDestItem.setCount(count);
                destinationContainer.setItemStack(destinationSlot, resultDestItem, false);
            }
        }

        var destItemStackResponseSlot = new ItemStackResponseContainer(
                ContainerActionProcessor.getSlotType(destinationContainer, destinationSlot),
                List.of(
                        new ItemStackResponseSlot(
                                ContainerActionProcessor.toNetworkSlotIndex(destinationContainer, destinationSlot),
                                ContainerActionProcessor.toNetworkSlotIndex(destinationContainer, destinationSlot),
                                resultDestItem.getCount(),
                                resultDestItem.getUniqueId(),
                                resultDestItem.getCustomName(),
                                resultDestItem.getDamage(),
                                ""
                        )
                ),
                new FullContainerName(ContainerActionProcessor.getSlotType(destinationContainer, destinationSlot), null)
        );

        // Special case: no need to respond to CREATED_OUTPUT
        if (sourceContainer.getContainerType() == ContainerTypes.CREATED_OUTPUT) {
            return new ActionResponse(true, List.of(destItemStackResponseSlot));
        }

        return new ActionResponse(
                true,
                List.of(new ItemStackResponseContainer(
                        ContainerActionProcessor.getSlotType(sourceContainer, sourceSlot),
                        List.of(
                                new ItemStackResponseSlot(
                                        ContainerActionProcessor.toNetworkSlotIndex(sourceContainer, sourceSlot),
                                        ContainerActionProcessor.toNetworkSlotIndex(sourceContainer, sourceSlot),
                                        resultSourItem.getCount(),
                                        resultSourItem.getUniqueId(),
                                        resultSourItem.getCustomName(),
                                        resultSourItem.getDamage(),
                                        ""
                                )
                        ),
                        new FullContainerName(ContainerActionProcessor.getSlotType(sourceContainer, sourceSlot), null)
                ), destItemStackResponseSlot)
        );
    }
}
