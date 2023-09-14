package cn.allay.api.item.component.base;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.UseItemOn;
import cn.allay.api.item.interfaces.ItemAirStack;
import cn.allay.api.item.type.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.world.World;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.Objects;

import static cn.allay.api.item.CommonUseItemFunctions.createPlaceBlockUseOn;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public class ItemBaseComponentImpl<T extends ItemStack> implements ItemBaseComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");

    private static int STACK_NETWORK_ID_COUNTER = 1;

    protected ItemType<T> itemType;
    protected int count;
    protected int damage;
    @Nullable
    protected NbtMap nbt;
    @Nullable
    protected BlockState blockState;
    @Nullable
    protected Integer stackNetworkId;
    protected UseItemOn useItemOn;

    public ItemBaseComponentImpl(ItemStackInitInfo<T> initInfo) {
        this(initInfo, null);
    }

    public ItemBaseComponentImpl(
            ItemStackInitInfo<T> initInfo,
            @Nullable UseItemOn useItemOn
    ) {
        this.itemType = initInfo.getItemType();
        this.count = initInfo.count();
        this.damage = initInfo.damage();
        this.nbt = initInfo.nbt();
        this.blockState = initInfo.blockState();
        if (this.blockState == null && itemType.getBlockType() != null)
            this.blockState = itemType.getBlockType().getDefaultState();
        if (initInfo.autoAssignStackNetworkId()) {
            this.stackNetworkId = STACK_NETWORK_ID_COUNTER++;
        } else if (initInfo.stackNetworkId() != null) {
            if (initInfo.stackNetworkId() < 0)
                throw new IllegalArgumentException("stack network id cannot be negative");
            this.stackNetworkId = initInfo.stackNetworkId();
        } else {
            this.stackNetworkId = null;
        }
        this.useItemOn = Objects.requireNonNullElseGet(useItemOn, () -> createPlaceBlockUseOn());
    }

    @Override
    @Impl
    public ItemType<? extends ItemStack> getItemType() {
        return itemType;
    }

    @Override
    @Impl
    public int getCount() {
        return count;
    }

    @Override
    @Impl
    public void setCount(int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        this.count = count;
    }

    @Override
    @Impl
    public int getDamage() {
        return damage;
    }

    @Override
    @Impl
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    @Impl
    public @Nullable BlockState toBlockState() {
        return blockState;
    }

    @Override
    @Impl
    public void setBlockStateStyle(@Nullable BlockState blockState) {
        this.blockState = blockState;
    }

    @Nullable
    @Override
    @Impl
    public NbtMap getNbt() {
        return nbt;
    }

    @Override
    @Impl
    public void setNbt(@Nullable NbtMap nbt) {
        this.nbt = nbt;
    }

    //TODO: 缓存ItemData
    @Override
    @Impl
    public ItemData toNetworkItemData() {
        if (itemType == ItemAirStack.AIR_TYPE) {
            return ItemData.AIR;
        } else {
            return ItemData
                .builder()
                .definition(itemType.toNetworkDefinition())
                .blockDefinition(blockState != null ? blockState.toNetworkBlockDefinition() : () -> 0)
                .count(count)
                .damage(damage)
                .tag(nbt)
                .usingNetId(stackNetworkId != null)
                .netId(stackNetworkId != null ? stackNetworkId : 0)
                .build();
        }
    }

    @Nullable
    @Override
    @Impl
    public Integer getStackNetworkId() {
        return stackNetworkId;
    }

    @Override
    @Impl
    public void setStackNetworkId(int newStackNetworkId) {
        this.stackNetworkId = newStackNetworkId;
    }

    @Override
    @Impl
    public ItemStack copy(boolean newStackNetworkId) {
        return itemType.createItemStack(new ItemStackInitInfo.Simple<>(count, damage, nbt, blockState, stackNetworkId, newStackNetworkId));
    }

    @Override
    @Impl
    public boolean useItemOn(
            @Nullable EntityPlayer player, ItemStack itemStack,
            World world, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos,
            BlockFace blockFace) {
        return useItemOn.useItemOn(player, itemStack, world, targetBlockPos, placeBlockPos, clickPos, blockFace);
    }

    @Override
    @Impl
    public ItemStack copy() {
        return copy(true);
    }
}
