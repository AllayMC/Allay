package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneSwordStack extends ItemStack {
    ItemType<ItemStoneSwordStack> STONE_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemStoneSwordStack.class)
            .vanillaItem(VanillaItemId.STONE_SWORD)
            .build();
}
