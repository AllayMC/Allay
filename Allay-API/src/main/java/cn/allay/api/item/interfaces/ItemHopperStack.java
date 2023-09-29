package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHopperStack extends ItemStack {
  ItemType<ItemHopperStack> HOPPER_TYPE = ItemTypeBuilder
          .builder(ItemHopperStack.class)
          .vanillaItem(VanillaItemId.HOPPER)
          .build();
}
