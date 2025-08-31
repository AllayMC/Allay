package org.allaymc.api.blockentity.component;

import org.allaymc.api.entity.effect.EffectType;

/**
 * @author daoge_cmd
 */
public interface BlockEntityBeaconBaseComponent extends BlockEntityBaseComponent {
    /**
     * Get the level of the beacon.
     * <p>
     * level is the amount of the pyramid's levels, it is defined by the mineral blocks which build up the
     * pyramid, and can be 0-4.
     *
     * @return the level of the beacon
     */
    int getLevel();

    /**
     * Get the primary effect of the beacon.
     *
     * @return the primary effect of the beacon, or {@code null} if no primary effect
     */
    EffectType getPrimaryEffect();

    /**
     * Set the primary effect of the beacon.
     *
     * @param effectType the primary effect of the beacon
     */
    void setPrimaryEffect(EffectType effectType);

    /**
     * Check if the beacon has a primary effect.
     *
     * @return {@code true} if the beacon has a primary effect, otherwise {@code false}.
     */
    default boolean hasPrimaryEffect() {
        return getPrimaryEffect() != null;
    }

    /**
     * Get the secondary effect of the beacon.
     *
     * @return the secondary effect of the beacon, or {@code null} if no secondary effect
     */
    EffectType getSecondaryEffect();

    /**
     * Set the secondary effect of the beacon.
     *
     * @param effectType the secondary effect of the beacon
     */
    void setSecondaryEffect(EffectType effectType);

    /**
     * Check if the beacon has a secondary effect.
     *
     * @return {@code true} if the beacon has a secondary effect, otherwise {@code false}.
     */
    default boolean hasSecondaryEffect() {
        return getSecondaryEffect() != null;
    }
}
