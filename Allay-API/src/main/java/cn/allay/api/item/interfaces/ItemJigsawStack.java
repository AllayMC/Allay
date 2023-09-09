package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJigsawStack extends ItemStack {
    ItemType<ItemJigsawStack> JIGSAW_TYPE = ItemTypeBuilder
            .builder(ItemJigsawStack.class)
            .vanillaItem(VanillaItemId.JIGSAW)
            .build();
}
