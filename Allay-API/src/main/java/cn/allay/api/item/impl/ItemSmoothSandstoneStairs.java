package cn.allay.api.item.impl;

import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.impl.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemStackInitInfo;

import static cn.allay.api.item.CommonUseItemFunctions.ITEM_STAIR_USE_ON;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothSandstoneStairs extends ItemStack {
    @AutoRegister
    ComponentProvider<ItemBaseComponentImpl<?>> ITEM_BASE_COMPONENT =
            ComponentProvider.of(initInfo -> new ItemBaseComponentImpl<>((ItemStackInitInfo<?>) initInfo, ITEM_STAIR_USE_ON), ItemBaseComponentImpl.class);
}
