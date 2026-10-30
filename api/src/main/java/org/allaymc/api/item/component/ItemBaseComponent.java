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
 * {@code ItemBaseComponent} defines an item stack's mutable state and gameplay
 * operations. Stack state includes count, metadata, display data, enchantments,
 * and persistent custom data. The network stack ID serves request processing
 * separately from item type identity; callers can deliberately retain it during
 * copying, and item NBT does not persist that ID.
 *
 * <p>
 * Timed air use begins with {@code canUseItemInAir} and completes through
 * {@code useItemInAir}, while immediate actions use {@code rightClickItemInAir}.
 * Mining defaults consider both item classification and block properties;
 * {@code isCorrectToolFor} remains a separate suitability check from the speed
 * bonus. Mutable state is not synchronized by the base implementation, so
 * callers coordinate concurrent access and preserve non-{@code null} display
 * values required by its query and serialization paths.
 *
 * @author daoge_cmd
 */
public interface ItemBaseComponent extends ItemComponent, PersistentDataHolder {

    /**
     * {@code EMPTY_UNIQUE_ID} represents a stack without a network request ID.
     * Automatic allocation starts above this value, and {@code clearUniqueId} restores
     * the sentinel.
     */
    int EMPTY_UNIQUE_ID = 0;

    /**
     * Returns the registered item type that supplies this stack's shared behavior.
     */
    ItemType<? extends ItemStack> getItemType();

    /**
     * Returns the number of items represented by this stack.
     */
    int getCount();

    /**
     * Sets the stack count to a nonnegative value, including {@code 0}.
     * The base setter rejects negative counts without capping the value at the item
     * type's maximum stack size.
     *
     * @throws IllegalArgumentException if {@code count} is negative
     */
    void setCount(int count);

    /**
     * Subtracts the supplied amount through the stack's count setter.
     * The amount is not separately validated or clamped, and integer overflow is
     * unchecked; the base setter rejects an arithmetic result below zero.
     */
    default void reduceCount(int count) {
        setCount(getCount() - count);
    }

    /**
     * Adds the supplied amount through the stack's count setter.
     * The amount is not separately validated or capped, and integer overflow is
     * unchecked; the base setter rejects an arithmetic result below zero.
     */
    default void increaseCount(int count) {
        setCount(getCount() + count);
    }

    /**
     * Returns whether the count exactly equals the item type's maximum stack size.
     * The base implementation uses equality, so an oversized count does not report
     * a full stack through this query.
     */
    boolean isFull();

    /**
     * Returns the item metadata value currently stored on this stack.
     */
    int getMeta();

    /**
     * Sets the item metadata value to the supplied nonnegative integer.
     * The base setter rejects negative values without enforcing type-specific
     * variant ranges; specialized components interpret the stored metadata.
     *
     * @throws IllegalArgumentException if {@code meta} is negative
     */
    void setMeta(int meta);

    /**
     * Returns the durability threshold configured in this item type's gameplay data.
     */
    default int getMaxDamage() {
        return getItemType().getItemData().maxDamage();
    }

    /**
     * Returns the accumulated durability damage currently stored on this stack.
     */
    int getDamage();

    /**
     * Sets accumulated durability damage when the item type supports durability.
     * The base implementation logs and ignores unsupported items; otherwise it rejects
     * negative values without clamping damage to the configured maximum.
     *
     * @throws IllegalArgumentException if {@code damage} is negative for a damageable item
     */
    void setDamage(int damage);

    /**
     * Attempts a durability change after the unbreaking check and damage event.
     * The result is {@code false} when either rejects the attempt; {@code true}
     * means the setter was invoked, even if an unsupported item or zero event amount
     * leaves stored damage unchanged.
     */
    boolean tryIncreaseDamage(int increase);

    /**
     * Returns the repair-cost value currently stored on this item stack.
     */
    int getRepairCost();

    /**
     * Sets the nonnegative repair-cost value stored on this stack.
     * The base setter rejects negative values; saving includes a repair-cost entry
     * only when the stored value is positive.
     *
     * @throws IllegalArgumentException if {@code repairCost} is negative
     */
    void setRepairCost(int repairCost);

    /**
     * Returns whether a damageable item's stored damage reaches its positive limit.
     * The base implementation returns {@code false} for nondamageable items and for
     * invalid nonpositive limits rather than treating those limits as broken.
     */
    boolean isBroken();

    /**
     * Returns whether the current unbreaking check permits a durability attempt.
     * The base implementation always permits an absent unbreaking enchantment; this
     * query does not itself test whether the item type supports durability.
     */
    boolean canBeDamagedThisTime();

    /**
     * Returns whether the stored non-{@code null} custom name is nonempty.
     */
    default boolean hasCustomName() {
        return !getCustomName().isEmpty();
    }

    /**
     * Returns the custom display name stored on this item stack.
     * The base component starts with an empty string; its setter retains supplied
     * values directly, so callers must preserve the non-{@code null} convention.
     */
    String getCustomName();

    /**
     * Replaces the stack's custom display name with the supplied value.
     * Callers use an empty string to clear the name and must keep it non-{@code null}
     * for the base query and save paths.
     */
    void setCustomName(String customName);

    /**
     * Returns the stack's currently stored lore list reference.
     * The base component does not copy the list for reads; changes through a mutable
     * returned list affect later serialization of this stack.
     */
    List<String> getLore();

    /**
     * Replaces the stack's lore list with the supplied list reference.
     * The base component retains the list directly, so later caller mutations can
     * change the item; callers must supply a non-{@code null} list.
     */
    void setLore(List<String> lore);

    /**
     * Returns the block state represented by this item stack, when available.
     * The base component returns the mapped block type's default state and returns
     * {@code null} when the item has no block mapping.
     */
    BlockState toBlockState();

    /**
     * Returns the network stack ID used by item request processing.
     * {@code EMPTY_UNIQUE_ID} means no ID is assigned. Explicit assignment and
     * {@code copy(false)} can intentionally reuse IDs, so this accessor does not
     * guarantee a distinct value for every stack instance.
     */
    int getUniqueId();

    /**
     * Replaces the network stack ID with the supplied integer.
     * The base setter stores the value directly without reserving it from the
     * automatic allocator; callers maintain the identity needed by request processing.
     */
    void setUniqueId(int uniqueId);

    /**
     * Returns whether this stack's network ID differs from {@code EMPTY_UNIQUE_ID}.
     */
    default boolean hasUniqueId() {
        return getUniqueId() != EMPTY_UNIQUE_ID;
    }

    /**
     * Restores {@code EMPTY_UNIQUE_ID} as this stack's absent network-ID value.
     */
    default void clearUniqueId() {
        setUniqueId(EMPTY_UNIQUE_ID);
    }

    /**
     * Creates a stack copy while requesting a newly allocated network ID.
     * The copy follows {@code copy(true)} and restores state through the component
     * serialization path rather than promising a deep clone of every retained object.
     */
    default ItemStack copy() {
        return copy(true);
    }

    /**
     * Creates a stack copy using serialized component state and the chosen ID policy.
     * {@code true} requests a new network ID; {@code false} retains the current ID,
     * including {@code EMPTY_UNIQUE_ID}. Component-specific persistence determines
     * which state survives the copy.
     */
    ItemStack copy(boolean newStackNetworkId);

    /**
     * Serializes extra item state contributed by the base and sibling components.
     * Count, metadata, item type, and network stack ID are outside this extra tag;
     * the base component omits its ordinary empty or default entries.
     */
    NbtMap saveExtraTag();

    /**
     * Loads the supplied extra item state through component and base loading paths.
     * The base loader resets absent damage and repair cost to zero, merges
     * enchantments, and retains several other values when their tags are absent;
     * loading is not a general reset operation.
     */
    void loadExtraTag(NbtMap extraTag);

    /**
     * Handles an item's special use against a clicked block before block interaction.
     * A {@code true} result consumes that interaction in the player transaction
     * path, which skips the clicked block's interaction handler and ordinary
     * placement fallback.
     */
    @ApiStatus.OverrideOnly
    boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo);

    /**
     * Attempts to place the block represented by this item stack.
     * The default interface implementation returns {@code false} without side effects;
     * placement implementations define their interaction-context requirements,
     * including whether they support {@code null} context.
     */
    default boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return false;
    }

    /**
     * Handles immediate item activation in air for the supplied player.
     * The player transaction path invokes this operation when timed use is
     * unsupported and the immediate-use player event allows the action.
     */
    @ApiStatus.OverrideOnly
    void rightClickItemInAir(EntityPlayer player);

    /**
     * Returns whether the item supports timed use in air for this player.
     * A {@code true} result selects the timed-use path; a separate player start event
     * can still reject starting it. Immediate actions use {@code rightClickItemInAir}.
     */
    boolean canUseItemInAir(EntityPlayer player);

    /**
     * Completes an item's timed-use attempt for the supplied player.
     * {@code usingTime} is elapsed game ticks; the returned boolean reports the item
     * handler's result after the player completion event has allowed the operation.
     */
    @ApiStatus.OverrideOnly
    boolean useItemInAir(EntityPlayer player, long usingTime);

    /**
     * Receives an ongoing timed-use tick for capability-specific behavior.
     * {@code usedTime} is elapsed game ticks since use began; the default
     * implementation performs no action, leaving periodic feedback to overrides.
     */
    @ApiStatus.OverrideOnly
    default void onUseInAirTick(EntityPlayer player, long usedTime) {
    }

    /**
     * Checks merge compatibility while enforcing the combined maximum stack count.
     * This overload delegates to {@code canMerge(itemStack, false)}; the candidate
     * must be non-{@code null}, and the base comparison invokes component extra-tag
     * serialization.
     */
    default boolean canMerge(ItemStack itemStack) {
        return canMerge(itemStack, false);
    }

    /**
     * Returns whether two stacks have compatible type, metadata, extra state, and
     * block state. A {@code true} {@code ignoreCount} skips only the combined-count
     * limit; the base implementation still serializes extra tags for comparison and
     * rejects a {@code null} candidate.
     */
    boolean canMerge(ItemStack itemStack, boolean ignoreCount);

    /**
     * Serializes the stack's item type, count, metadata, and available extra state.
     * The base format narrows count to a byte and metadata to a short, includes
     * available block-state NBT, and excludes the network stack ID.
     */
    NbtMap saveNBT();

    /**
     * Returns the item's attack damage without attacker or victim context.
     * The base component uses the item type's configured value; callers use the
     * contextual overload for capabilities that add combat-dependent damage.
     */
    float calculateAttackDamage();

    /**
     * Returns attack damage with attacker and victim context available to overrides.
     * The default implementation delegates to {@code calculateAttackDamage()} and
     * does not inspect either entity; specialized weapons can incorporate combat
     * state.
     */
    default float calculateAttackDamage(Entity attacker, Entity victim) {
        return calculateAttackDamage();
    }

    /**
     * Returns whether this stack stores an instance of the supplied enchantment type.
     */
    boolean hasEnchantment(EnchantmentType enchantmentType);

    /**
     * Returns whether this stack currently stores at least one enchantment instance.
     */
    default boolean hasEnchantments() {
        return !getEnchantments().isEmpty();
    }

    /**
     * Sums stored enchantment protection factors for the supplied damage type without capping the total.
     */
    default int getEnchantmentProtectionFactor(DamageType damageType) {
        return getEnchantments().stream()
                .mapToInt(enchantmentInstance -> enchantmentInstance.getType().getProtectionFactor(damageType, enchantmentInstance.getLevel()))
                .sum();
    }

    /**
     * Returns the level stored for the supplied enchantment type.
     * An absent type yields {@code 0}; a stored level of {@code 0} therefore cannot
     * be distinguished from absence through this accessor alone.
     */
    int getEnchantmentLevel(EnchantmentType enchantmentType);

    /**
     * Returns the stack's stored enchantment instances through its current collection.
     * The base component exposes a live values view: removing or clearing entries
     * changes stored enchantments, while adding through that view is unsupported.
     */
    Collection<EnchantmentInstance> getEnchantments();

    /**
     * Stores an enchantment instance at the supplied level, replacing that type's
     * previous entry. The base implementation does not validate normal level limits,
     * item applicability, or conflicts with existing enchantments.
     */
    void addEnchantment(EnchantmentType enchantmentType, int level);

    /**
     * Stores the supplied enchantment instances by their types in iteration order.
     * Later entries of the same type replace earlier entries; the base component
     * retains the instances and performs no compatibility validation.
     */
    void addEnchantments(Collection<EnchantmentInstance> enchantmentInstances);

    /**
     * Removes and returns the stored instance for the supplied enchantment type.
     * The result is {@code null} when no entry exists, allowing callers to distinguish
     * an absent enchantment from a removed instance.
     */
    EnchantmentInstance removeEnchantment(EnchantmentType enchantmentType);

    /**
     * Removes every enchantment currently stored on this item stack.
     */
    void removeAllEnchantments();

    /**
     * Notifies item behavior that the supplied entity broke the supplied block.
     */
    @ApiStatus.OverrideOnly
    void onBreakBlock(BlockState block, Entity breaker);

    /**
     * Notifies item behavior of the supplied attacker's attack against the victim.
     */
    @ApiStatus.OverrideOnly
    void onAttackEntity(Entity attacker, Entity victim);

    /**
     * Attempts item-specific interaction between the performer and target entity.
     * The returned boolean reports whether item behavior handled the interaction;
     * the base component obtains the result from its component event.
     */
    @ApiStatus.OverrideOnly
    boolean interactEntity(Entity performer, Entity victim);

    /**
     * Returns the default mining-speed multiplier for the item and target block.
     * Sword classification takes precedence, followed by shears and their
     * block-specific values, then tier tags in the checked order. An unmatched item
     * uses {@code 1}; this query does not check tool suitability.
     */
    default double getBreakTimeBonus(BlockState blockState) {
        var itemType = getItemType();
        var blockType = blockState.getBlockType();
        if (itemType.hasItemTag(IS_SWORD)) {
            return 10d;
        }

        if (itemType == SHEARS) {
            // Shears use block-specific mining multipliers before the generic
            // tier fallback, keeping web and leaf handling distinct from wool.
            if (blockType.hasBlockTag(BlockTags.WOOL)) {
                return 5d;
            } else if (blockType == BlockTypes.WEB || blockType.hasBlockTag(BlockTags.LEAVES)) {
                return 15d;
            }

            return 2d;
        }

        // Use the first matching tier in this fixed order; overlapping custom
        // tier tags must not be treated as a maximum across every matching tag.
        if (itemType.hasItemTag(WOODEN_TIER)) return 2d;
        if (itemType.hasItemTag(STONE_TIER)) return 4d;
        if (itemType.hasItemTag(IRON_TIER)) return 6d;
        if (itemType.hasItemTag(DIAMOND_TIER)) return 8d;
        if (itemType.hasItemTag(NETHERITE_TIER)) return 9d;
        if (itemType.hasItemTag(GOLDEN_TIER)) return 12d;
        // Untagged items contribute the neutral multiplier so the caller's base
        // mining speed remains unchanged by this item-specific lookup.
        return 1d;
    }

    /**
     * Returns whether the item's tool classification is suitable for the block.
     * The base implementation first applies its required-tier comparison, then checks
     * category-specific block tags and exceptions; this result is separate from
     * {@code getBreakTimeBonus}.
     */
    boolean isCorrectToolFor(BlockState blockState);

    /**
     * Returns whether the default instant-break rule accepts this item and block.
     * Zero block hardness is always accepted; otherwise a sword accepts bamboo only.
     * Other combinations return {@code false} without testing tool suitability.
     */
    default boolean canInstantBreak(BlockState blockState) {
        var blockHardness = blockState.getBlockStateData().hardness();
        // Zero hardness bypasses normal timed mining for every item.
        if (blockHardness == 0) {
            return true;
        }

        if (getItemType().hasItemTag(IS_SWORD)) {
            return blockState.getBlockType() == BlockTypes.BAMBOO;
        }

        return false;
    }

    /**
     * Returns whether enchanting-table selection may bypass item applicability checks.
     * The default is {@code false}; a {@code true} override still leaves compatibility
     * and enchantment availability checks to the enchanting-table generator.
     */
    default boolean isAllEnchantmentsAvailableInEnchantTable() {
        return false;
    }

    /**
     * Returns whether existing enchantments report no conflict with the proposed type.
     * This query considers stored enchantment conflicts only; it does not validate
     * item applicability, level limits, or enchanting-table availability.
     */
    default boolean checkEnchantmentCompatibility(EnchantmentType type) {
        return getIncompatibleEnchantmentTypes(type).isEmpty();
    }

    /**
     * Returns a new set of stored enchantment types reporting incompatibility with the proposed type.
     */
    default Set<EnchantmentType> getIncompatibleEnchantmentTypes(EnchantmentType type) {
        return getEnchantments().stream()
                .map(EnchantmentInstance::getType)
                .filter(enchantmentType -> enchantmentType.isIncompatibleWith(type))
                .collect(Collectors.toSet());
    }

    /**
     * Returns the optional block-entity data carried for later placement.
     * The base component returns {@code null} when no data is present and applies
     * present data only if placement produces a block entity at the destination.
     */
    NbtMap getBlockEntityNBT();

    /**
     * Stores block-entity data for application after a later placement.
     * {@code null} clears the stored value. Callers capture data with
     * {@link org.allaymc.api.blockentity.BlockEntity#saveCleanNBT()} so old coordinates
     * and component-specific location data do not move into the new placement.
     */
    void setBlockEntityNBT(NbtMap blockEntityNBT);

    /**
     * Returns whether this stack holds non-{@code null} block-entity placement data.
     */
    default boolean hasBlockEntityNBT() {
        return getBlockEntityNBT() != null;
    }

    /**
     * Returns the inventory lock policy currently stored on this stack.
     */
    ItemLockMode getLockMode();

    /**
     * Stores the inventory lock policy used by item extra-tag persistence.
     * The base setter retains the supplied value; callers use non-{@code null}
     * {@code ItemLockMode.NONE} to select the omitted default policy.
     */
    void setLockMode(ItemLockMode lockMode);

    /**
     * Returns whether this stack is air or has a count of {@code 0}.
     */
    default boolean isEmptyOrAir() {
        return getItemType() == ItemTypes.AIR || getCount() == 0;
    }
}
