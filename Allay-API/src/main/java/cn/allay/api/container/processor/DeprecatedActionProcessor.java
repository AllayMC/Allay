package cn.allay.api.container.processor;

import cn.allay.api.player.Client;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;

import java.util.List;

/**
 * Allay Project 2023/7/29
 *
 * @author daoge_cmd
 */
public class DeprecatedActionProcessor<T extends ItemStackRequestAction> implements ContainerActionProcessor<T> {

    ItemStackRequestActionType actionType;

    public DeprecatedActionProcessor(ItemStackRequestActionType actionType) {
        this.actionType = actionType;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return actionType;
    }

    @Override
    public List<ItemStackResponse> handle(T action, Client client, int requestId) {
        return List.of();
    }
}
