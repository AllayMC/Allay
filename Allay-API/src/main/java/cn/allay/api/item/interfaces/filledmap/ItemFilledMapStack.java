package cn.allay.api.item.interfaces.filledmap;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFilledMapStack extends ItemStack {
    ItemType<ItemFilledMapStack> FILLED_MAP_TYPE = ItemTypeBuilder
            .builder(ItemFilledMapStack.class)
            .vanillaItem(VanillaItemId.FILLED_MAP)
            .build();
}
