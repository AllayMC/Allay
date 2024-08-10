package org.allaymc.server.item.component;

import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.data.VanillaEnchantmentTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Allay Project 2024/1/27
 *
 * @author daoge_cmd
 */
public class ItemArmorBaseComponentImpl extends ItemBaseComponentImpl implements ItemArmorBaseComponent {
    protected ArmorType armorType;

    public ItemArmorBaseComponentImpl(ItemStackInitInfo initInfo, ArmorType armorType) {
        super(initInfo);
        this.armorType = armorType;
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) return false;
        equipArmor(interactInfo.player(), thisItemStack);
        return true;
    }

    @Override
    public void clickItemInAir(EntityPlayer player) {
        equipArmor(player, thisItemStack);
    }

    @Override
    public boolean willDamageItem() {
        var level = getEnchantmentLevel(VanillaEnchantmentTypes.UNBREAKING);
        if (level == 0) return true;

        var possibility = 0.6f + 0.4f / (level + 1f);
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

    @Override
    public ArmorType getArmorType() {
        return armorType;
    }
}
