package org.allaymc.api.item.component.data;

import org.allaymc.api.item.component.ItemComponent;

/**
 * @author daoge_cmd
 */
public interface ItemDataComponent extends ItemComponent {
    /**
     * Get the item data.
     *
     * @return The item data.
     */
    ItemData getItemData();
}
