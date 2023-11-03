package cn.allay.api.container.processor;

import cn.allay.api.container.Container;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DropAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.Collections;

import static cn.allay.api.container.Container.EMPTY_SLOT_PLACE_HOLDER;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Slf4j
public class DropActionProcessor implements ContainerActionProcessor<DropAction> {
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.DROP;
    }

    @Override
    public ActionResponse handle(DropAction action, EntityPlayer player) {
        Container container = player.getReachableContainerBySlotType(action.getSource().getContainer());
        var count = action.getCount();
        var slot = action.getSource().getSlot();
        var item = container.getItemStack(slot);
        if (item.getStackNetworkId() != action.getSource().getStackNetworkId()) {
            log.warn("mismatch stack network id!");
            return error();
        }
        if (item == EMPTY_SLOT_PLACE_HOLDER) {
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
                                                        slot,
                                                        slot,
                                                        item.getCount(),
                                                        item.getStackNetworkId(),
                                                        item.getCustomName(),
                                                        item.getDurability()
                                                )
                                        )
                                )
                        )
        );
//        return new ItemStackResponse(
//                        OK,
//                        requestId,
//        );
    }
}
