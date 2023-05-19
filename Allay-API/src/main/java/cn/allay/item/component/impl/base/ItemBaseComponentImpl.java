package cn.allay.item.component.impl.base;

import cn.allay.component.annotation.Dependency;
import cn.allay.component.annotation.Impl;
import cn.allay.identifier.Identifier;
import cn.allay.item.ItemStack;
import cn.allay.item.component.ItemComponentImpl;
import cn.allay.item.component.impl.attribute.ItemAttributeComponent;
import cn.allay.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/19 <br>
 * Allay Project <br>
 */
public class ItemBaseComponentImpl implements ItemBaseComponent, ItemComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");

    protected ItemType<? extends ItemStack> itemType;
    protected int count;
    protected int meta;
    //todo: damage
    protected NbtMap nbt;

    public ItemBaseComponentImpl(ItemType<? extends ItemStack> itemType, int count, int meta, NbtMap nbt) {
        this.itemType = itemType;
        this.count = count;
        this.meta = meta;
        this.nbt = nbt;
    }

    @Override
    @Impl
    public ItemType<? extends ItemStack> getItemType() {
        return itemType;
    }

    @Override
    @Impl
    public int getCount() {
        return count;
    }

    @Override
    @Impl
    public void setCount(int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        this.count = count;
    }

    @Override
    @Impl
    public int getMeta() {
        return meta;
    }

    @Override
    @Impl
    public void setMeta(int meta) {
        this.meta = meta;
    }

    @Override
    @Impl
    public NbtMap getNbt() {
        return nbt;
    }

    @Override
    @Impl
    public void setNbt(NbtMap nbt) {
        this.nbt = nbt;
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
