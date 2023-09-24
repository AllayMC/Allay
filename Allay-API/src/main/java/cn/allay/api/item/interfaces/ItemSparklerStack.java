package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSparklerStack extends ItemStack {
    ItemType<ItemSparklerStack> SPARKLER_TYPE = ItemTypeBuilder
            .builder(ItemSparklerStack.class)
            .vanillaItem(VanillaItemId.SPARKLER)
            .build();
}
