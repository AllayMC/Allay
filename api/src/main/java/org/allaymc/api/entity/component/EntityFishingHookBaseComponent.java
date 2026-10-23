package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

/**
 * {@code EntityFishingHookBaseComponent} exposes the state of a cast hook and
 * its associated fishing rod. A hook may follow a caught entity or progress
 * through water-based fishing phases; reeling resolves the applicable catch
 * and ends the cast.
 *
 * @author daoge_cmd
 */
public interface EntityFishingHookBaseComponent extends EntityComponent {
    /**
     * {@code FishingState} identifies the current phase of a cast fishing
     * hook.
     */
    enum FishingState {
        /**
         * Airborne or outside the water-based fishing state machine.
         */
        FLYING,
        /**
         * Waiting in water before a virtual fish approaches.
         */
        WAITING,
        /**
         * A virtual fish approaching the hook through wake particles.
         */
        ATTRACTING,
        /**
         * A bite awaiting reeling before its catch window expires.
         */
        CAUGHT
    }

    /**
     * Returns the currently hooked entity, or {@code null} when no entity is
     * retained. A hooked entity takes precedence over the water-based catch
     * state during reeling and tick processing.
     */
    Entity getHookedEntity();

    /**
     * Stores the entity followed by this hook on subsequent ticks. Passing
     * {@code null} clears the target so water-based fishing phases can resume,
     * while a nonnull target takes precedence over catch loot.
     */
    void setHookedEntity(Entity entity);

    /**
     * Returns whether the hook currently retains a nonnull entity target.
     */
    default boolean hasHookedEntity() {
        return getHookedEntity() != null;
    }

    /**
     * Returns the retained fishing rod reference, or {@code null} when none is
     * assigned. Fishing calculations read enchantments from this same stack
     * rather than a copy captured at cast time.
     */
    ItemStack getFishingRod();

    /**
     * Retains the supplied fishing rod stack without copying its contents.
     * Passing {@code null} removes its enchantment contribution, and later
     * stack changes affect subsequent wait-time and loot calculations.
     */
    void setFishingRod(ItemStack fishingRod);

    /**
     * Returns the fishing phase currently recorded for this hook.
     */
    FishingState getFishingState();

    /**
     * Stores the current phase without initializing the associated fishing
     * timers. Normal phase transitions set those timers separately, so callers
     * changing this value must account for the existing timer state.
     */
    void setFishingState(FishingState state);

    /**
     * Returns whether the recorded fishing phase equals {@code
     * FishingState.CAUGHT}.
     */
    default boolean isCaught() {
        return getFishingState() == FishingState.CAUGHT;
    }

    /**
     * Resolves this cast by pulling a hooked entity or generating caught loot
     * and experience. Event cancellation suppresses the corresponding reward
     * or motion, but reeling still removes the hook and clears the player cast
     * reference.
     */
    void reelLine();

    /**
     * Returns the cached open-water eligibility used for treasure selection.
     * Water-based ticking refreshes the surrounding-block check periodically,
     * so this value may lag behind recent changes around the hook.
     */
    boolean isInOpenWater();
}
