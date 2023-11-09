package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChorusFlowerStack extends ItemStack {
  ItemType<ItemChorusFlowerStack> CHORUS_FLOWER_TYPE = ItemTypeBuilder
          .builder(ItemChorusFlowerStack.class)
          .vanillaItem(VanillaItemId.CHORUS_FLOWER)
          .build();
}
