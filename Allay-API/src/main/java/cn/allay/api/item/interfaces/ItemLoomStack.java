package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLoomStack extends ItemStack {
    ItemType<ItemLoomStack> LOOM_TYPE = ItemTypeBuilder
            .builder(ItemLoomStack.class)
            .vanillaItem(VanillaItemId.LOOM)
            .build();
}
