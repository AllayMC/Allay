package org.allaymc.api.entity.data;

/**
 * EntityAnimation represents an animation that may be played on an entity from an active resource pack on
 * the client.
 *
 * @param name          the name of the animation to be played
 * @param nextState     the state to transition to after the animation has finished playing within the animation controller
 * @param controller    the name of the controller to be used for the animation. controller with the same name must be defined
 *                      in a resource pack for it to work
 * @param stopCondition the condition that must be met for the animation to stop playing. This is often a MoLang expression
 *                      that can be used to query various entity properties to determine when the animation should stop playing
 */
public record EntityAnimation(String name, String nextState, String controller, String stopCondition) {
}
