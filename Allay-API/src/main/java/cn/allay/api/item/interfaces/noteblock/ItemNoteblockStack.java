package cn.allay.api.item.interfaces.noteblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNoteblockStack extends ItemStack {
    ItemType<ItemNoteblockStack> NOTEBLOCK_TYPE = ItemTypeBuilder
            .builder(ItemNoteblockStack.class)
            .vanillaItem(VanillaItemId.NOTEBLOCK)
            .build();
}
