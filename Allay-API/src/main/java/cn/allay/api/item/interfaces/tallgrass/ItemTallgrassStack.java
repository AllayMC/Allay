package cn.allay.api.item.interfaces.tallgrass;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTallgrassStack extends ItemStack {
    ItemType<ItemTallgrassStack> TALLGRASS_TYPE = ItemTypeBuilder
            .builder(ItemTallgrassStack.class)
            .vanillaItem(VanillaItemId.TALLGRASS)
            .build();
}
