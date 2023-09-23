package cn.allay.api.item.interfaces.itemacaciadoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemAcaciaDoorStack extends ItemStack {
    ItemType<ItemItemAcaciaDoorStack> ITEM_ACACIA_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemAcaciaDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_ACACIA_DOOR)
            .build();
}
