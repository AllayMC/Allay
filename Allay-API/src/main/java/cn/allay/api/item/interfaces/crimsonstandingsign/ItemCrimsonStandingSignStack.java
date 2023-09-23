package cn.allay.api.item.interfaces.crimsonstandingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonStandingSignStack extends ItemStack {
    ItemType<ItemCrimsonStandingSignStack> CRIMSON_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonStandingSignStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_STANDING_SIGN)
            .build();
}
