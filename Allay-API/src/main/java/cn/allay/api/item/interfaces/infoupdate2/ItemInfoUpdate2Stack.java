package cn.allay.api.item.interfaces.infoupdate2;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInfoUpdate2Stack extends ItemStack {
    ItemType<ItemInfoUpdate2Stack> INFO_UPDATE2_TYPE = ItemTypeBuilder
            .builder(ItemInfoUpdate2Stack.class)
            .vanillaItem(VanillaItemId.INFO_UPDATE2)
            .build();
}
