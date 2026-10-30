package org.allaymc.api.entity.data;

/**
 * {@code EntityAnimation} describes a resource-pack animation requested for an
 * entity on the client. The packet encoder forwards the animation, controller,
 * next state, and stop expression to clients; the active client resource packs
 * must supply the referenced animation definitions.
 *
 * @param name resource-pack animation identifier sent to the client
 * @param nextState controller state to enter after the animation finishes
 * @param controller resource-pack animation controller identifier
 * @param stopCondition client-evaluated MoLang expression controlling animation termination
 */
public record EntityAnimation(String name, String nextState, String controller, String stopCondition) {
}
