package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeaves2Stack extends ItemStack {
    ItemType<ItemLeaves2Stack> LEAVES2_TYPE = ItemTypeBuilder
            .builder(ItemLeaves2Stack.class)
            .vanillaItem(VanillaItemId.LEAVES2)
            .build();
}
