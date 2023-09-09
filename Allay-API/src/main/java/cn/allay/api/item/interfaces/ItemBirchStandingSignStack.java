package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchStandingSignStack extends ItemStack {
    ItemType<ItemBirchStandingSignStack> BIRCH_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBirchStandingSignStack.class)
            .vanillaItem(VanillaItemId.BIRCH_STANDING_SIGN)
            .build();
}
