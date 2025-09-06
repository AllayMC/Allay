package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.UnopenedContainerId;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ArmorTier;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.protocol.bedrock.packet.MobArmorEquipmentPacket;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class PlayerArmorContainer extends PlayerContainer {

    public static final int HELMET_SLOT = 0;
    public static final int CHESTPLATE_SLOT = 1;
    public static final int LEGGINGS_SLOT = 2;
    public static final int BOOTS_SLOT = 3;
    public static final float KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR = 0.1f;

    public PlayerArmorContainer(Supplier<EntityPlayer> playerSupplier) {
        super(FullContainerType.ARMOR, playerSupplier);
        addSlotChangeListener(0, this::onArmorChange);
        addSlotChangeListener(1, this::onArmorChange);
        addSlotChangeListener(2, this::onArmorChange);
        addSlotChangeListener(3, this::onArmorChange);
    }

    protected void onArmorChange(ItemStack newItemStack) {
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
        player.sendPacketToViewers(buildArmorEquipmentPacket(player.getRuntimeId()));
    }

    public void sendArmorEquipmentPacketTo(EntityPlayer player) {
        player.sendPacket(buildArmorEquipmentPacket(playerSupplier.get().getRuntimeId()));
    }

    private @NotNull MobArmorEquipmentPacket buildArmorEquipmentPacket(long runtimeId) {
        var packet = new MobArmorEquipmentPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setBody(ItemAirStack.AIR_STACK.toNetworkItemData());
        packet.setHelmet(getHelmet().toNetworkItemData());
        packet.setChestplate(getChestplate().toNetworkItemData());
        packet.setLeggings(getLeggings().toNetworkItemData());
        packet.setBoots(getBoots().toNetworkItemData());
        return packet;
    }

    public ItemStack getHelmet() {
        return getItemStack(HELMET_SLOT);
    }

    public void setHelmet(ItemStack itemStack) {
        setItemStack(HELMET_SLOT, itemStack);
    }

    public ItemStack getChestplate() {
        return getItemStack(CHESTPLATE_SLOT);
    }

    public void setChestplate(ItemStack itemStack) {
        setItemStack(CHESTPLATE_SLOT, itemStack);
    }

    public ItemStack getLeggings() {
        return getItemStack(LEGGINGS_SLOT);
    }

    public void setLeggings(ItemStack itemStack) {
        setItemStack(LEGGINGS_SLOT, itemStack);
    }

    public ItemStack getBoots() {
        return getItemStack(BOOTS_SLOT);
    }

    public void setBoots(ItemStack itemStack) {
        setItemStack(BOOTS_SLOT, itemStack);
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.ARMOR;
    }
}
