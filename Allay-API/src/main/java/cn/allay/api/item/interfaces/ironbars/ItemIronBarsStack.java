package cn.allay.api.item.interfaces.ironbars;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronBarsStack extends ItemStack {
    ItemType<ItemIronBarsStack> IRON_BARS_TYPE = ItemTypeBuilder
            .builder(ItemIronBarsStack.class)
            .vanillaItem(VanillaItemId.IRON_BARS)
            .build();
}
