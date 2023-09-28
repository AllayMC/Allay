package cn.allay.api.item.interfaces.piston;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStickyPistonStack extends ItemStack {
  ItemType<ItemStickyPistonStack> STICKY_PISTON_TYPE = ItemTypeBuilder
          .builder(ItemStickyPistonStack.class)
          .vanillaItem(VanillaItemId.STICKY_PISTON)
          .build();
}
