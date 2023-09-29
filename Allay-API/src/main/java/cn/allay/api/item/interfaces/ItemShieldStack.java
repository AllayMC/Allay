package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShieldStack extends ItemStack {
  ItemType<ItemShieldStack> SHIELD_TYPE = ItemTypeBuilder
          .builder(ItemShieldStack.class)
          .vanillaItem(VanillaItemId.SHIELD)
          .build();
}
