package cn.allay.api.item.interfaces.potato;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPotatoStack extends ItemStack {
    ItemType<ItemPotatoStack> POTATO_TYPE = ItemTypeBuilder
            .builder(ItemPotatoStack.class)
            .vanillaItem(VanillaItemId.POTATO)
            .build();
}
