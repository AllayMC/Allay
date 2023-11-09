package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenAppleStack extends ItemStack {
  ItemType<ItemGoldenAppleStack> GOLDEN_APPLE_TYPE = ItemTypeBuilder
          .builder(ItemGoldenAppleStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_APPLE)
          .build();
}
