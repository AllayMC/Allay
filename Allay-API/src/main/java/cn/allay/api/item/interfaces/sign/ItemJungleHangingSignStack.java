package cn.allay.api.item.interfaces.sign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleHangingSignStack extends ItemStack {
  ItemType<ItemJungleHangingSignStack> JUNGLE_HANGING_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemJungleHangingSignStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_HANGING_SIGN)
          .build();
}
