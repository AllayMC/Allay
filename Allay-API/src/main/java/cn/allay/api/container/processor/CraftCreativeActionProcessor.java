package cn.allay.api.container.processor;

import cn.allay.api.client.Client;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.item.type.CreativeItemRegistry;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftCreativeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;

import java.util.LinkedHashMap;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftCreativeActionProcessor implements ContainerActionProcessor<CraftCreativeAction> {
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_CREATIVE;
    }

    @Override
    public ItemStackResponse handle(CraftCreativeAction action, Client client, int requestId, LinkedHashMap<ItemStackRequestActionType, ItemStackResponse> chainInfo) {
        var item = CreativeItemRegistry.getRegistry().get(action.getCreativeItemNetworkId() - 1);
        if (item == null) {
            log.warn("Unknown creative item network id: {}", action.getCreativeItemNetworkId() - 1);
            return error(requestId);
        }
        item = item.copy(true);
        item.setCount(item.getItemAttributes().maxStackSize());
        client.getPlayerEntity().getContainer(FullContainerType.CREATED_OUTPUT).setItemStack(0, item);
        //从创造物品栏拿东西不需要响应
        return null;
    }
}
