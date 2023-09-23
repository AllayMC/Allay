package cn.allay.api.item.interfaces.feather;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFeatherStack extends ItemStack {
    ItemType<ItemFeatherStack> FEATHER_TYPE = ItemTypeBuilder
            .builder(ItemFeatherStack.class)
            .vanillaItem(VanillaItemId.FEATHER)
            .build();
}
