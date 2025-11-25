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
 * @author daoge_cmd
 */
@Slf4j
public class BeaconPaymentActionProcessor implements ContainerActionProcessor<BeaconPaymentAction> {
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

        if (action.getPrimaryEffect() != 0) {
            blockEntityBeacon.setPrimaryEffect(Registries.EFFECTS.getByK1(action.getPrimaryEffect()));
        }
        if (action.getSecondaryEffect() != 0) {
            blockEntityBeacon.setSecondaryEffect(Registries.EFFECTS.getByK1(action.getSecondaryEffect()));
        }
        return null;
    }

    protected boolean validateDestoryAction(Container container, DestroyAction destroyAction) {
        var source = destroyAction.getSource();
        return destroyAction.getCount() == 1 &&
               source.getContainerName().getContainer() == ContainerSlotType.BEACON_PAYMENT &&
               ContainerActionProcessor.fromNetworkSlotIndex(container, source.getSlot()) == BeaconContainer.BEACON_PAYMENT_SLOT;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.BEACON_PAYMENT;
    }
}
