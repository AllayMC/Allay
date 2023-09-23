package cn.allay.api.item.interfaces.repeater;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRepeaterStack extends ItemStack {
    ItemType<ItemRepeaterStack> REPEATER_TYPE = ItemTypeBuilder
            .builder(ItemRepeaterStack.class)
            .vanillaItem(VanillaItemId.REPEATER)
            .build();
}
