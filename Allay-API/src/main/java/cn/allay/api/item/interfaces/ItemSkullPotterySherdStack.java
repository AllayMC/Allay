package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSkullPotterySherdStack extends ItemStack {
    ItemType<ItemSkullPotterySherdStack> SKULL_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemSkullPotterySherdStack.class)
            .vanillaItem(VanillaItemId.SKULL_POTTERY_SHERD)
            .build();
}
