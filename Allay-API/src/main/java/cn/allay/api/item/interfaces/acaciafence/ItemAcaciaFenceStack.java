package cn.allay.api.item.interfaces.acaciafence;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaFenceStack extends ItemStack {
    ItemType<ItemAcaciaFenceStack> ACACIA_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaFenceStack.class)
            .vanillaItem(VanillaItemId.ACACIA_FENCE)
            .build();
}
