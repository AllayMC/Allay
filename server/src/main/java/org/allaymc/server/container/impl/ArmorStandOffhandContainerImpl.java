package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.OffhandContainer;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * Offhand container for armor stands.
 *
 * @author daoge_cmd
 */
public class ArmorStandOffhandContainerImpl extends BaseContainer implements OffhandContainer {

    protected final Supplier<EntityArmorStand> armorStandSupplier;

    public ArmorStandOffhandContainerImpl(Supplier<EntityArmorStand> armorStandSupplier) {
        super(ContainerTypes.OFFHAND);
        this.armorStandSupplier = armorStandSupplier;
        addSlotChangeListener(0, this::onOffhandChange);
    }

    protected void onOffhandChange(ItemStack newItemStack) {
        armorStandSupplier.get().forEachViewers(viewer -> viewer.viewEntityOffhand(armorStandSupplier.get()));
    }
}
