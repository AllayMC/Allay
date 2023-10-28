package cn.allay.api.item.component.base;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponent;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.world.World;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.List;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemBaseComponent extends ItemComponent {

    ItemType<? extends ItemStack> getItemType();

    int getCount();

    void setCount(int count);

    int getMeta();

    void setMeta(int meta);

    int getDurability();

    void setDurability(int durability);

    String getCustomName();

    void setCustomName(String customName);

    List<String> getLore();

    void setLore(List<String>  lore);

    BlockState toBlockState();

    void setBlockStateStyle(@Nullable BlockState blockState);

    ItemData toNetworkItemData();

    int EMPTY_STACK_NETWORK_ID = 0;

    default boolean hasStackNetworkId() {
        return getStackNetworkId() != EMPTY_STACK_NETWORK_ID;
    }

    int getStackNetworkId();

    void setStackNetworkId(int newStackNetworkId);

    int assignNewStackNetworkId();

    default void clearStackNetworkId() {
        setStackNetworkId(0);
    }

    ItemStack copy();

    ItemStack copy(boolean newStackNetworkId);

    @Nullable
    NbtMap saveExtraTag();

    NbtMap getCustomNBTContent();

    void setCustomNBTContent(NbtMap customNBTContent);

    void loadExtraTag(NbtMap extraTag);

    boolean useItemOn(
            @Nullable EntityPlayer player, ItemStack itemStack,
            World world, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos,
            BlockFace blockFace);

    // TODO: boolean useInAir();

    default NbtMap saveNBT() {
        var builder = NbtMap.builder()
                .putByte("Count", (byte) getCount())
                .putShort("Damage", (short) getMeta())
                .putString("Name", getItemType().getIdentifier().toString());
        var extraTag = saveExtraTag();
        if (extraTag != null) {
            builder.putCompound("tag", saveExtraTag());
        }
        var blockState = toBlockState();
        if (blockState != null) {
            builder.put("Block", blockState.getBlockStateTag());
        }
        // User's custom nbt content
        builder.putAll(getCustomNBTContent());
        //TODO: CanDestroy
        //TODO: CanPlaceOn
        return builder.build();
    }

    default boolean canMerge(ItemStack itemStack) {
        var extraTag1 = saveExtraTag();
        if (extraTag1 == null) extraTag1 = NbtMap.EMPTY;
        var extraTag2 = itemStack.saveExtraTag();
        if (extraTag2 == null) extraTag2 = NbtMap.EMPTY;
        return itemStack.getItemType() == getItemType() &&
               itemStack.getMeta() == getMeta() &&
               extraTag1.equals(extraTag2) &&
               itemStack.toBlockState() == toBlockState();
    }
}
