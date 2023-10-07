package cn.allay.api.item.component.base;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.init.ItemStackInitInfo;
import cn.allay.api.item.init.SimpleItemStackInitInfo;
import cn.allay.api.item.interfaces.ItemAirStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.world.World;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

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
    protected NbtMap nbt;
    protected BlockState blockState;
    protected Integer stackNetworkId;

    public ItemBaseComponentImpl(
            ItemStackInitInfo<T> initInfo
    ) {
        this.itemType = initInfo.getItemType();
        this.count = initInfo.count();
        this.damage = initInfo.damage();
        this.nbt = initInfo.nbt();
        this.blockState = initInfo.blockState();
        if (this.blockState == null && itemType.getBlockType() != null)
            this.blockState = itemType.getBlockType().getDefaultState();
        var specifiedNetworkId = initInfo.stackNetworkId();
        if (specifiedNetworkId != null) {
            if (specifiedNetworkId < 0)
                throw new IllegalArgumentException("stack network id cannot be negative");
            this.stackNetworkId = initInfo.stackNetworkId();
        } else if (initInfo.autoAssignStackNetworkId()) {
            this.stackNetworkId = STACK_NETWORK_ID_COUNTER++;
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
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public BlockState toBlockState() {
        return blockState;
    }

    @Override
    public void setBlockStateStyle(@Nullable BlockState blockState) {
        this.blockState = blockState;
    }

    @Override
    public NbtMap getNbt() {
        return nbt;
    }

    @Override
    public void setNbt(NbtMap nbt) {
        this.nbt = nbt;
    }

    //TODO: 缓存ItemData
    @Override
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

    @Override
    public Integer getStackNetworkId() {
        return stackNetworkId;
    }

    @Override
    public void setStackNetworkId(@Nullable Integer newStackNetworkId) {
        this.stackNetworkId = newStackNetworkId;
    }

    @Override
    public Integer assignNewStackNetworkId() {
        stackNetworkId = STACK_NETWORK_ID_COUNTER++;
        return stackNetworkId;
    }

    @Override
    public ItemStack copy(boolean newStackNetworkId) {
        return itemType.createItemStack(
                SimpleItemStackInitInfo
                        .builder()
                        .count(count)
                        .damage(damage)
                        .nbt(nbt)
                        .blockState(blockState)
                        .stackNetworkId(newStackNetworkId ? null : stackNetworkId)
                        .autoAssignStackNetworkId(newStackNetworkId)
                        .build()
        );
    }

    @Override
    public boolean useItemOn(
            @Nullable EntityPlayer player, ItemStack itemStack,
            World world, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos,
            BlockFace blockFace) {
        var blockState = itemStack.toBlockState();
        if (blockState == null)
            return false;
        return tryPlaceBlockState(player, itemStack, world, targetBlockPos, placeBlockPos, clickPos, blockFace, blockState);
    }

    protected boolean tryPlaceBlockState(@Nullable EntityPlayer player, ItemStack itemStack, World world, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace, BlockState blockState) {
        if (player != null && hasEntityCollision(world, placeBlockPos, blockState))
            return false;
        BlockType<?> blockType = blockState.blockType();
        assert blockType != null;
        boolean result = blockType.getBlockBehavior().place(player, world, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace);
        tryConsumeItem(player, itemStack);
        return result;
    }

    protected void tryConsumeItem(EntityPlayer player, ItemStack itemStack) {
        if (player == null || player.getClient().getGameType() != GameType.CREATIVE)
            itemStack.setCount(itemStack.getCount() - 1);
    }

    protected boolean hasEntityCollision(World world, Vector3ic placePos, BlockState blockState) {
        var block_aabb = blockState.getBehavior().getBlockAttributes(blockState)
                .computeOffsetVoxelShape(
                        placePos.x(),
                        placePos.y(),
                        placePos.z()
                );
        return !world.getEntityPhysicsService().computeCollidingEntities(block_aabb).isEmpty();
    }

    @Override
    public ItemStack copy() {
        return copy(true);
    }
}
