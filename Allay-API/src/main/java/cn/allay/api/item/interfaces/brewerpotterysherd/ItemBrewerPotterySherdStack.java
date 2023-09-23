package cn.allay.api.item.interfaces.brewerpotterysherd;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrewerPotterySherdStack extends ItemStack {
    ItemType<ItemBrewerPotterySherdStack> BREWER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemBrewerPotterySherdStack.class)
            .vanillaItem(VanillaItemId.BREWER_POTTERY_SHERD)
            .build();
}
