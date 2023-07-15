package cn.allay.api.entity.component.impl.inventory;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.inventory.InventoryViewer;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface EntityInventoryViewerComponent extends InventoryViewer {
    @Override
    @Inject
    int assignInventoryId();
}
