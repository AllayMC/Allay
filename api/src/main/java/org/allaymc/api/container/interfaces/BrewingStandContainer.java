package org.allaymc.api.container.interfaces;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypes;
import org.jetbrains.annotations.Range;

/**
 * {@code BrewingStandContainer} exposes brewing reagent, bottle, and fuel slots together
 * with directional access for automated transfer. Bottle accessors translate their
 * zero-based bottle index to container slots one through three; the reagent and fuel
 * keep their own fixed indices. The item stacks follow the ordinary container sharing
 * and notification rules.
 *
 * <p>
 * Top insertion accepts stacks classified as reagents, side insertion distinguishes
 * blaze powder from bottles, and bottom insertion is denied. Extraction from below
 * exposes only the three bottle slots. Reagent classification excludes fuel and bottle
 * categories rather than checking registered brewing recipes, so transfer eligibility
 * alone does not establish that brewing can start.
 *
 * @author daoge_cmd
 */
public interface BrewingStandContainer extends BlockContainer, SidedContainer {
    /**
     * The brewing slot constants separate the reagent and fuel from the three
     * intervening bottle slots. Brewing reagent slot index.
     */
    int REAGENT_SLOT = 0;

    /**
     * Brewing fuel slot index.
     */
    int FUEL_SLOT = 4;

    /**
     * Returns the stack stored in the brewing reagent slot. The returned stack is shared
     * with the container; callers must notify that slot after directly changing its
     * contents.
     */
    default ItemStack getReagent() {
        return getItemStack(REAGENT_SLOT);
    }

    /**
     * Replaces the brewing reagent slot and notifies container observers. {@code
     * itemStack} must be non-{@code null}; the container stores the supplied reference
     * rather than making an independent copy.
     */
    default void setReagent(ItemStack itemStack) {
        setItemStack(REAGENT_SLOT, itemStack);
    }

    /**
     * Returns the stack stored in the brewing fuel slot. The returned stack is shared
     * with the container; callers must notify that slot after directly changing its
     * contents.
     */
    default ItemStack getFuel() {
        return getItemStack(FUEL_SLOT);
    }

    /**
     * Replaces the brewing fuel slot and notifies container observers. {@code itemStack}
     * must be non-{@code null}; the container stores the supplied reference rather than
     * making an independent copy.
     */
    default void setFuel(ItemStack itemStack) {
        setItemStack(FUEL_SLOT, itemStack);
    }

    /**
     * Returns the bottle stack for a zero-based result index from {@code 0} through
     * {@code 2}. The index maps to container slots one through three, and the returned
     * stack remains shared.
     */
    default ItemStack getResult(@Range(from = 0, to = 2) int slot) {
        // Bottle indices exclude the preceding reagent slot.
        return getItemStack(slot + 1);
    }

    /**
     * Replaces a bottle stack using a zero-based result index from {@code 0} through
     * {@code 2}. The supplied non-{@code null} stack is retained, and ordinary slot
     * notifications follow assignment.
     */
    default void setResult(@Range(from = 0, to = 2) int slot, ItemStack itemStack) {
        // Bottle indices exclude the preceding reagent slot.
        setItemStack(slot + 1, itemStack);
    }

    /**
     * Selects brewing insertion candidates by face and item category. {@code UP} accepts
     * the reagent classification into {@code REAGENT_SLOT}; {@code DOWN} denies
     * insertion before any fuel or bottle selection. Lateral access sends blaze powder
     * to {@code FUEL_SLOT} and recognized bottle types to slots one through three.
     *
     * <p>
     * The reagent predicate only excludes fuel and bottle categories, so this result
     * does not verify a registered brewing recipe. Transfer code must still check slot
     * capacity before moving a stack.
     */
    @Override
    default int[] getAllowedInsertSlots(BlockFace side, ItemStack stack) {
        if (side == BlockFace.UP) {
            return isBrewingReagent(stack) ? new int[]{REAGENT_SLOT} : new int[0];
        }
        if (side == BlockFace.DOWN) {
            return new int[0];
        }
        if (isBrewingFuel(stack)) {
            return new int[]{FUEL_SLOT};
        }
        if (isBrewingBottle(stack)) {
            return new int[]{1, 2, 3};
        }
        return new int[0];
    }

    /**
     * Returns the three bottle slots for extraction from below. Every other face returns
     * an empty array, preventing automated extraction of reagent and fuel through these
     * sided access rules.
     */
    @Override
    default int[] getAllowedExtractSlots(BlockFace side) {
        if (side == BlockFace.DOWN) {
            return new int[]{1, 2, 3};
        }
        return new int[0];
    }

    /**
     * Returns whether the stack is blaze powder for brewing fuel. {@code null} and the
     * shared air stack return {@code false} before the item type is read from the stack.
     */
    private static boolean isBrewingFuel(ItemStack stack) {
        if (stack == null || stack == ItemAirStack.AIR_STACK) {
            return false;
        }
        return stack.getItemType() == ItemTypes.BLAZE_POWDER;
    }

    /**
     * Returns whether the stack is a glass bottle or a supported potion bottle. {@code
     * null} and the shared air stack return {@code false} without accessing an item
     * type.
     */
    private static boolean isBrewingBottle(ItemStack stack) {
        if (stack == null || stack == ItemAirStack.AIR_STACK) {
            return false;
        }
        var type = stack.getItemType();
        return type == ItemTypes.GLASS_BOTTLE ||
                type == ItemTypes.POTION ||
                type == ItemTypes.SPLASH_POTION ||
                type == ItemTypes.LINGERING_POTION;
    }

    /**
     * Returns whether neither the fuel nor bottle predicate accepts the stack. This
     * exclusion-based classification also accepts {@code null} and air; callers must not
     * treat it as brewing recipe validation.
     */
    private static boolean isBrewingReagent(ItemStack stack) {
        return !isBrewingFuel(stack) && !isBrewingBottle(stack);
    }
}
