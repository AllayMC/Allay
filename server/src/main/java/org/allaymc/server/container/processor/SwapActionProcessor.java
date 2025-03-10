package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.SwapAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author daoge_cmd
 */
@Slf4j
public class SwapActionProcessor implements ContainerActionProcessor<SwapAction> {
    @Override
    public ActionResponse handle(SwapAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var sourceContainer = player.getReachableContainerBySlotType(action.getSource().getContainerName().getContainer());
        var destinationContainer = player.getReachableContainerBySlotType(action.getDestination().getContainerName().getContainer());

        var sourceSlot = sourceContainer.fromNetworkSlotIndex(action.getSource().getSlot());
        var destinationSlot = destinationContainer.fromNetworkSlotIndex(action.getDestination().getSlot());

        var sourceItem = sourceContainer.getItemStack(sourceSlot);
        if (failToValidateStackNetworkId(sourceItem.getStackNetworkId(), action.getSource().getStackNetworkId())) {
            log.warn("mismatch stack network id!");
            return error();
        }

        var destinationItem = destinationContainer.getItemStack(destinationSlot);
        if (failToValidateStackNetworkId(destinationItem.getStackNetworkId(), action.getDestination().getStackNetworkId())) {
            log.warn("mismatch stack network id!");
            return error();
        }

        sourceContainer.setItemStack(sourceSlot, destinationItem, false);
        destinationContainer.setItemStack(destinationSlot, sourceItem, false);
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
                                                destinationItem.getDurability(),
                                                ""
                                        )
                                ),
                                new FullContainerName(sourceContainer.getSlotType(sourceSlot), null)
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
                                                sourceItem.getDurability(),
                                                ""
                                        )
                                ),
                                new FullContainerName(destinationContainer.getSlotType(destinationSlot), null)
                        )
                )
        );
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.SWAP;
    }
}
