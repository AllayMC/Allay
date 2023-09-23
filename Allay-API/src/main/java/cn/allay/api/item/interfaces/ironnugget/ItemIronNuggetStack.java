package cn.allay.api.item.interfaces.ironnugget;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronNuggetStack extends ItemStack {
    ItemType<ItemIronNuggetStack> IRON_NUGGET_TYPE = ItemTypeBuilder
            .builder(ItemIronNuggetStack.class)
            .vanillaItem(VanillaItemId.IRON_NUGGET)
            .build();
}
