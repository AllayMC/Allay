package org.allaymc.api.item.interfaces.liquid;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLavaStack extends ItemStack {
  ItemType<ItemLavaStack> LAVA_TYPE = ItemTypeBuilder
          .builder(ItemLavaStack.class)
          .vanillaItem(VanillaItemId.LAVA)
          .build();
}
