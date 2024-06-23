package org.allaymc.server.item.component.armor;

import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.type.EnchantmentUnbreakingType;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Allay Project 2024/1/27
 *
 * @author daoge_cmd
 */
public class ItemArmorBaseComponentImpl<T extends ItemStack> extends ItemBaseComponentImpl<T> {
    public ItemArmorBaseComponentImpl(ItemStackInitInfo<T> initInfo) {
        super(initInfo);
    }

    @Override
    public boolean useItemOn(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) return false;
        equipArmor(interactInfo.player(), thisItemStack);
        return true;
    }

    @Override
    public boolean useItemInAir(EntityPlayer player) {
        equipArmor(player, thisItemStack);
        return true;
    }

    @Override
    public boolean willDamageItem() {
        float level = getEnchantmentLevel(EnchantmentUnbreakingType.UNBREAKING_TYPE);
        if (level == 0) return true;
        float possibility = 0.6f + 0.4f / (level + 1f);
        return ThreadLocalRandom.current().nextFloat() <= possibility;
    }

    protected void equipArmor(EntityPlayer player, ItemStack itemStack) {
        var armorType = getArmorType();
        var armorContainer = player.getContainer(FullContainerType.ARMOR);
        if (armorContainer.isEmpty(armorType.ordinal())) {
            player.getContainer(FullContainerType.PLAYER_INVENTORY).clearItemInHand();
        } else {
            // Swap armor
            player.getContainer(FullContainerType.PLAYER_INVENTORY).setItemInHand(armorContainer.getItemStack(armorType.ordinal()));
        }
        armorContainer.setItemStack(getArmorType().ordinal(), itemStack);
    }

    protected ArmorType getArmorType() {
        var identifier = getItemType().getIdentifier().toString();
        if (identifier.contains("helmet")) return ArmorType.HELMET;
        if (identifier.contains("chestplate")) return ArmorType.CHESTPLATE;
        if (identifier.contains("leggings")) return ArmorType.LEGGINGS;
        if (identifier.contains("boots")) return ArmorType.BOOTS;
        return null;
    }

    public enum ArmorType {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }
}
