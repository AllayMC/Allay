package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ArmorTier;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class ArmorContainerImpl extends AbstractPlayerContainer implements ArmorContainer {

    protected static final float KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR = 0.1f;

    public ArmorContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerTypes.ARMOR, playerSupplier);
        addSlotChangeListener(0, this::onArmorChange);
        addSlotChangeListener(1, this::onArmorChange);
        addSlotChangeListener(2, this::onArmorChange);
        addSlotChangeListener(3, this::onArmorChange);
    }

    protected void onArmorChange(ItemStack newItemStack) {
        // Recalculate knockback resistance
        var knockbackResistance = 0.0f;
        for (var itemStack : content) {
            if (itemStack == ItemAirStack.AIR_STACK ||
                ItemHelper.getArmorTier(itemStack.getItemType()) != ArmorTier.NETHERITE) {
                continue;
            }

            knockbackResistance += KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR;
        }
        var player = playerSupplier.get();
        player.setKnockbackResistance(knockbackResistance);
        // Send armor to viewers
        player.forEachViewers(viewer -> viewer.viewEntityArmors(player));
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.ARMOR;
    }
}
