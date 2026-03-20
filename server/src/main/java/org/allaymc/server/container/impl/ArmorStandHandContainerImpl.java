package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorStandHandContainer;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.function.Supplier;

/**
 * Hand container for armor stands.
 *
 * @author daoge_cmd
 */
public class ArmorStandHandContainerImpl extends BaseContainer implements ArmorStandHandContainer {

    protected final Supplier<EntityArmorStand> armorStandSupplier;

    public ArmorStandHandContainerImpl(Supplier<EntityArmorStand> armorStandSupplier) {
        super(ContainerTypes.ARMOR_STAND_HAND);
        this.armorStandSupplier = armorStandSupplier;
        addSlotChangeListener(0, this::onHandChange);
    }

    protected void onHandChange(ItemStack newItemStack) {
        var armorStand = armorStandSupplier.get();
        armorStand.forEachViewers(viewer -> viewer.viewEntityHand(armorStand));
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
