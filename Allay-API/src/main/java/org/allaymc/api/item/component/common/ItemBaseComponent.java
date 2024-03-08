package org.allaymc.api.item.component.common;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemComponent;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
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

    NbtMap saveExtraTag();

    NbtMap getCustomNBTContent();

    void setCustomNBTContent(NbtMap customNBTContent);

    void loadExtraTag(NbtMap extraTag);

    boolean useItemOn(
            EntityPlayer player, Dimension dimension,
            Vector3ic targetBlockPos, Vector3ic placeBlockPos,
            Vector3fc clickPos, BlockFace blockFace);

    boolean useItemInAir(EntityPlayer player);

    default boolean canMerge(ItemStack itemStack) {
        return canMerge(itemStack, false);
    }

    boolean canMerge(ItemStack itemStack, boolean ignoreCount);

    // TODO: boolean useInAir();

    default NbtMap saveNBT() {
        var builder = NbtMap.builder()
                .putByte("Count", (byte) getCount())
                .putShort("Damage", (short) getMeta())
                .putString("Name", getItemType().getIdentifier().toString());
        var extraTag = saveExtraTag();
        if (extraTag != null) {
            builder.putCompound("tag", extraTag);
        }
        var blockState = toBlockState();
        if (blockState != null) {
            builder.put("Block", blockState.getBlockStateTag());
        }
        //TODO: CanDestroy
        //TODO: CanPlaceOn
        return builder.build();
    }

    float calculateAttackDamage();
}
