package cn.allay.api.item.interfaces.cherrysign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherrySignStack extends ItemStack {
    ItemType<ItemCherrySignStack> CHERRY_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCherrySignStack.class)
            .vanillaItem(VanillaItemId.CHERRY_SIGN)
            .build();
}
