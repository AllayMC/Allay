package cn.allay.api.item.interfaces.hangingsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveHangingSignStack extends ItemStack {
  ItemType<ItemMangroveHangingSignStack> MANGROVE_HANGING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemMangroveHangingSignStack.class)
          .vanillaItem(VanillaItemId.MANGROVE_HANGING_SIGN)
          .build();
}
