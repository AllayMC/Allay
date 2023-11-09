package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBowlStack extends ItemStack {
  ItemType<ItemBowlStack> BOWL_TYPE = ItemTypeBuilder
          .builder(ItemBowlStack.class)
          .vanillaItem(VanillaItemId.BOWL)
          .build();
}
