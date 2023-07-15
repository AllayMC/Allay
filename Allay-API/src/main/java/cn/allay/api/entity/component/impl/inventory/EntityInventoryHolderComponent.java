package cn.allay.api.entity.component.impl.inventory;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.inventory.Inventory;
import cn.allay.api.inventory.InventoryHolder;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface EntityInventoryHolderComponent extends InventoryHolder {
    @UnmodifiableView
    @Override
    @Inject
    Map<ContainerType, Inventory> getInventories();

    @Override
    @Nullable
    @Inject
    Inventory getInventory(ContainerType type);

    @Override
    @Inject
    void addInventory(Inventory inventory);
}
