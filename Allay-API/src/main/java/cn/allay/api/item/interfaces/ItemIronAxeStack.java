package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronAxeStack extends ItemStack {
  ItemType<ItemIronAxeStack> IRON_AXE_TYPE = ItemTypeBuilder
          .builder(ItemIronAxeStack.class)
          .vanillaItem(VanillaItemId.IRON_AXE)
          .build();
}
