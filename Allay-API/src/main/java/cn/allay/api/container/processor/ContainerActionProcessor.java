package cn.allay.api.container.processor;

import cn.allay.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;

import java.util.LinkedHashMap;
import java.util.List;

import static org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus.ERROR;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
public interface ContainerActionProcessor<T extends ItemStackRequestAction> {
    ItemStackRequestActionType getType();

    ItemStackResponse handle(T action, EntityPlayer player, int requestId, LinkedHashMap<ItemStackRequestActionType, ItemStackResponse> chainInfo);

    default ItemStackResponse error(int requestId) {
        return new ItemStackResponse(ERROR, requestId, List.of());
    }
}
