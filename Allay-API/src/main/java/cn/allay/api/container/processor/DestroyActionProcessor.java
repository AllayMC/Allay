package cn.allay.api.container.processor;

import cn.allay.api.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DestroyAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.Collections;
import java.util.LinkedHashMap;

import static cn.allay.api.container.Container.EMPTY_SLOT_PLACE_HOLDER;
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
    public ItemStackResponse handle(DestroyAction action, Client client, int requestId, LinkedHashMap<ItemStackRequestActionType, ItemStackResponse> chainInfo) {
        if (client.getGameType() != GameType.CREATIVE) {
            log.warn("only Creative Mode can destroy item");
            return error(requestId);
        }
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
            container.onSlotChange(slot);
        } else {
            item = EMPTY_SLOT_PLACE_HOLDER;
            container.setItemStack(slot, item);
        }
        if (chainInfo.containsKey(action.getType())) {
            return chainInfo.get(action.getType());
        }
        return new ItemStackResponse(
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
                                                item.getCustomName(),
                                                item.getDurability()
                                        )
                                )
                        )
                )
        );
    }
}
