package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneWireStack extends ItemStack {
  ItemType<ItemRedstoneWireStack> REDSTONE_WIRE_TYPE = ItemTypeBuilder
          .builder(ItemRedstoneWireStack.class)
          .vanillaItem(VanillaItemId.REDSTONE_WIRE)
          .build();
}
