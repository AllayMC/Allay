package org.allaymc.api.entity.action;

import org.allaymc.api.entity.Entity;

/**
 * {@code PickedUpAction} describes a pickup animation linking an entity to its
 * collector. The packet encoder sends the collected entity's identity together
 * with {@code picker}'s runtime identity, allowing clients to display
 * collection; inventory transfer and server removal occur at the caller.
 *
 * @param picker entity displayed as the collector; the packet encoder dereferences this reference, so {@code null} is not usable
 *
 * @author daoge_cmd
 */
public record PickedUpAction(Entity picker) implements EntityAction {
}
