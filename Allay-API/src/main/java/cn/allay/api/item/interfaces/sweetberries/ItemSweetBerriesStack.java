package cn.allay.api.item.interfaces.sweetberries;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSweetBerriesStack extends ItemStack {
    ItemType<ItemSweetBerriesStack> SWEET_BERRIES_TYPE = ItemTypeBuilder
            .builder(ItemSweetBerriesStack.class)
            .vanillaItem(VanillaItemId.SWEET_BERRIES)
            .build();
}
