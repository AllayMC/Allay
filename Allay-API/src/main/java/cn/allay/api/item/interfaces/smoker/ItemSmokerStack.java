package cn.allay.api.item.interfaces.smoker;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmokerStack extends ItemStack {
    ItemType<ItemSmokerStack> SMOKER_TYPE = ItemTypeBuilder
            .builder(ItemSmokerStack.class)
            .vanillaItem(VanillaItemId.SMOKER)
            .build();
}
