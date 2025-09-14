package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.PlayerArmorContainer;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ArmorTier;
import org.allaymc.api.item.interfaces.ItemAirStack;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class PlayerArmorContainerImpl extends AbstractPlayerContainer implements PlayerArmorContainer {

    public PlayerArmorContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerType.ARMOR, playerSupplier);
        addSlotChangeListener(0, this::onArmorChange);
        addSlotChangeListener(1, this::onArmorChange);
        addSlotChangeListener(2, this::onArmorChange);
        addSlotChangeListener(3, this::onArmorChange);
    }

    @Override
    public void onArmorChange(ItemStack newItemStack) {
        // Recalculate knockback resistance
        var knockbackResistance = AttributeType.KNOCKBACK_RESISTANCE.getDefaultValue();
        for (var itemStack : content) {
            if (itemStack == ItemAirStack.AIR_STACK) continue;
            if (ItemHelper.getArmorTier(itemStack.getItemType()) != ArmorTier.NETHERITE) continue;
            knockbackResistance += KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR;
        }
        var player = playerSupplier.get();
        player.setAttributeValue(AttributeType.KNOCKBACK_RESISTANCE, knockbackResistance);
        // Send armor to viewers
        player.forEachViewers(viewer -> viewer.viewEntityArmors(player));
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.ARMOR;
    }
}
