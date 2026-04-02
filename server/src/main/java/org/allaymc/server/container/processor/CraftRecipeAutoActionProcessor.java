package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.RecipeContainer;
import org.allaymc.api.eventbus.event.player.PlayerCraftItemEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.recipe.ShapedRecipe;
import org.allaymc.api.item.recipe.ShapelessRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.NetworkData;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.AutoCraftRecipeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.*;

import static org.allaymc.server.container.processor.CraftRecipeActionProcessor.RECIPE_OUTPUTS_DATA_KEY;

/**
 * @author daoge_cmd
 */
@Slf4j
public class CraftRecipeAutoActionProcessor implements ContainerActionProcessor<AutoCraftRecipeAction> {
    protected final CraftRecipeActionProcessor craftRecipeActionProcessor = new CraftRecipeActionProcessor();

    @Override
    public ActionResponse handle(AutoCraftRecipeAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var recipeNetworkId = action.recipeNetworkId();
        if (recipeNetworkId <= 0 || recipeNetworkId > NetworkData.INDEXED_RECIPES.size()) {
            log.warn("Unknown auto craft recipe network id {}", recipeNetworkId);
            return error();
        }

        var recipe = NetworkData.INDEXED_RECIPES.get(recipeNetworkId - 1);
        if (!(recipe instanceof ShapedRecipe || recipe instanceof ShapelessRecipe)) {
            log.warn("Recipe {} is not supported by auto craft", recipe.getIdentifier());
            return error();
        }

        var craftingContainer = craftRecipeActionProcessor.getCraftingContainer(player);
        if (craftingContainer == null) {
            log.warn("Received a CraftRecipeAutoAction without an available crafting container!");
            return error();
        }

        if (!canAutoCraftInContainer(recipe, craftingContainer)) {
            log.warn("Recipe {} cannot be auto crafted in current container!", recipe.getIdentifier());
            return error();
        }

        var timesCrafted = action.timesCrafted();
        if (timesCrafted < 1) {
            log.warn("Times crafted must be at least one! Actual: {}", timesCrafted);
            return error();
        }

        var expectedIngredients = buildExpectedIngredients(recipe, timesCrafted);
        if (expectedIngredients.isEmpty()) {
            log.warn("Recipe {} has no craftable ingredients!", recipe.getIdentifier());
            return error();
        }

        var consumedItems = collectConsumedItems(player, craftingContainer, actions, currentActionIndex + 1);
        if (consumedItems == null) {
            log.warn("Failed to collect consumed items for recipe {}!", recipe.getIdentifier());
            return error();
        }

        if (consumedItems.size() != expectedIngredients.size()) {
            log.warn("Mismatched consumed item count! Expected: {}, Actual: {}", expectedIngredients.size(), consumedItems.size());
            return error();
        }

        if (!matchConsumedItems(consumedItems, expectedIngredients)) {
            log.warn("Mismatched auto craft ingredients! Recipe identifier: {}", recipe.getIdentifier());
            return error();
        }

        var outputs = recipe.getOutputs();
        var event = new PlayerCraftItemEvent(player.getControlledEntity(), recipe, outputs);
        if (!event.call()) {
            return error();
        }

        if (outputs != null && outputs.length == 1) {
            var output = outputs[0].copy(false);
            output.setCount(output.getCount() * timesCrafted);
            player.getControlledEntity().getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, output, false);
        } else {
            if (timesCrafted != 1) {
                log.warn("Number of requested crafts for multi-outputs recipe should be one! Actual: {}", timesCrafted);
                return error();
            }

            dataPool.put(RECIPE_OUTPUTS_DATA_KEY, outputs);
        }

        return null;
    }

    protected boolean canAutoCraftInContainer(Recipe recipe, RecipeContainer craftingContainer) {
        int craftingDimension = craftingContainer.getContainerType() == ContainerTypes.CRAFTING_TABLE ? 3 : 2;
        int craftingSlots = craftingContainer.getContainerType().getSize();

        if (recipe instanceof ShapedRecipe shaped) {
            if (shaped.getPattern().length > craftingDimension) return false;
            var pattern = shaped.getPattern();
            return (pattern.length == 0 ? 0 : pattern[0].length) <= craftingDimension;
        }

        if (recipe instanceof ShapelessRecipe shapeless) {
            return shapeless.getType() == ShapelessRecipe.Type.CRAFTING &&
                   shapeless.getIngredients().length <= craftingSlots;
        }

        return false;
    }

    protected List<ItemDescriptor> buildExpectedIngredients(Recipe recipe, int timesCrafted) {
        var expectedIngredients = new ArrayList<ItemDescriptor>();

        switch (recipe) {
            case ShapedRecipe shaped -> {
                for (var row : shaped.getPattern()) {
                    for (var key : row) {
                        if (key == ShapedRecipe.EMPTY_KEY_CHAR) {
                            continue;
                        }

                        var descriptor = shaped.getKeys().get(key);
                        for (int i = 0; i < timesCrafted; i++) {
                            expectedIngredients.add(descriptor);
                        }
                    }
                }
            }
            case ShapelessRecipe shapeless -> {
                for (var ingredient : shapeless.getIngredients()) {
                    for (int i = 0; i < timesCrafted; i++) {
                        expectedIngredients.add(ingredient);
                    }
                }
            }
            default -> {
            }
        }

        return expectedIngredients;
    }

    protected List<ItemStack> collectConsumedItems(Player player, RecipeContainer craftingContainer, ItemStackRequestAction[] actions, int startIndex) {
        var consumeActions = craftRecipeActionProcessor.findAllConsumeActions(actions, startIndex);
        if (consumeActions.isEmpty()) {
            log.warn("Missing consume actions for auto craft request!");
            return null;
        }

        Map<Container, Map<Integer, Integer>> consumedSlotCounts = new LinkedHashMap<>();
        for (var consumeAction : consumeActions) {
            var sourceContainer = ContainerActionProcessor.getContainerFrom(player, consumeAction.source().containerName());
            if (sourceContainer == null) {
                log.warn("Cannot find consume source container {}", consumeAction.source().containerName());
                return null;
            }

            if (!isValidConsumeSource(sourceContainer, craftingContainer)) {
                log.warn("Invalid consume source container {}", sourceContainer.getContainerType());
                return null;
            }

            int slot;
            try {
                slot = ContainerActionProcessor.fromNetworkSlotIndex(sourceContainer, consumeAction.source().slot());
            } catch (RuntimeException e) {
                log.warn("Invalid consume source slot {}", consumeAction.source().slot());
                return null;
            }

            var item = sourceContainer.getItemStack(slot);
            if (item.isEmptyOrAir()) {
                log.warn("Cannot consume an empty item in slot {}!", slot);
                return null;
            }

            if (failToValidateStackUniqueId(item.getUniqueId(), consumeAction.source().stackNetworkId())) {
                log.warn("Mismatch source stack unique id!");
                return null;
            }

            var count = consumeAction.count();
            if (count <= 0) {
                log.warn("Cannot consume {} items!", count);
                return null;
            }

            var slotCounts = consumedSlotCounts.computeIfAbsent(sourceContainer, key -> new LinkedHashMap<>());
            var consumedCount = slotCounts.getOrDefault(slot, 0) + count;
            if (consumedCount > item.getCount()) {
                log.warn("Cannot consume more items than the current amount! Expected at most: {}, Actual: {}", item.getCount(), consumedCount);
                return null;
            }

            slotCounts.put(slot, consumedCount);
        }

        var consumedItems = new ArrayList<ItemStack>();
        for (var containerEntry : consumedSlotCounts.entrySet()) {
            var container = containerEntry.getKey();
            for (var slotEntry : containerEntry.getValue().entrySet()) {
                var item = container.getItemStack(slotEntry.getKey()).copy(false);
                item.setCount(1);
                for (int i = 0; i < slotEntry.getValue(); i++) {
                    consumedItems.add(item);
                }
            }
        }

        return consumedItems;
    }

    protected boolean isValidConsumeSource(Container sourceContainer, RecipeContainer craftingContainer) {
        var sourceType = sourceContainer.getContainerType();
        return sourceType == craftingContainer.getContainerType() || sourceType == ContainerTypes.INVENTORY;
    }

    protected boolean matchConsumedItems(List<ItemStack> consumedItems, List<ItemDescriptor> expectedIngredients) {
        int[] matches = new int[expectedIngredients.size()];
        Arrays.fill(matches, -1);

        for (int consumedIndex = 0; consumedIndex < consumedItems.size(); consumedIndex++) {
            if (!tryMatchConsumedItem(consumedItems, expectedIngredients, consumedIndex, new boolean[expectedIngredients.size()], matches)) {
                return false;
            }
        }

        return true;
    }

    protected boolean tryMatchConsumedItem(List<ItemStack> consumedItems, List<ItemDescriptor> expectedIngredients, int consumedIndex, boolean[] visited, int[] matches) {
        var consumedItem = consumedItems.get(consumedIndex);

        for (int expectedIndex = 0; expectedIndex < expectedIngredients.size(); expectedIndex++) {
            if (visited[expectedIndex]) {
                continue;
            }

            if (!expectedIngredients.get(expectedIndex).match(consumedItem)) {
                continue;
            }

            visited[expectedIndex] = true;
            if (matches[expectedIndex] == -1 || tryMatchConsumedItem(consumedItems, expectedIngredients, matches[expectedIndex], visited, matches)) {
                matches[expectedIndex] = consumedIndex;
                return true;
            }
        }

        return false;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE_AUTO;
    }
}
