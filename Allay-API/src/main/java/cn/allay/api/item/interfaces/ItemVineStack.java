package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVineStack extends ItemStack {
    ItemType<ItemVineStack> VINE_TYPE = ItemTypeBuilder
            .builder(ItemVineStack.class)
            .vanillaItem(VanillaItemId.VINE)
            .build();
}
