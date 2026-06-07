package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.OffhandContainer;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * Offhand container for entities such as zombie and armor stand.
 *
 * @author daoge_cmd
 */
public class EntityOffhandContainerImpl extends BaseContainer implements OffhandContainer {

    protected final Supplier<Entity> entitySupplier;

    public EntityOffhandContainerImpl(Supplier<Entity> entitySupplier) {
        super(ContainerTypes.OFFHAND);
        this.entitySupplier = entitySupplier;
        addSlotChangeListener(0, this::onOffhandChange);
    }

    protected void onOffhandChange(ItemStack newItemStack) {
        entitySupplier.get().forEachViewers(viewer -> viewer.viewEntityOffhand((Entity & EntityContainerHolderComponent) entitySupplier.get()));
    }
}
