package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.RecipeContainer;
import org.allaymc.api.container.interfaces.StonecutterContainer;
import org.allaymc.api.eventbus.event.player.PlayerCraftItemEvent;
import org.allaymc.api.eventbus.event.player.PlayerEnchantItemEvent;
import org.allaymc.api.item.component.ItemTrimmableComponent;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.*;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.server.item.enchantment.EnchantmentOptionGenerator;
import org.allaymc.server.item.recipe.ComplexRecipe;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.registry.InternalRegistries;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ConsumeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * {@code CraftRecipeActionProcessor} validates normal crafting requests and stages their
 * results for later stack-request actions. Recipe lookup uses the player's negotiated
 * recipe table, while enchanting options occupy a separate network ID range. Workstation
 * selection determines the input representation and the validation performed before
 * output creation.
 *
 * <p>
 * Single outputs are copied into the created-output container; multiple outputs remain
 * in request-local data for later create actions. Crafting input checks inspect
 * subsequent consume actions but leave actual consumption to their handlers. Enchanting
 * and smithing helpers may apply effects before later validation or event dispatch, so
 * an error does not imply that all earlier state is unchanged.
 *
 * @author daoge_cmd
 */
@Slf4j
public class CraftRecipeActionProcessor implements ContainerActionProcessor<CraftRecipeAction> {

    /**
     * {@code RECIPE_OUTPUTS_DATA_KEY} names the request-local output array shared by
     * recipe validation and subsequent {@code CreateActionProcessor} invocations.
     */
    public static final String RECIPE_OUTPUTS_DATA_KEY = "recipe_outputs";

    /**
     * Validates the requested recipe against the selected workstation and stages outputs
     * for subsequent request actions. Enchanting-range IDs use the dedicated option
     * path; ordinary IDs must resolve to a supported recipe in the player's negotiated
     * table.
     *
     * <p>
     * Matching precedes workstation-specific checks and the craft event. Single outputs
     * are copied with their existing network ID and multiplied by the requested craft
     * count; multi-output recipes require one requested craft and store their output
     * array in {@code dataPool}. Helpers may already have modified created output or
     * other state when a later check or cancellation returns an error.
     */
    @Override
    public ActionResponse handle(CraftRecipeAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var recipeNetworkId = action.recipeNetworkId();
        if (recipeNetworkId >= EnchantmentOptionGenerator.NETWORK_ID_COUNTER_INITIAL_VALUE) {
            return handleEnchantTableRecipe(player, recipeNetworkId);
        }

        var recipe = ((AllayPlayer) player).getProtocol().getData().recipeTable().recipesByNetworkId().get(recipeNetworkId);
        if (recipe == null) {
            log.warn("Unknown craft recipe network id {}", recipeNetworkId);
            return error();
        }
        var isCraftingRecipe = recipe instanceof ShapedRecipe ||
                               recipe instanceof ShapelessRecipe ||
                               recipe instanceof ComplexRecipe;
        if (!(isCraftingRecipe || recipe instanceof SmithingRecipe)) {
            return error();
        }

        var recipeContainer = findRecipeContainer(player, recipe);
        if (recipeContainer == null) {
            log.warn("Received a CraftRecipeAction without an opened recipe container for recipe {}!", recipe.getIdentifier());
            return error();
        }

        var recipeInput = recipeContainer.createRecipeInput();
        if (!recipe.match(recipeInput)) {
            log.warn("Mismatched recipe! Recipe identifier: {}", recipe.getIdentifier());
            return error();
        }

        var numberOfRequestedCrafts = action.numberOfRequestedCrafts();
        ActionResponse error = null;
        if (isCraftingRecipe) {
            error = handleCraftingContainer(recipeContainer, numberOfRequestedCrafts, currentActionIndex, actions);
        } else if (recipe instanceof SmithingTrimRecipe) {
            error = handleSmithingTableTrim(player);
        }

        if (error != null) {
            log.warn("Failed to handle recipe {} for player {}!", recipe.getIdentifier(), player.getOriginName());
            return error;
        }

        var outputs = switch (recipe) {
            // Complex recipes derive output identity from the matched inputs, so use
            // their input-aware output path instead of the static recipe output
            // array.
            case ComplexRecipe complex -> complex.getOutputs(recipeInput);
            default -> recipe.getOutputs();
        };

        var event = new PlayerCraftItemEvent(player.getControlledEntity(), recipe, outputs);
        if (!event.call()) {
            return error();
        }

        if (outputs != null && outputs.length == 1) {
            // A single-output request has no later create action; stage its copied
            // result now so following transfer actions can resolve the created-output
            // source.
            var output = outputs[0].copy(false);
            output.setCount(output.getCount() * numberOfRequestedCrafts);
            player.getControlledEntity().getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, output, false);
        } else {
            if (numberOfRequestedCrafts != 1) {
                log.warn("Number of requested crafts for multi-outputs recipe should be one! Actual: {}", numberOfRequestedCrafts);
                return error();
            }

            // Multi-output requests select individual results through later create
            // actions; retain the output array in this request's data pool for those
            // selections.
            dataPool.put(RECIPE_OUTPUTS_DATA_KEY, outputs);
        }

        return null;
    }

    /**
     * Applies an enchanting option to the opened table input and stages the enchanted
     * copy. Option lookup removes the option before validating the input or dispatching
     * the enchant event, so later failure does not make that option available again.
     *
     * <p>
     * Noncreative players must meet the option's required XP level and the
     * listener-adjusted lapis requirement. The XP deduction equals that lapis count,
     * while later actions perform ingredient consumption. A newly identified enchanted
     * copy enters created output before the enchantment seed regenerates; success
     * contributes no separate action response.
     */
    protected ActionResponse handleEnchantTableRecipe(Player player, int recipeNetworkId) {
        // Consume the option before validation; cancellation or a later resource
        // failure does not restore this network option for another request.
        var data = EnchantmentOptionGenerator.removeEnchantOption(recipeNetworkId);
        if (data == null) {
            log.warn("Can't find enchant table recipe by network id {}", recipeNetworkId);
            return error();
        }

        var enchantTableContainer = player.getOpenedContainer(ContainerTypes.ENCHANT_TABLE);
        var inputItem = enchantTableContainer.getInput();
        if (inputItem.getItemType() == ItemTypes.AIR) {
            log.warn("Input item is air!");
            return error();
        }

        List<EnchantmentInstance> enchantments = data.enchantments();
        int requiredLapisLazuliCount = data.requiredLapisLazuliCount();
        int requiredXpLevel = data.requiredXpLevel();
        var event = new PlayerEnchantItemEvent(player.getControlledEntity(), inputItem, enchantments, requiredLapisLazuliCount);
        if (!event.call()) {
            return error();
        }

        enchantments = event.getEnchantments();
        requiredLapisLazuliCount = event.getRequiredLapisLazuliCount();

        var entity = player.getControlledEntity();
        if (entity.getGameMode() != GameMode.CREATIVE) {
            var material = enchantTableContainer.getMaterial();
            if (material.getItemType() != ItemTypes.LAPIS_LAZULI || material.getCount() < requiredLapisLazuliCount) {
                log.warn("Not enough lapis lazuli! Need: {}, Current: {}", requiredLapisLazuliCount, enchantTableContainer.getMaterial().getCount());
                return error();
            }

            if (entity.getExperienceLevel() < requiredXpLevel) {
                log.warn("Not enough experience level! Need: {}, Current: {}", requiredXpLevel, entity.getExperienceLevel());
                return error();
            }
            // The required XP threshold comes from the option, but the actual level
            // deduction follows the listener-adjusted lapis count used by this
            // operation.
            entity.setExperienceLevel(entity.getExperienceLevel() - requiredLapisLazuliCount);
        }

        var enchantedItem = inputItem.copy(true);
        enchantedItem.addEnchantments(enchantments);
        // Stage the newly identified enchanted stack before regenerating the seed;
        // following place actions return this result to the table input slot.
        entity.getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, enchantedItem, false);
        entity.regenerateEnchantmentSeed();

        return null;
    }

    /**
     * Checks available ingredient counts and the number of subsequent consume actions
     * for a crafting input. A missing container returns an error, and empty input slots
     * do not require consume actions.
     *
     * <p>
     * Each occupied input slot must contain at least {@code numberOfRequestedCrafts}
     * items. The helper compares occupied-slot count with all later consume actions
     * rather than matching those actions to specific input slots or quantities; their
     * handlers still own consumption checks. {@code null} means these checks passed
     * without consuming any stacks.
     */
    protected ActionResponse handleCraftingContainer(RecipeContainer craftingContainer, int numberOfRequestedCrafts, int currentActionIndex, ItemStackRequestAction[] actions) {
        if (craftingContainer == null) {
            log.warn("Received a CraftRecipeAction without an opened crafting container!");
            return error();
        }

        var inputSlots = getCraftingInputSlots(craftingContainer);

        for (int slot : inputSlots) {
            var ingredient = craftingContainer.getItemStack(slot);
            if (ingredient == ItemAirStack.AIR_STACK) {
                continue;
            }

            if (ingredient.getCount() < numberOfRequestedCrafts) {
                log.warn("Not enough ingredients in slot {}! Expected: {}, Actual: {}", slot, numberOfRequestedCrafts, ingredient.getCount());
                return error();
            }
        }

        // Compare occupied inputs with all subsequent consume actions; this count
        // check does not associate individual consume targets or quantities with
        // those inputs.
        var consumeActions = findAllConsumeActions(actions, currentActionIndex + 1);
        int consumeActionCountNeeded = 0;
        for (int slot : inputSlots) {
            if (!craftingContainer.isEmpty(slot)) {
                consumeActionCountNeeded++;
            }
        }
        if (consumeActions.size() != consumeActionCountNeeded) {
            log.warn("Mismatched consume action count! Expected: {}, Actual: {}", consumeActionCountNeeded, consumeActions.size());
            return error();
        }

        return null;
    }

    /**
     * Returns the internal slots considered ingredients for crafting validation.
     * Stonecutter storage exposes only its input slot, while other layouts contribute
     * every index in their declared container size.
     */
    protected int[] getCraftingInputSlots(RecipeContainer container) {
        // Stonecutter result storage is not an ingredient; validate only its input
        // while grid layouts use every declared slot as crafting input.
        if (container.getContainerType() == ContainerTypes.STONECUTTER) {
            return new int[]{StonecutterContainer.INPUT_SLOT};
        }

        int size = container.getContainerType().getSize();
        int[] slots = new int[size];
        for (int i = 0; i < size; i++) {
            slots[i] = i;
        }
        return slots;
    }

    /**
     * Builds trimmed equipment from the opened smithing table's template, input, and
     * material. Missing inputs, unmatched pattern or material metadata, and a
     * nontrimmable copied input return an error; success stages output before the
     * caller's craft event.
     */
    protected ActionResponse handleSmithingTableTrim(Player player) {
        var container = player.getOpenedContainer(ContainerTypes.SMITHING_TABLE);
        if (container == null) {
            log.warn("Received a CraftRecipeActionProcessor without an opened container!");
            return error();
        }

        var templateItem = container.getTemplate();
        var inputItem = container.getInput();
        var materialItem = container.getMaterial();
        if (templateItem.isEmptyOrAir() || inputItem.isEmptyOrAir() || materialItem.isEmptyOrAir()) {
            log.warn("One of input items is empty");
            return error();
        }

        var trimPattern = InternalRegistries.TRIM_PATTERNS
                .getContent()
                .values()
                .stream()
                .filter(pattern -> pattern.itemType() == templateItem.getItemType())
                .findFirst()
                .orElse(null);
        var trimMaterial = InternalRegistries.TRIM_MATERIALS
                .getContent()
                .values()
                .stream()
                .filter(material -> material.itemType() == materialItem.getItemType())
                .findFirst()
                .orElse(null);

        if (trimPattern == null || trimMaterial == null) {
            log.warn("Trim pattern or material not found");
            return error();
        }

        var result = inputItem.copy();
        if (!(result instanceof ItemTrimmableComponent trimComponent)) {
            log.warn("Input item is not a trimble item");
            return error();
        }

        trimComponent.trim(trimPattern, trimMaterial);
        player.getControlledEntity().getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, result, false);
        return null;
    }

    /**
     * Returns consume actions from the supplied start index through the end of the
     * request in encounter order. The result retains the original action references, and
     * {@code startIndex} must be a valid array-range boundary.
     */
    protected List<ConsumeAction> findAllConsumeActions(ItemStackRequestAction[] actions, int startIndex) {
        return Arrays.stream(actions, startIndex, actions.length)
                .filter(action -> action instanceof ConsumeAction)
                .map(action -> (ConsumeAction) action)
                .toList();
    }

    /**
     * Selects the workstation appropriate to the recipe kind. Crafting prefers an opened
     * table before the personal grid, stonecutting and smithing require their open
     * containers, and cartography recipes return {@code null}.
     *
     * <p>
     * Unrecognized kinds fall back to the first recipe-capable open container
     * encountered by {@code findFirstRecipeContainer}. That fallback has no sorted
     * workstation priority, and no eligible open container returns {@code null}.
     */
    protected RecipeContainer findRecipeContainer(Player player, Recipe recipe) {
        if (recipe instanceof ShapelessRecipe shapeless) {
            return switch (shapeless.getType()) {
                case STONECUTTER -> player.getOpenedContainer(ContainerTypes.STONECUTTER);
                case CRAFTING -> getCraftingContainer(player);
                case CARTOGRAPHY_TABLE -> null;
            };
        }

        if (recipe instanceof ShapedRecipe || recipe instanceof ComplexRecipe) {
            return getCraftingContainer(player);
        }

        if (recipe instanceof SmithingRecipe) {
            return player.getOpenedContainer(ContainerTypes.SMITHING_TABLE);
        }

        // Unrecognized recipe kinds fall back to the first recipe-capable open
        // container encountered; the open set supplies no sorted workstation
        // priority for this selection.
        return findFirstRecipeContainer(player);
    }

    /**
     * Returns the opened crafting table when present, otherwise the player's held
     * personal crafting grid. The fallback does not require an explicitly opened
     * inventory window before exposing that grid.
     */
    protected RecipeContainer getCraftingContainer(Player player) {
        RecipeContainer craftingContainer = player.getOpenedContainer(ContainerTypes.CRAFTING_TABLE);
        if (craftingContainer != null) {
            return craftingContainer;
        }

        // The personal grid remains available without an opened crafting-table
        // window, so recipe resolution falls back to the player's held grid storage.
        return player.getControlledEntity().getContainer(ContainerTypes.CRAFTING_GRID);
    }

    /**
     * Returns the first recipe-capable container encountered among the player's open
     * containers. No match returns {@code null}, and the open-container iteration order
     * does not establish a workstation priority.
     */
    protected RecipeContainer findFirstRecipeContainer(Player player) {
        for (var container : player.getOpenedContainers()) {
            if (container instanceof RecipeContainer recipeContainer) {
                return recipeContainer;
            }
        }
        return null;
    }

    /**
     * Returns the normal recipe-crafting action type handled by this processor.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE;
    }
}
