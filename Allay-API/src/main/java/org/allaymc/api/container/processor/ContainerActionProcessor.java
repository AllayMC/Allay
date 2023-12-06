package org.allaymc.api.container.processor;

import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/7/26
 *
 * @author daoge_cmd
 */
public interface ContainerActionProcessor<T extends ItemStackRequestAction> {

    ItemStackRequestActionType getType();

    ActionResponse ERROR_RESPONSE = new ActionResponse(false, List.of());

    /**
     * @param action             action object
     * @param player             the player who sent the action
     * @param currentActionIndex the index of the action in the request
     * @param actions            all actions in the request
     * @param dataPool           a map that can be used to store data between actions
     * @return the response to the action
     */
    ActionResponse handle(T action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<Object, Object> dataPool);

    default ActionResponse error() {
        return ERROR_RESPONSE;
    }

    default boolean failToValidateStackNetworkId(int expectedSNID, int clientSNID) {
        //若客户端发来的stackNetworkId小于0，说明客户端保证数据无误并要求遵从服务端的数据
        //这通常发生在当一个ItemStackRequest中有多个action时且多个action有相同的source/destination container
        //第一个action检查完id后后面的action就不需要重复检查了
        return clientSNID > 0 && expectedSNID != clientSNID;
    }
}
