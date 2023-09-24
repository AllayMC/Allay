package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIceBombStack extends ItemStack {
    ItemType<ItemIceBombStack> ICE_BOMB_TYPE = ItemTypeBuilder
            .builder(ItemIceBombStack.class)
            .vanillaItem(VanillaItemId.ICE_BOMB)
            .build();
}
