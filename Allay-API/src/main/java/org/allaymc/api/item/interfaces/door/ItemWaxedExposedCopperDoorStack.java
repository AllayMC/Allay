package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCopperDoorStack extends ItemStack {
  ItemType<ItemWaxedExposedCopperDoorStack> WAXED_EXPOSED_COPPER_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemWaxedExposedCopperDoorStack.class)
          .vanillaItem(VanillaItemId.WAXED_EXPOSED_COPPER_DOOR)
          .build();
}
