package org.allaymc.api.entity.component;

/**
 * @author daoge_cmd
 */
public interface EntityXpOrbBaseComponent extends EntityPickableBaseComponent {
    /**
     * Get the experience value of this entity xp orb
     *
     * @return the experience value
     */
    int getExperienceValue();

    /**
     * Set the experience value of this entity xp orb
     *
     * @param experienceValue the experience value
     */
    void setExperienceValue(int experienceValue);
}
