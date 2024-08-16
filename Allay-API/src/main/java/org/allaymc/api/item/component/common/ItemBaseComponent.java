package org.allaymc.api.item.component.common;

import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.type.AbstractEnchantmentProtectionType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.joml.Vector3ic;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.allaymc.api.data.VanillaItemTags.*;
import static org.allaymc.api.data.VanillaMaterialTypes.*;
import static org.allaymc.api.item.type.ItemTypes.SHEARS;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemBaseComponent extends ItemComponent {

    int EMPTY_STACK_NETWORK_ID = 0;

    ItemType<? extends ItemStack> getItemType();

    int getCount();

    boolean isFull();

    void setCount(int count);

    default void reduceCount(int count) {
        setCount(getCount() - count);
    }

    default void increaseCount(int count) {
        setCount(getCount() + count);
    }

    int getMeta();

    void setMeta(int meta);

    int getDurability();

    void setDurability(int durability);

    boolean isBroken();

    void increaseDurability(int addition);

    boolean willDamageItem();

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
        setStackNetworkId(EMPTY_STACK_NETWORK_ID);
    }

    default ItemStack copy() {
        return copy(true);
    }

    ItemStack copy(boolean newStackNetworkId);

    NbtMap saveExtraTag();

    NbtMap getCustomNBTContent();

    void setCustomNBTContent(NbtMap customNBTContent);

    void loadExtraTag(NbtMap extraTag);

    /**
     * Called when player right-click a block no matter the return value of {@code player.isUsingItemOnBlock()}
     *
     * @param dimension     The dimension the player is in
     * @param placeBlockPos The position of the block being right-clicked
     * @param interactInfo  Information about the interaction
     */
    default void rightClickItemOn(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {}

    /**
     * Attempt to use this item on a block.
     * <p>
     * This method will be called only when client thinks "he can" use the item. In other words, when {@code player.isUsingItemOnBlock()} return {@code true}.
     * This method should handle reducing item count, durability, etc., on successful use.
     * No need to send item updates separately as the caller will handle it.
     * <p>
     * Note: Placing blocks will not invoke this method.
     *
     * @return true if successfully used
     */
    default boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        return false;
    }

    /**
     * Attempt to place a block using this item, regardless of whether this item is a block item. <br>
     * This method won't decide the block state depending on the placement info <br>
     * Use which block state really will be decided in BlockBaseComponent::place() method <br>
     *
     * @return {@code true} if the block is successfully placed,
     * {@code false} if placement fails (reasons: not a block item, placement check fails, event is canceled)
     */
    default boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return false;
    }

    /**
     * Called when the player clicked item in air
     *
     * @param player The player who clicked
     */
    default void clickItemInAir(EntityPlayer player) {}

    /**
     * Called before the player prepares to use the item
     *
     * @param player The player preparing to use the item
     *
     * @return Whether this item can be used
     */
    boolean canUseItemInAir(EntityPlayer player);

    /**
     * Called when the player thinks he has finished using an item
     *
     * @param player   The player using the item
     * @param usedTime The time spent
     *
     * @return Whether the item was used
     */
    boolean useItemInAir(EntityPlayer player, long usedTime);

    /**
     * Called when the player releases the item after holding right-click for a period
     */
    default void releaseUsingItem(EntityPlayer player, long usedTime) {}

    default boolean canMerge(ItemStack itemStack) {
        return canMerge(itemStack, false);
    }

    boolean canMerge(ItemStack itemStack, boolean ignoreCount);

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

    default boolean hasProtectionEnchantment() {
        return getEnchantments().stream().anyMatch(enchantmentInstance -> enchantmentInstance.getType() instanceof AbstractEnchantmentProtectionType);
    }

    default int getEnchantmentProtectionFactor(DamageContainer.DamageType damageType) {
        return getEnchantments().stream()
                .filter(enchantmentInstance -> enchantmentInstance.getType() instanceof AbstractEnchantmentProtectionType)
                .mapToInt(enchantmentInstance -> ((AbstractEnchantmentProtectionType) enchantmentInstance.getType()).getProtectionFactor(damageType, enchantmentInstance.getLevel()))
                .sum();
    }

    int getEnchantmentLevel(EnchantmentType enchantmentType);

    Collection<EnchantmentInstance> getEnchantments();

    void addEnchantment(EnchantmentType enchantmentType, int level);

    void removeEnchantment(EnchantmentType enchantmentType);

    void removeAllEnchantments();

    /**
     * Called when the item is used to break a block.
     *
     * @param block   The block being broken
     * @param breaker The entity breaking the block
     */
    void onBreakBlock(BlockState block, Entity breaker);

    /**
     * Called when the item is used to attack an entity.
     *
     * @param attacker The entity attacking
     * @param victim   The entity being attacked
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
        if (itemType == SHEARS) {
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
        double blockHardness = blockState.getBlockStateData().hardness();
        // Blocks with hardness of 0 can be instantly broken
        if (blockHardness == 0) return true;
        if (getItemType().hasItemTag(IS_SWORD)) {
            // Swords can break bamboo instantly
            return blockState.getBlockType() == BlockTypes.BAMBOO;
        }
        return false;
    }

    default boolean checkEnchantmentCompatibility(EnchantmentType type) {
        return getIncompatibleEnchantmentTypes(type).isEmpty();
    }

    default Set<EnchantmentType> getIncompatibleEnchantmentTypes(EnchantmentType type) {
        return getEnchantments().stream()
                .map(EnchantmentInstance::getType)
                .filter(enchantmentInstanceType -> enchantmentInstanceType.checkIncompatible(type))
                .collect(Collectors.toSet());
    }
}
