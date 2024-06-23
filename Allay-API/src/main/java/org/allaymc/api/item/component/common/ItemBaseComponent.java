package org.allaymc.api.item.component.common;

import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.joml.Vector3ic;

import java.util.Collection;
import java.util.List;

import static org.allaymc.api.data.VanillaItemTags.*;
import static org.allaymc.api.data.VanillaMaterialTypes.*;
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

    /**
     * Attempt to use this item on a block.
     * <p>
     * This method should handle reducing item count, durability, etc., on successful use.
     * No need to send item updates separately as the caller will handle it.
     * <p>
     * Note: Placing blocks will not invoke this method.
     *
     * @return true if successfully used
     */
    default boolean useItemOn(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        return false;
    }

    /**
     * Attempt to place a block using this item, regardless of whether this item is a block item.
     *
     * @return {@code true} if the block is successfully placed,
     * {@code false} if placement fails (reasons: not a block item, placement check fails, event is canceled)
     */
    default boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return false;
    }

    default boolean useItemInAir(EntityPlayer player) {
        return false;
    }

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

        // TODO: CanDestroy
        // TODO: CanPlaceOn
        return builder.build();
    }

    float calculateAttackDamage();

    boolean hasEnchantment(EnchantmentType enchantmentType);

    int getEnchantmentLevel(EnchantmentType enchantmentType);

    Collection<EnchantmentInstance> getEnchantments();

    void addEnchantment(EnchantmentType enchantmentType, short level);

    void removeEnchantment(EnchantmentType enchantmentType);

    void removeAllEnchantments();

    /**
     * Called when the item is used to break a block.
     * @param block The block being broken
     * @param breaker The entity breaking the block
     */
    void onBreakBlock(BlockState block, Entity breaker);

    /**
     * Called when the item is used to attack an entity.
     * @param attacker The entity attacking
     * @param victim The entity being attacked
     */
    void onAttackEntity(Entity attacker, Entity victim);

    /**
     * Get the break time bonus for breaking a block with this item.
     *
     * @param blockState The block to be broken
     *
     * @return Break time bonus
     */
    default double getBreakTimeBonus(BlockState blockState) {
        var itemType = getItemType();
        var materialType = blockState.getBlockType().getMaterial().materialType();
        // Swords break cobwebs faster
        if (itemType.hasItemTag(IS_SWORD)) {
            if (materialType == WEB) return 15.0;
        }
        if (itemType == SHEARS_TYPE) {
            // Shears break wool and leaves faster
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
     * Check if this item is the correct tool for breaking the specified block.
     * <p>
     * If it's not the correct tool, breaking the block with this item will result in speed penalty and no drops.
     *
     * @param blockState The block to be broken
     *
     * @return Whether it is the correct tool
     */
    boolean isCorrectToolFor(BlockState blockState);

    default boolean canInstantBreak(BlockState blockState) {
        double blockHardness = blockState.getBlockAttributes().hardness();
        // Blocks with hardness of 0 can be instantly broken
        if (blockHardness == 0) return true;
        if (getItemType().hasItemTag(IS_SWORD)) {
            // Swords can instantly break bamboo
            return blockState.getBlockType() == BlockTypes.BAMBOO_TYPE;
        }
        return false;
    }

    default boolean checkEnchantmentCompatibility(EnchantmentType type) {
        return getIncompatibleEnchantmentType(type) == null;
    }

    default EnchantmentType getIncompatibleEnchantmentType(EnchantmentType type) {
        for (var enchantmentInstance : getEnchantments()) {
            if (enchantmentInstance.getType().checkIncompatible(type)) {
                return enchantmentInstance.getType();
            }
        }
        return null;
    }
}
