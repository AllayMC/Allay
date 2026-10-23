package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.interfaces.BlockEntityBeacon;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.BeaconContainer;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.BeaconPaymentAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DestroyAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Map;

/**
 * {@code BeaconPaymentActionProcessor} updates beacon effects after validating a payment
 * item and the immediately following destroy action. The beacon payment action leaves
 * item removal to that later action and contributes no successful response entry of its
 * own.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BeaconPaymentActionProcessor implements ContainerActionProcessor<BeaconPaymentAction> {
    /**
     * Applies requested nonzero beacon effects after checking payment and the following
     * destroy action. Invalid payment, a missing or mismatched destroy action, or an
     * absent beacon block entity returns an error. The successful {@code null} result
     * leaves payment consumption to the next action, so later failure does not undo
     * effect changes.
     */
    @Override
    public ActionResponse handle(BeaconPaymentAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getControlledEntity().getContainer(ContainerTypes.BEACON);
        var itemType = container.getBeaconPayment().getItemType();
        if (!itemType.hasItemTag(ItemTags.BEACON_PAYMENT)) {
            log.warn("Invalid item type for beacon payment: {}", itemType.getIdentifier());
            return error();
        }

        if (actions.length == currentActionIndex + 1 ||
            !(actions[currentActionIndex + 1] instanceof DestroyAction destroyAction)) {
            log.warn("Destroy action not found after beacon payment action");
            return error();
        }

        if (!validateDestoryAction(container, destroyAction)) {
            log.warn("Invalid destroy action");
            return error();
        }

        var blockPos = container.getBlockPos();
        if (!(blockPos.dimension().getBlockEntity(blockPos) instanceof BlockEntityBeacon blockEntityBeacon)) {
            log.warn("Beacon block entity not found at {}", blockPos);
            return error();
        }

        if (action.primaryEffect() != 0) {
            blockEntityBeacon.setPrimaryEffect(Registries.EFFECTS.getByK1(action.primaryEffect()));
        }
        if (action.secondaryEffect() != 0) {
            blockEntityBeacon.setSecondaryEffect(Registries.EFFECTS.getByK1(action.secondaryEffect()));
        }
        return null;
    }

    /**
     * Returns whether the following destroy removes one item from the beacon payment
     * category and translated payment slot. This check does not validate the destroy
     * action's stack ID; its handler performs that separately.
     */
    protected boolean validateDestoryAction(Container container, DestroyAction destroyAction) {
        var source = destroyAction.source();
        return destroyAction.count() == 1 &&
               source.containerName().container() == ContainerSlotType.BEACON_PAYMENT &&
               ContainerActionProcessor.fromNetworkSlotIndex(container, source.slot()) == BeaconContainer.BEACON_PAYMENT_SLOT;
    }

    /**
     * Returns the stack-request action type dispatched to {@code
     * BeaconPaymentActionProcessor}.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.BEACON_PAYMENT;
    }
}
