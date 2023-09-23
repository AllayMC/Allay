package cn.allay.api.item.interfaces.gravel;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGravelStack extends ItemStack {
    ItemType<ItemGravelStack> GRAVEL_TYPE = ItemTypeBuilder
            .builder(ItemGravelStack.class)
            .vanillaItem(VanillaItemId.GRAVEL)
            .build();
}
