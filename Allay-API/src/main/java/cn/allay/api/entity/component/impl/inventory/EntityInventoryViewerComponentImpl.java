package cn.allay.api.entity.component.impl.inventory;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.identifier.Identifier;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public class EntityInventoryViewerComponentImpl implements EntityInventoryViewerComponent, EntityComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:entity_inventory_viewer_component");

    protected final AtomicInteger idCounter = new AtomicInteger(0);

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    @Impl
    public int assignInventoryId() {
        return idCounter.getAndIncrement();
    }
}
