package cn.allay.api.container.processor;

import cn.allay.api.client.Client;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;

import java.util.List;

import static org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus.ERROR;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
public interface ContainerActionProcessor<T extends ItemStackRequestAction> {
    ItemStackRequestActionType getType();

    List<ItemStackResponse> handle(T action, Client client, int requestId);

    default List<ItemStackResponse> error(int requestId) {
        return List.of(new ItemStackResponse(ERROR, requestId, List.of()));
    }
}
