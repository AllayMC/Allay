package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeeNestStack extends ItemStack {
    ItemType<ItemBeeNestStack> BEE_NEST_TYPE = ItemTypeBuilder
            .builder(ItemBeeNestStack.class)
            .vanillaItem(VanillaItemId.BEE_NEST)
            .build();
}
