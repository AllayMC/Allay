package org.allaymc.api.entity.component;

/**
 * {@code EntityArrowBaseComponent} exposes arrow impact properties,
 * enchantment levels, and pickup flags. Arrow physics consumes these values
 * when calculating hits, while player pickup handling combines {@code
 * inGround}, {@code infinite}, and {@code pickUpDisabled} to decide whether an
 * overlapping arrow becomes an inventory item.
 *
 * @author daoge_cmd
 */
public interface EntityArrowBaseComponent extends EntityBaseComponent {

    /**
     * Returns whether the arrow carries the critical-impact and particle flag.
     */
    boolean isCritical();

    /**
     * Changes the critical-impact flag and publishes the updated entity state.
     * Critical damage handling reads this flag on entity impact, and block
     * impact clears it when the arrow becomes embedded.
     */
    void setCritical(boolean critical);

    /**
     * Returns whether the arrow is currently marked as embedded in a block.
     */
    boolean isInGround();

    /**
     * Changes the stored flag that marks the arrow as embedded. Arrow physics
     * uses this flag to suppress motion while block collision remains, and
     * player pickup requires it.
     */
    void setInGround(boolean inGround);

    /**
     * Returns the arrow base damage before impact-specific damage adjustments.
     */
    float getBaseDamage();

    /**
     * Stores the arrow base damage used by later impact calculations.
     */
    void setBaseDamage(float damage);

    /**
     * Returns the stored Power enchantment level used by arrow damage.
     */
    int getPowerLevel();

    /**
     * Stores the Power enchantment level used by later entity impacts.
     */
    void setPowerLevel(int level);

    /**
     * Returns the stored Punch enchantment level used by impact knockback.
     */
    int getPunchLevel();

    /**
     * Stores the Punch enchantment level used by later impact knockback.
     */
    void setPunchLevel(int level);

    /**
     * Returns whether pickup removes this arrow without granting an item.
     * Creative shots and applicable Infinity shots set this flag; {@code
     * isPickUpDisabled()} takes precedence and prevents that pickup handling
     * entirely.
     */
    boolean isInfinite();

    /**
     * Changes whether pickup discards this arrow instead of granting an item.
     * This flag applies only after pickup handling accepts the embedded arrow
     * and its pickup-disabled flag is clear.
     */
    void setInfinite(boolean infinite);

    /**
     * Returns whether player pickup handling skips this arrow entirely. A
     * disabled arrow remains in the world during that handling, including when
     * the separate infinite flag is set.
     */
    boolean isPickUpDisabled();

    /**
     * Changes whether players may process this arrow during pickup. Passing
     * {@code true} leaves an overlapping embedded arrow untouched by pickup,
     * while {@code false} allows the remaining pickup rules to run.
     */
    void setPickUpDisabled(boolean disablePickUp);

    /**
     * Returns the stored Piercing level that controls continued entity
     * penetration.
     */
    int getPiercingLevel();

    /**
     * Stores the Piercing level used when deciding whether impacts remove the
     * arrow.
     */
    void setPiercingLevel(int level);
}
