package cn.allay.item.component.impl.base;

import cn.allay.component.annotation.Inject;
import cn.allay.item.ItemStack;
import cn.allay.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemBaseComponent {

    @Inject
    ItemType<? extends ItemStack> getItemType();

    @Inject
    int getCount();

    @Inject
    void setCount(int count);

    @Inject
    int getMeta();

    @Inject
    void setMeta(int meta);

    @Inject
    NbtMap getNbt();

    @Inject
    void setNbt(NbtMap nbt);
}
