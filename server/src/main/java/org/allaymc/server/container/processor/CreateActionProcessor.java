package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CreateAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Map;

import static org.allaymc.server.container.processor.CraftRecipeActionProcessor.RECIPE_OUTPUTS_DATA_KEY;

/**
 * @author daoge_cmd
 */
@Slf4j
public class CreateActionProcessor implements ContainerActionProcessor<CreateAction> {
    @Override
    public ActionResponse handle(CreateAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var outputs = (ItemStack[]) dataPool.get(RECIPE_OUTPUTS_DATA_KEY);
        if (outputs == null) {
            log.warn("Recipe not found in data pool!");
            return error();
        }

        // No need to copy because when we get item from created output, we will copy it
        var output = outputs[action.getSlot()];
        player.getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, output, false);
        return null;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CREATE;
    }
}
