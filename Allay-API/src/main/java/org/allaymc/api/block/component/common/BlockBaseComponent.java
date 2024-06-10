package org.allaymc.api.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.function.*;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.data.VanillaBlockTags;
import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.common.EntityContainerHolderComponent;
import org.allaymc.api.entity.effect.type.EffectConduitPowerType;
import org.allaymc.api.entity.effect.type.EffectHasteType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.type.EnchantmentAquaAffinityType;
import org.allaymc.api.item.enchantment.type.EnchantmentEfficiencyType;
import org.allaymc.api.world.Dimension;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public interface BlockBaseComponent extends OnNeighborUpdate, OnRandomUpdate, Place, OnPlace, OnInteract, OnReplace, OnScheduledUpdate, BlockComponent {
    BlockType<? extends BlockBehavior> getBlockType();

    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z, Dimension dimension) {
        updateBlockProperty(propertyType, value, x, y, z, dimension, 0);
    }

    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z, Dimension dimension, int layer) {
        var chunk = dimension.getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) return;
        int xIndex = x & 15;
        int zIndex = z & 15;
        var oldBlockState = chunk.getBlockState(xIndex, y, zIndex, layer);
        if (oldBlockState.getBlockType() != getBlockType()) {
            throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + oldBlockState.getBlockType().getIdentifier());
        }
        var newBlockState = oldBlockState.setProperty(propertyType, value);
        if (oldBlockState == newBlockState) return;
        chunk.setBlockState(xIndex, y, zIndex, newBlockState, layer);
        chunk.sendChunkPacket(Dimension.createBlockUpdatePacket(newBlockState, x, y, z, layer));
    }

    default double calculateBreakTime(BlockState blockState, ItemStack usedItem, Entity entity) {
        checkBlockType(blockState);
        double blockHardness = blockState.getBlockAttributes().hardness();
        if (blockHardness == 0) return 0;
        boolean isCorrectTool = isCorrectTool(blockState, usedItem);
        boolean canHarvestWithHand = canHarvestWithHand();
        boolean hasAquaAffinity = false;
        boolean isInWater = false;
        boolean isOnGround = true;
        int hasteEffectLevel = 0;
        int miningFatigueLevel = 0;
        int efficiencyLevel = 0;

        if (entity != null) {
            isInWater = entity.isInWater();
            isOnGround = entity.isOnGround();
            hasteEffectLevel = entity.getEffectLevel(EffectHasteType.HASTE_TYPE);
            // 潮涌核心保证至少两级的急速效果
            if (entity.hasEffect(EffectConduitPowerType.CONDUIT_POWER_TYPE)) {
                hasteEffectLevel = Integer.max(hasteEffectLevel, 2);
            }
            miningFatigueLevel = entity.getEffectLevel(EffectHasteType.HASTE_TYPE);
            if (entity instanceof EntityContainerHolderComponent containerHolder) {
                if (containerHolder.hasContainer(FullContainerType.ARMOR)) {
                    hasAquaAffinity = containerHolder.getContainer(FullContainerType.ARMOR).getItemStack(0).hasEnchantment(EnchantmentAquaAffinityType.AQUA_AFFINITY_TYPE);
                }
                if (containerHolder.hasContainer(FullContainerType.PLAYER_INVENTORY)) {
                    efficiencyLevel = containerHolder.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand().getEnchantmentLevel(EnchantmentEfficiencyType.EFFICIENCY_TYPE);
                }
            }
        }

        // Calculate break time
        double baseTime = ((isCorrectTool || canHarvestWithHand) ? 1.5 : 5.0) * blockHardness;
        double speed = 1.0d / baseTime;
        if (isCorrectTool) {
            // 工具等级（木制，石制，铁制，etc...）加成
            speed *= toolBreakTimeBonus(usedItem, blockState);
            // 工具效率附魔加成
            speed += speedBonusByEfficiency(efficiencyLevel);
        }
        // 实体急迫药水效果加成
        speed *= 1.0d + (0.2d * hasteEffectLevel);
        // 实体挖掘疲劳效果负加成
        if (miningFatigueLevel != 0) speed /= Math.pow(miningFatigueLevel, 3);
        // 在水中但是没有水下速挖掘效果
        if (isInWater && !hasAquaAffinity) speed *= 0.2d;
        // 在半空中
        if (!isInWater && !isOnGround) speed *= 0.2d;
        return 1.0d / speed;
    }

    private static double speedBonusByEfficiency(int efficiencyLevel) {
        if (efficiencyLevel == 0) return 0;
        return efficiencyLevel * efficiencyLevel + 1;
    }

    private double toolBreakTimeBonus(ItemStack usedItem, BlockState blockState) {
        // TODO: 实现剑破坏蜘蛛网，剪刀破坏羊毛和树叶和蜘蛛网的加速
        var itemType = usedItem.getItemType();
        if (itemType.hasItemTag(VanillaItemTags.GOLDEN_TIER)) return 12.0;
        if (itemType.hasItemTag(VanillaItemTags.NETHERITE_TIER)) return 9.0;
        if (itemType.hasItemTag(VanillaItemTags.DIAMOND_TIER)) return 8.0;
        if (itemType.hasItemTag(VanillaItemTags.IRON_TIER)) return 6.0;
        if (itemType.hasItemTag(VanillaItemTags.STONE_TIER)) return 4.0;
        if (itemType.hasItemTag(VanillaItemTags.WOODEN_TIER)) return 2.0;
        return 1.0;
    }

    default boolean isCorrectTool(BlockState blockState, ItemStack usedItem) {
        checkBlockType(blockState);
        return false;
    }

    /**
     * 部分方块（例如石头，黑曜石）不可以被徒手挖去，强行挖取它们不会掉落任何物品，
     * 且挖掘速度会受到惩罚(baseTime增大5倍，正常是1.5倍)
     * @return 是否可以徒手挖取
     */
    default boolean canHarvestWithHand() {
        return true;
    }

    private void checkBlockType(BlockState blockState) {
        if (blockState.getBlockType() != getBlockType()) throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + blockState.getBlockType().getIdentifier());
    }
}
