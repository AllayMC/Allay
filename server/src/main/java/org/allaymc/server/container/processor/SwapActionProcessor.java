package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
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
    public ActionResponse handle(SwapAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var sourceContainer = ContainerActionProcessor.getContainerFrom(player, action.source().containerName());
        var destinationContainer = ContainerActionProcessor.getContainerFrom(player, action.destination().containerName());

        var sourceSlot = ContainerActionProcessor.fromNetworkSlotIndex(sourceContainer, action.source().slot());
        var destinationSlot = ContainerActionProcessor.fromNetworkSlotIndex(destinationContainer, action.destination().slot());

        if (ContainerActionProcessor.tryHandleFakeContainer(sourceContainer, sourceSlot, destinationContainer, destinationSlot)) {
            return error();
        }

        var sourceItem = sourceContainer.getItemStack(sourceSlot);
        if (failToValidateStackUniqueId(sourceItem.getUniqueId(), action.source().stackNetworkId())) {
            log.warn("mismatch stack unique id!");
            return error();
        }

        var destinationItem = destinationContainer.getItemStack(destinationSlot);
        if (failToValidateStackUniqueId(destinationItem.getUniqueId(), action.destination().stackNetworkId())) {
            log.warn("mismatch stack unique id!");
            return error();
        }

        if (!ContainerActionProcessor.canPlaceItemToSlot(destinationContainer, destinationSlot, sourceItem)) {
            log.warn("cannot place item {} into restricted slot {}", sourceItem.getItemType().getIdentifier(), destinationSlot);
            return error();
        }

        if (!ContainerActionProcessor.canPlaceItemToSlot(sourceContainer, sourceSlot, destinationItem)) {
            log.warn("cannot place item {} into restricted slot {}", destinationItem.getItemType().getIdentifier(), sourceSlot);
            return error();
        }

        sourceContainer.setItemStack(sourceSlot, destinationItem, false);
        destinationContainer.setItemStack(destinationSlot, sourceItem, false);
        return new ActionResponse(
                true,
                List.of(
                        new ItemStackResponseContainer(
                                ContainerActionProcessor.getSlotType(sourceContainer, sourceSlot),
                                Collections.singletonList(
                                        new ItemStackResponseSlot(
                                                ContainerActionProcessor.toNetworkSlotIndex(sourceContainer, sourceSlot),
                                                ContainerActionProcessor.toNetworkSlotIndex(sourceContainer, sourceSlot),
                                                destinationItem.getCount(),
                                                destinationItem.getUniqueId(),
                                                destinationItem.getCustomName(),
                                                destinationItem.getDamage(),
                                                ""
                                        )
                                ),
                                new FullContainerName(ContainerActionProcessor.getSlotType(sourceContainer, sourceSlot), null)
                        ),
                        new ItemStackResponseContainer(
                                ContainerActionProcessor.getSlotType(destinationContainer, destinationSlot),
                                Collections.singletonList(
                                        new ItemStackResponseSlot(
                                                ContainerActionProcessor.toNetworkSlotIndex(destinationContainer, destinationSlot),
                                                ContainerActionProcessor.toNetworkSlotIndex(destinationContainer, destinationSlot),
                                                sourceItem.getCount(),
                                                sourceItem.getUniqueId(),
                                                sourceItem.getCustomName(),
                                                sourceItem.getDamage(),
                                                ""
                                        )
                                ),
                                new FullContainerName(ContainerActionProcessor.getSlotType(destinationContainer, destinationSlot), null)
                        )
                )
        );
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.SWAP;
    }
}
