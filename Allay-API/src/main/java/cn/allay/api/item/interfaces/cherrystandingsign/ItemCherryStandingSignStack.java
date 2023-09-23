package cn.allay.api.item.interfaces.cherrystandingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryStandingSignStack extends ItemStack {
    ItemType<ItemCherryStandingSignStack> CHERRY_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCherryStandingSignStack.class)
            .vanillaItem(VanillaItemId.CHERRY_STANDING_SIGN)
            .build();
}
