package org.allaymc.server.item.component.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.data.*;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.common.ItemBaseComponent;
import org.allaymc.api.item.component.common.ItemDataComponent;
import org.allaymc.api.item.component.event.*;
import org.allaymc.api.item.enchantment.EnchantmentHelper;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.enchantment.SimpleEnchantmentInstance;
import org.allaymc.api.item.enchantment.type.EnchantmentUnbreakingType;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.type.InternalBlockTypeData;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.joml.Vector3ic;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.item.ItemHelper.*;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
@Slf4j
public class ItemBaseComponentImpl<T extends ItemStack> implements ItemBaseComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");

    // TODO: Due to differences between server-side and client-side block placement checks,
    //  which cannot be synchronized 100%, "block swallowing" phenomenon may occur.
    //  Here, the check is temporarily disabled.
    protected static final boolean DO_BLOCK_PLACING_CHECK = false;

    private static int STACK_NETWORK_ID_COUNTER = 1;

    @Dependency
    protected ItemDataComponent attributeComponent;

    @ComponentedObject
    protected T thisItemStack;

    @Manager
    protected ComponentManager<T> manager;

    @Getter
    protected ItemType<T> itemType;
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

    public ItemBaseComponentImpl(ItemStackInitInfo<T> initInfo) {
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

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        loadExtraTag(((ItemStackInitInfo<?>) initInfo).extraTag());
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        this.durability = extraTag.getInt("Damage", 0);
        extraTag.listenForCompound("display", displayNbt -> {
            this.customName = displayNbt.getString("Name");
            this.lore = extraTag.getList("Lore", NbtType.STRING);
        });

        extraTag.listenForList("ench", NbtType.COMPOUND, enchsNbt -> enchsNbt.forEach(enchNbt -> {
            var enchantment = EnchantmentHelper.fromNBT(enchNbt);
            this.enchantments.put(enchantment.getType(), enchantment);
        }));

        extraTag.listenForCompound("CustomNBT", customNbt -> this.customNBTContent = customNbt);

        var event = new ItemLoadExtraTagEvent(extraTag);
        manager.callEvent(event);
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
        if (durability < 0)
            throw new IllegalArgumentException("Durability must bigger than zero!");
        this.durability = durability;
    }

    @Override
    public boolean willDamageItem() {
        var level = getEnchantmentLevel(EnchantmentUnbreakingType.UNBREAKING_TYPE);
        if (level == 0) return true;

        var possibility = 1f / (level + 1f);
        return ThreadLocalRandom.current().nextFloat() <= possibility;
    }

    @Override
    public BlockState toBlockState() {
        return itemType.getBlockType() == null ?
                null :
                VanillaItemMetaBlockStateBiMap.getRegistry().getMetaToBlockStateMapper(itemType).apply(meta);
    }

    @Override
    public ItemData toNetworkItemData() {
        if (itemType == ItemTypes.AIR_TYPE) return ItemData.AIR;

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
    public int assignNewStackNetworkId() {
        stackNetworkId = STACK_NETWORK_ID_COUNTER++;
        return stackNetworkId;
    }

    @Override
    public ItemStack copy(boolean newStackNetworkId) {
        var extraTag = saveExtraTag();
        return itemType.createItemStack(
                SimpleItemStackInitInfo
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

        //TODO: item lock type

        // Custom NBT content
        if (!customNBTContent.isEmpty()) nbtBuilder.put("CustomNBT", customNBTContent);

        var event = new ItemSaveExtraTagEvent(nbtBuilder);
        manager.callEvent(event);

        return nbtBuilder.isEmpty() ? null : nbtBuilder.build();
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (thisItemStack.getItemType().getBlockType() == null) return false;
        var blockState = thisItemStack.toBlockState();
        return tryPlaceBlockState(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        var event = new ItemTryUseEvent(player, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    @Override
    public boolean useItemInAir(EntityPlayer player, long usedTime) {
        var event = new ItemUsedEvent(player, usedTime, false);
        manager.callEvent(event);
        return event.isCanBeUsed();
    }

    protected boolean tryPlaceBlockState(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var player = placementInfo.player();
        if (player != null && DO_BLOCK_PLACING_CHECK && hasEntityCollision(dimension, placeBlockPos, blockState))
            return false;

        var oldBlockState = dimension.getBlockState(placeBlockPos);
        if (!oldBlockState.getBlockType().hasBlockTag(VanillaBlockTags.REPLACEABLE)) return false;

        var blockType = blockState.getBlockType();
        var result = blockType.getBlockBehavior().place(dimension, blockState, placeBlockPos, placementInfo);
        if (result) {
            tryConsumeItem(player);
            var event = new ItemPlacedAsBlockEvent(dimension, placeBlockPos, thisItemStack);
            manager.callEvent(event);
        }
        return result;
    }

    protected void tryConsumeItem(EntityPlayer player) {
        if (player == null || player.getGameType() != GameType.CREATIVE)
            thisItemStack.setCount(thisItemStack.getCount() - 1);
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
               (ignoreCount || count + itemStack.getCount() <= attributeComponent.getItemData().maxStackSize()) &&
               extraTag1.equals(extraTag2) &&
               itemStack.toBlockState() == toBlockState();
    }

    @Override
    public float calculateAttackDamage() {
        return attributeComponent.getItemData().attackDamage();
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
        enchantments.put(enchantmentType, new SimpleEnchantmentInstance(enchantmentType, level));
    }

    @Override
    public void removeEnchantment(EnchantmentType enchantmentType) {
        enchantments.remove(enchantmentType);
    }

    @Override
    public void removeAllEnchantments() {
        enchantments.clear();
    }

    @Override
    public void onBreakBlock(BlockState block, Entity breaker) {
        manager.callEvent(new ItemBreakBlockEvent(block, breaker));
    }

    @Override
    public void onAttackEntity(Entity attacker, Entity victim) {
        manager.callEvent(new ItemAttackEntityEvent(attacker, victim));
    }

    @Override
    public boolean isBroken() {
        var maxDamage = attributeComponent.getItemData().maxDamage();
        // This item does not support durability
        if (maxDamage == 0) return false;
        return durability >= maxDamage;
    }

    @Override
    public boolean isCorrectToolFor(BlockState blockState) {
        var blockType = blockState.getBlockType();
        var vanillaItemId = VanillaItemId.fromIdentifier(itemType.getIdentifier());
        var vanillaBlockId = VanillaBlockId.fromIdentifier(blockType.getIdentifier());
        if (vanillaItemId != null && vanillaBlockId != null) {
            var specialTools = InternalBlockTypeData.getSpecialTools(vanillaBlockId);
            if (specialTools != null)
                return Arrays.stream(specialTools).anyMatch(tool -> tool == vanillaItemId);
        }

        var materialType = blockState.getBlockType().getMaterial().materialType();
        if (itemType == ItemTypes.SHEARS_TYPE) {
            if (blockType == BlockTypes.VINE_TYPE || blockType == BlockTypes.GLOW_LICHEN_TYPE) return true;

            return materialType == VanillaMaterialTypes.CLOTH ||
                   materialType == VanillaMaterialTypes.LEAVES ||
                   materialType == VanillaMaterialTypes.PLANT ||
                   materialType == VanillaMaterialTypes.WEB;
        }

        if (isAxe(itemType)) return materialType == VanillaMaterialTypes.WOOD;

        if (isShovel(itemType))
            return materialType == VanillaMaterialTypes.DIRT ||
                   materialType == VanillaMaterialTypes.CLAY ||
                   materialType == VanillaMaterialTypes.SAND ||
                   materialType == VanillaMaterialTypes.SNOW ||
                   materialType == VanillaMaterialTypes.TOPSNOW;

        if (isHoe(itemType)) {
            if (
                    blockType == BlockTypes.DRIED_KELP_BLOCK_TYPE ||
                    blockType == BlockTypes.HAY_BLOCK_TYPE ||
                    blockType == BlockTypes.TARGET_TYPE ||
                    blockType == BlockTypes.SPONGE_TYPE ||
                    blockType == BlockTypes.MOSS_BLOCK_TYPE
            ) return true;

            return materialType == VanillaMaterialTypes.LEAVES ||
                   materialType == VanillaMaterialTypes.NETHERWART ||
                   materialType == VanillaMaterialTypes.SCULK;
        }

        if (isSword(itemType)) {
            if (
                    blockType == BlockTypes.BAMBOO_TYPE ||
                    blockType == BlockTypes.BAMBOO_SAPLING_TYPE ||
                    blockType == BlockTypes.COCOA_TYPE ||
                    blockType == BlockTypes.HAY_BLOCK_TYPE ||
                    blockType == BlockTypes.VINE_TYPE ||
                    blockType == BlockTypes.GLOW_LICHEN_TYPE
            ) return true;

            return materialType == VanillaMaterialTypes.VEGETABLE ||
                   materialType == VanillaMaterialTypes.LEAVES ||
                   materialType == VanillaMaterialTypes.WEB;
        }

        return false;
    }
}
