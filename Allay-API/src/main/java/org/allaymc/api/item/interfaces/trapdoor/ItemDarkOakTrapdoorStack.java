package org.allaymc.api.item.interfaces.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakTrapdoorStack extends ItemStack {
  ItemType<ItemDarkOakTrapdoorStack> DARK_OAK_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemDarkOakTrapdoorStack.class)
          .vanillaItem(VanillaItemId.DARK_OAK_TRAPDOOR)
          .build();
}
