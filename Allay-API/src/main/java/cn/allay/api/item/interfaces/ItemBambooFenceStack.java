package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooFenceStack extends ItemStack {
    ItemType<ItemBambooFenceStack> BAMBOO_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemBambooFenceStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_FENCE)
            .build();
}
