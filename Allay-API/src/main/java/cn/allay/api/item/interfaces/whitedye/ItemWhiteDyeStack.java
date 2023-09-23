package cn.allay.api.item.interfaces.whitedye;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteDyeStack extends ItemStack {
    ItemType<ItemWhiteDyeStack> WHITE_DYE_TYPE = ItemTypeBuilder
            .builder(ItemWhiteDyeStack.class)
            .vanillaItem(VanillaItemId.WHITE_DYE)
            .build();
}
