package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DestroyAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.Collections;
import java.util.Map;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_STACK;
import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * @author daoge_cmd
 */
@Slf4j
public class DestroyActionProcessor implements ContainerActionProcessor<DestroyAction> {
    @Override
    public ActionResponse handle(DestroyAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getContainerBySlotType(action.getSource().getContainerName().getContainer());
        var count = action.getCount();
        var slot = container.fromNetworkSlotIndex(action.getSource().getSlot());

        var item = container.getItemStack(slot);
        if (failToValidateStackNetworkId(item.getStackNetworkId(), action.getSource().getStackNetworkId())) {
            log.warn("mismatch stack network id!");
            return error();
        }

        if (item.getItemType() == AIR) {
            log.warn("cannot destroy an air!");
            return error();
        }

        if (item.getCount() < count) {
            log.warn("cannot destroy more items than the current amount!");
            return error();
        }

        if (item.getCount() > count) {
            item.setCount(item.getCount() - count);
            container.notifySlotChange(slot, false);
        } else {
            item = AIR_STACK;
            container.clearSlot(slot, false);
        }

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
                                                item.getDamage(),
                                                ""
                                        )
                                ),
                                new FullContainerName(container.getSlotType(slot), null)
                        )
                )
        );
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.DESTROY;
    }
}
