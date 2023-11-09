package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaDoorStack extends ItemStack {
  ItemType<ItemAcaciaDoorStack> ACACIA_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemAcaciaDoorStack.class)
          .vanillaItem(VanillaItemId.ACACIA_DOOR)
          .build();
}
