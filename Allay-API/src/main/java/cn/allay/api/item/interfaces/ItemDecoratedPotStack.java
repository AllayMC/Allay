package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDecoratedPotStack extends ItemStack {
    ItemType<ItemDecoratedPotStack> DECORATED_POT_TYPE = ItemTypeBuilder
            .builder(ItemDecoratedPotStack.class)
            .vanillaItem(VanillaItemId.DECORATED_POT)
            .build();
}
