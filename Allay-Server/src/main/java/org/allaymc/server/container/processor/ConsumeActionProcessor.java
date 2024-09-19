package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ConsumeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.List;
import java.util.Map;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ConsumeActionProcessor implements ContainerActionProcessor<ConsumeAction> {
    @Override
    public ActionResponse handle(ConsumeAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<Object, Object> dataPool) {
        // We have validated the recipe in CraftRecipeActionProcessor, so here we can believe the client directly
        var sourceContainer = player.getReachableContainerBySlotType(action.getSource().getContainerName().getContainer());
        var sourceStackNetworkId = action.getSource().getStackNetworkId();
        var slot = sourceContainer.fromNetworkSlotIndex(action.getSource().getSlot());

        var count = action.getCount();
        if (count == 0) {
            log.warn("cannot consume 0 items!");
            return error();
        }

        var item = sourceContainer.getItemStack(slot);
        if (failToValidateStackNetworkId(item.getStackNetworkId(), sourceStackNetworkId)) {
            log.warn("mismatch stack network id!");
            return error();
        }

        if (item.getItemType() == AIR) {
            log.warn("cannot consume an air!");
            return error();
        }

        if (item.getCount() < count) {
            log.warn("cannot consume more items than the current amount!");
            return error();
        }

        if (item.getCount() > count) {
            item.setCount(item.getCount() - count);
            sourceContainer.onSlotChange(slot);
        } else {
            item = ItemAirStack.AIR_STACK;
            sourceContainer.clearSlot(slot);
        }

        return new ActionResponse(
                true,
                List.of(
                        new ItemStackResponseContainer(
                                sourceContainer.getSlotType(slot),
                                List.of(
                                        new ItemStackResponseSlot(
                                                sourceContainer.toNetworkSlotIndex(slot),
                                                sourceContainer.toNetworkSlotIndex(slot),
                                                item.getCount(),
                                                item.getStackNetworkId(),
                                                item.getCustomName(),
                                                item.getDurability()
                                        )
                                ),
                                new FullContainerName(sourceContainer.getSlotType(slot), null)
                        )
                )
        );
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CONSUME;
    }
}
