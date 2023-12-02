package org.allaymc.api.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.item.recipe.CraftingRecipe;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ConsumeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.allaymc.api.container.FullContainerType.*;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftRecipeActionProcessor implements ContainerActionProcessor<CraftRecipeAction> {

    public static final String RECIPE_DATA_KEY = "recipe";

    @Override
    public ActionResponse handle(CraftRecipeAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<Object, Object> dataPool) {
        var craftingGridContainer = player.getContainer(CRAFTING_GRID);
        var recipe = RecipeRegistry.getRegistry().getRecipeByNetworkId(action.getRecipeNetworkId());
        var input = craftingGridContainer.createCraftingInput();
        var matched = recipe.match(input);
        if (!matched) {
            log.warn("Mismatched recipe! Network id: " + recipe.getNetworkId());
            return error();
        } else {
            dataPool.put(RECIPE_DATA_KEY, recipe);
            // Validate the consume action count which client sent
            // 还有一部分检查被放在了ConsumeActionProcessor里面（例如消耗物品数量检查）
            var consumeActions = findAllConsumeActions(actions, currentActionIndex + 1);
            var consumeActionCountNeeded = craftingGridContainer.calculateShouldConsumedItemCount();
            if (consumeActions.size() != consumeActionCountNeeded) {
                log.warn("Mismatched consume action count! Expected: " + consumeActionCountNeeded + ", Actual: " + consumeActions.size());
                return error();
            }
        }
        return null;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE;
    }

    protected List<ConsumeAction> findAllConsumeActions(ItemStackRequestAction[] actions, int startIndex) {
        var found = new ArrayList<ConsumeAction>();
        for (int i = startIndex; i < actions.length; i++) {
            var action = actions[i];
            if (action instanceof ConsumeAction consumeAction) {
                found.add(consumeAction);
            }
        }
        return found;
    }
}
