package org.allaymc.api.item.component.common;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.EnumMap;
import java.util.List;

import static org.allaymc.api.block.type.BlockTypes.BAMBOO_BLOCK_TYPE;
import static org.allaymc.api.data.VanillaItemTags.*;
import static org.allaymc.api.data.VanillaItemTags.WOODEN_TIER;
import static org.allaymc.api.data.VanillaMaterialTypes.*;
import static org.allaymc.api.item.type.ItemTypes.BAMBOO_TYPE;
import static org.allaymc.api.item.type.ItemTypes.SHEARS_TYPE;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemBaseComponent extends ItemComponent {

    int EMPTY_STACK_NETWORK_ID = 0;

    ItemType<? extends ItemStack> getItemType();

    int getCount();

    void setCount(int count);

    int getMeta();

    void setMeta(int meta);

    int getDurability();

    void setDurability(int durability);

    String getCustomName();

    void setCustomName(String customName);

    List<String> getLore();

    void setLore(List<String> lore);

    BlockState toBlockState();

    ItemData toNetworkItemData();

    default boolean hasStackNetworkId() {
        return getStackNetworkId() != EMPTY_STACK_NETWORK_ID;
    }

    int getStackNetworkId();

    void setStackNetworkId(int newStackNetworkId);

    int assignNewStackNetworkId();

    default void clearStackNetworkId() {
        setStackNetworkId(0);
    }

    ItemStack copy();

    ItemStack copy(boolean newStackNetworkId);

    NbtMap saveExtraTag();

    NbtMap getCustomNBTContent();

    void setCustomNBTContent(NbtMap customNBTContent);

    void loadExtraTag(NbtMap extraTag);

    boolean useItemOn(
            EntityPlayer player, Dimension dimension,
            Vector3ic targetBlockPos, Vector3ic placeBlockPos,
            Vector3fc clickPos, BlockFace blockFace);

    boolean useItemInAir(EntityPlayer player);

    default boolean canMerge(ItemStack itemStack) {
        return canMerge(itemStack, false);
    }

    boolean canMerge(ItemStack itemStack, boolean ignoreCount);

    // TODO: boolean useInAir();

    default NbtMap saveNBT() {
        var builder = NbtMap.builder()
                .putByte("Count", (byte) getCount())
                .putShort("Damage", (short) getMeta())
                .putString("Name", getItemType().getIdentifier().toString());
        var extraTag = saveExtraTag();
        if (extraTag != null) {
            builder.putCompound("tag", extraTag);
        }
        var blockState = toBlockState();
        if (blockState != null) {
            builder.put("Block", blockState.getBlockStateTag());
        }
        //TODO: CanDestroy
        //TODO: CanPlaceOn
        return builder.build();
    }

    float calculateAttackDamage();

    boolean hasEnchantment(EnchantmentType enchantmentType);

    short getEnchantmentLevel(EnchantmentType enchantmentType);

    void addEnchantment(EnchantmentType enchantmentType, short level);

    /**
     * 获取破坏方块的加速倍数 <br>
     * @param blockState 要破坏的方块
     * @return 加速倍速
     */
    default double getBreakTimeBonus(BlockState blockState) {
        var itemType = getItemType();
        var materialType = blockState.getBlockType().getMaterial().materialType();
        // 剑破坏蜘蛛网加速
        if (itemType.hasItemTag(IS_SWORD)) {
            if (materialType == WEB) return 15.0;
        }
        if (itemType == SHEARS_TYPE) {
            // 剪刀破坏羊毛和树叶加速
            if (materialType == CLOTH) {
                return 5.0;
            } else if (materialType == WEB || materialType == LEAVES) {
                return 15.0;
            }
            return 1.0;
        }
        if (!itemType.hasItemTag(IS_TOOL)) return 1.0;
        if (itemType.hasItemTag(GOLDEN_TIER)) return 12.0;
        if (itemType.hasItemTag(NETHERITE_TIER)) return 9.0;
        if (itemType.hasItemTag(DIAMOND_TIER)) return 8.0;
        if (itemType.hasItemTag(IRON_TIER)) return 6.0;
        if (itemType.hasItemTag(STONE_TIER)) return 4.0;
        if (itemType.hasItemTag(WOODEN_TIER)) return 2.0;
        return 1.0;
    }

    /**
     * 判断是否是指定方块的正确破坏工具 <br>
     * 若不是正确破坏工具，使用此物品破坏方块时将获得速度惩罚，且不会产生掉落物 <br>
     * @param blockState 要破坏的方块
     * @return 是否是正确工具
     */
    boolean isCorrectToolFor(BlockState blockState);

    default boolean canInstantBreak(BlockState blockState) {
        double blockHardness = blockState.getBlockAttributes().hardness();
        // 硬度为0的方块可以被瞬间破坏
        if (blockHardness == 0) return true;
        if (getItemType().hasItemTag(IS_SWORD)) {
            // 剑可以瞬间破坏竹子
            return blockState.getBlockType() == BlockTypes.BAMBOO_TYPE;
        }
        return false;
    }
}
