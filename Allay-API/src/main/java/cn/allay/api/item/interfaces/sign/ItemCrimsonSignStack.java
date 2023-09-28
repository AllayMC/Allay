package cn.allay.api.item.interfaces.sign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonSignStack extends ItemStack {
  ItemType<ItemCrimsonSignStack> CRIMSON_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonSignStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_SIGN)
          .build();
}
