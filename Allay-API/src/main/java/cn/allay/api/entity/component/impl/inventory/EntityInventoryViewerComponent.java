package cn.allay.api.entity.component.impl.inventory;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.inventory.Inventory;
import cn.allay.api.inventory.InventoryViewer;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface EntityInventoryViewerComponent extends InventoryViewer {
    @Override
    @Inject
    byte assignInventoryId();

    @Override
    @Inject
    void sendContents(Inventory inventory);

    @Override
    @Inject
    void sendContent(Inventory inventory, int slot);

    @Override
    @Inject
    void onOpen(byte assignedId, Inventory inventory);

    @Override
    @Inject
    void onClose(byte assignedId, Inventory inventory);


    @Override
    @Inject
    @Nullable
    Inventory getOpenedInventory();
}
