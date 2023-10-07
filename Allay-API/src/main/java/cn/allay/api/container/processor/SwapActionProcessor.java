package cn.allay.api.container.processor;

import cn.allay.api.client.Client;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.SwapAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;

import java.util.LinkedHashMap;

/**
 * Allay Project 2023/10/7
 *
 * @author daoge_cmd
 */
public class SwapActionProcessor implements ContainerActionProcessor<SwapAction> {
    @Override
    public ItemStackResponse handle(SwapAction action, Client client, int requestId, LinkedHashMap<ItemStackRequestActionType, ItemStackResponse> chainInfo) {
        return null;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.SWAP;
    }
}
