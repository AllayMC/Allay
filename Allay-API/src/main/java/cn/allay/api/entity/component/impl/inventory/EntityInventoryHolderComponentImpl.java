package cn.allay.api.entity.component.impl.inventory;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.inventory.Inventory;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public class EntityInventoryHolderComponentImpl implements EntityInventoryHolderComponent, EntityComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_inventory_holder_component");
    protected final Map<ContainerType, Inventory> inventories = new HashMap<>();

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @UnmodifiableView
    @Override
    @Impl
    public Map<ContainerType, Inventory> getInventories() {
        return Collections.unmodifiableMap(inventories);
    }

    @Nullable
    @Override
    @Impl
    public Inventory getInventory(ContainerType type) {
        return inventories.get(type);
    }

    @Override
    @Impl
    public void addInventory(Inventory inventory) {
        if (inventories.containsKey(inventory.getType()))
            throw new IllegalArgumentException("Inventory " + inventory.getType() + "already exists");
        inventories.put(inventory.getType(), inventory);
    }
}
