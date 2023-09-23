package cn.allay.api.item.interfaces.bookshelf;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBookshelfStack extends ItemStack {
    ItemType<ItemBookshelfStack> BOOKSHELF_TYPE = ItemTypeBuilder
            .builder(ItemBookshelfStack.class)
            .vanillaItem(VanillaItemId.BOOKSHELF)
            .build();
}
