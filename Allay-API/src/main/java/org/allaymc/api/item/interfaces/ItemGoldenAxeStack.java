package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenAxeStack extends ItemStack {
  ItemType<ItemGoldenAxeStack> GOLDEN_AXE_TYPE = ItemTypeBuilder
          .builder(ItemGoldenAxeStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_AXE)
          .build();
}
