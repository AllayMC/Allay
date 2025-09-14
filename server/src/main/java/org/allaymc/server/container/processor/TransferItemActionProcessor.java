package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerType;
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
 * @author daoge_cmd
 */
@Slf4j
public abstract class TransferItemActionProcessor<T extends TransferItemStackRequestAction> implements ContainerActionProcessor<T> {

    @Override
    public ActionResponse handle(T action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var source = ContainerActionProcessor.getContainerFrom(player, action.getSource().getContainerName());
        var destination = ContainerActionProcessor.getContainerFrom(player, action.getDestination().getContainerName());

        int sourceSlot = ContainerActionProcessor.fromNetworkSlotIndex(source, action.getSource().getSlot());
        int sourceStackNetworkId = action.getSource().getStackNetworkId();

        int destinationSlot = ContainerActionProcessor.fromNetworkSlotIndex(destination, action.getDestination().getSlot());
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

        if (destItem.getCount() + count > destItem.getItemType().getItemData().maxStackSize()) {
            log.warn("destination stack size bigger than the max stack size!");
            return error();
        }

        var event = new ContainerItemMoveEvent(
                source, sourceSlot,
                destination, destinationSlot,
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
            source.setItemStack(sourceSlot, resultSourItem, false);
            if (destItem.getItemType() != AIR) {
                resultDestItem = destItem;
                // Destination item is not empty, just add count, and keep the same stack network id
                resultDestItem.setCount(destItem.getCount() + count);
                destination.notifySlotChange(destinationSlot, false);
            } else {
                // Destination item is empty, move the original stack to the new position, and use the source item's stack network id (like changing positions)
                if (source.getContainerType() == ContainerType.CREATED_OUTPUT) {
                    // HACK: If taken from CREATED_OUTPUT, the server needs to create a new stack network id
                    sourItem = sourItem.copy(true);
                }
                resultDestItem = sourItem;
                destination.setItemStack(destinationSlot, resultDestItem, false);
            }
        } else {
            // Case 2: Take part of the item
            resultSourItem = sourItem;
            resultSourItem.setCount(resultSourItem.getCount() - count);
            source.notifySlotChange(sourceSlot, false);
            if (destItem.getItemType() != AIR) {
                // Destination item is not empty
                resultDestItem = destItem;
                resultDestItem.setCount(destItem.getCount() + count);
                destination.notifySlotChange(destinationSlot, false);
            } else {
                // Destination item is empty, create a new stack network id for the separated sub-item stack
                resultDestItem = sourItem.copy(true);
                resultDestItem.setCount(count);
                destination.setItemStack(destinationSlot, resultDestItem, false);
            }
        }

        var destItemStackResponseSlot = new ItemStackResponseContainer(
                ContainerActionProcessor.getSlotType(destination, destinationSlot),
                List.of(
                        new ItemStackResponseSlot(
                                ContainerActionProcessor.toNetworkSlotIndex(destination, destinationSlot),
                                ContainerActionProcessor.toNetworkSlotIndex(destination, destinationSlot),
                                resultDestItem.getCount(),
                                resultDestItem.getStackNetworkId(),
                                resultDestItem.getCustomName(),
                                resultDestItem.getDamage(),
                                ""
                        )
                ),
                new FullContainerName(ContainerActionProcessor.getSlotType(destination, destinationSlot), null)
        );

        // Special case: no need to respond to CREATED_OUTPUT
        if (source.getContainerType() == ContainerType.CREATED_OUTPUT) {
            return new ActionResponse(true, List.of(destItemStackResponseSlot));
        }

        return new ActionResponse(
                true,
                List.of(new ItemStackResponseContainer(
                        ContainerActionProcessor.getSlotType(source, sourceSlot),
                        List.of(
                                new ItemStackResponseSlot(
                                        ContainerActionProcessor.toNetworkSlotIndex(source, sourceSlot),
                                        ContainerActionProcessor.toNetworkSlotIndex(source, sourceSlot),
                                        resultSourItem.getCount(),
                                        resultSourItem.getStackNetworkId(),
                                        resultSourItem.getCustomName(),
                                        resultSourItem.getDamage(),
                                        ""
                                )
                        ),
                        new FullContainerName(ContainerActionProcessor.getSlotType(source, sourceSlot), null)
                ), destItemStackResponseSlot)
        );
    }
}
