package cn.allay.api.item.interfaces.graydye;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayDyeStack extends ItemStack {
    ItemType<ItemGrayDyeStack> GRAY_DYE_TYPE = ItemTypeBuilder
            .builder(ItemGrayDyeStack.class)
            .vanillaItem(VanillaItemId.GRAY_DYE)
            .build();
}
