package org.allaymc.server.item.component;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.BlockHelper;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.item.ItemDamageEvent;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.data.ItemLockMode;
import org.allaymc.api.item.enchantment.EnchantmentHelper;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.BlockPlaceSound;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.item.component.event.*;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.jetbrains.annotations.VisibleForTesting;
import org.joml.Vector3ic;
import org.joml.primitives.AABBd;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static org.allaymc.server.world.physics.AllayEntityPhysicsEngine.FAT_AABB_MARGIN;

/**
 * {@code ItemBaseComponentImpl} stores mutable stack state, serializes item data,
 * and dispatches item actions to sibling components. Construction takes count
 * and metadata directly from initialization values, prefers an explicit network
 * ID, and otherwise optionally allocates one. Extra-tag loading runs only after
 * component injection makes the manager available.
 *
 * <p>
 * The complete item format separates count, metadata, type identity, and optional
 * block-state data from extra component state. {@code copy} rebuilds a stack
 * through that extra-state path while selecting whether to preserve its network
 * ID; it does not deep-clone arbitrary retained values. Loading resets damage
 * and repair cost but conditionally retains or merges other state, so loading
 * over an existing stack differs from constructing a fresh one. Mutable stack
 * fields and collection views have no internal synchronization.
 *
 * @author daoge_cmd
 */
@Slf4j
public class ItemBaseComponentImpl implements ItemBaseComponent {

    /**
     * {@code IDENTIFIER} identifies the core item-state provider used by item type
     * builders when no explicit base component replaces it.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");

    /**
     * {@code TAG_COUNT} identifies the outer stack count byte. {@code TAG_META},
     * {@code TAG_NAME}, {@code TAG_EXTRA_TAG}, and {@code TAG_BLOCK} name the remaining
     * outer item fields consumed or produced by item persistence.
     */
    protected static final String TAG_COUNT = "Count";
    /**
     * Outer metadata short key.
     */
    protected static final String TAG_META = "Damage";
    /**
     * Outer item identifier key.
     */
    protected static final String TAG_NAME = "Name";
    /**
     * Outer extra-data compound key.
     */
    protected static final String TAG_EXTRA_TAG = "tag";
    /**
     * Outer block-state compound key.
     */
    protected static final String TAG_BLOCK = "Block";

    /**
     * {@code TAG_DAMAGE} identifies durability damage inside the extra tag.
     * The remaining extra-tag keys below encode repair cost, display data,
     * enchantments, block-entity data, lock mode, and persistent custom data.
     */
    protected static final String TAG_DAMAGE = "Damage";
    /**
     * Extra-tag repair-cost integer key.
     */
    protected static final String TAG_REPAIR_COST = "RepairCost";
    /**
     * Extra-tag display compound key.
     */
    protected static final String TAG_DISPLAY = "display";
    /**
     * Display custom-name string key.
     */
    protected static final String TAG_CUSTOM_NAME = "Name";
    /**
     * Display lore string-list key.
     */
    protected static final String TAG_LORE = "Lore";
    /**
     * Extra-tag enchantment-list key.
     */
    protected static final String TAG_ENCHANTMENT = "ench";
    /**
     * Extra-tag block-entity compound key.
     */
    protected static final String TAG_BLOCK_ENTITY = "BlockEntityTag";
    /**
     * Extra-tag lock ordinal key.
     */
    protected static final String TAG_LOCK_MODE = "minecraft:item_lock";
    /**
     * Extra-tag custom-data compound key.
     */
    protected static final String TAG_PDC = "PDC";

    /**
     * {@code UNIQUE_ID} allocates automatic stack IDs starting at {@code 1}, leaving
     * {@code 0} as the absent-ID sentinel. Explicit assignments do not advance this
     * counter, and integer overflow is not checked.
     */
    private static final AtomicInteger UNIQUE_ID = new AtomicInteger(1);

    /**
     * Injected component-owning item stack.
     */
    @ComponentObject
    protected ItemStack thisItemStack;

    /**
     * Injected sibling-component event dispatcher.
     */
    @Manager
    protected ComponentManager manager;

    /**
     * Owning registered item type.
     */
    @Getter
    protected ItemType<?> itemType;
    /**
     * Current stack item count.
     */
    @Getter
    protected int count;
    /**
     * Current item metadata value.
     */
    @Getter
    protected int meta;
    /**
     * Accumulated item durability damage.
     */
    @Getter
    protected int damage;
    /**
     * Stored item repair cost.
     */
    @Getter
    protected int repairCost;
    /**
     * {@code customName} holds the display name and starts empty. Its generated
     * setter retains values directly, so callers must keep it non-{@code null} for
     * name queries and serialization.
     */
    @Getter
    @Setter
    protected String customName = "";
    /**
     * {@code lore} holds display lines exposed by the generated getter. The setter
     * retains the supplied list directly, and loading can replace this reference;
     * callers must keep it non-{@code null}.
     */
    @Getter
    @Setter
    protected List<String> lore = new ArrayList<>();
    /**
     * {@code enchantments} indexes stored instances by type. Loading merges entries
     * without clearing existing types, and {@code getEnchantments} exposes the live
     * values collection for removal and iteration.
     */
    protected Map<EnchantmentType, EnchantmentInstance> enchantments = new HashMap<>();
    /**
     * {@code lockMode} holds the persisted inventory lock policy. The default
     * {@code ItemLockMode.NONE} is omitted when saving, and callers must preserve a
     * non-{@code null} value.
     */
    @Getter
    @Setter
    protected ItemLockMode lockMode = ItemLockMode.NONE;
    /**
     * {@code persistentDataContainer} stores editable custom metadata. A present
     * {@code TAG_PDC} compound clears and repopulates this container; absent data
     * retains it, and the generated accessor exposes the same container.
     */
    @Getter
    @Setter
    protected PersistentDataContainer persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);

    /**
     * {@code blockEntityNBT} retains optional data applied to a block entity after
     * placement. The generated setter stores the reference directly; {@code null}
     * means no data should be applied.
     */
    @Getter
    @Setter
    protected NbtMap blockEntityNBT;
    /**
     * {@code uniqueId} holds the network request ID. The generated setter does not
     * validate or reserve values, and {@code EMPTY_UNIQUE_ID} represents an absent
     * ID.
     */
    @Getter
    @Setter
    protected int uniqueId;

    /**
     * Creates base state from non-{@code null} initialization values.
     * Count and metadata are copied without setter validation. A nonzero explicit
     * network ID must be positive and takes precedence over automatic assignment;
     * otherwise assignment either increments the counter or leaves the absent-ID
     * sentinel. Extra data loads in {@code onInitFinish}.
     *
     * @throws IllegalArgumentException if a nonzero explicit network ID is negative
     */
    public ItemBaseComponentImpl(ItemStackInitInfo initInfo) {
        this.itemType = initInfo.getItemType();
        this.count = initInfo.count();
        this.meta = initInfo.meta();
        var specifiedNetworkId = initInfo.uniqueId();
        if (specifiedNetworkId != EMPTY_UNIQUE_ID) {
            Preconditions.checkArgument(specifiedNetworkId > 0, "Specified ItemStack network id must be greater than 0");
            this.uniqueId = specifiedNetworkId;
        } else if (initInfo.assignUniqueId()) {
            this.uniqueId = UNIQUE_ID.getAndIncrement();
        }
    }

    /**
     * Returns the next automatic network ID without consuming the counter value.
     */
    @VisibleForTesting
    public static int getCurrentUniqueIdCounter() {
        return UNIQUE_ID.get();
    }

    /**
     * Loads initialization extra data after component injection has finished.
     * The manager and owning stack must already be injected because
     * {@code loadExtraTag} dispatches a component event before loading base fields.
     */
    @OnInitFinish
    public void onInitFinish(ItemStackInitInfo initInfo) {
        loadExtraTag(initInfo.extraTag());
    }

    /**
     * Loads component data and overlays base state from the supplied extra tag.
     * Damage and repair cost reset to {@code 0} when absent; display data,
     * block-entity data, and lock mode change only when their expected entries are
     * present. Enchantments merge by type rather than replacing the collection.
     *
     * <p>
     * A present persistent-data compound clears the existing container before
     * repopulation. Component handlers run first, but replacing their event's map
     * does not replace this method's argument. Unknown enchantments can produce a
     * {@code null} dereference, and invalid lock ordinals fail array lookup; earlier
     * state changes are not rolled back after either failure.
     */
    @Override
    public void loadExtraTag(NbtMap extraTag) {
        manager.callEvent(new CItemLoadExtraTagEvent(extraTag));

        this.damage = extraTag.getInt(TAG_DAMAGE, 0);
        this.repairCost = extraTag.getInt(TAG_REPAIR_COST, 0);

        extraTag.listenForCompound(TAG_DISPLAY, displayNbt -> {
            this.customName = displayNbt.getString(TAG_CUSTOM_NAME);
            this.lore = displayNbt.getList(TAG_LORE, NbtType.STRING);
        });
        // Merge by enchantment type rather than clearing the map, preserving
        // existing types when the supplied tag contains only a partial list.
        extraTag.listenForList(TAG_ENCHANTMENT, NbtType.COMPOUND, enchsNbt -> enchsNbt.forEach(enchNbt -> {
            var enchantment = EnchantmentHelper.fromNBT(enchNbt);
            this.enchantments.put(enchantment.getType(), enchantment);
        }));
        extraTag.listenForCompound(TAG_BLOCK_ENTITY, nbt -> this.blockEntityNBT = nbt);
        // The persisted byte is an enum ordinal, so declaration order determines
        // its meaning and an invalid ordinal fails instead of choosing a default.
        extraTag.listenForByte(TAG_LOCK_MODE, lockMode -> this.lockMode = ItemLockMode.values()[lockMode]);
        extraTag.listenForCompound(TAG_PDC, customNbt -> {
            this.persistentDataContainer.clear();
            this.persistentDataContainer.putAll(customNbt);
        });

    }

    /**
     * Builds extra item NBT after sibling components contribute to the same builder.
     * Base fields are written afterward, so an emitted base key replaces a
     * component entry at that key; omitted defaults leave component contributions
     * untouched.
     *
     * <p>
     * The base representation separates display fields into a compound, serializes
     * enchantments individually, and stores lock mode as an enum ordinal byte.
     * Ordinary empty/default values add no base entry. Network ID, count, and
     * metadata remain outside this extra tag; retained collections and custom data
     * must be readable throughout serialization.
     */
    @Override
    public NbtMap saveExtraTag() {
        var nbtBuilder = NbtMap.builder();
        manager.callEvent(new CItemSaveExtraTagEvent(nbtBuilder));

        if (damage != 0) {
            nbtBuilder.putInt(TAG_DAMAGE, damage);
        }
        if (repairCost > 0) {
            nbtBuilder.putInt(TAG_REPAIR_COST, repairCost);
        }

        var displayBuilder = NbtMap.builder();
        if (!this.customName.isEmpty()) {
            displayBuilder.put(TAG_CUSTOM_NAME, this.customName);
        }
        if (!this.lore.isEmpty()) {
            displayBuilder.putList(TAG_LORE, NbtType.STRING, this.lore);
        }
        if (!displayBuilder.isEmpty()) {
            nbtBuilder.putCompound(TAG_DISPLAY, displayBuilder.build());
        }

        if (!enchantments.isEmpty()) {
            var enchantmentNBT = this.enchantments.values().stream()
                    .map(EnchantmentInstance::saveNBT)
                    .toList();
            nbtBuilder.putList(TAG_ENCHANTMENT, NbtType.COMPOUND, enchantmentNBT);
        }

        if (blockEntityNBT != null) {
            nbtBuilder.putCompound(TAG_BLOCK_ENTITY, blockEntityNBT);
        }

        if (lockMode != ItemLockMode.NONE) {
            nbtBuilder.putByte(TAG_LOCK_MODE, (byte) lockMode.ordinal());
        }

        if (!persistentDataContainer.isEmpty()) {
            nbtBuilder.put(TAG_PDC, persistentDataContainer.toNbt());
        }

        return nbtBuilder.build();
    }

    /**
     * Builds complete item NBT with type identity, count, metadata, and optional
     * extra and block-state data. Count narrows to a signed byte and metadata to a
     * signed short without range validation, so callers cannot assume arbitrary
     * integer values survive a load round trip.
     *
     * <p>
     * Extra state is collected through {@code saveExtraTag}, preserving component
     * participation in the item representation. A non-{@code null} block state adds
     * its NBT separately. The network stack ID is excluded because it belongs to
     * request processing rather than persisted item identity.
     */
    @Override
    public NbtMap saveNBT() {
        // The item format stores count as a byte and metadata as a short;
        // narrowing here preserves that format without validating wider values.
        var builder = NbtMap.builder()
                .putByte(TAG_COUNT, (byte) getCount())
                .putShort(TAG_META, (short) getMeta())
                .putString(TAG_NAME, getItemType().getIdentifier().toString());

        var extraTag = saveExtraTag();
        if (!extraTag.isEmpty()) {
            builder.putCompound(TAG_EXTRA_TAG, extraTag);
        }

        var blockState = toBlockState();
        if (blockState != null) {
            builder.put(TAG_BLOCK, blockState.getBlockStateNBT());
        }

        // TODO: Persist CanDestroy and CanPlaceOn item restrictions when their
        // gameplay representation and matching load support are implemented.

        return builder.build();
    }

    /**
     * Dispatches block use to sibling components and returns the result available
     * when dispatch returns. The result begins at {@code false}; a handler can mark
     * the interaction handled through {@code CItemUseOnBlockEvent.canBeUsed}.
     */
    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        var event = new CItemUseOnBlockEvent(dimension, placeBlockPos, interactInfo, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    /**
     * Returns whether count exactly equals the configured maximum stack size.
     * Counts larger than that maximum return {@code false}; neither construction
     * nor the count setter caps stored values at the maximum.
     */
    @Override
    public boolean isFull() {
        return count == itemType.getItemData().maxStackSize();
    }

    /**
     * Stores a nonnegative stack count without enforcing the maximum stack size.
     * {@code 0} is valid so consumption can leave an empty stack; a negative value
     * throws before changing stored count.
     *
     * @throws IllegalArgumentException if {@code count} is negative
     */
    @Override
    public void setCount(int count) {
        Preconditions.checkArgument(count >= 0, "Count must be greater or equal to 0");
        this.count = count;
    }

    /**
     * Stores nonnegative metadata without checking the item type's variant range.
     * A negative value throws before changing {@code meta}; components and
     * serialization determine how an accepted value is interpreted.
     *
     * @throws IllegalArgumentException if {@code meta} is negative
     */
    @Override
    public void setMeta(int meta) {
        Preconditions.checkArgument(meta >= 0, "Meta must be greater or equal to 0");
        this.meta = meta;
    }

    /**
     * Stores durability damage only when the item type is damageable.
     * Unsupported types log a warning and keep their current value. Damageable
     * types reject negative values without enforcing {@code maxDamage} as an upper
     * bound.
     *
     * @throws IllegalArgumentException if {@code damage} is negative for a damageable item
     */
    @Override
    public void setDamage(int damage) {
        if (!itemType.getItemData().isDamageable()) {
            log.warn("Item {} does not support durability!", itemType.getIdentifier());
            return;
        }

        Preconditions.checkArgument(damage >= 0, "Damage must be greater or equal to 0");
        this.damage = damage;
    }

    /**
     * Stores a nonnegative repair cost on this item stack.
     * A negative argument throws before updating the field; {@code 0} remains
     * valid and is omitted by extra-tag saving.
     *
     * @throws IllegalArgumentException if {@code repairCost} is negative
     */
    @Override
    public void setRepairCost(int repairCost) {
        Preconditions.checkArgument(repairCost >= 0, "RepairCost must be greater or equal to 0");
        this.repairCost = repairCost;
    }

    /**
     * Runs the unbreaking chance check for this durability attempt.
     * An absent enchantment always permits the attempt; positive levels use a
     * reciprocal chance. This check does not test the item's durability-support
     * flag.
     */
    @Override
    public boolean canBeDamagedThisTime() {
        var level = getEnchantmentLevel(EnchantmentTypes.UNBREAKING);
        if (level == 0) {
            return true;
        }

        // Positive unbreaking levels reduce the per-attempt probability using
        // the reciprocal rule; this random check does not determine durability support.
        var possibility = 1f / (level + 1f);
        return ThreadLocalRandom.current().nextFloat() <= possibility;
    }

    /**
     * Returns the mapped block type's default state, when a mapping exists.
     * Items without an associated block return {@code null}; this base conversion
     * does not derive block properties from metadata or extra tags.
     */
    @Override
    public BlockState toBlockState() {
        return itemType.getBlockType() != null ? itemType.getBlockType().getDefaultState() : null;
    }

    /**
     * Recreates this stack from count, metadata, and serialized extra component
     * state. A {@code true} flag clears the requested ID before enabling automatic
     * allocation; a {@code false} flag preserves the current ID and disables
     * allocation, including when that ID is absent.
     *
     * <p>
     * Using the item factory reconstructs the configured component composition
     * rather than copying Java fields directly. Only state represented by that
     * serialization path is restored, and retained nested values are not promised
     * to be deep copies. An invalid explicitly retained ID can fail builder
     * validation.
     */
    @Override
    public ItemStack copy(boolean newStackNetworkId) {
        return itemType.createItemStack(
                ItemStackInitInfo
                        .builder()
                        .count(count)
                        .meta(meta)
                        .extraTag(saveExtraTag())
                        .uniqueId(newStackNetworkId ? EMPTY_UNIQUE_ID : uniqueId)
                        .assignUniqueId(newStackNetworkId)
                        .build()
        );
    }

    /**
     * Attempts placement of the owning stack's represented block state.
     * An item without a mapped block returns {@code false}; otherwise the shared
     * helper accepts optional interaction information and owns collision, behavior,
     * and post-placement handling.
     */
    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (thisItemStack.getItemType().getBlockType() == null) {
            return false;
        }

        var blockState = thisItemStack.toBlockState();
        return tryPlaceBlockState(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the player's immediate air-click notification to sibling item components.
     */
    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        manager.callEvent(new CItemRightClickInAirEvent(player));
    }

    /**
     * Dispatches an entity interaction and returns the result available at return.
     * The event starts unhandled, and sibling components can set its
     * {@code canBeUsed} value; this method performs no additional interaction itself.
     */
    @Override
    public boolean interactEntity(Entity performer, Entity victim) {
        var event = new CItemInteractEntityEvent(performer, victim, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    /**
     * Asks sibling components whether the player can begin timed air use.
     * The event starts at {@code false}, and this method returns the
     * {@code canBeUsed} value available when component dispatch returns.
     */
    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        var event = new CItemTryUseInAirEvent(player, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    /**
     * Dispatches a completed timed-use attempt with elapsed game ticks.
     * The event starts at {@code false}, and this method returns its
     * {@code canBeUsed} value available at dispatch return without another base effect.
     */
    @Override
    public boolean useItemInAir(EntityPlayer player, long usingTime) {
        var event = new CItemUseInAirEvent(player, usingTime, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    /**
     * Attempts shared block placement with entity-collision checking enabled.
     * The full helper checks collisions only when {@code placementInfo} is present;
     * {@code null} context also omits player-specific consumption after successful
     * placement.
     */
    protected boolean tryPlaceBlockState(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, blockState, placeBlockPos, placementInfo, true);
    }

    /**
     * Attempts a block-behavior combination or placement at the supplied position.
     * The caller supplies a non-{@code null} state. Entity collision is checked only
     * when interaction information is present and {@code checkEntityCollision} is {@code true};
     * a nonreplaceable target delegates directly to {@code combine}.
     *
     * <p>
     * A successful {@code place} call precedes sound, block-entity data application,
     * optional player consumption, and the placed-item component event. The direct
     * combination path does not run those effects. A failure in a later effect does
     * not roll back the already placed block, so the sequence is not an atomic
     * world-and-inventory transaction.
     */
    protected boolean tryPlaceBlockState(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo, boolean checkEntityCollision) {
        EntityPlayer player = null;
        if (placementInfo != null) {
            if (checkEntityCollision && hasEntityCollision(dimension, placeBlockPos, blockState)) {
                return false;
            }
            player = placementInfo.player();
        }

        var blockBehavior = blockState.getBlockType().getBlockBehavior();
        var oldBlockState = dimension.getBlockState(placeBlockPos);
        if (!oldBlockState.getBlockType().hasBlockTag(BlockTags.REPLACEABLE)) {
            return blockBehavior.combine(dimension, blockState, placeBlockPos, placementInfo);
        }

        var result = blockBehavior.place(dimension, blockState, placeBlockPos, placementInfo);
        if (result) {
            // The block already exists before data application and item
            // consumption; component observers receive the placed event only
            // after these effects complete, without rollback on later failure.
            dimension.addSound(placeBlockPos.x() + 0.5f, placeBlockPos.y() + 0.5f, placeBlockPos.z() + 0.5f, new BlockPlaceSound(blockState));
            tryApplyBlockEntityNBT(dimension, placeBlockPos);
            if (player != null) {
                tryConsumeItem(player);
            }
            manager.callEvent(new CItemPlacedAsBlockEvent(dimension, placeBlockPos, thisItemStack));
        }

        return result;
    }

    /**
     * Applies retained block-entity NBT to an entity at the placed position.
     * Absent data or a missing block entity produces no update. Callers must retain
     * location-independent clean data because the entity loads it directly.
     */
    protected void tryApplyBlockEntityNBT(Dimension dimension, Vector3ic placeBlockPos) {
        if (this.blockEntityNBT == null) {
            return;
        }

        var blockEntity = dimension.getBlockEntity(placeBlockPos);
        // Some placed blocks have no implemented block entity, so leave the
        // placement successful rather than fail while applying optional carried data.
        if (blockEntity == null) {
            return;
        }

        blockEntity.loadNBT(this.blockEntityNBT);
    }

    /**
     * Reduces the owning stack by one unless the player has infinite blocks.
     * A {@code null} player also consumes one item; an empty stack can therefore
     * fail through the count setter rather than clamp at zero.
     */
    protected void tryConsumeItem(EntityPlayer player) {
        if (player == null || !player.getController().hasInfiniteBlock()) {
            thisItemStack.reduceCount(1);
        }
    }

    /**
     * Checks the placed block's offset collision shape against nearby entities.
     * Candidate entity boxes shrink by the physics engine's fat margin before the
     * final intersection check, allowing placement beside inflated broad-phase
     * bounds.
     */
    protected boolean hasEntityCollision(Dimension dimension, Vector3ic placePos, BlockState blockState) {
        var collisionShape = blockState.getBlockStateData().computeOffsetCollisionShape(placePos);
        return dimension.getEntityManager().getPhysicsService()
                .computeCollidingEntities(collisionShape)
                .stream()
                // Remove the physics engine's broad-phase padding before the
                // final test so inflated bounds do not reject adjacent placement.
                .anyMatch(entity -> collisionShape.intersectsAABB(entity.getOffsetAABB().expand(-FAT_AABB_MARGIN, new AABBd())));
    }

    /**
     * Checks type identity, metadata, count policy, extra NBT equality, and block-state
     * identity to decide merge compatibility. The candidate must be non-{@code null};
     * {@code ignoreCount} bypasses only the maximum combined-count test, and extra
     * comparison invokes both stacks' save paths.
     */
    @Override
    public boolean canMerge(ItemStack itemStack, boolean ignoreCount) {
        Objects.requireNonNull(itemStack);
        return itemStack.getItemType() == getItemType() &&
               itemStack.getMeta() == getMeta() &&
               (ignoreCount || count + itemStack.getCount() <= itemType.getItemData().maxStackSize()) &&
               saveExtraTag().equals(itemStack.saveExtraTag()) &&
               itemStack.toBlockState() == toBlockState();
    }

    /**
     * Returns the base attack damage from the owning item type's data.
     */
    @Override
    public float calculateAttackDamage() {
        return itemType.getItemData().attackDamage();
    }

    /**
     * Returns whether the enchantment map contains the supplied type key.
     */
    @Override
    public boolean hasEnchantment(EnchantmentType enchantmentType) {
        return enchantments.containsKey(enchantmentType);
    }

    /**
     * Returns the stored level for the supplied enchantment type.
     * A missing entry yields {@code 0}, so this result alone cannot distinguish
     * absence from an explicitly stored zero-level enchantment.
     */
    @Override
    public int getEnchantmentLevel(EnchantmentType enchantmentType) {
        var instance = enchantments.get(enchantmentType);
        return instance == null ? 0 : instance.getLevel();
    }

    /**
     * Returns the live values collection of the stored enchantment map.
     * Removing entries or clearing this collection changes the stack; adding
     * through the map values view is unsupported by the backing map.
     */
    @Override
    public Collection<EnchantmentInstance> getEnchantments() {
        return enchantments.values();
    }

    /**
     * Stores a newly created enchantment instance under its supplied type.
     * The previous entry of that type is replaced; this method does not enforce
     * level limits, applicability, or compatibility with existing entries.
     */
    @Override
    public void addEnchantment(EnchantmentType enchantmentType, int level) {
        enchantments.put(enchantmentType, new EnchantmentInstance(enchantmentType, level));
    }

    /**
     * Stores supplied enchantment instances by type in collection iteration order.
     * The component retains each instance directly; later entries of the same type
     * replace earlier entries without compatibility or level validation.
     */
    @Override
    public void addEnchantments(Collection<EnchantmentInstance> enchantmentInstances) {
        enchantmentInstances.forEach(instance -> enchantments.put(instance.getType(), instance));
    }

    /**
     * Removes the enchantment entry for the supplied type and returns its instance.
     * A missing entry returns {@code null}; removing one type leaves every other
     * stored enchantment unchanged.
     */
    @Override
    public EnchantmentInstance removeEnchantment(EnchantmentType enchantmentType) {
        return enchantments.remove(enchantmentType);
    }

    /**
     * Removes every entry from this stack's stored enchantment map.
     */
    @Override
    public void removeAllEnchantments() {
        enchantments.clear();
    }

    /**
     * Dispatches a block-break notification with the block and breaking entity.
     */
    @Override
    public void onBreakBlock(BlockState block, Entity breaker) {
        manager.callEvent(new CItemBreakBlockEvent(block, breaker));
    }

    /**
     * Dispatches an attack notification with the attacking and targeted entities.
     */
    @Override
    public void onAttackEntity(Entity attacker, Entity victim) {
        manager.callEvent(new CItemAttackEntityEvent(attacker, victim));
    }

    /**
     * Returns whether stored damage reaches a damageable item's positive maximum.
     * Nondamageable items return {@code false}; an invalid nonpositive maximum logs
     * a warning and also returns {@code false} instead of reporting a broken item.
     */
    @Override
    public boolean isBroken() {
        if (!itemType.getItemData().isDamageable()) {
            return false;
        }

        var maxDamage = itemType.getItemData().maxDamage();
        if (maxDamage <= 0) {
            // A nonpositive threshold cannot define when this damageable item
            // breaks; report the inconsistent data and avoid declaring it broken.
            log.warn("Item {} does not support durability (maxDamage <= 0) but isDamageable is false!", itemType.getIdentifier());
            return false;
        }

        return damage >= maxDamage;
    }

    /**
     * Attempts a damage update after the unbreaking check and item damage event.
     * Rejected chance or cancelled dispatch returns {@code false}. Otherwise the
     * listener-modified amount reaches {@code setDamage}, and this method returns
     * {@code true} even if that setter ignores an unsupported item.
     */
    @Override
    public boolean tryIncreaseDamage(int increase) {
        if (!canBeDamagedThisTime()) {
            return false;
        }

        var event = new ItemDamageEvent(thisItemStack, increase);
        if (!event.call()) {
            return false;
        }

        setDamage(getDamage() + event.getDamage());
        return true;
    }

    /**
     * Selects tool suitability by required tier and category-specific block rules.
     * A block with a required tier rejects missing tool tiers and tiers that do not
     * compare strictly better through {@code ToolTier.isBetterThan}, including an
     * equal tier.
     *
     * <p>
     * After that gate, shears and standard tool categories use their block tags and
     * listed exceptions. The first matching tool category determines the result;
     * an uncategorized item returns {@code false}. This method does not substitute
     * the block's harvest requirements with the separate mining-speed multiplier.
     */
    @Override
    public boolean isCorrectToolFor(BlockState blockState) {
        var blockType = blockState.getBlockType();

        var requiredToolTier = BlockHelper.getRequiredToolTier(blockType);
        if (requiredToolTier != null) {
            var toolTier = ItemHelper.getToolTier(itemType);
            if (toolTier == null || !toolTier.isBetterThan(requiredToolTier)) {
                return false;
            }
        }

        if (itemType == ItemTypes.SHEARS) {
            if (blockType == BlockTypes.VINE || blockType == BlockTypes.GLOW_LICHEN) {
                return true;
            }

            return blockType.hasBlockTag(BlockTags.WOOL) ||
                   blockType.hasBlockTag(BlockTags.LEAVES) ||
                   blockType.hasBlockTag(BlockTags.PLANT) ||
                   blockType.hasBlockTag(BlockTags.IS_SHEARS_ITEM_DESTRUCTIBLE);
        }

        if (ItemHelper.isPickaxe(itemType)) {
            return blockType.hasBlockTag(BlockTags.IS_PICKAXE_ITEM_DESTRUCTIBLE);
        }

        if (ItemHelper.isAxe(itemType)) {
            return blockType.hasBlockTag(BlockTags.IS_AXE_ITEM_DESTRUCTIBLE);
        }

        if (ItemHelper.isShovel(itemType)) {
            return blockType.hasBlockTag(BlockTags.IS_SHOVEL_ITEM_DESTRUCTIBLE);
        }

        if (ItemHelper.isHoe(itemType)) {
            return blockType.hasBlockTag(BlockTags.IS_HOE_ITEM_DESTRUCTIBLE);
        }

        if (ItemHelper.isSword(itemType)) {
            if (
                    blockType == BlockTypes.BAMBOO ||
                    blockType == BlockTypes.BAMBOO_SAPLING ||
                    blockType == BlockTypes.COCOA ||
                    blockType == BlockTypes.HAY_BLOCK ||
                    blockType == BlockTypes.VINE ||
                    blockType == BlockTypes.GLOW_LICHEN
            ) return true;

            return blockType.hasBlockTag(BlockTags.IS_SWORD_ITEM_DESTRUCTIBLE);
        }

        // No recognized tool category matched this block, so this item cannot
        // satisfy the suitability check even if it has a sufficient tier tag.
        return false;
    }
}
