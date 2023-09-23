package cn.allay.api.item.interfaces.ghasttear;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGhastTearStack extends ItemStack {
    ItemType<ItemGhastTearStack> GHAST_TEAR_TYPE = ItemTypeBuilder
            .builder(ItemGhastTearStack.class)
            .vanillaItem(VanillaItemId.GHAST_TEAR)
            .build();
}
