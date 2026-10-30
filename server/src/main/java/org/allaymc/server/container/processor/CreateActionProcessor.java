package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CreateAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Map;

import static org.allaymc.server.container.processor.CraftRecipeActionProcessor.RECIPE_OUTPUTS_DATA_KEY;

/**
 * {@code CreateActionProcessor} stages one selected output from a previously validated
 * multi-output recipe. The request-local data pool connects recipe selection to this
 * action, and the created-output slot retains the selected stack reference for
 * subsequent transfer or consumption.
 *
 * @author daoge_cmd
 */
@Slf4j
public class CreateActionProcessor implements ContainerActionProcessor<CreateAction> {
    /**
     * Installs the selected request-local recipe output in created-output storage and
     * returns {@code null}. Missing output data returns an error; the supplied output
     * index must be valid, and the selected stack is retained without copying before
     * later actions use it.
     */
    @Override
    public ActionResponse handle(CreateAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var outputs = (ItemStack[]) dataPool.get(RECIPE_OUTPUTS_DATA_KEY);
        if (outputs == null) {
            log.warn("Recipe not found in data pool!");
            return error();
        }

        // Retain the staged recipe stack by reference; full transfers from
        // CREATED_OUTPUT assign a new network ID, while other later actions may still
        // mutate this source.
        var output = outputs[action.slot()];
        player.getControlledEntity().getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, output, false);
        return null;
    }

    /**
     * Returns the stack-request action type dispatched to {@code CreateActionProcessor}.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CREATE;
    }
}
