package cn.allay.api.inventory;

import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface InventoryHolder {
    @UnmodifiableView
    Map<Class<? extends Inventory>, Inventory> getInventories();

    @Nullable
    Inventory getInventory(Class<? extends Inventory> type);

    void addInventory(Inventory inventory);
}
