package cn.allay.api.item.interfaces.endrod;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndRodStack extends ItemStack {
    ItemType<ItemEndRodStack> END_ROD_TYPE = ItemTypeBuilder
            .builder(ItemEndRodStack.class)
            .vanillaItem(VanillaItemId.END_ROD)
            .build();
}
