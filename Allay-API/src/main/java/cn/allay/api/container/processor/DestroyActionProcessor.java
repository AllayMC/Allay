package cn.allay.api.container.processor;

import cn.allay.api.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DestroyAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.Collections;
import java.util.List;

import static cn.allay.api.container.Container.AIR_STACK;
import static cn.allay.api.item.interfaces.ItemAirStack.AIR_TYPE;
import static org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus.OK;

/**
 * Allay Project 2023/7/28
 *
 * @author daoge_cmd
 */
@Slf4j
public class DestroyActionProcessor implements ContainerActionProcessor<DestroyAction> {
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.DESTROY;
    }

    @Override
    public List<ItemStackResponse> handle(DestroyAction action, Client client, int requestId) {
        var container = client.getPlayerEntity().getContainerBySlotType(action.getSource().getContainer());
        var count = action.getCount();
        var slot = action.getSource().getSlot();
        var item = container.getItemStack(slot);
        if (item.getStackNetworkId() != action.getSource().getStackNetworkId()) {
            log.warn("mismatch stack network id!");
            return error(requestId);
        }
        if (item.getItemType() == AIR_TYPE) {
            log.warn("cannot destroy an air!");
            return error(requestId);
        }
        if (item.getCount() < count) {
            log.warn("cannot destroy more items than the current amount!");
            return error(requestId);
        }
        if (item.getCount() > count) {
            item.setCount(item.getCount() - count);
            container.onSlotChange(slot, item);
        } else {
            item = AIR_STACK;
            container.setItemStack(slot, item);
        }
        return Collections.singletonList(
                new ItemStackResponse(
                        OK,
                        requestId,
                        Collections.singletonList(
                                new ItemStackResponseContainer(
                                        container.getSlotType(slot),
                                        Collections.singletonList(
                                                new ItemStackResponseSlot(
                                                        slot,
                                                        slot,
                                                        item.getCount(),
                                                        item.getStackNetworkId(),
                                                        "",
                                                        0
                                                )
                                        )
                                )
                        )
                )
        );
    }
}
