package cn.allay.api.item.interfaces.pumpkin;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPumpkinStack extends ItemStack {
    ItemType<ItemPumpkinStack> PUMPKIN_TYPE = ItemTypeBuilder
            .builder(ItemPumpkinStack.class)
            .vanillaItem(VanillaItemId.PUMPKIN)
            .build();
}
