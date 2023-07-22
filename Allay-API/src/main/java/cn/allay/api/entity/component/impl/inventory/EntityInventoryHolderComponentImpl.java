package cn.allay.api.entity.component.impl.inventory;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.inventory.Inventory;
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
    protected final Map<Class<? extends Inventory>, Inventory> inventories = new HashMap<>();


    public EntityInventoryHolderComponentImpl() {
    }

    public EntityInventoryHolderComponentImpl(Inventory... inventories) {
        for (Inventory inventory : inventories) {
            addInventory(inventory);
        }
    }

    @UnmodifiableView
    @Override
    @Impl
    public Map<Class<? extends Inventory>, Inventory> getInventories() {
        return Collections.unmodifiableMap(inventories);
    }

    @Nullable
    @Override
    @Impl
    public Inventory getInventory(Class<? extends Inventory> type) {
        return inventories.get(type);
    }

    @Override
    @Impl
    public void addInventory(Inventory inventory) {
        if (inventories.containsKey(inventory.getClass()))
            throw new IllegalArgumentException("Inventory " + inventory.getType() + "already exists");
        inventories.put(inventory.getClass(), inventory);
    }

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
