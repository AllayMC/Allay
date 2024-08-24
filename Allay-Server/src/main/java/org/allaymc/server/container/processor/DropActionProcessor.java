package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DropAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.Collections;
import java.util.Map;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_STACK;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Slf4j
public class DropActionProcessor implements ContainerActionProcessor<DropAction> {
    @Override
    public ActionResponse handle(DropAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<Object, Object> dataPool) {
        var container = player.getReachableContainerBySlotType(action.getSource().getContainerName().getContainer());
        var count = action.getCount();
        var slot = container.fromNetworkSlotIndex(action.getSource().getSlot());

        var item = container.getItemStack(slot);
        if (failToValidateStackNetworkId(item.getStackNetworkId(), action.getSource().getStackNetworkId())) {
            log.warn("mismatch stack network id!");
            return error();
        }

        if (item == AIR_STACK) {
            log.warn("cannot throw an air!");
            return error();
        }

        if (item.getCount() < count) {
            log.warn("cannot throw more items than the current amount!");
            return error();
        }

        player.forceDropItem(container, slot, count);
        item = container.getItemStack(slot);
        return new ActionResponse(
                true,
                Collections.singletonList(
                        new ItemStackResponseContainer(
                                container.getSlotType(slot),
                                Collections.singletonList(
                                        new ItemStackResponseSlot(
                                                container.toNetworkSlotIndex(slot),
                                                container.toNetworkSlotIndex(slot),
                                                item.getCount(),
                                                item.getStackNetworkId(),
                                                item.getCustomName(),
                                                item.getDurability()
                                        )
                                ),
                                new FullContainerName(container.getSlotType(slot), 0)
                        )
                )
        );
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.DROP;
    }
}
