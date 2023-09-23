package cn.allay.api.item.interfaces.archerpotterysherd;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArcherPotterySherdStack extends ItemStack {
    ItemType<ItemArcherPotterySherdStack> ARCHER_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemArcherPotterySherdStack.class)
            .vanillaItem(VanillaItemId.ARCHER_POTTERY_SHERD)
            .build();
}
