package cn.allay.api.item.interfaces.clock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemClockStack extends ItemStack {
    ItemType<ItemClockStack> CLOCK_TYPE = ItemTypeBuilder
            .builder(ItemClockStack.class)
            .vanillaItem(VanillaItemId.CLOCK)
            .build();
}
