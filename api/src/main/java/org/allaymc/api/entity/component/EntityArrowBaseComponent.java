package org.allaymc.api.entity.component;

import org.allaymc.api.item.data.PotionType;

/**
 * @author daoge_cmd
 */
public interface EntityArrowBaseComponent extends EntityBaseComponent {

    /**
     * Checks if the arrow is critical. A critical arrow deals extra damage and has a different particle effect.
     *
     * @return {@code true} if the arrow is critical, {@code false} otherwise.
     */
    boolean isCritical();

    /**
     * Sets whether the arrow is critical. A critical arrow deals extra damage and has a different particle effect.
     *
     * @param critical {@code true} to make the arrow critical, {@code false} otherwise.
     */
    void setCritical(boolean critical);

    /**
     * Gets the base damage of the arrow.
     *
     * @return the base damage of the arrow.
     */
    float getBaseDamage();

    /**
     * Sets the base damage of the arrow.
     *
     * @param damage the base damage to set.
     */
    void setBaseDamage(float damage);

    /**
     * Gets the power level of the arrow. Power level increases the damage dealt by the arrow.
     *
     * @return the power level of the arrow.
     */
    int getPowerLevel();

    /**
     * Sets the power level of the arrow. Power level increases the damage dealt by the arrow.
     *
     * @param level the power level to set.
     */
    void setPowerLevel(int level);

    /**
     * Gets the punch level of the arrow. Punch level increases the knockback effect of the arrow.
     *
     * @return the punch level of the arrow.
     */
    int getPunchLevel();

    /**
     * Sets the punch level of the arrow. Punch level increases the knockback effect of the arrow.
     *
     * @param level the punch level to set.
     */
    void setPunchLevel(int level);

    /**
     * Gets the flame level of the arrow. Flame level determines whether the arrow sets targets on fire.
     *
     * @return the flame level of the arrow.
     */
    int getFlameLevel();

    /**
     * Sets the flame level of the arrow. Flame level determines whether the arrow sets targets on fire.
     *
     * @param level the flame level to set.
     */
    void setFlameLevel(int level);

    /**
     * Gets the infinity level of the arrow. Infinity level determines whether the arrow is consumed on use.
     *
     * @return the infinity level of the arrow.
     */
    int getInfinityLevel();

    /**
     * Sets the infinity level of the arrow. Infinity level determines whether the arrow is consumed on use.
     *
     * @param level the infinity level to set.
     */
    void setInfinityLevel(int level);

    /**
     * Gets the potion type of this arrow.
     *
     * @return the potion type of this arrow. Can be {@code null} if the arrow has no potion effect.
     */
    PotionType getPotionType();

    /**
     * Sets the potion type of this arrow.
     *
     * @param potionType the potion type to set. Can be {@code null} to remove any potion effect.
     */
    void setPotionType(PotionType potionType);
}
