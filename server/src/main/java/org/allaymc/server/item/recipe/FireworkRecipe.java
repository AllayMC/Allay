package org.allaymc.server.item.recipe;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.interfaces.ItemDyeStack;
import org.allaymc.api.item.interfaces.ItemFireworkStarStack;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.FireworkExplosion;
import org.allaymc.api.world.data.FireworkType;

import java.util.*;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * FireworkRecipe is the recipe for firework rocket and firework star.
 *
 * @author daoge_cmd
 */
public final class FireworkRecipe extends ComplexRecipe {

    public static final FireworkRecipe INSTANCE = new FireworkRecipe();

    private FireworkRecipe() {
        super(ComplexRecipeIds.FIREWORKS);
    }

    @Override
    public boolean match(RecipeInput input) {
        return getOutputs(input) != null;
    }

    @Override
    public ItemStack[] getOutputs(RecipeInput input) {
        if (!(input instanceof CraftingRecipeInput craftingRecipeInput)) {
            return null;
        }

        var inputItems = collectNonAirItems(craftingRecipeInput.flattenedItems());
        if (inputItems.size() < 2) {
            // Fast check for the minimum input size
            return null;
        }

        // Check if the input tends to craft firework rocket by checking if there is paper in the input
        var paperCount = getItemTypeCount(ItemTypes.PAPER, inputItems);
        var gunpowerCount = getItemTypeCount(ItemTypes.GUNPOWDER, inputItems);
        var dyeCount = getItemTagCount(ItemTags.DYE, inputItems);
        if (paperCount > 0) {
            // Craft firework rocket
            if (paperCount != 1) {
                // Only one paper is allowed in the input
                return null;
            }

            if (gunpowerCount == 0 || gunpowerCount > 3) {
                // At least one gunpowder and no more than three gunpowder are allowed in the input
                return null;
            }

            if (containInvalidRocketIngredient(inputItems)) {
                return null;
            }

            var explosions = new HashSet<FireworkExplosion>();
            for (var inputItem : inputItems) {
                if (inputItem instanceof ItemFireworkStarStack star) {
                    explosions.add(star.getExplosion());
                }
            }

            var rocket = ItemTypes.FIREWORK_ROCKET.createItemStack(3);
            rocket.setExplosions(explosions);
            rocket.setDuration(gunpowerCount);

            return new ItemStack[]{rocket};
        } else if (gunpowerCount == 1) {
            // Craft firework star, only one gunpowder is allowed in the input
            if (dyeCount == 0) {
                // At least one dye is required in the input
                return null;
            }

            if (containInvalidStarIngredient(inputItems)) {
                return null;
            }

            var type = FireworkType.SMALL_SPHERE;
            var colors = new ArrayList<DyeColor>();
            var flicker = false;
            var trail = false;

            for (var inputItem : inputItems) {
                var itemType = inputItem.getItemType();
                if (inputItem instanceof ItemDyeStack dye) {
                    colors.add(dye.getDyeColor());
                } else if (itemType.hasItemTag(ItemTags.HEAD)) {
                    type = FireworkType.CREEPER_HEAD;
                } else if (itemType == ItemTypes.GOLD_NUGGET) {
                    type = FireworkType.STAR;
                } else if (itemType == ItemTypes.FEATHER) {
                    type = FireworkType.BURST;
                } else if (itemType == ItemTypes.FIRE_CHARGE) {
                    type = FireworkType.HUGE_SPHERE;
                } else if (itemType == ItemTypes.GLOWSTONE_DUST) {
                    flicker = true;
                } else if (itemType == ItemTypes.DIAMOND) {
                    trail = true;
                }
            }

            var star = ItemTypes.FIREWORK_STAR.createItemStack(1);
            star.setExplosion(new FireworkExplosion(
                    type, colors, Collections.emptyList(),
                    flicker, trail
            ));

            return new ItemStack[]{star};
        } else if (getItemTypeCount(ItemTypes.FIREWORK_STAR, inputItems) == 1) {
            // Add fade color to the firework star
            if (dyeCount < 1) {
                // At least one dye is required in the input
                return null;
            }

            ItemFireworkStarStack star = null;
            var fadeColors = new ArrayList<DyeColor>();
            for (var inputItem : inputItems) {
                if (inputItem instanceof ItemFireworkStarStack s) {
                    star = s;
                } else if (inputItem instanceof ItemDyeStack dye) {
                    fadeColors.add(dye.getDyeColor());
                }
            }

            var old = Objects.requireNonNull(star).getExplosion();
            star.setExplosion(new FireworkExplosion(
                    old.type(), old.colors(), fadeColors,
                    old.flicker(), old.trail()
            ));

            return new ItemStack[]{star};
        }

        return null;
    }

    private List<ItemStack> collectNonAirItems(ItemStack[] items) {
        return Arrays.stream(items).filter(item -> item.getItemType() != AIR).toList();
    }

    private int getItemTypeCount(ItemType<?> itemType, List<ItemStack> items) {
        return (int) items.stream().filter(item -> item.getItemType() == itemType).count();
    }

    private int getItemTagCount(ItemTag itemTag, List<ItemStack> items) {
        return (int) items.stream().filter(item -> item.getItemType().hasItemTag(itemTag)).count();
    }

    private boolean containInvalidRocketIngredient(List<ItemStack> items) {
        return items.stream().anyMatch(item ->
                item.getItemType() != ItemTypes.PAPER &&
                item.getItemType() != ItemTypes.GUNPOWDER &&
                item.getItemType() != ItemTypes.FIREWORK_STAR
        );
    }

    private boolean containInvalidStarIngredient(List<ItemStack> items) {
        return items.stream().anyMatch(item ->
                // Base material
                item.getItemType() != ItemTypes.GUNPOWDER &&
                !item.getItemType().hasItemTag(ItemTags.DYE) &&
                // Shape effect material
                !item.getItemType().hasItemTag(ItemTags.HEAD) &&
                item.getItemType() != ItemTypes.GOLD_NUGGET &&
                item.getItemType() != ItemTypes.FEATHER &&
                item.getItemType() != ItemTypes.FIRE_CHARGE &&
                // Additional effect material
                item.getItemType() != ItemTypes.GLOWSTONE_DUST &&
                item.getItemType() != ItemTypes.DIAMOND
        );
    }
}
