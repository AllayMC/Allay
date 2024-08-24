package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.data.ArmorTier;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.cloudburstmc.protocol.bedrock.packet.MobArmorEquipmentPacket;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
public class PlayerArmorContainer extends BaseContainer {

    public static final int HELMET_SLOT = 0;
    public static final int CHESTPLATE_SLOT = 1;
    public static final int LEGGINGS_SLOT = 2;
    public static final int BOOTS_SLOT = 3;
    public static final float KNOCKBACK_RESISTANCE_PER_NETHERITE_ARMOR = 0.1f;

    protected Supplier<EntityPlayer> playerSupplier;

    public PlayerArmorContainer(Supplier<EntityPlayer> playerSupplier) {
        super(FullContainerType.ARMOR);
        this.playerSupplier = playerSupplier;
        addOnSlotChangeListener(0, this::onArmorChange);
        addOnSlotChangeListener(1, this::onArmorChange);
        addOnSlotChangeListener(2, this::onArmorChange);
        addOnSlotChangeListener(3, this::onArmorChange);
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
        player.setAttribute(AttributeType.KNOCKBACK_RESISTANCE, knockbackResistance);
        // Send armor to viewers
        player.sendPacketToViewers(buildArmorEquipmentPacket(player.getRuntimeId()));
    }

    public void sendArmorEquipmentPacketTo(EntityPlayer player) {
        player.sendPacket(buildArmorEquipmentPacket(playerSupplier.get().getRuntimeId()));
    }

    private @NotNull MobArmorEquipmentPacket buildArmorEquipmentPacket(long runtimeId) {
        var pk = new MobArmorEquipmentPacket();
        pk.setRuntimeEntityId(runtimeId);
        pk.setBody(ItemAirStack.AIR_STACK.toNetworkItemData());
        pk.setHelmet(getHelmet().toNetworkItemData());
        pk.setChestplate(getChestplate().toNetworkItemData());
        pk.setLeggings(getLeggings().toNetworkItemData());
        pk.setBoots(getBoots().toNetworkItemData());
        return pk;
    }

    public ItemStack getHelmet() {
        return getItemStack(HELMET_SLOT);
    }

    public ItemStack getChestplate() {
        return getItemStack(CHESTPLATE_SLOT);
    }

    public ItemStack getLeggings() {
        return getItemStack(LEGGINGS_SLOT);
    }

    public ItemStack getBoots() {
        return getItemStack(BOOTS_SLOT);
    }

    public void setHelmet(ItemStack itemStack) {
        setItemStack(HELMET_SLOT, itemStack);
    }

    public void setChestplate(ItemStack itemStack) {
        setItemStack(CHESTPLATE_SLOT, itemStack);
    }

    public void setLeggings(ItemStack itemStack) {
        setItemStack(LEGGINGS_SLOT, itemStack);
    }

    public void setBoots(ItemStack itemStack) {
        setItemStack(BOOTS_SLOT, itemStack);
    }
}
