package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSeaLanternStack extends ItemStack {
  ItemType<ItemSeaLanternStack> SEA_LANTERN_TYPE = ItemTypeBuilder
          .builder(ItemSeaLanternStack.class)
          .vanillaItem(VanillaItemId.SEA_LANTERN)
          .build();
}
