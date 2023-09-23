package cn.allay.api.item.interfaces.greenwool;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenWoolStack extends ItemStack {
    ItemType<ItemGreenWoolStack> GREEN_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemGreenWoolStack.class)
            .vanillaItem(VanillaItemId.GREEN_WOOL)
            .build();
}
