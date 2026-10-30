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
 * {@code CraftRecipeOptionalActionProcessor} calculates anvil repair, enchantment merge,
 * and rename results for the currently opened anvil. Request-local filter strings supply
 * the selected custom name, and input repair history combines with operation costs to
 * determine the charged experience levels and future repair penalty.
 *
 * <p>
 * Material repairs and equal-item repairs use different durability bonuses. Enchantment
 * compatibility, levels, rarity, and book discounts affect the operation cost, while a
 * present rename caps that total at the configured limit. Noncreative execution can
 * damage the anvil and deduct experience before the result event runs; cancelling that
 * event prevents output publication without restoring those prior effects.
 *
 * @author IWareQ
 */
@Slf4j
public class CraftRecipeOptionalActionProcessor implements ContainerActionProcessor<CraftRecipeOptionalAction> {
    /**
     * {@code FILTER_STRINGS_DATA_KEY} names the request-local string array supplied by
     * packet processing and read for anvil rename selection.
     */
    public static final String FILTER_STRINGS_DATA_KEY = "filterStrings";

    // ANVIL_MATERIAL_REPAIR_MULTIPLIER and ANVIL_ITEM_REPAIR_MULTIPLIER scale
    // different repair paths against maximum durability: consumable materials restore
    // a fraction per item, while equal-item merging adds its separate bonus.
    /**
     * Material repair percentage of maximum item durability.
     */
    private static final int ANVIL_MATERIAL_REPAIR_MULTIPLIER = 25;
    /**
     * Equal-item merge bonus percentage of maximum durability.
     */
    private static final int ANVIL_ITEM_REPAIR_MULTIPLIER = 12;

    /**
     * {@code ANVIL_DAMAGE_CHANCE} is the probability of attempting anvil damage during
     * an accepted noncreative operation, before the result event is dispatched.
     */
    private static final float ANVIL_DAMAGE_CHANCE = 0.12f;
    /**
     * {@code ANVIL_COST_LIMIT} caps totals whenever renaming is requested and rejects
     * larger noncreative totals when that rename cap does not apply.
     */
    private static final int ANVIL_COST_LIMIT = 39;

    /**
     * Builds an anvil result from input, material, and request filter strings. The
     * player must have an anvil open, and any selected filter index must address the
     * supplied array; the existing length guard does not reject every invalid index.
     *
     * <p>
     * Repair history adds to operation costs from material repair, durability merging,
     * enchantment compatibility and levels, and renaming. A present rename caps the
     * total at {@code ANVIL_COST_LIMIT}, even when other operations also contribute.
     * Noncreative players must afford the accepted cost.
     *
     * <p>
     * Anvil damage and experience deduction occur before the take-result event.
     * Cancellation returns an error without restoring those effects; successful dispatch
     * stages the listener's result and returns {@code null}, leaving input consumption
     * to later actions.
     */
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

        if (filterStrings.length < action.filteredStringIndex()) {
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

            if (
                    inputItem instanceof ItemRepairableComponent repairableComponent &&
                    repairableComponent.canBeRepairedBy(materialItem.getItemType())
            ) {
                // Material repair converts the configured percentage into durability
                // points, capped by the damage remaining on the result after each
                // material item.
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
                var isEnchantedBook = materialItem.getItemType() == ItemTypes.ENCHANTED_BOOK;

                if (!isEnchantedBook && inputItem.getItemType() == materialItem.getItemType()) {
                    actionCost += 2;

                    // Equal-item repair receives its separate percentage bonus when
                    // converting both inputs' damage into the result's remaining
                    // damage value.
                    var bonusDamage = maxDamage * ANVIL_ITEM_REPAIR_MULTIPLIER / 100;
                    var mergedDamage = inputItem.getDamage() + materialItem.getDamage() - bonusDamage;
                    resultItem.setDamage(Math.max(0, mergedDamage - maxDamage));
                }

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
                    // Book-supplied enchantments halve the rarity cost with integer
                    // truncation but retain at least one level of cost per applied
                    // increase.
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

        var renamed = filterStrings.length != 0;
        if (renamed) {
            var customName = filterStrings[action.filteredStringIndex()];
            if (customName == null || customName.isEmpty()) {
                if (!resultItem.getCustomName().isEmpty()) {
                    customName = "";
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

        // Any supplied rename activates this cap, including requests with repair or
        // enchantment costs; the implementation does not restrict the cap to
        // rename-only operations.
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

            // Apply anvil damage and charge experience before the result event; a
            // later cancellation prevents output publication but does not compensate
            // these earlier effects.
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
            // Nontrivial operations increase future repair history by doubling the
            // larger input penalty and adding one; rename-only work preserves the
            // larger existing penalty.
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

    /**
     * Returns the optional recipe action type used for anvil requests.
     */
    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL;
    }
}
