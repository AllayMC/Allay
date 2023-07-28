package cn.allay.api.container.processor;

import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

/**
 * Allay Project 2023/7/28
 *
 * @author daoge_cmd
 */
public class TakeActionProcessor extends TransferItemActionProcessor {
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.TAKE;
    }
}
