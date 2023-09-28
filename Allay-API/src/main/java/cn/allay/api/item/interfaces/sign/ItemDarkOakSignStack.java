package cn.allay.api.item.interfaces.sign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakSignStack extends ItemStack {
  ItemType<ItemDarkOakSignStack> DARK_OAK_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemDarkOakSignStack.class)
          .vanillaItem(VanillaItemId.DARK_OAK_SIGN)
          .build();
}
