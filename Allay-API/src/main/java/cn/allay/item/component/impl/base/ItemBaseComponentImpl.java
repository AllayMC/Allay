package cn.allay.item.component.impl.base;

import cn.allay.component.annotation.Impl;
import cn.allay.identifier.Identifier;
import cn.allay.item.ItemStack;
import cn.allay.item.component.ItemComponentImpl;
import cn.allay.item.type.ItemType;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public class ItemBaseComponentImpl implements ItemBaseComponent, ItemComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");

    protected ItemType<? extends ItemStack> itemType;

    public ItemBaseComponentImpl(ItemType<? extends ItemStack> itemType) {
        this.itemType = itemType;
    }

    @Override
    @Impl
    public ItemType<? extends ItemStack> getItemType() {
        return itemType;
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
