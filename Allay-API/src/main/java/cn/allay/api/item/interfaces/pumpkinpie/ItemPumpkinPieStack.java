package cn.allay.api.item.interfaces.pumpkinpie;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPumpkinPieStack extends ItemStack {
    ItemType<ItemPumpkinPieStack> PUMPKIN_PIE_TYPE = ItemTypeBuilder
            .builder(ItemPumpkinPieStack.class)
            .vanillaItem(VanillaItemId.PUMPKIN_PIE)
            .build();
}
