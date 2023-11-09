package org.allaymc.api.item.interfaces.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayTerracottaStack extends ItemStack {
  ItemType<ItemLightGrayTerracottaStack> LIGHT_GRAY_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemLightGrayTerracottaStack.class)
          .vanillaItem(VanillaItemId.LIGHT_GRAY_TERRACOTTA)
          .build();
}
