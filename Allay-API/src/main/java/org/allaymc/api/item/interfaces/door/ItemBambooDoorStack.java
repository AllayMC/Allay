package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooDoorStack extends ItemStack {
  ItemType<ItemBambooDoorStack> BAMBOO_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemBambooDoorStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_DOOR)
          .build();
}
