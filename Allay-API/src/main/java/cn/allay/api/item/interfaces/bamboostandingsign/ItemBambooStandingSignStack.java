package cn.allay.api.item.interfaces.bamboostandingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooStandingSignStack extends ItemStack {
    ItemType<ItemBambooStandingSignStack> BAMBOO_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBambooStandingSignStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_STANDING_SIGN)
            .build();
}
