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
 * {@code TransferItemActionProcessor} moves stack quantities between reachable container
 * slots for place and take requests. The processor translates network slot identities,
 * checks available quantity, destination type and capacity, equipment placement, and
 * positive client IDs before dispatching the cancellable move event.
 *
 * <p>
 * A full move into empty storage normally carries the source stack identity, while a
 * split creates a new identity for the destination stack. Merging retains the occupied
 * destination's identity, and created-output moves receive special identity and response
 * handling. Source changes precede destination changes with ordinary viewer updates
 * suppressed; slot listeners still run, and failures do not roll back earlier mutations.
 *
 * @author daoge_cmd
 */
@Slf4j
public abstract class TransferItemActionProcessor<T extends TransferItemStackRequestAction> implements ContainerActionProcessor<T> {

    /**
     * Transfers the requested count after endpoint, stack, placement, and capacity
     * checks. Fake endpoints dispatch a click and return an error, and cancelling the
     * move event prevents the following source and destination updates.
     *
     * <p>
     * Full moves to empty destinations retain the source ID except when taking created
     * output; split stacks receive a new destination ID, and merges retain the occupied
     * destination ID. Source mutation precedes destination mutation, so listener or
     * later failures can leave partial effects. Created-output sources omit their own
     * response entry; successful destination state is always reported.
     */
    @Override
    public ActionResponse handle(T action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var sourceContainer = ContainerActionProcessor.getContainerFrom(player, action.source().containerName());
        var destinationContainer = ContainerActionProcessor.getContainerFrom(player, action.destination().containerName());

        int sourceSlot = ContainerActionProcessor.fromNetworkSlotIndex(sourceContainer, action.source().slot());
        int sourceStackNetworkId = action.source().stackNetworkId();

        int destinationSlot = ContainerActionProcessor.fromNetworkSlotIndex(destinationContainer, action.destination().slot());
        int destinationStackNetworkId = action.destination().stackNetworkId();

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

        var count = action.count();
        if (sourItem.getCount() < count) {
            log.warn("place an item that has not enough count is not allowed");
            return error();
        }

        var destItem = destinationContainer.getItemStack(destinationSlot);
        if (destItem.getItemType() != AIR && destItem.getItemType() != sourItem.getItemType()) {
            log.warn("place an item to a slot that has a different item is not allowed");
            return error();
        }

        if (!ContainerActionProcessor.canPlaceItemToSlot(destinationContainer, destinationSlot, sourItem)) {
            log.warn("cannot place item {} into restricted slot {}", sourItem.getItemType().getIdentifier(), destinationSlot);
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
            // Clear the source before destination assignment. Listener failures
            // during either notification leave earlier changes applied rather than
            // rolling the transfer back.
            resultSourItem = ItemAirStack.AIR_STACK;
            sourceContainer.setItemStack(sourceSlot, resultSourItem, false);
            if (destItem.getItemType() != AIR) {
                resultDestItem = destItem;
                // An occupied destination keeps its network identity when counts
                // merge, allowing later request actions to continue addressing the
                // same destination stack.
                resultDestItem.setCount(destItem.getCount() + count);
                destinationContainer.notifySlotChange(destinationSlot, false);
            } else {
                // A full move into empty storage normally transfers the existing
                // stack reference and identity rather than creating a separately
                // identified split stack.
                if (sourceContainer.getContainerType() == ContainerTypes.CREATED_OUTPUT) {
                    // Created output receives a fresh identity when moved into
                    // ordinary storage, separating the resulting inventory stack from
                    // the staged crafting output.
                    sourItem = sourItem.copy(true);
                }
                resultDestItem = sourItem;
                destinationContainer.setItemStack(destinationSlot, resultDestItem, false);
            }
        } else {
            // Keep the remaining source under its existing identity before creating
            // or extending the destination; source notification can run listeners
            // before that destination changes.
            resultSourItem = sourItem;
            resultSourItem.setCount(resultSourItem.getCount() - count);
            sourceContainer.notifySlotChange(sourceSlot, false);
            if (destItem.getItemType() != AIR) {
                resultDestItem = destItem;
                resultDestItem.setCount(destItem.getCount() + count);
                destinationContainer.notifySlotChange(destinationSlot, false);
            } else {
                // A split into empty storage creates a distinct destination network
                // identity while the reduced source continues to use its original
                // stack identity.
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

        // Created output is a staging source; report only its destination so the
        // response does not expose a separate created-output slot change.
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
