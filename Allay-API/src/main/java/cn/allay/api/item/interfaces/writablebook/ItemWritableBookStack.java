package cn.allay.api.item.interfaces.writablebook;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWritableBookStack extends ItemStack {
    ItemType<ItemWritableBookStack> WRITABLE_BOOK_TYPE = ItemTypeBuilder
            .builder(ItemWritableBookStack.class)
            .vanillaItem(VanillaItemId.WRITABLE_BOOK)
            .build();
}
