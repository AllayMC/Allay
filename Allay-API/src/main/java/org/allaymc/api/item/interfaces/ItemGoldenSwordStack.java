package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenSwordStack extends ItemStack {
  ItemType<ItemGoldenSwordStack> GOLDEN_SWORD_TYPE = ItemTypeBuilder
          .builder(ItemGoldenSwordStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_SWORD)
          .build();
}
