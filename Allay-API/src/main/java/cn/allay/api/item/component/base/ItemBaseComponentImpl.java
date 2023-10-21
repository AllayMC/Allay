package cn.allay.api.item.component.base;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.enchantment.EnchantmentHelper;
import cn.allay.api.item.enchantment.EnchantmentInstance;
import cn.allay.api.item.enchantment.EnchantmentRegistry;
import cn.allay.api.item.enchantment.EnchantmentType;
import cn.allay.api.item.init.ItemStackInitInfo;
import cn.allay.api.item.init.SimpleItemStackInitInfo;
import cn.allay.api.item.interfaces.ItemAirStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.world.World;
import lombok.extern.slf4j.Slf4j;
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

    protected ItemType<T> itemType;
    protected int count;
    protected int meta;
    protected int durability;
    protected String customName = "";
    protected List<String> lore = new ArrayList<>();
    protected Map<EnchantmentType, EnchantmentInstance> enchantments = new HashMap<>();
    //TODO: item lock type
    protected BlockState blockState;
    protected int stackNetworkId;

    public ItemBaseComponentImpl(ItemStackInitInfo<T> initInfo) {
        this.itemType = initInfo.getItemType();
        this.count = initInfo.count();
        this.meta = initInfo.meta();
        this.blockState = initInfo.blockState();
        if (this.blockState == null && itemType.getBlockType() != null)
            this.blockState = itemType.getBlockType().getDefaultState();
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
        this.meta = meta;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public void setDurability(int durability) {
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
        return blockState;
    }

    @Override
    public void setBlockStateStyle(@Nullable BlockState blockState) {
        this.blockState = blockState;
    }

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
                        .blockState(blockState)
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
            displayBuilder.put( "Name", this.customName );
        }
        if ( !this.lore.isEmpty() ) {
            displayBuilder.putList( "Lore", NbtType.STRING, this.lore );
        }
        if ( !displayBuilder.isEmpty() ) {
            nbtBuilder.putCompound( "display", displayBuilder.build() );
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
        if (player == null || player.getGameType() != GameType.CREATIVE)
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
