package org.allaymc.api.entity.component;

/**
 * {@code EntityXpOrbBaseComponent} exposes the experience amount carried by a
 * pickable orb entity. Pickup can spend part of this amount on Mending before
 * granting the remainder, then clears the stored amount before removing the
 * orb.
 *
 * @author daoge_cmd
 */
public interface EntityXpOrbBaseComponent extends EntityPickableBaseComponent {
    /**
     * Returns the experience amount currently stored by this orb entity.
     */
    int getExperienceValue();

    /**
     * Replaces the stored experience amount and publishes updated orb state. A
     * value of {@code 0} prevents collision pickup from granting experience or
     * removing the orb through that path.
     */
    void setExperienceValue(int experienceValue);
}
