package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.RecipeContainer;
import org.allaymc.api.container.impl.CraftingContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerEnchantItemEvent;
import org.allaymc.api.item.component.ItemTrimComponent;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.impl.CraftingRecipe;
import org.allaymc.api.item.recipe.impl.SmithingTrimRecipe;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.item.enchantment.EnchantmentOptionGenerator;
import org.allaymc.server.registry.InternalRegistries;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ConsumeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author daoge_cmd
 */
@Slf4j
public class CraftRecipeActionProcessor implements ContainerActionProcessor<CraftRecipeAction> {
    public static final String RECIPE_DATA_KEY = "recipe";

    @Override
    public ActionResponse handle(CraftRecipeAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var recipeNetworkId = action.getRecipeNetworkId();
        if (recipeNetworkId >= EnchantmentOptionGenerator.NETWORK_ID_COUNTER_INITIAL_VALUE) {
            return handleEnchantTableRecipe(player, recipeNetworkId);
        } else {
            var recipe = Registries.RECIPES.get(recipeNetworkId);
            var numberOfRequestedCrafts = action.getNumberOfRequestedCrafts();

            if (recipe instanceof CraftingRecipe craftingRecipe) {
                var openedContainer = player.getIdToContainerMap().values().toArray(new Container[0])[0];
                if (openedContainer instanceof RecipeContainer recipeContainer) {
                    var recipeInput = recipeContainer.createRecipeInput();
                    if (!recipe.match(recipeInput)) {
                        log.warn("Mismatched recipe! Network id: {}", recipe.getNetworkId());
                        return error();
                    }
                }

                // Special cases
                ActionResponse error = null;
                var tag = craftingRecipe.getTag();
                if (tag.equalsIgnoreCase("crafting_table")) {
                    error = handleCraftingTable(player, numberOfRequestedCrafts, currentActionIndex, actions);
                } else if (craftingRecipe instanceof SmithingTrimRecipe) {
                    error = handleSmithingTableTrim(player);
                }

                if (error != null) {
                    return error;
                }

                if (craftingRecipe.getOutputs() != null && craftingRecipe.getOutputs().length == 1) {
                    // If the recipe outputs a single item, the client will not send a CreateAction,
                    // so we directly set the output in CREATED_OUTPUT in CraftRecipeAction
                    var output = craftingRecipe.getOutputs()[0].copy(false);
                    output.setCount(output.getCount() * numberOfRequestedCrafts);
                    player.getContainer(FullContainerType.CREATED_OUTPUT).setItemStack(output);
                } else {
                    if (numberOfRequestedCrafts != 1) {
                        log.warn("Number of requested crafts for multi-outputs recipe should be one! Actual: {}", numberOfRequestedCrafts);
                        return error();
                    }
                    // If the recipe outputs multiple items, the client will send a CreateAction, so we will set the output in CREATED_OUTPUT in CreateActionProcessor
                    // Put recipe to data pool so that CreateActionProcessor can get it
                    dataPool.put(RECIPE_DATA_KEY, craftingRecipe);
                }

                return null;
            }

            return error();
        }
    }

    protected ActionResponse handleEnchantTableRecipe(EntityPlayer player, int recipeNetworkId) {
        var data = EnchantmentOptionGenerator.removeEnchantOption(recipeNetworkId);
        if (data == null) {
            log.warn("Can't find enchant table recipe by network id {}", recipeNetworkId);
            return error();
        }

        var enchantTableContainer = player.getOpenedContainer(FullContainerType.ENCHANT_TABLE);
        var inputItem = enchantTableContainer.getInput();
        if (inputItem.getItemType() == ItemTypes.AIR) {
            log.warn("Input item is air!");
            return error();
        }

        List<EnchantmentInstance> enchantments = data.enchantments();
        int requiredLapisLazuliCount = data.requiredLapisLazuliCount();
        int requiredXpLevel = data.requiredXpLevel();
        var event = new PlayerEnchantItemEvent(player, inputItem, enchantments, requiredLapisLazuliCount);
        if (!event.call()) {
            return error();
        }

        enchantments = event.getEnchantments();
        requiredLapisLazuliCount = event.getRequiredLapisLazuliCount();

        if (player.getGameType() != GameType.CREATIVE) {
            var material = enchantTableContainer.getMaterial();
            if (material.getItemType() != ItemTypes.LAPIS_LAZULI || material.getCount() < requiredLapisLazuliCount) {
                log.warn("Not enough lapis lazuli! Need: {}, Current: {}", requiredLapisLazuliCount, enchantTableContainer.getMaterial().getCount());
                return error();
            }

            if (player.getExperienceLevel() < requiredXpLevel) {
                log.warn("Not enough experience level! Need: {}, Current: {}", requiredXpLevel, player.getExperienceLevel());
                return error();
            }
            // Required lapis lazuli count is also the cost of xp level
            player.setExperienceLevel(player.getExperienceLevel() - requiredLapisLazuliCount);
        }

        var enchantedItem = inputItem.copy(true);
        enchantedItem.addEnchantments(enchantments);
        // Copy the enchanted item to CREATED_OUTPUT, and client will send a PlaceAction
        // to move the enchanted item back to the input slot of the enchant table container
        player.getContainer(FullContainerType.CREATED_OUTPUT).setItemStack(enchantedItem);
        player.regenerateEnchantmentSeed();

        return null;
    }

    protected ActionResponse handleCraftingTable(EntityPlayer player, int numberOfRequestedCrafts, int currentActionIndex, ItemStackRequestAction[] actions) {
        CraftingContainer craftingContainer = player.getOpenedContainer(FullContainerType.CRAFTING_TABLE);
        if (craftingContainer == null) {
            // The player is not opening a crafting table, using crafting grid instead
            craftingContainer = player.getContainer(FullContainerType.CRAFTING_GRID);
        }

        // Validate if the player has provided enough ingredients
        var itemStackArray = craftingContainer.getItemStackArray();
        for (int slot = 0; slot < itemStackArray.length; slot++) {
            var ingredient = itemStackArray[slot];
            // Skip empty slot because we have checked item type above
            if (ingredient == ItemAirStack.AIR_STACK) {
                continue;
            }

            if (ingredient.getCount() < numberOfRequestedCrafts) {
                log.warn("Not enough ingredients in slot {}! Expected: {}, Actual: {}", slot, numberOfRequestedCrafts, ingredient.getCount());
                return error();
            }
        }

        // Validate the consume action count which client sent
        // Some checks are also placed in ConsumeActionProcessor (e.g., item consumption count check)
        var consumeActions = findAllConsumeActions(actions, currentActionIndex + 1);
        var consumeActionCountNeeded = craftingContainer.calculateShouldConsumedItemSlotCount();
        if (consumeActions.size() != consumeActionCountNeeded) {
            log.warn("Mismatched consume action count! Expected: {}, Actual: {}", consumeActionCountNeeded, consumeActions.size());
            return error();
        }

        return null;
    }

    protected ActionResponse handleSmithingTableTrim(EntityPlayer player) {
        var container = player.getOpenedContainer(FullContainerType.SMITHING_TABLE);
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

        var trimPattern = InternalRegistries.TRIM_PATTERNS.getContent().stream().filter(pattern ->
                pattern.getItemName().equals(templateItem.getItemType().getIdentifier().toString())
        ).findFirst().orElse(null);
        var trimMaterial = InternalRegistries.TRIM_MATERIALS.getContent().stream().filter(material ->
                material.getItemName().equals(materialItem.getItemType().getIdentifier().toString())
        ).findFirst().orElse(null);

        if (trimPattern == null || trimMaterial == null) {
            log.warn("Trim pattern or material not found");
            return error();
        }

        var result = inputItem.copy();
        if (!(result instanceof ItemTrimComponent trimComponent)) {
            log.warn("Input item is not a trimble item");
            return error();
        }

        trimComponent.trim(trimPattern, trimMaterial);
        player.getContainer(FullContainerType.CREATED_OUTPUT).setItemStack(result);
        return null;
    }

    protected List<ConsumeAction> findAllConsumeActions(ItemStackRequestAction[] actions, int startIndex) {
        return Arrays.stream(actions, startIndex, actions.length)
                .filter(action -> action instanceof ConsumeAction)
                .map(action -> (ConsumeAction) action)
                .toList();
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE;
    }
}
