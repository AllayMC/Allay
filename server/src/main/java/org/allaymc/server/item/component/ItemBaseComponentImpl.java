package org.allaymc.server.item.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.BlockHelper;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.block.BlockPlaceEvent;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.component.data.ItemDataComponent;
import org.allaymc.api.item.enchantment.EnchantmentHelper;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.type.EnchantmentTypes;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.item.component.event.*;
import org.allaymc.server.utils.ItemMetaBlockStateBiMap;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.joml.Vector3ic;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.item.ItemHelper.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ItemBaseComponentImpl implements ItemBaseComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");

    private static int STACK_NETWORK_ID_COUNTER = 1;

    @Dependency
    protected ItemDataComponent itemDataComponent;

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
    protected int durability;
    @Getter
    @Setter
    protected String customName = "";
    @Getter
    @Setter
    protected List<String> lore = new ArrayList<>();
    protected Map<EnchantmentType, EnchantmentInstance> enchantments = new HashMap<>();
    //TODO: item lock type
    @Getter
    @Setter
    protected NbtMap customNBTContent = NbtMap.EMPTY;
    @Getter
    @Setter
    protected int stackNetworkId;

    public ItemBaseComponentImpl(ItemStackInitInfo initInfo) {
        this.itemType = initInfo.getItemType();
        this.count = initInfo.count();
        this.meta = initInfo.meta();
        var specifiedNetworkId = initInfo.stackNetworkId();
        if (specifiedNetworkId != EMPTY_STACK_NETWORK_ID) {
            if (specifiedNetworkId < 0)
                throw new IllegalArgumentException("stack network id cannot be negative");
            this.stackNetworkId = specifiedNetworkId;
        } else if (initInfo.autoAssignStackNetworkId()) {
            this.stackNetworkId = STACK_NETWORK_ID_COUNTER++;
        }
    }

    public static int getCurrentStackNetworkIdCounter() {
        return STACK_NETWORK_ID_COUNTER;
    }

    @OnInitFinish
    public void onInitFinish(ItemStackInitInfo initInfo) {
        loadExtraTag(initInfo.extraTag());
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        this.durability = extraTag.getInt("Damage", 0);
        extraTag.listenForCompound("display", displayNbt -> {
            this.customName = displayNbt.getString("Name");
            this.lore = displayNbt.getList("Lore", NbtType.STRING);
        });

        extraTag.listenForList("ench", NbtType.COMPOUND, enchsNbt -> enchsNbt.forEach(enchNbt -> {
            var enchantment = EnchantmentHelper.fromNBT(enchNbt);
            this.enchantments.put(enchantment.getType(), enchantment);
        }));

        extraTag.listenForCompound("CustomNBT", customNbt -> this.customNBTContent = customNbt);

        var event = new CItemLoadExtraTagEvent(extraTag);
        manager.callEvent(event);
    }

    @Override
    public NbtMap saveExtraTag() {
        var nbtBuilder = NbtMap.builder();
        if (durability != 0) nbtBuilder.putInt("Damage", durability);

        var displayBuilder = NbtMap.builder();
        if (!this.customName.isEmpty()) displayBuilder.put("Name", this.customName);
        if (!this.lore.isEmpty()) displayBuilder.putList("Lore", NbtType.STRING, this.lore);
        if (!displayBuilder.isEmpty()) nbtBuilder.putCompound("display", displayBuilder.build());

        if (!enchantments.isEmpty()) {
            var enchantmentNBT = this.enchantments.values().stream()
                    .map(EnchantmentInstance::saveNBT)
                    .toList();
            nbtBuilder.putList("ench", NbtType.COMPOUND, enchantmentNBT);
        }

        // TODO: item lock type

        // Custom NBT content
        if (!customNBTContent.isEmpty()) nbtBuilder.put("CustomNBT", customNBTContent);

        var event = new CItemSaveExtraTagEvent(nbtBuilder);
        manager.callEvent(event);

        return nbtBuilder.isEmpty() ? null : nbtBuilder.build();
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
        return count == itemDataComponent.getItemData().maxStackSize();
    }

    @Override
    public void setCount(int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        this.count = count;
    }

    @Override
    public void setMeta(int meta) {
        if (meta < 0)
            throw new IllegalArgumentException("Meta must bigger than zero!");
        this.meta = meta;
    }

    @Override
    public void setDurability(int durability) {
        if (!itemDataComponent.getItemData().isDamageable()) {
            log.warn("Item {} does not support durability!", itemType.getIdentifier());
            return;
        }
        if (durability < 0)
            throw new IllegalArgumentException("Durability must bigger than zero!");
        this.durability = durability;
    }

    @Override
    public boolean canBeDamagedThisTime() {
        var level = getEnchantmentLevel(EnchantmentTypes.UNBREAKING);
        if (level == 0) return true;

        var possibility = 1f / (level + 1f);
        return ThreadLocalRandom.current().nextFloat() <= possibility;
    }

    @Override
    public BlockState toBlockState() {
        return itemType.getBlockType() == null ?
                null :
                ItemMetaBlockStateBiMap.getMetaToBlockStateMapper(itemType).apply(meta);
    }

    @Override
    public ItemData toNetworkItemData() {
        if (itemType == ItemTypes.AIR) return ItemData.AIR;

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
        if (thisItemStack.getItemType().getBlockType() == null) return false;
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

        var oldBlockState = dimension.getBlockState(placeBlockPos);
        if (!oldBlockState.getBlockType().hasBlockTag(BlockCustomTags.REPLACEABLE)) {
            return false;
        }

        var blockType = blockState.getBlockType();

        var event = new BlockPlaceEvent(
                new BlockStateWithPos(blockState, new Position3i(placeBlockPos, dimension), 0),
                oldBlockState, thisItemStack, player, placementInfo
        );
        if (!event.call()) {
            return false;
        }

        var result = blockType.getBlockBehavior().place(dimension, blockState, placeBlockPos, placementInfo);
        if (result && player != null) {
            dimension.addLevelSoundEvent(placeBlockPos.x() + 0.5f, placeBlockPos.y() + 0.5f, placeBlockPos.z() + 0.5f, SoundEvent.PLACE, blockState.blockStateHash());
            tryConsumeItem(player);
            var e = new CItemPlacedAsBlockEvent(dimension, placeBlockPos, thisItemStack);
            manager.callEvent(e);
        }
        return result;
    }

    protected void tryConsumeItem(EntityPlayer player) {
        if (player == null || player.getGameType() != GameType.CREATIVE) {
            thisItemStack.setCount(thisItemStack.getCount() - 1);
        }
    }

    protected boolean hasEntityCollision(Dimension dimension, Vector3ic placePos, BlockState blockState) {
        var blockAABB = blockState.getBehavior().getBlockStateData(blockState).computeOffsetCollisionShape(
                placePos.x(),
                placePos.y(),
                placePos.z()
        );
        return !dimension.getEntityPhysicsService().computeCollidingEntities(blockAABB).isEmpty();
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
               (ignoreCount || count + itemStack.getCount() <= itemDataComponent.getItemData().maxStackSize()) &&
               extraTag1.equals(extraTag2) &&
               itemStack.toBlockState() == toBlockState();
    }

    @Override
    public float calculateAttackDamage() {
        return itemDataComponent.getItemData().attackDamage();
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
        if (!itemDataComponent.getItemData().isDamageable()) {
            return false;
        }
        var maxDamage = itemDataComponent.getItemData().maxDamage();
        // This item does not support durability
        if (maxDamage == 0) return false;
        return durability >= maxDamage;
    }

    @Override
    public boolean tryReduceDurability(int reduction) {
        if (!canBeDamagedThisTime()) {
            return false;
        }
        setDurability(getDurability() + reduction);
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

        if (isPickaxe(itemType)) {
            return blockType.hasBlockTag(BlockTags.IS_PICKAXE_ITEM_DESTRUCTIBLE);
        }

        if (isAxe(itemType)) {
            return blockType.hasBlockTag(BlockTags.IS_AXE_ITEM_DESTRUCTIBLE);
        }

        if (isShovel(itemType)) {
            return blockType.hasBlockTag(BlockTags.IS_SHOVEL_ITEM_DESTRUCTIBLE);
        }

        if (isHoe(itemType)) {
            return blockType.hasBlockTag(BlockTags.IS_HOE_ITEM_DESTRUCTIBLE);
        }

        if (isSword(itemType)) {
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
