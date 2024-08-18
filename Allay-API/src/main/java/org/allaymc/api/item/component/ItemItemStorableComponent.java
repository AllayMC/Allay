package org.allaymc.api.item.component;

import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

/**
 * Allay Project 2024/6/21
 *
 * @author daoge_cmd
 */
public interface ItemItemStorableComponent extends ItemComponent {
    @UnmodifiableView
    List<NbtMap> getStoredItems();

    void setStoredItems(List<NbtMap> items);
}
