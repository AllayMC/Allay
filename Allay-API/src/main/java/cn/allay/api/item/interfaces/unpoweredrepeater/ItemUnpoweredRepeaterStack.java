package cn.allay.api.item.interfaces.unpoweredrepeater;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnpoweredRepeaterStack extends ItemStack {
    ItemType<ItemUnpoweredRepeaterStack> UNPOWERED_REPEATER_TYPE = ItemTypeBuilder
            .builder(ItemUnpoweredRepeaterStack.class)
            .vanillaItem(VanillaItemId.UNPOWERED_REPEATER)
            .build();
}
