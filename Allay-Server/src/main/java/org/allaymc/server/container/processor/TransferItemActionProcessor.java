package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.TransferItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.List;
import java.util.Map;

import static org.allaymc.api.item.type.ItemTypes.AIR_TYPE;

/**
 * Allay Project 2023/7/28
 *
 * @author daoge_cmd
 */
@Slf4j
public abstract class TransferItemActionProcessor<T extends TransferItemStackRequestAction> implements ContainerActionProcessor<T> {

    @Override
    public ActionResponse handle(T action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<Object, Object> dataPool) {
        var source = player.getReachableContainerBySlotType(action.getSource().getContainer());
        var destination = player.getReachableContainerBySlotType(action.getDestination().getContainer());
        int sourceSlot = source.fromNetworkSlotIndex(action.getSource().getSlot());
        int sourceStackNetworkId = action.getSource().getStackNetworkId();
        int destinationSlot = destination.fromNetworkSlotIndex(action.getDestination().getSlot());
        int destinationStackNetworkId = action.getDestination().getStackNetworkId();
        int count = action.getCount();
        var sourItem = source.getItemStack(sourceSlot);
        if (sourItem.getItemType() == AIR_TYPE) {
            log.warn("place an air item is not allowed");
            return error();
        }
        if (failToValidateStackNetworkId(sourItem.getStackNetworkId(), sourceStackNetworkId)) {
            log.warn("mismatch source stack network id!");
            return error();
        }
        if (sourItem.getCount() < count) {
            log.warn("place an item that has not enough count is not allowed");
            return error();
        }
        var destItem = destination.getItemStack(destinationSlot);
        if (destItem.getItemType() != AIR_TYPE && destItem.getItemType() != sourItem.getItemType()) {
            log.warn("place an item to a slot that has a different item is not allowed");
            return error();
        }
        if (failToValidateStackNetworkId(destItem.getStackNetworkId(), destinationStackNetworkId)) {
            log.warn("mismatch destination stack network id!");
            return error();
        }
        if (destItem.getCount() + count > destItem.getItemAttributes().maxStackSize()) {
            log.warn("destination stack size bigger than the max stack size!");
            return error();
        }
        ItemStack resultSourItem;
        ItemStack resultDestItem;
        if (sourItem.getCount() == count) {
            // Scenario 1: Take all items
            resultSourItem = Container.EMPTY_SLOT_PLACE_HOLDER;
            source.setItemStack(sourceSlot, resultSourItem);
            if (destItem.getItemType() != AIR_TYPE) {
                resultDestItem = destItem;
                // If the target item is not empty, simply add the quantity. The target item's network stack id remains unchanged
                resultDestItem.setCount(destItem.getCount() + count);
                destination.onSlotChange(destinationSlot);
            } else {
                // If the target item is empty, move the existing stack to the new position.
                // The network stack id of the target item remains the same as
                // the source item's network stack id (essentially shifting positions).
                if (source.getContainerType() == FullContainerType.CREATED_OUTPUT) {
                    // HACK: If taken from CREATED_OUTPUT, the server needs to create a new network stack id.
                    sourItem = sourItem.copy(true);
                }
                resultDestItem = sourItem;
                destination.setItemStack(destinationSlot, resultDestItem);
            }
        } else {
            // Scenario 2: Take a portion
            resultSourItem = sourItem;
            resultSourItem.setCount(resultSourItem.getCount() - count);
            source.onSlotChange(sourceSlot);
            if (destItem.getItemType() != AIR_TYPE) {
                // If the target item is not empty
                resultDestItem = destItem;
                resultDestItem.setCount(destItem.getCount() + count);
                destination.onSlotChange(destinationSlot);
            } else {
                // If the target item is empty, create a new network stack id for the split-off sub-item stack
                resultDestItem = sourItem.copy(true);
                resultDestItem.setCount(count);
                destination.setItemStack(destinationSlot, resultDestItem);
            }
        }
        var destItemStackResponseSlot =
                new ItemStackResponseContainer(
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
                        )
                );
        // No response is needed for CREATED_OUTPUT (mj's strange hack).
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
                            )
                    ), destItemStackResponseSlot)
            );
        } else {
            return new ActionResponse(
                    true,
                    List.of(destItemStackResponseSlot)
            );
        }
    }

}
