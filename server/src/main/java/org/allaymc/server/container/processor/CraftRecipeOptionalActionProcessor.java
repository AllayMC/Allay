package org.allaymc.server.container.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockAnvilBaseComponent;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.tag.ItemTags;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Sound;
import org.cloudburstmc.protocol.bedrock.data.GameType;
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

    private static final float ANVIL_MATERIAL_REPAIR_MULTIPLIER = 0.25f;
    private static final float ANVIL_ITEM_REPAIR_MULTIPLIER = 0.12f;

    private static final float ANVIL_DAMAGE_CHANCE = 0.12f;
    private static final int ANVIL_COST_LIMIT = 39;

    @Override
    public ActionResponse handle(CraftRecipeOptionalAction action, EntityPlayer player, int currentActionIndex, ItemStackRequestAction[] actions, Map<String, Object> dataPool) {
        var container = player.getOpenedContainer(FullContainerType.ANVIL);
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

            var repairMaterial = getRepairMaterial(inputItem.getItemType());

            var maxDamage = resultItem.getMaxDurability();

            // Case 1: Repair by repairing materials
            if (repairMaterial != null && repairMaterial == materialItem.getItemType()) {
                var delta = (int) Math.min(resultItem.getDurability(), maxDamage * ANVIL_MATERIAL_REPAIR_MULTIPLIER);
                if (delta <= 0) {
                    log.warn("Input item is already fully repaired");
                    return error();
                }

                var newDamage = resultItem.getDurability();
                for (int i = 0; i < materialItem.getCount() && delta > 0; i++) {
                    actionCost++;

                    newDamage = Math.max(0, newDamage - delta);
                    delta = (int) Math.min(newDamage, maxDamage * ANVIL_MATERIAL_REPAIR_MULTIPLIER);
                }

                resultItem.setDurability(newDamage);
            } else if (inputItem.getItemType() == materialItem.getItemType()) {
                // Case 2: Merge equal items
                // TODO: Enchantments merging
                actionCost += 2;

                var remainingDurabilityInput = maxDamage - inputItem.getDurability();
                var remainingDurabilityMaterial = maxDamage - materialItem.getDurability();

                var totalRemainingDurability = (int) (remainingDurabilityInput + remainingDurabilityMaterial + (maxDamage * ANVIL_ITEM_REPAIR_MULTIPLIER));
                var finalDurability = Math.min(totalRemainingDurability, maxDamage);
                resultItem.setDurability(maxDamage - finalDurability);
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

        if (player.getGameType() != GameType.CREATIVE) {
            if (totalCost > ANVIL_COST_LIMIT) {
                log.warn("Repair cost exceeds anvil limit of {}", ANVIL_COST_LIMIT);
                return error();
            }

            if (player.getExperienceLevel() < totalCost) {
                log.warn("Player doesn't have enough experience levels");
                return error();
            }

            var anvilPos = container.getBlockPos();
            if (ThreadLocalRandom.current().nextFloat() < ANVIL_DAMAGE_CHANCE) {
                var anvilState = player.getDimension().getBlockState(anvilPos);
                if (anvilState.getBehavior() instanceof BlockAnvilBaseComponent anvilComponent) {
                    anvilState = anvilComponent.damage(anvilState);
                    if (anvilState.getBlockType() == BlockTypes.AIR) {
                        player.getDimension().addSound(anvilPos, Sound.RANDOM_ANVIL_BREAK);
                    }
                    player.getDimension().setBlockState(anvilPos, anvilState);
                }
            }

            player.getDimension().addSound(anvilPos, Sound.RANDOM_ANVIL_USE);

            player.setExperienceLevel(player.getExperienceLevel() - totalCost);
        }

        var newRepairCost = Math.max(inputItem.getRepairCost(), materialItem.getRepairCost());
        if (actionCost > 1 || !renamed) {
            newRepairCost = newRepairCost * 2 + 1;
        }

        resultItem.setRepairCost(newRepairCost);

        player.getContainer(FullContainerType.CREATED_OUTPUT).setItemStack(resultItem);
        return null;
    }

    // TODO: Replace this with a better method (maybe component?)
    protected ItemType<?> getRepairMaterial(ItemType<?> itemType) {
        if (itemType.hasItemTag(ItemTags.WOODEN_TIER)) {
            return ItemTypes.PLANKS;
        } else if (itemType.hasItemTag(ItemTags.STONE_TIER)) {
            return ItemTypes.COBBLESTONE;
        } else if (itemType.hasItemTag(ItemTags.IRON_TIER)) {
            return ItemTypes.IRON_INGOT;
        } else if (itemType.hasItemTag(ItemTags.GOLDEN_TIER)) {
            return ItemTypes.GOLD_INGOT;
        } else if (itemType.hasItemTag(ItemTags.DIAMOND_TIER)) {
            return ItemTypes.DIAMOND;
        } else if (itemType.hasItemTag(ItemTags.NETHERITE_TIER)) {
            return ItemTypes.NETHERITE_INGOT;
        }

        return null;
    }

    @Override
    public ItemStackRequestActionType getType() {
        return ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL;
    }
}
