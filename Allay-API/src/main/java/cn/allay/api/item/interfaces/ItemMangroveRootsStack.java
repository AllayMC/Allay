package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveRootsStack extends ItemStack {
  ItemType<ItemMangroveRootsStack> MANGROVE_ROOTS_TYPE = ItemTypeBuilder
          .builder(ItemMangroveRootsStack.class)
          .vanillaItem(VanillaItemId.MANGROVE_ROOTS)
          .build();
}
