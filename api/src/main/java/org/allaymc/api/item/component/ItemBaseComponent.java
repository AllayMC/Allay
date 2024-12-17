package org.allaymc.api.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
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

import static org.allaymc.api.item.tag.ItemTags.*;
import static org.allaymc.api.item.type.ItemTypes.SHEARS;

/**
 * @author daoge_cmd
 */
public interface ItemBaseComponent extends ItemComponent {

    int EMPTY_STACK_NETWORK_ID = 0;

    /**
     * Get the item type.
     *
     * @return The item type.
     */
    ItemType<? extends ItemStack> getItemType();

    /**
     * Get the item count.
     *
     * @return The item count.
     */
    int getCount();

    /**
     * Set the item count.
     *
     * @param count The item count.
     */
    void setCount(int count);

    /**
     * Reduce the item count.
     *
     * @param count The count to reduce.
     */
    default void reduceCount(int count) {
        setCount(getCount() - count);
    }

    /**
     * Increase the item count.
     *
     * @param count The count to increase.
     */
    default void increaseCount(int count) {
        setCount(getCount() + count);
    }

    /**
     * Check if the item is full.
     * <p>
     * A full item is an item with a count of its max stack size.
     *
     * @return {@code true} if the item is full, {@code false} otherwise.
     */
    boolean isFull();

    /**
     * Get the item meta.
     *
     * @return The item meta.
     */
    int getMeta();

    /**
     * Set the item meta.
     *
     * @param meta The item meta.
     */
    void setMeta(int meta);

    /**
     * Get the item durability.
     *
     * @return The item durability.
     */
    int getDurability();

    /**
     * Set the item durability.
     * <p>
     * Note that the durability is not the same as the meta,
     * and bigger durability means the item is more damaged.
     *
     * @param durability The item durability.
     */
    void setDurability(int durability);

    /**
     * Reduce the item durability.
     *
     * @param reduction The reduction.
     */
    void reduceDurability(int reduction);

    /**
     * Check if the item is broken.
     *
     * @return {@code true} if the item is broken, {@code false} otherwise.
     */
    boolean isBroken();

    /**
     * Check if the item can be damaged this time.
     *
     * @return {@code true} if the item can be damaged this time, {@code false} otherwise.
     */
    boolean canBeDamagedThisTime();

    /**
     * Get the custom name.
     *
     * @return The custom name.
     */
    String getCustomName();

    /**
     * Set the custom name.
     *
     * @param customName The custom name.
     */
    void setCustomName(String customName);

    /**
     * Get the lore.
     *
     * @return The lore.
     */
    List<String> getLore();

    /**
     * Set the lore.
     *
     * @param lore The lore.
     */
    void setLore(List<String> lore);

    /**
     * Get the block state.
     *
     * @return The block state.
     */
    BlockState toBlockState();

    /**
     * Get the network item data.
     *
     * @return The network item data.
     */
    ItemData toNetworkItemData();

    /**
     * Check if the item has a stack network id.
     *
     * @return {@code true} if the item has a stack network id, {@code false} otherwise.
     */
    default boolean hasStackNetworkId() {
        return getStackNetworkId() != EMPTY_STACK_NETWORK_ID;
    }

    /**
     * Get the stack network id.
     *
     * @return The stack network id.
     */
    int getStackNetworkId();

    /**
     * Set the stack network id.
     *
     * @param newStackNetworkId The new stack network id.
     */
    void setStackNetworkId(int newStackNetworkId);

    /**
     * Clear the stack network id.
     */
    default void clearStackNetworkId() {
        setStackNetworkId(EMPTY_STACK_NETWORK_ID);
    }

    /**
     * Copy the item stack.
     *
     * @return The copied item stack.
     */
    default ItemStack copy() {
        return copy(true);
    }

    /**
     * Copy the item stack.
     *
     * @param newStackNetworkId Whether to generate a new stack network id.
     *
     * @return The copied item stack.
     */
    ItemStack copy(boolean newStackNetworkId);

    /**
     * Save the extra tag.
     *
     * @return The extra tag.
     */
    NbtMap saveExtraTag();

    /**
     * Load the extra tag.
     *
     * @param extraTag The extra tag.
     */
    void loadExtraTag(NbtMap extraTag);

    /**
     * Get the custom NBT content.
     * <p>
     * Custom nbt content is stored in the item extra tag, which
     * plugin can store some custom data in it.
     *
     * @return The custom NBT content.
     */
    NbtMap getCustomNBTContent();

    /**
     * Set the custom NBT content.
     *
     * @param customNBTContent The custom NBT content.
     */
    void setCustomNBTContent(NbtMap customNBTContent);

    /**
     * Called when player right-click a block no matter the return value of {@code player.isUsingItemOnBlock()}.
     *
     * @param dimension     The dimension the player is in.
     * @param placeBlockPos The position of the block being right-clicked.
     * @param interactInfo  Information about the interaction.
     */
    void rightClickItemOn(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo);

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
    boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo);

    /**
     * Attempt to place a block using this item, regardless of whether this item is a block item.
     * <p>
     * This method won't decide complex block property value based on the placement info.
     * However, it will decide the base block variant using the information from item_meta_block_state_bimap.nbt.
     * Use which block state really will be decided in BlockBaseComponent::place() method.
     *
     * @param dimension     The dimension.
     * @param placeBlockPos The position where the block will be placed.
     * @param placementInfo The placement information, can be null.
     *
     * @return {@code true} if the block is successfully placed,
     * {@code false} if placement fails (reasons: not a block item, placement check fails, event is canceled).
     */
    default boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return false;
    }

    /**
     * Called when the player clicked item in air.
     *
     * @param player The player who clicked.
     */
    default void clickItemInAir(EntityPlayer player) {}

    /**
     * Called before the player prepares to use the item.
     *
     * @param player The player preparing to use the item.
     *
     * @return Whether this item can be used.
     */
    boolean canUseItemInAir(EntityPlayer player);

    /**
     * Called when the player thinks he has finished using an item.
     *
     * @param player   The player using the item.
     * @param usedTime The time spent.
     *
     * @return Whether the item was used.
     */
    boolean useItemInAir(EntityPlayer player, long usedTime);

    /**
     * Called when the player releases the item after holding right-click for a period.
     */
    default void releaseUsingItem(EntityPlayer player, long usedTime) {}

    /**
     * Check if the item can merge with the specified item stack.
     *
     * @param itemStack The item stack to merge.
     *
     * @return {@code true} if the item can merge, {@code false} otherwise.
     */
    default boolean canMerge(ItemStack itemStack) {
        return canMerge(itemStack, false);
    }

    /**
     * Check if the item can merge with the specified item stack.
     *
     * @param itemStack   The item stack to merge.
     * @param ignoreCount Whether to ignore the count.
     *
     * @return {@code true} if the item can merge, {@code false} otherwise.
     */
    boolean canMerge(ItemStack itemStack, boolean ignoreCount);

    /**
     * Save the item to NBT.
     *
     * @return The NBT.
     */
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

    /**
     * Calculate the attack damage.
     *
     * @return The attack damage.
     */
    float calculateAttackDamage();

    /**
     * Check if the item has the specified enchantment.
     *
     * @return {@code true} if the item has the specified enchantment, {@code false} otherwise.
     */
    boolean hasEnchantment(EnchantmentType enchantmentType);

    /**
     * Check if the item has any enchantments.
     *
     * @return {@code true} if the item has enchantments, {@code false} otherwise.
     */
    default boolean hasEnchantment() {
        return !getEnchantments().isEmpty();
    }

    /**
     * Check if the item has any protection type enchantment.
     *
     * @return {@code true} if the item has protection type enchantment, {@code false} otherwise.
     */
    default boolean hasProtectionEnchantment() {
        return getEnchantments().stream().anyMatch(enchantmentInstance -> enchantmentInstance.getType() instanceof AbstractEnchantmentProtectionType);
    }

    /**
     * Get the protection factor of the item.
     *
     * @param damageType The damage type.
     *
     * @return The protection factor.
     */
    default int getEnchantmentProtectionFactor(DamageContainer.DamageType damageType) {
        return getEnchantments().stream()
                .filter(enchantmentInstance -> enchantmentInstance.getType() instanceof AbstractEnchantmentProtectionType)
                .mapToInt(enchantmentInstance -> ((AbstractEnchantmentProtectionType) enchantmentInstance.getType()).getProtectionFactor(damageType, enchantmentInstance.getLevel()))
                .sum();
    }

    /**
     * Get the level of the specified enchantment.
     *
     * @param enchantmentType The enchantment type.
     *
     * @return The level of the enchantment, or zero if the item doesn't have the enchantment.
     */
    int getEnchantmentLevel(EnchantmentType enchantmentType);

    /**
     * Get all enchantments of the item.
     *
     * @return The enchantments.
     */
    Collection<EnchantmentInstance> getEnchantments();

    /**
     * Add an enchantment to the item.
     *
     * @param enchantmentType The enchantment type.
     * @param level           The level of the enchantment.
     */
    void addEnchantment(EnchantmentType enchantmentType, int level);

    /**
     * Add multiple enchantments to the item.
     *
     * @param enchantmentInstances The enchantment instances that will be added.
     */
    void addEnchantments(Collection<EnchantmentInstance> enchantmentInstances);

    /**
     * Remove an enchantment from the item.
     *
     * @param enchantmentType The enchantment type.
     *
     * @return The removed enchantment, or {@code null} if the item doesn't have the enchantment.
     */
    EnchantmentInstance removeEnchantment(EnchantmentType enchantmentType);

    /**
     * Remove all enchantments from the item.
     */
    void removeAllEnchantments();

    /**
     * Called when the item is used to break a block.
     *
     * @param block   The block being broken
     * @param breaker The entity breaking the block
     */
    void onBreakBlock(BlockState block, Entity breaker);

    /**
     * Called when the item is used to attack an entity successfully.
     *
     * @param attacker The entity attacking.
     * @param victim   The entity being attacked.
     */
    void onAttackEntity(Entity attacker, Entity victim);

    /**
     * Interact an entity with this item and given performer.
     *
     * @param performer the entity who interact the target entity.
     * @param victim    the target entity who will be interacted.
     *
     * @return {@code true} if the interaction is successful, {@code false} otherwise.
     */
    default boolean interactEntity(Entity performer, Entity victim) {
        return false;
    }

    /**
     * Get the break time bonus for breaking a block with this item.
     *
     * @param blockState The block to be broken.
     *
     * @return Break time bonus.
     */
    default double getBreakTimeBonus(BlockState blockState) {
        var itemType = getItemType();
        var blockType = blockState.getBlockType();
        // Swords break cobwebs faster
        if (itemType.hasItemTag(IS_SWORD)) {
            if (blockType == BlockTypes.WEB) return 15d;
        }
        if (itemType == SHEARS) {
            // Shears break wool and leaves faster
            if (blockType.hasBlockTag(BlockTags.WOOL)) {
                return 5d;
            } else if (blockType == BlockTypes.WEB || blockType.hasBlockTag(BlockTags.LEAVES)) {
                return 15d;
            }
            return 1d;
        }
        if (!itemType.hasItemTag(IS_TOOL)) return 1d;
        if (itemType.hasItemTag(GOLDEN_TIER)) return 12d;
        if (itemType.hasItemTag(NETHERITE_TIER)) return 9d;
        if (itemType.hasItemTag(DIAMOND_TIER)) return 8d;
        if (itemType.hasItemTag(IRON_TIER)) return 6d;
        if (itemType.hasItemTag(STONE_TIER)) return 4d;
        if (itemType.hasItemTag(WOODEN_TIER)) return 2d;
        return 1d;
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

    /**
     * Check if this item can instantly break the specified block.
     *
     * @param blockState The block to be broken
     *
     * @return Whether the block can be instantly broken
     */
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

    /**
     * Check if the specified enchantment type is compatible with this item.
     *
     * @param type The enchantment type.
     *
     * @return {@code true} if the enchantment is compatible, {@code false} otherwise.
     */
    default boolean checkEnchantmentCompatibility(EnchantmentType type) {
        return getIncompatibleEnchantmentTypes(type).isEmpty();
    }

    /**
     * Get the incompatible enchantment types with the specified type.
     *
     * @param type The enchantment type.
     *
     * @return The incompatible enchantment types.
     */
    default Set<EnchantmentType> getIncompatibleEnchantmentTypes(EnchantmentType type) {
        return getEnchantments().stream()
                .map(EnchantmentInstance::getType)
                .filter(enchantmentType -> enchantmentType.isIncompatibleWith(type))
                .collect(Collectors.toSet());
    }
}
