package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulLanternStack extends ItemStack {
  ItemType<ItemSoulLanternStack> SOUL_LANTERN_TYPE = ItemTypeBuilder
          .builder(ItemSoulLanternStack.class)
          .vanillaItem(VanillaItemId.SOUL_LANTERN)
          .build();
}
