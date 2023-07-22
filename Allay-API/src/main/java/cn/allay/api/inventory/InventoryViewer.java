package cn.allay.api.inventory;

import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface InventoryViewer {

    byte assignInventoryId();

    void sendContents(Inventory inventory);

    void sendContent(Inventory inventory, int slot);

    void onOpen(byte assignedId, Inventory inventory);

    void onClose(byte assignedId, Inventory inventory);

    @Nullable
    Inventory getOpenedInventory();
}
