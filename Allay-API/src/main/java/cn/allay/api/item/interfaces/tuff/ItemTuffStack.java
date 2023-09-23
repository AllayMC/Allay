package cn.allay.api.item.interfaces.tuff;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffStack extends ItemStack {
    ItemType<ItemTuffStack> TUFF_TYPE = ItemTypeBuilder
            .builder(ItemTuffStack.class)
            .vanillaItem(VanillaItemId.TUFF)
            .build();
}
