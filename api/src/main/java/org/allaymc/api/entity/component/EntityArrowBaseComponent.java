package org.allaymc.api.entity.component;

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
     * @param critical {@code true} to make the arrow critical, {@code false} otherwise
     */
    void setCritical(boolean critical);

    /**
     * Gets the base damage of the arrow.
     *
     * @return the base damage of the arrow
     */
    float getBaseDamage();

    /**
     * Sets the base damage of the arrow.
     *
     * @param damage the base damage to set
     */
    void setBaseDamage(float damage);

    /**
     * Gets the power level of the arrow. Power level increases the damage dealt by the arrow.
     *
     * @return the power level of the arrow
     */
    int getPowerLevel();

    /**
     * Sets the power level of the arrow. Power level increases the damage dealt by the arrow.
     *
     * @param level the power level to set
     */
    void setPowerLevel(int level);

    /**
     * Gets the punch level of the arrow. Punch level increases the knockback effect of the arrow.
     *
     * @return the punch level of the arrow
     */
    int getPunchLevel();

    /**
     * Sets the punch level of the arrow. Punch level increases the knockback effect of the arrow.
     *
     * @param level the punch level to set
     */
    void setPunchLevel(int level);

    /**
     * Checks if the arrow is shot by a bow with infinity enchantment or the shooter is a creative player.
     * An infinite arrow cannot be picked up after being shot.
     *
     * @return {@code true} if the arrow is infinite, {@code false} otherwise.
     */
    boolean isInfinite();

    /**
     * Sets whether the arrow is shot by a bow with infinity enchantment or the shooter is a creative player.
     * An infinite arrow cannot be picked up after being shot.
     *
     * @param infinite {@code true} to make the arrow infinite, {@code false} otherwise
     */
    void setInfinite(boolean infinite);

    /**
     * Checks if the arrow can be picked up by players.
     *
     * @return {@code true} if the arrow cannot be picked up, {@code false} otherwise.
     */
    boolean isPickUpDisabled();

    /**
     * Sets whether the arrow can be picked up by players.
     *
     * @param disablePickUp {@code true} to disable picking up the arrow, {@code false} to enable it
     */
    void setPickUpDisabled(boolean disablePickUp);

    /**
     * Gets the piercing level of the arrow. Piercing allows the arrow to pass through entities.
     *
     * @return the piercing level of the arrow
     */
    int getPiercingLevel();

    /**
     * Sets the piercing level of the arrow. Piercing allows the arrow to pass through entities.
     *
     * @param level the piercing level to set
     */
    void setPiercingLevel(int level);
}
