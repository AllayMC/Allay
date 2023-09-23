package cn.allay.api.item.interfaces.magentawool;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaWoolStack extends ItemStack {
    ItemType<ItemMagentaWoolStack> MAGENTA_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemMagentaWoolStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_WOOL)
            .build();
}
