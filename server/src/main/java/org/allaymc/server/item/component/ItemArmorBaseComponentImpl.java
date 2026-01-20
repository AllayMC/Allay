package org.allaymc.server.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.CauldronLiquid;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.component.ItemDyeableComponent;
import org.allaymc.api.item.data.ArmorType;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.EquipItemSound;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.CAULDRON_LIQUID;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.FILL_LEVEL;

/**
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
        if (interactInfo == null) {
            return false;
        }

        // If dyeable armor (leather armor) clicks on a water cauldron, let cauldron handle it
        if (thisItemStack instanceof ItemDyeableComponent) {
            var clickedBlock = dimension.getBlockState(interactInfo.clickedBlockPos());
            if (clickedBlock.getBlockType() == BlockTypes.CAULDRON) {
                var fillLevel = clickedBlock.getPropertyValue(FILL_LEVEL);
                var liquid = clickedBlock.getPropertyValue(CAULDRON_LIQUID);
                if (fillLevel > 0 && liquid == CauldronLiquid.WATER) {
                    return false;
                }
            }
        }

        equipArmor(interactInfo.player(), thisItemStack);
        return true;
    }

    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        equipArmor(player, thisItemStack);
    }

    @Override
    public boolean canBeDamagedThisTime() {
        var level = getEnchantmentLevel(EnchantmentTypes.UNBREAKING);
        if (level == 0) return true;

        var possibility = 0.6f + 0.4f / (level + 1f);
        return ThreadLocalRandom.current().nextFloat() <= possibility;
    }

    protected void equipArmor(EntityPlayer player, ItemStack itemStack) {
        var armorType = getArmorType();
        var armorContainer = player.getContainer(ContainerTypes.ARMOR);
        if (armorContainer.isEmpty(armorType.ordinal())) {
            player.getContainer(ContainerTypes.INVENTORY).clearItemInHand();
        } else {
            // Swap armor
            player.getContainer(ContainerTypes.INVENTORY).setItemInHand(armorContainer.getItemStack(armorType.ordinal()));
        }
        armorContainer.setItemStack(getArmorType().ordinal(), itemStack);
        player.getDimension().addSound(player.getLocation(), new EquipItemSound(itemStack.getItemType()));
    }

    @Override
    public ArmorType getArmorType() {
        return armorType;
    }
}
