package org.allaymc.api.block.component.common;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.block.function.*;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.common.EntityContainerHolderComponent;
import org.allaymc.api.entity.effect.type.EffectConduitPowerType;
import org.allaymc.api.entity.effect.type.EffectHasteType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.type.EnchantmentAquaAffinityType;
import org.allaymc.api.item.enchantment.type.EnchantmentEfficiencyType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.world.Dimension;

import static org.allaymc.api.item.ItemHelper.isSword;

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

    default boolean canDamageItem(ItemType<?> itemType) {
        return true;
    }

    default double calculateBreakTime(BlockState blockState, ItemStack usedItem, Entity entity) {
        checkBlockType(blockState);
        if (usedItem.canInstantBreak(blockState)) return 0;
        float blockHardness = blockState.getBlockAttributes().hardness();
        boolean isCorrectTool = usedItem.isCorrectToolFor(blockState);
        boolean isAlwaysDestroyable = getBlockType().getMaterial().isAlwaysDestroyable();
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
        // TODO: 需要进一步验证算法
        double baseTime = ((isCorrectTool || isAlwaysDestroyable) ? 1.5 : 5.0) * blockHardness;
        double speed = 1.0d / baseTime;
        if (isCorrectTool) {
            // 工具等级（木制，石制，铁制，etc...）加成
            speed *= usedItem.getBreakTimeBonus(blockState);
            // 工具效率附魔加成
            speed += speedBonusByEfficiency(efficiencyLevel);
        } else if (isSword(usedItem.getItemType())) {
            // 剑挖掘方块的最低速率是1.5倍
            speed *= 1.5;
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

    private void checkBlockType(BlockState blockState) {
        if (blockState.getBlockType() != getBlockType()) throw new IllegalArgumentException("Block type is not match! Expected: " + getBlockType().getIdentifier() + ", actual: " + blockState.getBlockType().getIdentifier());
    }
}
