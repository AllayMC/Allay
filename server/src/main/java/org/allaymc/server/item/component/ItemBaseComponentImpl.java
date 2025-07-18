package org.allaymc.server.item.component;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.BlockHelper;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.data.ItemLockMode;
import org.allaymc.api.item.enchantment.EnchantmentHelper;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.pdc.PersistentDataContainer;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.item.component.event.*;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.joml.Vector3ic;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static final AtomicInteger STACK_NETWORK_ID_COUNTER = new AtomicInteger(1);

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
    protected int stackNetworkId;

    public ItemBaseComponentImpl(ItemStackInitInfo initInfo) {
        this.itemType = initInfo.getItemType();
        this.count = initInfo.count();
        this.meta = initInfo.meta();
        var specifiedNetworkId = initInfo.stackNetworkId();
        if (specifiedNetworkId != EMPTY_STACK_NETWORK_ID) {
            Preconditions.checkArgument(specifiedNetworkId > 0, "Specified ItemStack network id must be greater than 0");
            this.stackNetworkId = specifiedNetworkId;
        } else if (initInfo.autoAssignStackNetworkId()) {
            this.stackNetworkId = STACK_NETWORK_ID_COUNTER.getAndIncrement();
        }
    }

    public static int getCurrentStackNetworkIdCounter() {
        return STACK_NETWORK_ID_COUNTER.get();
    }

    @OnInitFinish
    public void onInitFinish(ItemStackInitInfo initInfo) {
        loadExtraTag(initInfo.extraTag());
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
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

        var event = new CItemLoadExtraTagEvent(extraTag);
        manager.callEvent(event);
    }

    @Override
    public NbtMap saveExtraTag() {
        var nbtBuilder = NbtMap.builder();
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

        var event = new CItemSaveExtraTagEvent(nbtBuilder);
        manager.callEvent(event);

        return nbtBuilder.isEmpty() ? null : nbtBuilder.build();
    }

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder()
                .putByte(TAG_COUNT, (byte) getCount())
                .putShort(TAG_META, (short) getMeta())
                .putString(TAG_NAME, getItemType().getIdentifier().toString());

        var extraTag = saveExtraTag();
        if (extraTag != null) {
            builder.putCompound(TAG_EXTRA_TAG, extraTag);
        }

        var blockState = toBlockState();
        if (blockState != null) {
            builder.put(TAG_BLOCK, blockState.getBlockStateTag());
        }

        // TODO: CanDestroy
        // TODO: CanPlaceOn

        return builder.build();
    }

    @Override
    public void rightClickItemOn(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        var event = new CItemRightClickOnBlockEvent(dimension, placeBlockPos, interactInfo);
        manager.callEvent(event);
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
    public ItemData toNetworkItemData() {
        if (itemType == ItemTypes.AIR) {
            return ItemData.AIR;
        }

        var blockState = toBlockState();
        return ItemData
                .builder()
                .definition(itemType.toNetworkDefinition())
                .blockDefinition(blockState != null ? blockState.toNetworkBlockDefinition() : () -> 0)
                .count(count)
                .damage(meta)
                .tag(saveExtraTag())
                .usingNetId(hasStackNetworkId())
                .netId(stackNetworkId)
                .build();
    }

    @Override
    public ItemStack copy(boolean newStackNetworkId) {
        var extraTag = saveExtraTag();
        return itemType.createItemStack(
                ItemStackInitInfo
                        .builder()
                        .count(count)
                        .meta(meta)
                        .extraTag(extraTag != null ? extraTag : NbtMap.EMPTY)
                        .stackNetworkId(newStackNetworkId ? EMPTY_STACK_NETWORK_ID : stackNetworkId)
                        .autoAssignStackNetworkId(newStackNetworkId)
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
    public boolean canUseItemInAir(EntityPlayer player) {
        var event = new CItemTryUseInAirEvent(player, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    @Override
    public boolean useItemInAir(EntityPlayer player, long usedTime) {
        var event = new CItemUsedInAirEvent(player, usedTime, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    protected boolean tryPlaceBlockState(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        EntityPlayer player = null;
        if (placementInfo != null) {
            if (hasEntityCollision(dimension, placeBlockPos, blockState)) {
                return false;
            }
            player = placementInfo.player();
        }

        var blockBehavior = blockState.getBlockType().getBlockBehavior();
        var oldBlockState = dimension.getBlockState(placeBlockPos);
        if (!oldBlockState.getBlockType().hasBlockTag(BlockCustomTags.REPLACEABLE)) {
            return blockBehavior.combine(dimension, blockState, placeBlockPos, placementInfo);
        }

        var result = blockBehavior.place(dimension, blockState, placeBlockPos, placementInfo);
        if (result) {
            dimension.addLevelSoundEvent(placeBlockPos.x() + 0.5f, placeBlockPos.y() + 0.5f, placeBlockPos.z() + 0.5f, SoundEvent.PLACE, blockState.blockStateHash());
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

        // The position data should be removed
        var builder = blockEntityNBT.toBuilder();
        builder.remove("x");
        builder.remove("y");
        builder.remove("z");
        blockEntity.loadNBT(builder.build());
    }

    protected void tryConsumeItem(EntityPlayer player) {
        if (player == null || player.getGameType() != GameType.CREATIVE) {
            thisItemStack.reduceCount(1);
        }
    }

    protected boolean hasEntityCollision(Dimension dimension, Vector3ic placePos, BlockState blockState) {
        var blockAABB = blockState.getBehavior().getBlockStateData(blockState).computeOffsetCollisionShape(
                placePos.x(),
                placePos.y(),
                placePos.z()
        );
        return !dimension.getEntityService().getPhysicsService().computeCollidingEntities(blockAABB).isEmpty();
    }

    @Override
    public boolean canMerge(ItemStack itemStack, boolean ignoreCount) {
        Objects.requireNonNull(itemStack);

        var extraTag1 = saveExtraTag();
        if (extraTag1 == null) extraTag1 = NbtMap.EMPTY;

        var extraTag2 = itemStack.saveExtraTag();
        if (extraTag2 == null) extraTag2 = NbtMap.EMPTY;
        return itemStack.getItemType() == getItemType() &&
               itemStack.getMeta() == getMeta() &&
               (ignoreCount || count + itemStack.getCount() <= itemType.getItemData().maxStackSize()) &&
               extraTag1.equals(extraTag2) &&
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
        setDamage(getDamage() + increase);
        return true;
    }

    @Override
    public boolean isCorrectToolFor(BlockState blockState) {
        var blockType = blockState.getBlockType();

        var requiredToolTier = BlockHelper.getRequiredToolTier(blockType);
        // requiredToolTier != null means that this block has tool tier requirement
        if (requiredToolTier != null && !ItemHelper.getToolTier(itemType).isBetterThan(requiredToolTier)) {
            // The tool tier is not enough
            return false;
        }

        if (itemType == ItemTypes.SHEARS) {
            if (blockType == BlockTypes.VINE || blockType == BlockTypes.GLOW_LICHEN) {
                return true;
            }

            return blockType.hasBlockTag(BlockCustomTags.WOOL) ||
                   blockType.hasBlockTag(BlockCustomTags.LEAVES) ||
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
