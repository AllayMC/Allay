package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * Armor container for armor stands.
 *
 * @author daoge_cmd
 */
public class ArmorStandArmorContainerImpl extends BaseContainer implements ArmorContainer {

    protected final Supplier<EntityArmorStand> armorStandSupplier;

    public ArmorStandArmorContainerImpl(Supplier<EntityArmorStand> armorStandSupplier) {
        super(ContainerTypes.ARMOR);
        this.armorStandSupplier = armorStandSupplier;
        addSlotChangeListener(0, this::onArmorChange);
        addSlotChangeListener(1, this::onArmorChange);
        addSlotChangeListener(2, this::onArmorChange);
        addSlotChangeListener(3, this::onArmorChange);
    }

    protected void onArmorChange(ItemStack newItemStack) {
        var armorStand = armorStandSupplier.get();
        armorStand.forEachViewers(viewer -> viewer.viewEntityArmors(armorStand));
    }
}
