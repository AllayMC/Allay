package org.allaymc.api.entity.action;

import org.allaymc.api.entity.Entity;

/**
 * PickedUpAction makes an item get picked up by a collector. After this animation, the item disappears
 * from viewers watching it.
 *
 * @param picker the picker who picks the entity
 * @author daoge_cmd
 */
public record PickedUpAction(Entity picker) implements EntityAction {
}
