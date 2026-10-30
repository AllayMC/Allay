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
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.AutoCraftRecipeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.*;

import static org.allaymc.server.container.processor.CraftRecipeActionProcessor.RECIPE_OUTPUTS_DATA_KEY;

/**
 * {@code CraftRecipeAutoActionProcessor} validates automatic shaped and shapeless
 * crafting against upcoming ingredient consumption rather than a prearranged grid. The
 * player's negotiated table supplies the recipe, and the chosen crafting container
 * limits the allowed recipe dimensions and consume-source containers.
 *
 * <p>
 * Repeated consume actions for one source slot are summed before matching so separate
 * actions cannot independently reuse the same available count. The processor expands
 * those totals into one-item samples and matches them to repeated ingredient descriptors
 * with an augmenting-path assignment. Reassigning earlier matches handles overlapping
 * descriptor predicates that a greedy first match could reject. Actual consumption
 * remains in later request actions.
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftRecipeAutoActionProcessor implements ContainerActionProcessor<AutoCraftRecipeAction> {
    /**
     * Shared crafting-container selection and consume-action lookup helper.
     */
    protected final CraftRecipeActionProcessor craftRecipeActionProcessor = new CraftRecipeActionProcessor();

    /**
     * Validates automatic crafting and stages results after the craft event approves.
     * The recipe ID must resolve to a shaped or shapeless recipe that fits the selected
     * container, and {@code timesCrafted} must be positive.
     *
     * <p>
     * Expected descriptors repeat for every requested craft, while upcoming consume
     * actions supply validated source totals. Their one-to-one match must succeed before
     * output staging. Single output counts are multiplied and staged directly; multiple
     * outputs require one craft and remain in request-local data. Success returns {@code
     * null} without consuming inputs.
     */
    @Override
    public ActionResponse handle(AutoCraftRecipeAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var recipeNetworkId = action.recipeNetworkId();
        if (recipeNetworkId <= 0) {
            log.warn("Unknown auto craft recipe network id {}", recipeNetworkId);
            return error();
        }

        var recipe = ((AllayPlayer) player).getProtocol().getData().recipeTable().recipesByNetworkId().get(recipeNetworkId);
        if (recipe == null) {
            log.warn("Unknown auto craft recipe network id {}", recipeNetworkId);
            return error();
        }
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

    /**
     * Returns whether the recipe fits the selected crafting layout. An opened crafting
     * table permits three rows and columns, other layouts use two, and shapeless recipes
     * must use crafting semantics with no more ingredients than available slots.
     */
    protected boolean canAutoCraftInContainer(Recipe recipe, RecipeContainer craftingContainer) {
        // Automatic crafting uses a three-by-three table or the two-by-two personal
        // grid; recipe dimensions must fit the selected container before ingredient
        // matching.
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

    /**
     * Expands shaped nonempty cells or shapeless ingredients into descriptors repeated
     * for the requested craft count. The result retains descriptor references;
     * unsupported recipe kinds and nonpositive repetition counts contribute no entries.
     */
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

    /**
     * Builds one-item samples for all consume actions remaining after {@code
     * startIndex}. Sources must use the selected crafting type or player inventory,
     * contain nonempty stacks, and request positive counts. Positive client stack IDs
     * must match the stored ID; zero and negative IDs bypass that comparison.
     *
     * <p>
     * Counts accumulate by container and internal slot before comparison with available
     * quantity, preventing repeated actions from independently claiming the same stock.
     * Missing or rejected sources and translation exceptions return {@code null}.
     * Samples preserve source network IDs and repeat the same per-slot sample reference
     * for each consumed item; no source stack is consumed here.
     */
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
                // An unmapped network slot cannot identify a valid consume source;
                // reject collection rather than admitting an ingredient with an
                // unknown storage address.
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
            // Sum repeated claims on this container slot before checking
            // availability, otherwise separate consume actions could each pass
            // against the same original count.
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

    /**
     * Returns whether consumption uses the selected crafting container type or the
     * player inventory type. The check compares type identities, rather than requiring
     * the source object to equal the selected container.
     */
    protected boolean isValidConsumeSource(Container sourceContainer, RecipeContainer craftingContainer) {
        var sourceType = sourceContainer.getContainerType();
        return sourceType == craftingContainer.getContainerType() || sourceType == ContainerTypes.INVENTORY;
    }

    /**
     * Assigns each consumed sample to a distinct matching ingredient descriptor. The
     * caller checks equal list sizes when every expected descriptor must also be
     * covered; this helper only requires a place for every consumed sample.
     *
     * <p>
     * Each sample starts an augmenting-path search with fresh visited markers and the
     * current shared assignment. Earlier samples can move to another eligible descriptor
     * to free a match, avoiding failures caused solely by overlapping predicates and
     * initial iteration order.
     */
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

    /**
     * Finds a descriptor assignment for one consumed sample using the current augmenting
     * path. {@code matches} stores the consumed index assigned to each descriptor, with
     * {@code -1} marking an unused descriptor.
     *
     * <p>
     * A descriptor visited on this search path is skipped to bound recursion. An
     * occupied descriptor can be reassigned only after its previous sample finds another
     * match; successful recursion then installs this sample. {@code visited} and {@code
     * matches} are mutated in place for the caller's search.
     */
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
            // Reassign the prior sample along an augmenting path before claiming its
            // descriptor, so overlapping ingredient predicates do not fail on a
            // greedy earlier choice.
            if (matches[expectedIndex] == -1 || tryMatchConsumedItem(consumedItems, expectedIngredients, matches[expectedIndex], visited, matches)) {
                matches[expectedIndex] = consumedIndex;
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the automatic recipe-crafting action type handled by this processor.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE_AUTO;
    }
}
