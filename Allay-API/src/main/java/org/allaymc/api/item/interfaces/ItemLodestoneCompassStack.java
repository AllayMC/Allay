package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLodestoneCompassStack extends ItemStack {
  ItemType<ItemLodestoneCompassStack> LODESTONE_COMPASS_TYPE = ItemTypeBuilder
          .builder(ItemLodestoneCompassStack.class)
          .vanillaItem(VanillaItemId.LODESTONE_COMPASS)
          .build();
}
