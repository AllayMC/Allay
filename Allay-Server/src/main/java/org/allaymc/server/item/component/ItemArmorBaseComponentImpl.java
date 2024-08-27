package org.allaymc.server.item.component;

import org.allaymc.api.block.poi.PlayerInteractInfo;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.data.VanillaEnchantmentTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
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
        playerEquipSound(player, itemStack.getItemType());
    }

    protected void playerEquipSound(EntityPlayer player, ItemType<?> itemType) {
        var pos = player.getLocation();
        var dimension = player.getDimension();
        if (itemType == ItemTypes.ELYTRA) {
            dimension.addLevelSoundEvent(pos, SoundEvent.ARMOR_EQUIP_ELYTRA);
            return;
        }
        var tier = ItemHelper.getArmorTier(itemType);
        switch (tier) {
            case LEATHER -> dimension.addLevelSoundEvent(pos, SoundEvent.ARMOR_EQUIP_LEATHER);
            case IRON -> dimension.addLevelSoundEvent(pos, SoundEvent.ARMOR_EQUIP_IRON);
            case CHAIN -> dimension.addLevelSoundEvent(pos, SoundEvent.ARMOR_EQUIP_CHAIN);
            case GOLD -> dimension.addLevelSoundEvent(pos, SoundEvent.ARMOR_EQUIP_GOLD);
            case DIAMOND, NETHERITE -> dimension.addLevelSoundEvent(pos, SoundEvent.ARMOR_EQUIP_DIAMOND);
            default -> dimension.addLevelSoundEvent(pos, SoundEvent.ARMOR_EQUIP_GENERIC);
        }
    }

    @Override
    public ArmorType getArmorType() {
        return armorType;
    }

    @Override
    protected boolean canIncreaseDurabilityThisTime() {
        var unbreakingLevel = getEnchantmentLevel(VanillaEnchantmentTypes.UNBREAKING);
        if (unbreakingLevel == 0) return true;

        var possibility = 0.6f + 0.4f / (unbreakingLevel + 1f);
        return ThreadLocalRandom.current().nextFloat() <= possibility;
    }
}
