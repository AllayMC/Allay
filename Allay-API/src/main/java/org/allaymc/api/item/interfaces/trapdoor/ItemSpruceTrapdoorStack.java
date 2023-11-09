package org.allaymc.api.item.interfaces.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceTrapdoorStack extends ItemStack {
  ItemType<ItemSpruceTrapdoorStack> SPRUCE_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemSpruceTrapdoorStack.class)
          .vanillaItem(VanillaItemId.SPRUCE_TRAPDOOR)
          .build();
}
