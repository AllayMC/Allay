package cn.allay.api.item.component.impl.base;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.component.annotation.Inject;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.jetbrains.annotations.Nullable;

/**
 * Allay Project 2023/5/19
 *
 * @author daoge_cmd
 */
public interface ItemBaseComponent {

    @Inject
    ItemType<? extends ItemStack> getItemType();

    @Inject
    int getCount();

    @Inject
    void setCount(int count);

    @Inject
    int getDamage();

    @Inject
    void setDamage(int damage);

    @Nullable
    @Inject
    NbtMap getNbt();

    @Inject
    void setNbt(@Nullable  NbtMap nbt);

    @Nullable
    @Inject
    BlockState getBlockState();

    @Inject
    void setBlockState(@Nullable  BlockState blockState);

    @Inject
    ItemData toNetworkItemData();
}
