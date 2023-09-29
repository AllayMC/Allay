package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMuddyMangroveRootsStack extends ItemStack {
  ItemType<ItemMuddyMangroveRootsStack> MUDDY_MANGROVE_ROOTS_TYPE = ItemTypeBuilder
          .builder(ItemMuddyMangroveRootsStack.class)
          .vanillaItem(VanillaItemId.MUDDY_MANGROVE_ROOTS)
          .build();
}
