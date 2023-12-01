package org.allaymc.api.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import static org.allaymc.api.container.FullContainerType.CRAFTING_TABLE;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftRecipeActionProcessor implements ContainerActionProcessor<CraftRecipeAction> {

    @Override
    public ActionResponse handle(CraftRecipeAction action, EntityPlayer player) {
        var craftingTableContainer = player.getContainer(CRAFTING_TABLE);
        var recipe = RecipeRegistry.getRegistry().getRecipeByNetworkId(action.getRecipeNetworkId());
        var input = craftingTableContainer.createInput();
        var matched = recipe.match(input);
        if (!matched) {
            log.warn("Mismatched recipe! Network id: " + recipe.getNetworkId());
            return error();
        } else {
            // TODO
            return null;
        }
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE;
    }
}
