package cn.allay.api.container.processor;

import cn.allay.api.container.Container;
import cn.allay.api.item.type.CreativeItemRegistry;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftCreativeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;

import java.util.List;

import static org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus.OK;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftCreativeActionProcessor implements ContainerActionProcessor {
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_CREATIVE;
    }

    public List<ItemStackResponse> handle(CraftCreativeAction action, int requestId, Container destination, int slot) {
        var item = CreativeItemRegistry.getRegistry().get(action.getCreativeItemNetworkId());
        if (item == null) {
            log.warn("Unknown creative item network id: {}", action.getCreativeItemNetworkId());
            return error(requestId);
        }
        item = item.copy(true);
        item.setCount(item.getItemAttributes().maxStackSize());
        destination.setItemStack(slot, item);
        //从创造物品栏拿东西不需要响应
        return List.of();
    }
}
