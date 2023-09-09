package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitSmokerStack extends ItemStack {
    ItemType<ItemLitSmokerStack> LIT_SMOKER_TYPE = ItemTypeBuilder
            .builder(ItemLitSmokerStack.class)
            .vanillaItem(VanillaItemId.LIT_SMOKER)
            .build();
}
