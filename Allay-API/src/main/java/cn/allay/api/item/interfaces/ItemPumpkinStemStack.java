package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPumpkinStemStack extends ItemStack {
    ItemType<ItemPumpkinStemStack> PUMPKIN_STEM_TYPE = ItemTypeBuilder
            .builder(ItemPumpkinStemStack.class)
            .vanillaItem(VanillaItemId.PUMPKIN_STEM)
            .build();
}
