package cn.allay.api.item.interfaces.cherryfence;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryFenceStack extends ItemStack {
    ItemType<ItemCherryFenceStack> CHERRY_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemCherryFenceStack.class)
            .vanillaItem(VanillaItemId.CHERRY_FENCE)
            .build();
}
