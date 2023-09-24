package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaDoorStack extends ItemStack {
    ItemType<ItemAcaciaDoorStack> ACACIA_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaDoorStack.class)
            .vanillaItem(VanillaItemId.ACACIA_DOOR)
            .build();
}
