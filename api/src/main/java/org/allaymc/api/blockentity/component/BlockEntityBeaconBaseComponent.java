package org.allaymc.api.blockentity.component;

import org.allaymc.api.entity.effect.EffectType;

/**
 * {@code BlockEntityBeaconBaseComponent} exposes a beacon's pyramid level and
 * selected effects. The server recalculates the level from mineral layers and
 * uses the selected effects when applying beacon powers; changing a selection
 * does not itself recalculate the pyramid.
 *
 * @author daoge_cmd
 */
public interface BlockEntityBeaconBaseComponent extends BlockEntityBaseComponent {
    /**
     * Returns the last calculated number of complete pyramid layers below the
     * beacon. Ordinary recalculation yields zero through four, although the
     * server also loads stored levels directly before the next recalculation.
     */
    int getLevel();

    /**
     * Returns the selected primary effect, or {@code null} when none is
     * selected. Selection presence alone does not establish eligibility for the
     * current pyramid level or an unobstructed beacon.
     */
    EffectType getPrimaryEffect();

    /**
     * Stores the selected primary effect for later beacon effect processing.
     * {@code null} clears the selection, and the server's setter does not
     * immediately broadcast state or apply effects to players.
     */
    void setPrimaryEffect(EffectType effectType);

    /**
     * Returns whether a primary effect selection is currently present.
     */
    default boolean hasPrimaryEffect() {
        return getPrimaryEffect() != null;
    }

    /**
     * Returns the selected secondary effect, or {@code null} when none is
     * selected. The server processes that selection only when a usable primary
     * selection produces an effect instance.
     */
    EffectType getSecondaryEffect();

    /**
     * Stores the selected secondary effect for later beacon effect processing.
     * {@code null} clears the selection, and the server's setter does not
     * immediately broadcast state or apply effects to players.
     */
    void setSecondaryEffect(EffectType effectType);

    /**
     * Returns whether a secondary effect selection is currently present.
     */
    default boolean hasSecondaryEffect() {
        return getSecondaryEffect() != null;
    }
}
