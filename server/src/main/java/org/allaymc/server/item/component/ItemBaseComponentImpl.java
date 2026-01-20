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
import org.allaymc.api.player.GameMode;
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
 * @author daoge_cmd
 */
@Slf4j
public class ItemBaseComponentImpl implements ItemBaseComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");

    protected static final String TAG_COUNT = "Count";
    protected static final String TAG_META = "Damage";
    protected static final String TAG_NAME = "Name";
    protected static final String TAG_EXTRA_TAG = "tag";
    protected static final String TAG_BLOCK = "Block";

    // The following tags are in extra tag.
    protected static final String TAG_DAMAGE = "Damage";
    protected static final String TAG_REPAIR_COST = "RepairCost";
    protected static final String TAG_DISPLAY = "display";
    protected static final String TAG_CUSTOM_NAME = "Name";
    protected static final String TAG_LORE = "Lore";
    protected static final String TAG_ENCHANTMENT = "ench";
    protected static final String TAG_BLOCK_ENTITY = "BlockEntityTag";
    protected static final String TAG_LOCK_MODE = "minecraft:item_lock";
    protected static final String TAG_PDC = "PDC";

    // The unique id counter should start at 1 because 0 is used to indicate that this item stack does not have a unique id
    private static final AtomicInteger UNIQUE_ID = new AtomicInteger(1);

    @ComponentObject
    protected ItemStack thisItemStack;

    @Manager
    protected ComponentManager manager;

    @Getter
    protected ItemType<?> itemType;
    @Getter
    protected int count;
    @Getter
    protected int meta;
    @Getter
    protected int damage;
    @Getter
    protected int repairCost;
    @Getter
    @Setter
    protected String customName = "";
    @Getter
    @Setter
    protected List<String> lore = new ArrayList<>();
    protected Map<EnchantmentType, EnchantmentInstance> enchantments = new HashMap<>();
    @Getter
    @Setter
    protected ItemLockMode lockMode = ItemLockMode.NONE;
    @Getter
    @Setter
    protected PersistentDataContainer persistentDataContainer = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);

    @Getter
    @Setter
    protected NbtMap blockEntityNBT;
    @Getter
    @Setter
    protected int uniqueId;

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

    @VisibleForTesting
    public static int getCurrentUniqueIdCounter() {
        return UNIQUE_ID.get();
    }

    @OnInitFinish
    public void onInitFinish(ItemStackInitInfo initInfo) {
        loadExtraTag(initInfo.extraTag());
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        manager.callEvent(new CItemLoadExtraTagEvent(extraTag));

        this.damage = extraTag.getInt(TAG_DAMAGE, 0);
        this.repairCost = extraTag.getInt(TAG_REPAIR_COST, 0);

        extraTag.listenForCompound(TAG_DISPLAY, displayNbt -> {
            this.customName = displayNbt.getString(TAG_CUSTOM_NAME);
            this.lore = displayNbt.getList(TAG_LORE, NbtType.STRING);
        });
        extraTag.listenForList(TAG_ENCHANTMENT, NbtType.COMPOUND, enchsNbt -> enchsNbt.forEach(enchNbt -> {
            var enchantment = EnchantmentHelper.fromNBT(enchNbt);
            this.enchantments.put(enchantment.getType(), enchantment);
        }));
        extraTag.listenForCompound(TAG_BLOCK_ENTITY, nbt -> this.blockEntityNBT = nbt);
        extraTag.listenForByte(TAG_LOCK_MODE, lockMode -> this.lockMode = ItemLockMode.values()[lockMode]);
        extraTag.listenForCompound(TAG_PDC, customNbt -> {
            this.persistentDataContainer.clear();
            this.persistentDataContainer.putAll(customNbt);
        });

    }

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

    @Override
    public NbtMap saveNBT() {
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

        // TODO: CanDestroy
        // TODO: CanPlaceOn

        return builder.build();
    }

    @Override
    public void rightClickItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        manager.callEvent(new CItemRightClickOnBlockEvent(dimension, placeBlockPos, interactInfo));
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        var event = new CItemUseOnBlockEvent(dimension, placeBlockPos, interactInfo, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    @Override
    public boolean isFull() {
        return count == itemType.getItemData().maxStackSize();
    }

    @Override
    public void setCount(int count) {
        // Setting count to zero is valid, because in some places we need to write like this
        Preconditions.checkArgument(count >= 0, "Count must be greater or equal to 0");
        this.count = count;
    }

    @Override
    public void setMeta(int meta) {
        Preconditions.checkArgument(meta >= 0, "Meta must be greater or equal to 0");
        this.meta = meta;
    }

    @Override
    public void setDamage(int damage) {
        if (!itemType.getItemData().isDamageable()) {
            log.warn("Item {} does not support durability!", itemType.getIdentifier());
            return;
        }

        Preconditions.checkArgument(damage >= 0, "Damage must be greater or equal to 0");
        this.damage = damage;
    }

    @Override
    public void setRepairCost(int repairCost) {
        Preconditions.checkArgument(repairCost >= 0, "RepairCost must be greater or equal to 0");
        this.repairCost = repairCost;
    }

    @Override
    public boolean canBeDamagedThisTime() {
        var level = getEnchantmentLevel(EnchantmentTypes.UNBREAKING);
        if (level == 0) {
            return true;
        }

        var possibility = 1f / (level + 1f);
        return ThreadLocalRandom.current().nextFloat() <= possibility;
    }

    @Override
    public BlockState toBlockState() {
        return itemType.getBlockType() != null ? itemType.getBlockType().getDefaultState() : null;
    }

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

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (thisItemStack.getItemType().getBlockType() == null) {
            return false;
        }

        var blockState = thisItemStack.toBlockState();
        return tryPlaceBlockState(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        manager.callEvent(new CItemClickInAirEvent(player));
    }

    @Override
    public boolean interactEntity(Entity performer, Entity victim) {
        var event = new CItemInteractEntityEvent(performer, victim, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        var event = new CItemTryUseInAirEvent(player, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    @Override
    public boolean useItemInAir(EntityPlayer player, long usingTime) {
        var event = new CItemUseInAirEvent(player, usingTime, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    protected boolean tryPlaceBlockState(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, blockState, placeBlockPos, placementInfo, true);
    }

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
            dimension.addSound(placeBlockPos.x() + 0.5f, placeBlockPos.y() + 0.5f, placeBlockPos.z() + 0.5f, new BlockPlaceSound(blockState));
            tryApplyBlockEntityNBT(dimension, placeBlockPos);
            if (player != null) {
                tryConsumeItem(player);
            }
            manager.callEvent(new CItemPlacedAsBlockEvent(dimension, placeBlockPos, thisItemStack));
        }

        return result;
    }

    protected void tryApplyBlockEntityNBT(Dimension dimension, Vector3ic placeBlockPos) {
        if (this.blockEntityNBT == null) {
            return;
        }

        var blockEntity = dimension.getBlockEntity(placeBlockPos);
        // Block entity should also being spawned when placing block
        // if the block entity is implemented
        if (blockEntity == null) {
            return;
        }

        blockEntity.loadNBT(this.blockEntityNBT);
    }

    protected void tryConsumeItem(EntityPlayer player) {
        if (player == null || player.getGameMode() != GameMode.CREATIVE) {
            thisItemStack.reduceCount(1);
        }
    }

    protected boolean hasEntityCollision(Dimension dimension, Vector3ic placePos, BlockState blockState) {
        var collisionShape = blockState.getBlockStateData().computeOffsetCollisionShape(placePos);
        return dimension.getEntityManager().getPhysicsService()
                .computeCollidingEntities(collisionShape)
                .stream()
                // Shrink the entities' aabb slightly and check if there are still collisions. This allows placing block next to entities like painting
                .anyMatch(entity -> collisionShape.intersectsAABB(entity.getOffsetAABB().expand(-FAT_AABB_MARGIN, new AABBd())));
    }

    @Override
    public boolean canMerge(ItemStack itemStack, boolean ignoreCount) {
        Objects.requireNonNull(itemStack);
        return itemStack.getItemType() == getItemType() &&
               itemStack.getMeta() == getMeta() &&
               (ignoreCount || count + itemStack.getCount() <= itemType.getItemData().maxStackSize()) &&
               saveExtraTag().equals(itemStack.saveExtraTag()) &&
               itemStack.toBlockState() == toBlockState();
    }

    @Override
    public float calculateAttackDamage() {
        return itemType.getItemData().attackDamage();
    }

    @Override
    public boolean hasEnchantment(EnchantmentType enchantmentType) {
        return enchantments.containsKey(enchantmentType);
    }

    @Override
    public int getEnchantmentLevel(EnchantmentType enchantmentType) {
        var instance = enchantments.get(enchantmentType);
        return instance == null ? 0 : instance.getLevel();
    }

    @Override
    public Collection<EnchantmentInstance> getEnchantments() {
        return enchantments.values();
    }

    @Override
    public void addEnchantment(EnchantmentType enchantmentType, int level) {
        enchantments.put(enchantmentType, new EnchantmentInstance(enchantmentType, level));
    }

    @Override
    public void addEnchantments(Collection<EnchantmentInstance> enchantmentInstances) {
        enchantmentInstances.forEach(instance -> enchantments.put(instance.getType(), instance));
    }

    @Override
    public EnchantmentInstance removeEnchantment(EnchantmentType enchantmentType) {
        return enchantments.remove(enchantmentType);
    }

    @Override
    public void removeAllEnchantments() {
        enchantments.clear();
    }

    @Override
    public void onBreakBlock(BlockState block, Entity breaker) {
        manager.callEvent(new CItemBreakBlockEvent(block, breaker));
    }

    @Override
    public void onAttackEntity(Entity attacker, Entity victim) {
        manager.callEvent(new CItemAttackEntityEvent(attacker, victim));
    }

    @Override
    public boolean isBroken() {
        if (!itemType.getItemData().isDamageable()) {
            return false;
        }

        var maxDamage = itemType.getItemData().maxDamage();
        if (maxDamage <= 0) {
            // This should be a bug
            log.warn("Item {} does not support durability (maxDamage <= 0) but isDamageable is false!", itemType.getIdentifier());
            return false;
        }

        return damage >= maxDamage;
    }

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

    @Override
    public boolean isCorrectToolFor(BlockState blockState) {
        var blockType = blockState.getBlockType();

        var requiredToolTier = BlockHelper.getRequiredToolTier(blockType);
        // requiredToolTier != null means that this block has a tool tier requirement
        if (requiredToolTier != null) {
            var toolTier = ItemHelper.getToolTier(itemType);
            if (toolTier == null || !toolTier.isBetterThan(requiredToolTier)) {
                // The tool tier is not enough
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

        return false;
    }
}
