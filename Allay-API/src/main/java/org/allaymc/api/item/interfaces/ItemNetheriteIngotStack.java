package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteIngotStack extends ItemStack {
  ItemType<ItemNetheriteIngotStack> NETHERITE_INGOT_TYPE = ItemTypeBuilder
          .builder(ItemNetheriteIngotStack.class)
          .vanillaItem(VanillaItemId.NETHERITE_INGOT)
          .build();
}
