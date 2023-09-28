package cn.allay.api.item.interfaces.standingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedStandingSignStack extends ItemStack {
  ItemType<ItemWarpedStandingSignStack> WARPED_STANDING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemWarpedStandingSignStack.class)
          .vanillaItem(VanillaItemId.WARPED_STANDING_SIGN)
          .build();
}
