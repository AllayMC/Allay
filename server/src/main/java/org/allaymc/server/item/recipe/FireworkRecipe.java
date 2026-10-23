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
import org.allaymc.api.world.data.FireworkType;
import org.allaymc.api.world.explosion.FireworkExplosion;

import java.util.*;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * {@code FireworkRecipe} derives firework rockets, new stars, or star fade
 * colors from occupied crafting slots. Recipe selection follows ingredient
 * precedence: any paper selects the rocket branch, otherwise exactly one
 * gunpowder selects star creation, and otherwise one existing star can receive
 * fade colors. Counts refer to occupied slots rather than the counts stored
 * inside their stacks.
 *
 * <p>
 * Rocket and new-star branches allocate output stacks, but fade-color crafting
 * updates and returns the input star itself. Consequently {@code match}, which
 * calls {@code getOutputs}, can also update that star. New-star shape modifiers
 * follow flattened grid order, with the last recognized modifier winning. The
 * fade branch requires dye but does not apply the ingredient allowlists used by
 * the other two branches.
 *
 * @author daoge_cmd
 */
public final class FireworkRecipe extends ComplexRecipe {

    /**
     * {@code INSTANCE} supplies the shared firework recipe under the
     * client-defined special recipe UUID.
     */
    public static final FireworkRecipe INSTANCE = new FireworkRecipe();

    /**
     * Creates the singleton firework recipe with its protocol-defined special
     * UUID.
     */
    private FireworkRecipe() {
        super(ComplexRecipeIds.BANNER_FIREWORKS);
    }

    /**
     * Returns whether input-dependent output generation accepts the supplied
     * grid. This check calls {@code getOutputs} directly, so it allocates
     * discarded outputs for rocket or star creation and mutates the supplied
     * star in the fade-color branch.
     */
    @Override
    public boolean match(RecipeInput input) {
        return getOutputs(input) != null;
    }

    /**
     * Produces rockets, a new star, or an existing star with replaced fade
     * colors according to ingredient precedence. Noncrafting input and inputs
     * rejected by the selected branch return {@code null}; occupied-slot counts
     * drive selection without consuming stack counts.
     *
     * <p>
     * Paper requires exactly one paper slot, one to three gunpowder slots, and
     * only rocket ingredients. This branch creates three rockets, uses the
     * gunpowder-slot count as their duration value, and collects distinct
     * explosions from star components. Without paper, exactly one gunpowder
     * requires dye and accepted star ingredients; dye components supply colors,
     * and the last shape modifier in flattened order determines the new star's
     * shape.
     *
     * <p>
     * Otherwise, one star slot plus at least one dye-tagged slot selects fade
     * replacement. This branch accepts unrelated ingredients, replaces the
     * supplied star's explosion, and returns that same stack with its existing
     * count. The old type, primary colors, flicker, and trail are retained;
     * callers must account for the input mutation even when this method is
     * invoked through {@code match}.
     */
    @Override
    public ItemStack[] getOutputs(RecipeInput input) {
        if (!(input instanceof CraftingRecipeInput craftingRecipeInput)) {
            return null;
        }

        var inputItems = collectNonAirItems(craftingRecipeInput.flattenedItems());
        if (inputItems.size() < 2) {
            // Every supported branch needs a base ingredient and another occupied
            // slot, so fewer slots cannot produce a valid firework result.
            return null;
        }

        var paperCount = getItemTypeCount(ItemTypes.PAPER, inputItems);
        var gunpowerCount = getItemTypeCount(ItemTypes.GUNPOWDER, inputItems);
        var dyeCount = getItemTagCount(ItemTags.DYE, inputItems);
        if (paperCount > 0) {
            if (paperCount != 1) {
                return null;
            }

            // The gunpowder-slot count becomes rocket duration, and this recipe
            // accepts only the three supported duration values.
            if (gunpowerCount == 0 || gunpowerCount > 3) {
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
            if (dyeCount == 0) {
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
            if (dyeCount < 1) {
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

    /**
     * Returns an unmodifiable list of nonair stack references in flattened
     * order. The returned list keeps the original stacks, which allows
     * fade-color crafting to update the supplied star.
     */
    private List<ItemStack> collectNonAirItems(ItemStack[] items) {
        return Arrays.stream(items).filter(item -> item.getItemType() != AIR).toList();
    }

    /**
     * Counts occupied entries whose item-type identity equals the requested
     * type.
     */
    private int getItemTypeCount(ItemType<?> itemType, List<ItemStack> items) {
        return (int) items.stream().filter(item -> item.getItemType() == itemType).count();
    }

    /**
     * Counts occupied entries whose item types contain the requested item tag.
     */
    private int getItemTagCount(ItemTag itemTag, List<ItemStack> items) {
        return (int) items.stream().filter(item -> item.getItemType().hasItemTag(itemTag)).count();
    }

    /**
     * Returns whether any entry is outside the rocket ingredient allowlist.
     */
    private boolean containInvalidRocketIngredient(List<ItemStack> items) {
        return items.stream().anyMatch(item ->
                item.getItemType() != ItemTypes.PAPER &&
                item.getItemType() != ItemTypes.GUNPOWDER &&
                item.getItemType() != ItemTypes.FIREWORK_STAR
        );
    }

    /**
     * Returns whether any entry is outside the new-star ingredient allowlist.
     */
    private boolean containInvalidStarIngredient(List<ItemStack> items) {
        return items.stream().anyMatch(item ->
                item.getItemType() != ItemTypes.GUNPOWDER &&
                !item.getItemType().hasItemTag(ItemTags.DYE) &&
                !item.getItemType().hasItemTag(ItemTags.HEAD) &&
                item.getItemType() != ItemTypes.GOLD_NUGGET &&
                item.getItemType() != ItemTypes.FEATHER &&
                item.getItemType() != ItemTypes.FIRE_CHARGE &&
                item.getItemType() != ItemTypes.GLOWSTONE_DUST &&
                item.getItemType() != ItemTypes.DIAMOND
        );
    }
}
