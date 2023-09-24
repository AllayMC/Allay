package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFrostedIceStack extends ItemStack {
    ItemType<ItemFrostedIceStack> FROSTED_ICE_TYPE = ItemTypeBuilder
            .builder(ItemFrostedIceStack.class)
            .vanillaItem(VanillaItemId.FROSTED_ICE)
            .build();
}
