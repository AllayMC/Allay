package cn.allay.api.item.interfaces.junglestandingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleStandingSignStack extends ItemStack {
    ItemType<ItemJungleStandingSignStack> JUNGLE_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemJungleStandingSignStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_STANDING_SIGN)
            .build();
}
