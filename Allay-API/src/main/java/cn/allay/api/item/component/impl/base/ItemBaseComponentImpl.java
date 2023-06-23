package cn.allay.api.item.component.impl.base;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.ItemComponentImpl;
import cn.allay.api.item.type.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/19 <br>
 * Allay Project <br>
 */
public class ItemBaseComponentImpl implements ItemBaseComponent, ItemComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_base_component");

    protected ItemType<? extends ItemStack> itemType;
    protected int count;
    protected int meta;
    //todo: damage
    protected NbtMap nbt;

    public ItemBaseComponentImpl(ItemType<? extends ItemStack> itemType, ItemStackInitInfo initInfo) {
        this.itemType = itemType;
        this.count = initInfo.count();
        this.meta = initInfo.meta();
        this.nbt = initInfo.nbt();
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
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
