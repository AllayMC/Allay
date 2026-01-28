package org.allaymc.api.entity.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

/**
 * Component interface for fishing hook entity specific functionality.
 *
 * @author daoge_cmd
 */
public interface EntityFishingHookBaseComponent extends EntityComponent {
    /**
     * The fishing hook state.
     */
    enum FishingState {
        /**
         * The hook is in the air, flying towards water.
         */
        FLYING,
        /**
         * The hook is in water, waiting for a fish.
         */
        WAITING,
        /**
         * A fish is being attracted to the hook.
         */
        ATTRACTING,
        /**
         * A fish has bitten the hook.
         */
        CAUGHT
    }

    /**
     * Gets the entity that has been hooked by this fishing hook.
     *
     * @return the hooked entity, or {@code null} if none
     */
    Entity getHookedEntity();

    /**
     * Sets the entity that has been hooked by this fishing hook.
     *
     * @param entity the entity to hook, can be {@code null} to clear
     */
    void setHookedEntity(Entity entity);

    /**
     * Checks if this fishing hook has hooked an entity.
     *
     * @return {@code true} if an entity is hooked
     */
    default boolean hasHookedEntity() {
        return getHookedEntity() != null;
    }

    /**
     * Gets the fishing rod item stack associated with this hook.
     *
     * @return the fishing rod, or {@code null} if not set
     */
    ItemStack getFishingRod();

    /**
     * Sets the fishing rod item stack associated with this hook.
     *
     * @param fishingRod the fishing rod, can be {@code null}
     */
    void setFishingRod(ItemStack fishingRod);

    /**
     * Gets the current fishing state.
     *
     * @return the fishing state
     */
    FishingState getFishingState();

    /**
     * Sets the current fishing state.
     *
     * @param state the fishing state
     */
    void setFishingState(FishingState state);

    /**
     * Checks if a fish has bitten the hook and is ready to be reeled in.
     *
     * @return {@code true} if a fish has been caught
     */
    default boolean isCaught() {
        return getFishingState() == FishingState.CAUGHT;
    }

    /**
     * Reels in the fishing line, potentially catching a fish or pulling a hooked entity.
     * This method handles loot generation and experience dropping.
     */
    void reelLine();

    /**
     * Checks if the fishing hook is in open water.
     * Open water is required for catching treasure items.
     *
     * @return {@code true} if in open water
     */
    boolean isInOpenWater();
}
