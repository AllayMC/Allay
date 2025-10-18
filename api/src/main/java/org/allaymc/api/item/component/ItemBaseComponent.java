package org.allaymc.api.item.component;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemLockMode;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.pdc.PersistentDataHolder;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3ic;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.allaymc.api.item.data.ItemTags.*;
import static org.allaymc.api.item.type.ItemTypes.SHEARS;

/**
 * @author daoge_cmd
 */
public interface ItemBaseComponent extends ItemComponent, PersistentDataHolder {

    /**
     * The value returned by method {@link #getUniqueId()} when the item stack does not use the stack id.
     */
    int EMPTY_UNIQUE_ID = 0;

    /**
     * Gets the item type.
     *
     * @return the {@link ItemType}
     */
    ItemType<? extends ItemStack> getItemType();

    /**
     * Gets the item count.
     *
     * @return the current count
     */
    int getCount();

    /**
     * Sets the item count.
     *
     * @param count the new count
     */
    void setCount(int count);

    /**
     * Reduces the item count.
     *
     * @param count the amount to reduce
     */
    default void reduceCount(int count) {
        setCount(getCount() - count);
    }

    /**
     * Increases the item count.
     *
     * @param count the amount to increase
     */
    default void increaseCount(int count) {
        setCount(getCount() + count);
    }

    /**
     * Checks if the item stack is full.
     *
     * @return {@code true} if at max stack size, {@code false} otherwise
     */
    boolean isFull();

    /**
     * Gets the item meta value.
     *
     * @return the meta value
     */
    int getMeta();

    /**
     * Sets the item meta value.
     *
     * @param meta the new meta value
     */
    void setMeta(int meta);

    /**
     * Gets the maximum damage of the item.
     *
     * @return the max damage
     */
    default int getMaxDamage() {
        return getItemType().getItemData().maxDamage();
    }

    /**
     * Gets the current damage.
     *
     * @return the damage value
     */
    int getDamage();

    /**
     * Sets the damage.
     * <p>
     * Higher values indicate more damage.
     *
     * @param damage the new damage value
     */
    void setDamage(int damage);

    /**
     * Attempts to increase the damage.
     * <p>
     * May be ignored based on unbreaking enchantment.
     *
     * @param increase the amount to increase
     * @return {@code true} if increased, {@code false} if ignored
     */
    boolean tryIncreaseDamage(int increase);

    /**
     * Gets the repair cost.
     *
     * @return the repair cost
     */
    int getRepairCost();

    /**
     * Sets the repair cost.
     *
     * @param repairCost the new repair cost
     */
    void setRepairCost(int repairCost);

    /**
     * Checks if the item is broken.
     *
     * @return {@code true} if broken, {@code false} otherwise
     */
    boolean isBroken();

    /**
     * Checks if the item can be damaged now.
     *
     * @return {@code true} if damageable, {@code false} otherwise
     */
    boolean canBeDamagedThisTime();

    /**
     * Gets the custom name.
     *
     * @return the custom name, or empty if none
     */
    String getCustomName();

    /**
     * Sets the custom name.
     *
     * @param customName the new custom name
     */
    void setCustomName(String customName);

    /**
     * Gets the lore lines.
     *
     * @return the lore list
     */
    List<String> getLore();

    /**
     * Sets the lore lines.
     *
     * @param lore the new lore list
     */
    void setLore(List<String> lore);

    /**
     * Converts to a block state.
     *
     * @return the {@link BlockState}
     */
    BlockState toBlockState();

    /**
     * Gets the unique id of this item stack. Stack unique id is an increasing unique int value that associated
     * with a stack, mainly used in the new item stack request system to reduce network traffic footprint. Different
     * item stacks will have different unique ids.
     * <p>
     * Notes that item stack may don't have unique id, and in that case this method will return {@link #EMPTY_UNIQUE_ID}.
     *
     * @return the unique id
     */
    int getUniqueId();

    /**
     * Sets the unique id of this item stack.
     *
     * @param uniqueId the new unique id
     */
    void setUniqueId(int uniqueId);

    /**
     * Checks if the item stack has a unique id.
     *
     * @return {@code true} if present, {@code false} otherwise
     */
    default boolean hasUniqueId() {
        return getUniqueId() != EMPTY_UNIQUE_ID;
    }

    /**
     * Clears the unique id of this item stack.
     */
    default void clearUniqueId() {
        setUniqueId(EMPTY_UNIQUE_ID);
    }

    /**
     * Copies the item stack.
     *
     * @return the copied {@link ItemStack}
     */
    default ItemStack copy() {
        return copy(true);
    }

    /**
     * Copies the item stack with optional new network ID.
     *
     * @param newStackNetworkId whether to generate a new ID
     * @return the copied {@link ItemStack}
     */
    ItemStack copy(boolean newStackNetworkId);

    /**
     * Saves extra tag data.
     *
     * @return the {@link NbtMap} of extra tags
     */
    NbtMap saveExtraTag();

    /**
     * Loads extra tag data.
     *
     * @param extraTag the {@link NbtMap} to load
     */
    void loadExtraTag(NbtMap extraTag);

    /**
     * Handles right-click on a block.
     *
     * @param dimension     the {@link Dimension}
     * @param placeBlockPos the block position ({@link Vector3ic})
     * @param interactInfo  the {@link PlayerInteractInfo}
     */
    @ApiStatus.OverrideOnly
    void rightClickItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo);

    /**
     * Attempts to use the item on a block. Different from {@link #rightClickItemOnBlock(Dimension, Vector3ic, PlayerInteractInfo)},
     * this method will only be called when the client thinks he can use the item on the block.
     *
     * @param dimension     the {@link Dimension}
     * @param placeBlockPos the block position ({@link Vector3ic})
     * @param interactInfo  the {@link PlayerInteractInfo}
     * @return {@code true} if used, {@code false} otherwise
     */
    @ApiStatus.OverrideOnly
    boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo);

    /**
     * Attempts to place a block with this item.
     *
     * @param dimension     the {@link Dimension}
     * @param placeBlockPos the placement position ({@link Vector3ic})
     * @param placementInfo the {@link PlayerInteractInfo}, may be {@code null}
     * @return {@code true} if placed, {@code false} otherwise
     */
    default boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return false;
    }

    /**
     * Called when a player right-clicks this item in air. This method is only called when {@link #canUseItemInAir(EntityPlayer)}
     * return {@code false}.
     *
     * @param player the {@link EntityPlayer} that click this item in air
     */
    @ApiStatus.OverrideOnly
    void rightClickItemInAir(EntityPlayer player);

    /**
     * Checks if the item can be used in air. If return {@code true}, method {@link #useItemInAir} will be
     * called later with the time used when the player finishes using the item (usually when they release the
     * right mouse button or screen).
     * <p>
     * This method usually return {@code true} for those items which need time to be used. For the items that
     * can be used immediately, this method should return {@code false} and method {@link #rightClickItemInAir(EntityPlayer)}
     * will be called instead.
     *
     * @param player the {@link EntityPlayer} that try to use this item in air
     * @return {@code true} if usable, {@code false} otherwise
     */
    boolean canUseItemInAir(EntityPlayer player);

    /**
     * Called when the player finishes using certain item (e.g. foods).
     *
     * @param player    the player
     * @param usingTime the time the player has been using the item in air in game ticks
     * @return {@code true} if used, {@code false} otherwise
     */
    @ApiStatus.OverrideOnly
    boolean useItemInAir(EntityPlayer player, long usingTime);

    /**
     * Checks if this item can merge with another.
     *
     * @param itemStack the {@link ItemStack} to check
     * @return {@code true} if mergeable, {@code false} otherwise
     */
    default boolean canMerge(ItemStack itemStack) {
        return canMerge(itemStack, false);
    }

    /**
     * Checks if this item can merge with another.
     *
     * @param itemStack   the {@link ItemStack} to check
     * @param ignoreCount whether to ignore count in comparison. If set to {code false}, this method will check if the merged
     *                    count exceeds the maximum item count of a single stack for this item type
     * @return {@code true} if mergeable, {@code false} otherwise
     */
    boolean canMerge(ItemStack itemStack, boolean ignoreCount);

    /**
     * Saves item data to NBT.
     *
     * @return the {@link NbtMap} of item data
     */
    NbtMap saveNBT();

    /**
     * Calculates the attack damage.
     *
     * @return the attack damage value
     */
    float calculateAttackDamage();

    /**
     * Checks for a specific enchantment.
     *
     * @param enchantmentType the {@link EnchantmentType}
     * @return {@code true} if present, {@code false} otherwise
     */
    boolean hasEnchantment(EnchantmentType enchantmentType);

    /**
     * Checks for any enchantments.
     *
     * @return {@code true} if enchanted, {@code false} otherwise
     */
    default boolean hasEnchantments() {
        return !getEnchantments().isEmpty();
    }

    /**
     * Gets the protection factor for a damage type.
     *
     * @param damageType the {@link DamageType}
     * @return the total protection factor
     */
    default int getEnchantmentProtectionFactor(DamageType damageType) {
        return getEnchantments().stream()
                .mapToInt(enchantmentInstance -> enchantmentInstance.getType().getProtectionFactor(damageType, enchantmentInstance.getLevel()))
                .sum();
    }

    /**
     * Gets the level of an enchantment.
     *
     * @param enchantmentType the {@link EnchantmentType}
     * @return the level, or 0 if absent
     */
    int getEnchantmentLevel(EnchantmentType enchantmentType);

    /**
     * Gets all enchantments on the item.
     *
     * @return the {@link Collection} of {@link EnchantmentInstance}
     */
    Collection<EnchantmentInstance> getEnchantments();

    /**
     * Adds an enchantment to the item.
     *
     * @param enchantmentType the {@link EnchantmentType}
     * @param level           the enchantment level
     */
    void addEnchantment(EnchantmentType enchantmentType, int level);

    /**
     * Adds multiple enchantments to the item.
     *
     * @param enchantmentInstances the {@link Collection} of {@link EnchantmentInstance}
     */
    void addEnchantments(Collection<EnchantmentInstance> enchantmentInstances);

    /**
     * Removes an enchantment from the item.
     *
     * @param enchantmentType the {@link EnchantmentType}
     * @return the removed {@link EnchantmentInstance}, or {@code null} if absent
     */
    EnchantmentInstance removeEnchantment(EnchantmentType enchantmentType);

    /**
     * Removes all enchantments from the item.
     */
    void removeAllEnchantments();

    /**
     * Handles breaking a block with this item.
     *
     * @param block   the {@link BlockState} being broken
     * @param breaker the {@link Entity} breaking it
     */
    @ApiStatus.OverrideOnly
    void onBreakBlock(BlockState block, Entity breaker);

    /**
     * Handles attacking an entity with this item.
     *
     * @param attacker the {@link Entity} attacking
     * @param victim   the {@link Entity} attacked
     */
    @ApiStatus.OverrideOnly
    void onAttackEntity(Entity attacker, Entity victim);

    /**
     * Interacts an entity with this item.
     *
     * @param performer the {@link Entity} performing the interaction
     * @param victim    the {@link Entity} being interacted with
     * @return {@code true} if successful, {@code false} otherwise
     */
    @ApiStatus.OverrideOnly
    boolean interactEntity(Entity performer, Entity victim);

    /**
     * Gets the break time bonus for a block.
     *
     * @param blockState the {@link BlockState} to break
     * @return the bonus multiplier
     */
    default double getBreakTimeBonus(BlockState blockState) {
        var itemType = getItemType();
        var blockType = blockState.getBlockType();
        if (itemType.hasItemTag(IS_SWORD)) {
            return 10d;
        }

        if (itemType == SHEARS) {
            // Shears break wool and leaves faster
            if (blockType.hasBlockTag(BlockTags.WOOL)) {
                return 5d;
            } else if (blockType == BlockTypes.WEB || blockType.hasBlockTag(BlockTags.LEAVES)) {
                return 15d;
            }

            return 2d;
        }

        if (itemType.hasItemTag(WOODEN_TIER)) return 2d;
        if (itemType.hasItemTag(STONE_TIER)) return 4d;
        if (itemType.hasItemTag(IRON_TIER)) return 6d;
        if (itemType.hasItemTag(DIAMOND_TIER)) return 8d;
        if (itemType.hasItemTag(NETHERITE_TIER)) return 9d;
        if (itemType.hasItemTag(GOLDEN_TIER)) return 12d;
        return 1d;
    }

    /**
     * Checks if this is the correct tool for a block.
     *
     * @param blockState the {@link BlockState} to break
     * @return {@code true} if correct, {@code false} otherwise
     */
    boolean isCorrectToolFor(BlockState blockState);

    /**
     * Checks if this item can instantly break a block.
     *
     * @param blockState the {@link BlockState} to break
     * @return {@code true} if instant, {@code false} otherwise
     */
    default boolean canInstantBreak(BlockState blockState) {
        var blockHardness = blockState.getBlockStateData().hardness();
        // Blocks with hardness of 0 can be instantly broken
        if (blockHardness == 0) {
            return true;
        }

        if (getItemType().hasItemTag(IS_SWORD)) {
            // Swords can break bamboo instantly
            return blockState.getBlockType() == BlockTypes.BAMBOO;
        }

        return false;
    }

    /**
     * Checks if all enchantments are available in an enchant table.
     *
     * @return {@code true} if all available, {@code false} otherwise
     */
    default boolean isAllEnchantmentsAvailableInEnchantTable() {
        return false;
    }

    /**
     * Checks compatibility with an enchantment type.
     *
     * @param type the {@link EnchantmentType} to check
     * @return {@code true} if compatible, {@code false} otherwise
     */
    default boolean checkEnchantmentCompatibility(EnchantmentType type) {
        return getIncompatibleEnchantmentTypes(type).isEmpty();
    }

    /**
     * Gets incompatible enchantment types.
     *
     * @param type the {@link EnchantmentType} to check
     * @return the {@link Set} of incompatible {@link EnchantmentType}
     */
    default Set<EnchantmentType> getIncompatibleEnchantmentTypes(EnchantmentType type) {
        return getEnchantments().stream()
                .map(EnchantmentInstance::getType)
                .filter(enchantmentType -> enchantmentType.isIncompatibleWith(type))
                .collect(Collectors.toSet());
    }

    /**
     * Gets the block entity NBT data.
     * <p>
     * The block entity nbt will be stored in the item if player pick a block with ctrl pressed,
     * and the nbt will be used when the player place the block. Note that not every block has
     * block entity, so setting block entity nbt in an item whose block doesn't have block entity
     * will have no effect.
     *
     * @return the {@link NbtMap}, or {@code null} if absent
     */
    NbtMap getBlockEntityNBT();

    /**
     * Sets the block entity NBT data.
     *
     * @param blockEntityNBT the {@link NbtMap}, or {@code null} to clear
     */
    void setBlockEntityNBT(NbtMap blockEntityNBT);

    /**
     * Checks for block entity NBT data.
     *
     * @return {@code true} if present, {@code false} otherwise
     */
    default boolean hasBlockEntityNBT() {
        return getBlockEntityNBT() != null;
    }

    /**
     * Gets the item lock mode.
     *
     * @return the {@link ItemLockMode}
     */
    ItemLockMode getLockMode();

    /**
     * Sets the item lock mode.
     *
     * @param lockMode the {@link ItemLockMode}
     */
    void setLockMode(ItemLockMode lockMode);

    /**
     * Checks if the item stack is empty or air.
     *
     * @return {@code true} if empty or air, {@code false} otherwise
     */
    default boolean isEmptyOrAir() {
        return getItemType() == ItemTypes.AIR || getCount() == 0;
    }
}
