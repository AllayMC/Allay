package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeepingVinesStack extends ItemStack {
  ItemType<ItemWeepingVinesStack> WEEPING_VINES_TYPE = ItemTypeBuilder
          .builder(ItemWeepingVinesStack.class)
          .vanillaItem(VanillaItemId.WEEPING_VINES)
          .build();
}
