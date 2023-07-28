package cn.allay.api.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus.ERROR;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
public interface ContainerActionProcessor {
    ItemStackRequestActionType getType();

    default List<ItemStackResponse> error(int requestId) {
        return List.of(new ItemStackResponse(ERROR, requestId, List.of()));
    }
}
