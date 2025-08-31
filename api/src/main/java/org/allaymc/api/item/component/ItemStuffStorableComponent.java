package org.allaymc.api.item.component;

import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * ItemStuffStorableComponent is used in item that can store items, such as shulker boxes.
 *
 * @author daoge_cmd
 */
public interface ItemStuffStorableComponent extends ItemComponent {
    /**
     * Get the stored items.
     *
     * @return The stored items
     */
    @UnmodifiableView
    List<NbtMap> getStoredItems();

    /**
     * Set the stored items.
     *
     * @param items The stored items
     */
    void setStoredItems(List<NbtMap> items);
}
