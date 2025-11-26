package org.allaymc.api.entity.action;

/**
 * SimpleEntityAction contains entity actions that do not have additional parameters.
 *
 * @author daoge_cmd
 */
public enum SimpleEntityAction implements EntityAction {
    // SWING_ARM makes an entity or player swing its arm
    SWING_ARM,
    // HURT makes an entity display the animation for being hurt. The entity will be shown as red for a short duration
    HURT,
    // DEATH makes an entity display the death animation. After this animation, the entity disappears from viewers watching it
    DEATH,
    // EAT makes an entity display the eating particles at its mouth to viewers with the item in its hand being eaten
    EAT,
    // FIREWORK_EXPLODE makes a firework rocket display an explosion particle
    FIREWORK_EXPLODE,
    // TOTEM_USE displays the totem use particles and animation
    TOTEM_USE
}
