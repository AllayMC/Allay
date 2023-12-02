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

import static org.allaymc.api.container.FullContainerType.*;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftRecipeActionProcessor implements ContainerActionProcessor<CraftRecipeAction> {

    @Override
    public ActionResponse handle(CraftRecipeAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions) {
        var craftingGridContainer = player.getContainer(CRAFTING_GRID);
        var recipe = RecipeRegistry.getRegistry().getRecipeByNetworkId(action.getRecipeNetworkId());
        // The recipe should be a crafting recipe
        CraftingRecipe craftingRecipe;
        if (recipe instanceof CraftingRecipe c) {
            craftingRecipe = c;
        } else {
            log.warn("The recipe should be a crafting recipe! Actual recipe's network id: " + recipe.getNetworkId());
            return error();
        }
        var input = craftingGridContainer.createCraftingInput();
        var matched = craftingRecipe.match(input);
        if (!matched) {
            log.warn("Mismatched recipe! Network id: " + craftingRecipe.getNetworkId());
            return error();
        } else {
            // Validate the consume action count which client sent
            // 还有一部分检查被放在了ConsumeActionProcessor里面（例如消耗物品数量检查）
            var consumeActions = findAllConsumeActions(actions, currentActionIndex + 1);
            var consumeActionCountNeeded = craftingGridContainer.calculateShouldConsumedItemCount();
            if (consumeActions.size() != consumeActionCountNeeded) {
                log.warn("Mismatched consume action count! Expected: " + consumeActionCountNeeded + ", Actual: " + consumeActions.size());
                return error();
            } else {
                // Set first output item to CREATED_OUTPUT
                var createdOutput = player.getContainer(CREATED_OUTPUT);
                var outputs = craftingRecipe.getOutputs();
                var firstOutput = outputs[0];
                createdOutput.setItemStack(0, firstOutput);
                // Multi-outputs is possible, eg: cake recipe
                if (outputs.length > 1) {
                    var playerInventory = player.getContainer(PLAYER_INVENTORY);
                    var isPlayerInvFull = false;
                    for (var i = 1; i < outputs.length; i++) {
                        var moreOutput = outputs[i];
                        if (isPlayerInvFull) {
                            player.dropItemInPlayerPos(moreOutput);
                        } else {
                            if (playerInventory.tryAddItem(moreOutput) == -1) {
                                isPlayerInvFull = true;
                            }
                        }
                    }
                }
                return null;
            }
        }
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
