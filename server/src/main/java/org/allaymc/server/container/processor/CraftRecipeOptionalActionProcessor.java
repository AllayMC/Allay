package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.eventbus.event.block.AnvilDamageEvent;
import org.allaymc.api.eventbus.event.container.AnvilTakeResultEvent;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.api.world.sound.SimpleSound;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeOptionalAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author IWareQ
 */
@Slf4j
public class CraftRecipeOptionalActionProcessor implements ContainerActionProcessor<CraftRecipeOptionalAction> {
    public static final String FILTER_STRINGS_DATA_KEY = "filterStrings";

    private static final int ANVIL_MATERIAL_REPAIR_MULTIPLIER = 25;
    private static final int ANVIL_ITEM_REPAIR_MULTIPLIER = 12;

    private static final float ANVIL_DAMAGE_CHANCE = 0.12f;
    private static final int ANVIL_COST_LIMIT = 39;

    @Override
    public ActionResponse handle(CraftRecipeOptionalAction action, Player player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getOpenedContainer(ContainerTypes.ANVIL);
        if (container == null) {
            log.warn("Received a CraftRecipeOptionalAction without an opened container");
            return error();
        }

        var filterStrings = (String[]) dataPool.get(FILTER_STRINGS_DATA_KEY);
        if (filterStrings == null) {
            log.warn("filterStrings not found in the data pool");
            return error();
        }

        if (filterStrings.length < action.getFilteredStringIndex()) {
            log.warn("filterStrings length does not match action.getFilteredStringIndex()");
            return error();
        }

        var inputItem = container.getInput();
        var materialItem = container.getMaterial();
        if (inputItem.isEmptyOrAir() && materialItem.isEmptyOrAir()) {
            log.warn("Input and material item is empty");
            return error();
        }

        var resultItem = inputItem.copy();

        var anvilCost = inputItem.getRepairCost();
        var actionCost = 0;

        if (!materialItem.isEmptyOrAir()) {
            anvilCost += materialItem.getRepairCost();

            var maxDamage = resultItem.getMaxDamage();

            // Case 1: Repair by repairing materials
            if (
                    inputItem instanceof ItemRepairableComponent repairableComponent &&
                    repairableComponent.canBeRepairedBy(materialItem.getItemType())
            ) {
                var delta = Math.min(resultItem.getDamage(), maxDamage * ANVIL_MATERIAL_REPAIR_MULTIPLIER / 100);
                if (delta <= 0) {
                    log.warn("Input item is already fully repaired");
                    return error();
                }

                var newDamage = resultItem.getDamage();
                for (int i = 0; i < materialItem.getCount() && delta > 0; i++) {
                    actionCost++;

                    newDamage = Math.max(0, newDamage - delta);
                    delta = Math.min(newDamage, maxDamage * ANVIL_MATERIAL_REPAIR_MULTIPLIER / 100);
                }

                resultItem.setDamage(newDamage);
            } else {
                // Case 2: Merge equal items
                var isEnchantedBook = materialItem.getItemType() == ItemTypes.ENCHANTED_BOOK;

                // Step 1: Merge durability
                if (!isEnchantedBook && inputItem.getItemType() == materialItem.getItemType()) {
                    actionCost += 2;

                    var bonusDamage = maxDamage * ANVIL_ITEM_REPAIR_MULTIPLIER / 100;
                    var mergedDamage = inputItem.getDamage() + materialItem.getDamage() - bonusDamage;
                    resultItem.setDamage(Math.max(0, mergedDamage - maxDamage));
                }

                // Step 2: Merge enchantments
                boolean hasIncompatible = false;
                boolean hasCompatible = false;
                for (var materialEnchantment : materialItem.getEnchantments()) {
                    var materialEnchantmentType = materialEnchantment.getType();
                    var isApplicable = materialEnchantmentType.canBeAppliedTo(inputItem.getItemType());
                    if (inputItem.getItemType() == ItemTypes.ENCHANTED_BOOK) {
                        isApplicable = true;
                    }

                    for (var inputEnchantment : inputItem.getEnchantments()) {
                        if (
                                !materialEnchantment.equals(inputEnchantment) &&
                                (materialEnchantmentType.isIncompatibleWith(inputEnchantment.getType()) ||
                                 inputEnchantment.getType().isIncompatibleWith(materialEnchantmentType))
                        ) {
                            isApplicable = false;
                            actionCost++;
                        }
                    }

                    if (!isApplicable) {
                        hasIncompatible = true;
                        continue;
                    }
                    hasCompatible = true;

                    var resultLevel = materialEnchantment.getLevel();
                    var levelCost = resultLevel;

                    var existingEnchantLevel = inputItem.getEnchantmentLevel(materialEnchantmentType);
                    if (existingEnchantLevel != 0) {
                        if (existingEnchantLevel > resultLevel || (existingEnchantLevel == resultLevel && resultLevel == materialEnchantmentType.getMaxLevel())) {
                            hasIncompatible = true;
                            continue;
                        } else if (existingEnchantLevel == resultLevel) {
                            resultLevel++;
                        }

                        levelCost = resultLevel - existingEnchantLevel;
                    }

                    var rarityCost = materialEnchantmentType.getRarity().getAnvilCost();
                    if (isEnchantedBook) {
                        rarityCost = Math.max(1, rarityCost / 2);
                    }

                    resultItem.addEnchantment(materialEnchantmentType, resultLevel);
                    actionCost += rarityCost * levelCost;
                }

                if (hasIncompatible && !hasCompatible) {
                    log.warn("No compatible enchantments but have incompatible ones");
                    return error();
                }
            }
        }

        // Case 3: Renaming
        var renamed = filterStrings.length != 0;
        if (renamed) {
            var customName = filterStrings[action.getFilteredStringIndex()];
            if (customName == null || customName.isEmpty()) {
                if (!resultItem.getCustomName().isEmpty()) {
                    customName = ""; // Clear the custom name
                }
            }

            resultItem.setCustomName(customName);
            actionCost++;
        }

        var totalCost = anvilCost + actionCost;
        if (totalCost <= 0) {
            log.warn("Repair cost is less than or equal to zero");
            return error();
        }

        // If our only action was renaming, the cost should never exceed 40
        if (totalCost > ANVIL_COST_LIMIT && renamed) {
            totalCost = ANVIL_COST_LIMIT;
        }

        var entity = player.getControlledEntity();
        if (entity.getGameMode() != GameMode.CREATIVE) {
            if (totalCost > ANVIL_COST_LIMIT) {
                log.warn("Repair cost exceeds anvil limit of {}", ANVIL_COST_LIMIT);
                return error();
            }

            if (entity.getExperienceLevel() < totalCost) {
                log.warn("Player doesn't have enough experience levels");
                return error();
            }

            var anvilPos = container.getBlockPos();
            if (ThreadLocalRandom.current().nextFloat() < ANVIL_DAMAGE_CHANCE) {
                var anvilState = entity.getDimension().getBlockState(anvilPos);
                if (anvilState.getBehavior() instanceof BlockAnvilBaseComponent anvilComponent) {
                    var newAnvilState = anvilComponent.damage(anvilState);
                    var event = new AnvilDamageEvent(new Block(anvilState, anvilPos), newAnvilState);
                    if (event.call()) {
                        if (newAnvilState.getBlockType() == BlockTypes.AIR) {
                            entity.getDimension().addSound(anvilPos, SimpleSound.ANVIL_BREAK);
                        }

                        entity.getDimension().setBlockState(anvilPos, event.getNewState());
                    }
                }
            }

            entity.getDimension().addSound(anvilPos, SimpleSound.ANVIL_USE);
            entity.setExperienceLevel(entity.getExperienceLevel() - totalCost);
        }

        var newRepairCost = Math.max(inputItem.getRepairCost(), materialItem.getRepairCost());
        if (actionCost > 1 || !renamed) {
            newRepairCost = newRepairCost * 2 + 1;
        }

        resultItem.setRepairCost(newRepairCost);

        var event = new AnvilTakeResultEvent(player, container, resultItem);
        if (!event.call()) {
            return error();
        }

        entity.getContainer(ContainerTypes.CREATED_OUTPUT).setItemStack(0, event.getResultItem(), false);
        return null;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL;
    }
}
