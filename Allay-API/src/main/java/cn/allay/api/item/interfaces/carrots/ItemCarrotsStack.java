package cn.allay.api.item.interfaces.carrots;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarrotsStack extends ItemStack {
    ItemType<ItemCarrotsStack> CARROTS_TYPE = ItemTypeBuilder
            .builder(ItemCarrotsStack.class)
            .vanillaItem(VanillaItemId.CARROTS)
            .build();
}
