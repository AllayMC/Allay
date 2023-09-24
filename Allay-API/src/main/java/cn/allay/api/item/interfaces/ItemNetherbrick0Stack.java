package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherbrick0Stack extends ItemStack {
    ItemType<ItemNetherbrick0Stack> NETHERBRICK_TYPE = ItemTypeBuilder
            .builder(ItemNetherbrick0Stack.class)
            .vanillaItem(VanillaItemId.NETHERBRICK)
            .build();
}
