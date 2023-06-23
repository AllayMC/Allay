package cn.allay.api.item.component.impl.base;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/19 <br>
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
