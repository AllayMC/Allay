package org.allaymc.api.entity.component;

import org.allaymc.api.item.ItemStack;

/**
 * {@code EntityItemBaseComponent} exposes the stack carried by a dropped item
 * entity. Pickup and merging consume that same mutable stack, while {@code
 * null} represents an entity with no carried item and prevents stack
 * serialization.
 *
 * @author daoge_cmd
 */
public interface EntityItemBaseComponent extends EntityPickableBaseComponent {
    /**
     * Returns the carried stack reference, or {@code null} when the entity is
     * empty. The returned stack is shared with pickup, merge, and
     * serialization handling rather than copied for readers.
     */
    ItemStack getItemStack();

    /**
     * Retains the supplied stack and clears its unique stack network
     * identifier. Passing {@code null} empties the entity; callers supplying a
     * stack observe the identifier change on that same object.
     */
    void setItemStack(ItemStack itemStack);
}
