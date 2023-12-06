package org.allaymc.api.item.component.base;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.data.VanillaItemMetaBlockStateBiMap;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.attribute.ItemAttributeComponent;
import org.allaymc.api.item.enchantment.EnchantmentHelper;
import org.allaymc.api.item.enchantment.EnchantmentInstance;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
@Slf4j
public class ItemBaseComponentImpl<T extends ItemStack> implements ItemBaseComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");

    private static int STACK_NETWORK_ID_COUNTER = 1;

    public static int getCurrentStackNetworkIdCounter() {
        return STACK_NETWORK_ID_COUNTER;
    }

    @Dependency
    protected ItemAttributeComponent attributeComponent;

    protected ItemType<T> itemType;
    protected int count;
    protected int meta;
    protected int durability;
    protected String customName = "";
    protected List<String> lore = new ArrayList<>();
    protected Map<EnchantmentType, EnchantmentInstance> enchantments = new HashMap<>();
    //TODO: item lock type
    protected NbtMap customNBTContent = NbtMap.EMPTY;
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

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        loadExtraTag(((ItemStackInitInfo<?>) initInfo).extraTag());
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        this.durability = extraTag.getInt("Damage", 0);
        if (extraTag.containsKey("display")) {
            var displayTag = extraTag.getCompound("display");
            this.customName = displayTag.getString("Name");
            this.lore = extraTag.getList("Lore", NbtType.STRING);
        }
        if (extraTag.containsKey("ench")) {
            extraTag.getList("ench", NbtType.COMPOUND).forEach(tag -> {
                var enchantment = EnchantmentHelper.fromNBT(tag);
                this.enchantments.put(enchantment.getType(), enchantment);
            });
        }
    }

    @Override
    public ItemType<? extends ItemStack> getItemType() {
        return itemType;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        this.count = count;
    }

    @Override
    public int getMeta() {
        return meta;
    }

    @Override
    public void setMeta(int meta) {
        if (meta < 0)
            throw new IllegalArgumentException("Meta must bigger than zero!");
        this.meta = meta;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public void setDurability(int durability) {
        if (durability < 0)
            throw new IllegalArgumentException("Durability must bigger than zero!");
        this.durability = durability;
    }

    @Override
    public String getCustomName() {
        return customName;
    }

    @Override
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public List<String> getLore() {
        return lore;
    }

    @Override
    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    @Override
    public BlockState toBlockState() {
        return itemType.getBlockType() == null ?
                null :
                VanillaItemMetaBlockStateBiMap.getRegistry().getMetaToBlockStateMapper(itemType).apply(meta);
    }

    @Override
    public ItemData toNetworkItemData() {
        if (itemType == ItemAirStack.AIR_TYPE) {
            return ItemData.AIR;
        } else {
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
    }

    @Override
    public int getStackNetworkId() {
        return stackNetworkId;
    }

    @Override
    public void setStackNetworkId(int newStackNetworkId) {
        this.stackNetworkId = newStackNetworkId;
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
    @Nullable
    public NbtMap saveExtraTag() {
        NbtMapBuilder nbtBuilder = NbtMap.builder();
        if (durability != 0) {
            nbtBuilder.putInt("Damage", durability);
        }

        NbtMapBuilder displayBuilder = NbtMap.builder();
        if (!this.customName.isEmpty()) {
            displayBuilder.put("Name", this.customName);
        }
        if (!this.lore.isEmpty()) {
            displayBuilder.putList("Lore", NbtType.STRING, this.lore);
        }
        if (!displayBuilder.isEmpty()) {
            nbtBuilder.putCompound("display", displayBuilder.build());
        }
        if (!enchantments.isEmpty()) {
            List<NbtMap> enchantmentNBT = new ArrayList<>();
            for (var enchantment : this.enchantments.values()) {
                enchantmentNBT.add(enchantment.saveNBT());
            }
            nbtBuilder.putList("ench", NbtType.COMPOUND, enchantmentNBT);
        }
        //TODO: item lock type

        return nbtBuilder.isEmpty() ? null : nbtBuilder.build();
    }

    @Override
    public NbtMap getCustomNBTContent() {
        return customNBTContent;
    }

    @Override
    public void setCustomNBTContent(NbtMap customNBTContent) {
        this.customNBTContent = customNBTContent;
    }

    @Override
    public boolean useItemOn(
            @Nullable EntityPlayer player, ItemStack itemStack,
            Dimension dimension, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos,
            BlockFace blockFace) {
        if (itemStack.getItemType().getBlockType() == null)
            return false;
        var blockState = itemStack.toBlockState();
        return tryPlaceBlockState(player, itemStack, dimension, targetBlockPos, placeBlockPos, clickPos, blockFace, blockState);
    }

    protected boolean tryPlaceBlockState(@Nullable EntityPlayer player, ItemStack itemStack, Dimension dimension, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace, BlockState blockState) {
        if (player != null && hasEntityCollision(dimension, placeBlockPos, blockState))
            return false;
        BlockType<?> blockType = blockState.getBlockType();
        assert blockType != null;
        boolean result = blockType.getBlockBehavior().place(player, dimension, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace);
        tryConsumeItem(player, itemStack);
        return result;
    }

    protected void tryConsumeItem(EntityPlayer player, ItemStack itemStack) {
        if (player == null || player.getGameType() != GameType.CREATIVE)
            itemStack.setCount(itemStack.getCount() - 1);
    }

    protected boolean hasEntityCollision(Dimension dimension, Vector3ic placePos, BlockState blockState) {
        var block_aabb = blockState.getBehavior().getBlockAttributes(blockState)
                .computeOffsetVoxelShape(
                        placePos.x(),
                        placePos.y(),
                        placePos.z()
                );
        return !dimension.getEntityPhysicsService().computeCollidingEntities(block_aabb).isEmpty();
    }

    @Override
    public boolean canMerge(ItemStack itemStack, boolean ignoreCount) {
        var extraTag1 = saveExtraTag();
        if (extraTag1 == null) extraTag1 = NbtMap.EMPTY;
        var extraTag2 = itemStack.saveExtraTag();
        if (extraTag2 == null) extraTag2 = NbtMap.EMPTY;
        return itemStack.getItemType() == getItemType() &&
                itemStack.getMeta() == getMeta() &&
                (ignoreCount || count + itemStack.getCount() <= attributeComponent.getItemAttributes().maxStackSize()) &&
                extraTag1.equals(extraTag2) &&
                itemStack.toBlockState() == toBlockState();
    }

    @Override
    public ItemStack copy() {
        return copy(true);
    }
}
