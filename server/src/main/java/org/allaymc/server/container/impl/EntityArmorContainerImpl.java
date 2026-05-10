package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ArmorTier;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.nbt.NbtMap;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class EntityArmorContainerImpl extends BaseContainer implements ArmorContainer {

    protected static final float KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR = 0.1f;
    protected final Supplier<? extends Entity> entitySupplier;

    public EntityArmorContainerImpl(Supplier<? extends Entity> entitySupplier) {
        super(ContainerTypes.ARMOR);
        this.entitySupplier = entitySupplier;
        addSlotChangeListener(0, this::onArmorChange);
        addSlotChangeListener(1, this::onArmorChange);
        addSlotChangeListener(2, this::onArmorChange);
        addSlotChangeListener(3, this::onArmorChange);
    }

    protected void onArmorChange(ItemStack $) {
        var knockbackResistance = 0.0f;
        for (var itemStack : content) {
            if (itemStack == ItemAirStack.AIR_STACK ||
                ItemHelper.getArmorTier(itemStack.getItemType()) != ArmorTier.NETHERITE) {
                continue;
            }

            knockbackResistance += KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR;
        }

        var entity = entitySupplier.get();
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            physicsComponent.setKnockbackResistance(knockbackResistance);
        }
        if (entity instanceof EntityContainerHolderComponent) {
            entity.forEachViewers(viewer -> viewer.viewEntityArmors((Entity & EntityContainerHolderComponent) entity));
        }
    }

    @Override
    public void loadNBT(List<NbtMap> nbtList) {
        super.loadNBT(nbtList);
        onArmorChange(null);
    }
}
