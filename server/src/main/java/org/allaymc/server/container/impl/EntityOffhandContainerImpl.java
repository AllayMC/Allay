package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.OffhandContainer;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * {@code EntityOffhandContainerImpl} stores a non-player entity's offhand stack and
 * publishes offhand equipment through its slot listener. The supplier must resolve a
 * non-{@code null} owner with container access whenever notification occurs, because
 * this listener directly uses that owner's equipment contract.
 *
 * @author daoge_cmd
 */
public class EntityOffhandContainerImpl extends BaseContainer implements OffhandContainer {

    /**
     * {@code entitySupplier} is retained for offhand notifications. It must consistently
     * resolve a non-{@code null} entity implementing container access while its viewers
     * receive equipment updates.
     */
    protected final Supplier<Entity> entitySupplier;

    /**
     * Creates empty offhand storage and installs its equipment listener on slot zero.
     * The retained supplier must resolve a container-capable owner before later slot
     * changes invoke that listener.
     */
    public EntityOffhandContainerImpl(Supplier<Entity> entitySupplier) {
        super(ContainerTypes.OFFHAND);
        this.entitySupplier = entitySupplier;
        addSlotChangeListener(0, this::onOffhandChange);
    }

    /**
     * Publishes the resolved owner's current offhand equipment to its viewers. The
     * callback ignores {@code newItemStack}; the supplier must remain usable and return
     * a container-capable owner throughout viewer iteration.
     */
    protected void onOffhandChange(ItemStack newItemStack) {
        entitySupplier.get().forEachViewers(viewer -> viewer.viewEntityOffhand((Entity & EntityContainerHolderComponent) entitySupplier.get()));
    }
}
