package cn.allay.api.item.interfaces.sign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleSignStack extends ItemStack {
  ItemType<ItemJungleSignStack> JUNGLE_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemJungleSignStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_SIGN)
          .build();
}
