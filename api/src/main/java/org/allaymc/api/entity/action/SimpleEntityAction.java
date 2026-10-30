package org.allaymc.api.entity.action;

/**
 * {@code SimpleEntityAction} identifies client-visible entity actions that
 * need no additional action payload. The entity packet encoder maps each
 * constant to its protocol animation or event, while callers separately
 * perform gameplay effects such as damage, eating, or removal.
 *
 * @author daoge_cmd
 */
public enum SimpleEntityAction implements EntityAction {
    /**
     * Arm swing animation for the acting entity.
     */
    SWING_ARM,
    /**
     * Hurt animation indicating a recent hit to viewers.
     */
    HURT,
    /**
     * Death animation for an entity being shown as dead.
     */
    DEATH,
    /**
     * Eating particles using the entity's held item.
     */
    EAT,
    /**
     * Firework explosion event for the rocket entity.
     */
    FIREWORK_EXPLODE,
    /**
     * Totem activation particles and the associated client animation.
     */
    TOTEM_USE,
    /**
     * Fishing hook bite event with client splash effects.
     */
    FISHING_HOOK_BITE,
    /**
     * Wake animation when the entity leaves its sleeping pose.
     */
    WAKE_UP,
    /**
     * Heart particles indicating the entity's breeding state.
     */
    IN_LOVE,
    /**
     * Grass eating animation used by grazing entities.
     */
    EAT_GRASS
}
