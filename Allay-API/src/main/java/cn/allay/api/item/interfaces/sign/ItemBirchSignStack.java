package cn.allay.api.item.interfaces.sign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchSignStack extends ItemStack {
  ItemType<ItemBirchSignStack> BIRCH_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemBirchSignStack.class)
          .vanillaItem(VanillaItemId.BIRCH_SIGN)
          .build();
}
