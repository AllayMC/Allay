package cn.allay.api.container.processor;

import cn.allay.api.client.Client;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftResultsDeprecatedAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;

import java.util.LinkedHashMap;

/**
 * Allay Project 2023/7/29
 *
 * @author daoge_cmd
 */
public class CraftResultsDeprecatedActionProcessor implements ContainerActionProcessor<CraftResultsDeprecatedAction> {

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED;
    }

    @Override
    public ItemStackResponse handle(CraftResultsDeprecatedAction action, Client client, int requestId, LinkedHashMap<ItemStackRequestActionType, ItemStackResponse> chainInfo) {
        //Indicate that subsequent destroy action do not return a response
        chainInfo.put(ItemStackRequestActionType.DESTROY, null);
        return null;
    }
}
