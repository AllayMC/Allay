package cn.allay.api.item.interfaces.sign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStandingSignStack extends ItemStack {
  ItemType<ItemStandingSignStack> STANDING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemStandingSignStack.class)
          .vanillaItem(VanillaItemId.STANDING_SIGN)
          .build();
}
