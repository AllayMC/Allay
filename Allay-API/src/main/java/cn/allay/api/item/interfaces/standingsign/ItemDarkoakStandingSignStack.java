package cn.allay.api.item.interfaces.standingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkoakStandingSignStack extends ItemStack {
  ItemType<ItemDarkoakStandingSignStack> DARKOAK_STANDING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemDarkoakStandingSignStack.class)
          .vanillaItem(VanillaItemId.DARKOAK_STANDING_SIGN)
          .build();
}
