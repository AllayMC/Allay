package org.allaymc.api.item.interfaces.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueTerracottaStack extends ItemStack {
  ItemType<ItemLightBlueTerracottaStack> LIGHT_BLUE_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemLightBlueTerracottaStack.class)
          .vanillaItem(VanillaItemId.LIGHT_BLUE_TERRACOTTA)
          .build();
}
