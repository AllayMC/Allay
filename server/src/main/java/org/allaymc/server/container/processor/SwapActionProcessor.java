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
 * {@code SwapActionProcessor} exchanges two container stack references while preserving
 * each stack's network identity. It translates network endpoints to internal slots and
 * rejects fake-container movement after invoking the selected click listener.
 *
 * <p>
 * Both source and destination identities and equipment-placement rules are checked
 * before assignment. The source receives the destination stack before the destination
 * receives the source stack, with ordinary viewer sending suppressed for both writes.
 * Slot listeners still run between those writes; a failure there or during response
 * creation can leave an earlier effect applied without rollback.
 *
 * @author daoge_cmd
 */
@Slf4j
public class SwapActionProcessor implements ContainerActionProcessor<SwapAction> {
    /**
     * Swaps the two resolved slot references after validating their identities and
     * placement in both directions. A fake endpoint dispatches its click callback and
     * returns an error without performing the exchange.
     *
     * <p>
     * Source assignment precedes destination assignment, and each setter still invokes
     * slot listeners with {@code send=false}. The two writes preserve the original
     * network IDs but are not transactional; later failures leave earlier writes
     * applied. A successful response translates each resulting slot back to its network
     * category and index.
     */
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

        // Apply the source write before the destination write; slot listeners still
        // run between them, so failures do not roll back the first replacement.
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

    /**
     * Returns the stack-request action type dispatched to {@code SwapActionProcessor}.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.SWAP;
    }
}
