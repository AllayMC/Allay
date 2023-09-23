package cn.allay.api.item.interfaces.mangrovestandingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveStandingSignStack extends ItemStack {
    ItemType<ItemMangroveStandingSignStack> MANGROVE_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemMangroveStandingSignStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_STANDING_SIGN)
            .build();
}
