package org.allaymc.api.item.interfaces.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCopperTrapdoorStack extends ItemStack {
  ItemType<ItemExposedCopperTrapdoorStack> EXPOSED_COPPER_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemExposedCopperTrapdoorStack.class)
          .vanillaItem(VanillaItemId.EXPOSED_COPPER_TRAPDOOR)
          .build();
}
