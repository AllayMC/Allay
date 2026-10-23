package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.MineBlockAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.Collections;
import java.util.Map;

/**
 * {@code MineBlockActionProcessor} reconciles a mining request's held slot and stack
 * identity with server inventory state. The processor reports the current held stack and
 * damage without performing mining or item damage, and a durability prediction mismatch
 * is logged without rejecting that report.
 *
 * @author daoge_cmd
 */
@Slf4j
public class MineBlockActionProcessor implements ContainerActionProcessor<MineBlockAction> {
    /**
     * Reports the server's held stack after validating the selected hotbar slot and
     * positive stack ID. A predicted-durability mismatch only logs a warning; the
     * successful response still contains the server's current count, identity, name, and
     * damage.
     */
    @Override
    public ActionResponse handle(MineBlockAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getControlledEntity().getContainer(ContainerTypes.INVENTORY);
        int handSlot = player.getControlledEntity().getHandSlot();
        if (handSlot != action.hotbarSlot()) {
            log.warn("The held Item Index on the server side does not match the client side!");
            return error();
        }

        var itemInHand = container.getItemInHand();
        if (failToValidateStackUniqueId(itemInHand.getUniqueId(), action.stackNetworkId())) {
            log.warn("mismatch source stack unique id!");
            return error();
        }

        // Durability is authoritative on the server; a prediction mismatch still
        // returns that state so the client can reconcile without rejecting the mining
        // acknowledgement.
        if (itemInHand.getDamage() != action.predictedDurability()) {
            log.warn("Durability predicted by the client does not match that of the server! client: {}, server: {}, player: {}", action.predictedDurability(), itemInHand.getDamage(), player.getOriginName());
        }
        return new ActionResponse(
                true,
                Collections.singletonList(
                        new ItemStackResponseContainer(
                                ContainerActionProcessor.getSlotType(container, handSlot),
                                Collections.singletonList(
                                        new ItemStackResponseSlot(
                                                ContainerActionProcessor.toNetworkSlotIndex(container, handSlot),
                                                ContainerActionProcessor.toNetworkSlotIndex(container, handSlot),
                                                itemInHand.getCount(),
                                                itemInHand.getUniqueId(),
                                                itemInHand.getCustomName(),
                                                itemInHand.getDamage(),
                                                ""
                                        )
                                ),
                                new FullContainerName(ContainerActionProcessor.getSlotType(container, handSlot), null)
                        )
                )
        );
    }

    /**
     * Returns the stack-request action type dispatched to {@code
     * MineBlockActionProcessor}.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.MINE_BLOCK;
    }
}
