package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.EntityHandContainer;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.function.Supplier;

/**
 * {@code EntityHandContainerImpl} stores one main-hand stack for non-inventory entity
 * equipment. Its slot listener resolves the owner and publishes held-item equipment to
 * entity viewers when the owner supports container access, while the named hand
 * accessors use that same storage slot.
 *
 * @author daoge_cmd
 */
public class EntityHandContainerImpl extends BaseContainer implements EntityHandContainer {

    /**
     * {@code entitySupplier} resolves the owner during hand changes. Only owners
     * implementing container access produce a held-equipment update from this listener.
     */
    protected final Supplier<? extends Entity> entitySupplier;

    /**
     * Creates empty main-hand storage and registers its equipment listener on slot zero.
     * The owner supplier is retained for each later notification, allowing equipment
     * publication after owner initialization.
     */
    public EntityHandContainerImpl(Supplier<? extends Entity> entitySupplier) {
        super(ContainerTypes.ENTITY_HAND);
        this.entitySupplier = entitySupplier;
        addSlotChangeListener(0, this::onHandChange);
    }

    /**
     * Publishes the owner's current main-hand equipment when it supports container
     * access. The callback resolves the owner at notification time and ignores the
     * supplied stack in favor of owner state.
     */
    protected void onHandChange(ItemStack newItemStack) {
        var entity = entitySupplier.get();
        if (entity instanceof EntityContainerHolderComponent) {
            entity.forEachViewers(viewer -> viewer.viewEntityHand((Entity & EntityContainerHolderComponent) entity));
        }
    }

    /**
     * Returns the shared stack stored in main-hand slot zero. Directly changing this
     * returned stack requires a slot notification to publish both container contents and
     * the owner's held equipment.
     */
    @Override
    public ItemStack getItemInHand() {
        return getItemStack(0);
    }

    /**
     * Stores the supplied non-{@code null} stack in main-hand slot zero. Ordinary slot
     * notification follows assignment and also runs the registered equipment listener
     * for the resolved owner.
     */
    @Override
    public void setItemInHand(ItemStack itemStack) {
        setItemStack(0, itemStack);
    }

    /**
     * Replaces main-hand slot zero with the shared air sentinel. The ordinary assignment
     * notification updates container viewers and runs the held-equipment listener for
     * owners that support container access.
     */
    @Override
    public void clearItemInHand() {
        setItemStack(0, ItemAirStack.AIR_STACK);
    }
}
