package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWrittenBookStack extends ItemStack {
    ItemType<ItemWrittenBookStack> WRITTEN_BOOK_TYPE = ItemTypeBuilder
            .builder(ItemWrittenBookStack.class)
            .vanillaItem(VanillaItemId.WRITTEN_BOOK)
            .build();
}
