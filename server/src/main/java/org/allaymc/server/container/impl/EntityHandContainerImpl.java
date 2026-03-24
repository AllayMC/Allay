package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.EntityHandContainer;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class EntityHandContainerImpl extends BaseContainer implements EntityHandContainer {

    protected final Supplier<? extends Entity> entitySupplier;

    public EntityHandContainerImpl(Supplier<? extends Entity> entitySupplier) {
        super(ContainerTypes.ENTITY_HAND);
        this.entitySupplier = entitySupplier;
        addSlotChangeListener(0, this::onHandChange);
    }

    protected void onHandChange(ItemStack newItemStack) {
        var entity = entitySupplier.get();
        if (entity instanceof EntityContainerHolderComponent) {
            entity.forEachViewers(viewer -> viewer.viewEntityHand((Entity & EntityContainerHolderComponent) entity));
        }
    }

    @Override
    public ItemStack getItemInHand() {
        return getItemStack(0);
    }

    @Override
    public void setItemInHand(ItemStack itemStack) {
        setItemStack(0, itemStack);
    }

    @Override
    public void clearItemInHand() {
        setItemStack(0, ItemAirStack.AIR_STACK);
    }
}
