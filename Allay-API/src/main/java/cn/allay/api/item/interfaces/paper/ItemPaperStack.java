package cn.allay.api.item.interfaces.paper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPaperStack extends ItemStack {
    ItemType<ItemPaperStack> PAPER_TYPE = ItemTypeBuilder
            .builder(ItemPaperStack.class)
            .vanillaItem(VanillaItemId.PAPER)
            .build();
}
