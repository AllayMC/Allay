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
 * @author daoge_cmd
 */
@Slf4j
public class MineBlockActionProcessor implements ContainerActionProcessor<MineBlockAction> {
    @Override
    public ActionResponse handle(MineBlockAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getControlledEntity().getContainer(ContainerTypes.INVENTORY);
        int handSlot = player.getControlledEntity().getHandSlot();
        if (handSlot != action.getHotbarSlot()) {
            log.warn("The held Item Index on the server side does not match the client side!");
            return error();
        }

        var itemInHand = container.getItemInHand();
        if (failToValidateStackUniqueId(itemInHand.getUniqueId(), action.getStackNetworkId())) {
            log.warn("mismatch source stack unique id!");
            return error();
        }

        if (itemInHand.getDamage() != action.getPredictedDurability()) {
            log.warn("Durability predicted by the client does not match that of the server! client: {}, server: {}, player: {}", action.getPredictedDurability(), itemInHand.getDamage(), player.getOriginName());
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

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.MINE_BLOCK;
    }
}
