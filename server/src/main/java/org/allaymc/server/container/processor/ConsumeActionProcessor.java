package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.player.Player;
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
    public ActionResponse handle(ConsumeAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        // We have validated the recipe in CraftRecipeActionProcessor, so here we can believe the client directly
        var sourceContainer = ContainerActionProcessor.getContainerFrom(player, action.getSource().getContainerName());
        var sourceStackNetworkId = action.getSource().getStackNetworkId();
        var slot = ContainerActionProcessor.fromNetworkSlotIndex(sourceContainer, action.getSource().getSlot());

        var count = action.getCount();
        if (count == 0) {
            log.warn("Cannot consume 0 items!");
            return error();
        }

        var item = sourceContainer.getItemStack(slot);
        if (failToValidateStackUniqueId(item.getUniqueId(), sourceStackNetworkId)) {
            log.warn("Mismatch stack unique id!");
            return error();
        }

        if (item.getItemType() == AIR) {
            log.warn("Cannot consume an air!");
            return error();
        }

        if (item.getCount() < count) {
            log.warn("Cannot consume more items than the current amount!");
            return error();
        }

        if (item.getCount() > count) {
            item.setCount(item.getCount() - count);
            sourceContainer.notifySlotChange(slot, false);
        } else {
            item = ItemAirStack.AIR_STACK;
            sourceContainer.clearSlot(slot, false);
        }

        return new ActionResponse(
                true,
                List.of(
                        new ItemStackResponseContainer(
                                ContainerActionProcessor.getSlotType(sourceContainer, slot),
                                List.of(
                                        new ItemStackResponseSlot(
                                                ContainerActionProcessor.toNetworkSlotIndex(sourceContainer, slot),
                                                ContainerActionProcessor.toNetworkSlotIndex(sourceContainer, slot),
                                                item.getCount(),
                                                item.getUniqueId(),
                                                item.getCustomName(),
                                                item.getDamage(),
                                                ""
                                        )
                                ),
                                new FullContainerName(ContainerActionProcessor.getSlotType(sourceContainer, slot), null)
                        )
                )
        );
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CONSUME;
    }
}
