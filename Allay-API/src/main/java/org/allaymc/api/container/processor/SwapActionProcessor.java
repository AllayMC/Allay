package org.allaymc.api.container.processor;

import org.allaymc.api.container.Container;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.SwapAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.Collections;
import java.util.List;

/**
 * Allay Project 2023/10/7
 *
 * @author daoge_cmd
 */
@Slf4j
public class SwapActionProcessor implements ContainerActionProcessor<SwapAction> {
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.SWAP;
    }

    @Override
    public ActionResponse handle(SwapAction action, EntityPlayer player) {
        Container sourceContainer = player.getReachableContainerBySlotType(action.getSource().getContainer());
        Container destinationContainer = player.getReachableContainerBySlotType(action.getDestination().getContainer());
        var sourceSlot = sourceContainer.fromNetworkSlotIndex(action.getSource().getSlot());
        var destinationSlot = destinationContainer.fromNetworkSlotIndex(action.getDestination().getSlot());
        var sourceItem = sourceContainer.getItemStack(sourceSlot);
        var destinationItem = destinationContainer.getItemStack(destinationSlot);
        if (sourceItem.getStackNetworkId() != action.getSource().getStackNetworkId()) {
            log.warn("mismatch stack network id!");
            return error();
        }
        if (destinationItem.getStackNetworkId() != action.getDestination().getStackNetworkId()) {
            log.warn("mismatch stack network id!");
            return error();
        }
        sourceContainer.setItemStack(sourceSlot, destinationItem);
        destinationContainer.setItemStack(destinationSlot, sourceItem);
        return new ActionResponse(
                true,
                List.of(
                        new ItemStackResponseContainer(
                                sourceContainer.getSlotType(sourceSlot),
                                Collections.singletonList(
                                        new ItemStackResponseSlot(
                                                sourceContainer.toNetworkSlotIndex(sourceSlot),
                                                sourceContainer.toNetworkSlotIndex(sourceSlot),
                                                destinationItem.getCount(),
                                                destinationItem.getStackNetworkId(),
                                                destinationItem.getCustomName(),
                                                destinationItem.getDurability()
                                        )
                                )
                        ),
                        new ItemStackResponseContainer(
                                destinationContainer.getSlotType(destinationSlot),
                                Collections.singletonList(
                                        new ItemStackResponseSlot(
                                                destinationContainer.toNetworkSlotIndex(destinationSlot),
                                                destinationContainer.toNetworkSlotIndex(destinationSlot),
                                                sourceItem.getCount(),
                                                sourceItem.getStackNetworkId(),
                                                sourceItem.getCustomName(),
                                                sourceItem.getDurability()
                                        )
                                )
                        )
                )
        );
    }
}
