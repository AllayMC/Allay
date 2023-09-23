package cn.allay.api.item.interfaces.shears;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShearsStack extends ItemStack {
    ItemType<ItemShearsStack> SHEARS_TYPE = ItemTypeBuilder
            .builder(ItemShearsStack.class)
            .vanillaItem(VanillaItemId.SHEARS)
            .build();
}
