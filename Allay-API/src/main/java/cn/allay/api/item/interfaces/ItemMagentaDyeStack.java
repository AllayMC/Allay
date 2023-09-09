package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaDyeStack extends ItemStack {
    ItemType<ItemMagentaDyeStack> MAGENTA_DYE_TYPE = ItemTypeBuilder
            .builder(ItemMagentaDyeStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_DYE)
            .build();
}
