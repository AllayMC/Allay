package cn.allay.api.item.interfaces.calcite;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCalciteStack extends ItemStack {
    ItemType<ItemCalciteStack> CALCITE_TYPE = ItemTypeBuilder
            .builder(ItemCalciteStack.class)
            .vanillaItem(VanillaItemId.CALCITE)
            .build();
}
