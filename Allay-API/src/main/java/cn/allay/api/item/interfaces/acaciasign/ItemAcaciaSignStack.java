package cn.allay.api.item.interfaces.acaciasign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaSignStack extends ItemStack {
    ItemType<ItemAcaciaSignStack> ACACIA_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaSignStack.class)
            .vanillaItem(VanillaItemId.ACACIA_SIGN)
            .build();
}
