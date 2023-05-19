package cn.allay.item.component.impl.base;

import cn.allay.component.annotation.Inject;
import cn.allay.item.ItemStack;
import cn.allay.item.type.ItemType;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public interface ItemBaseComponent {

    @Inject
    ItemType<? extends ItemStack> getItemType();
}
