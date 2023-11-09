package org.allaymc.api.item.interfaces.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDragonEggStack extends ItemStack {
  ItemType<ItemDragonEggStack> DRAGON_EGG_TYPE = ItemTypeBuilder
          .builder(ItemDragonEggStack.class)
          .vanillaItem(VanillaItemId.DRAGON_EGG)
          .build();
}
