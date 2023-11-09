package org.allaymc.api.item.interfaces.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownConcreteStack extends ItemStack {
  ItemType<ItemBrownConcreteStack> BROWN_CONCRETE_TYPE = ItemTypeBuilder
          .builder(ItemBrownConcreteStack.class)
          .vanillaItem(VanillaItemId.BROWN_CONCRETE)
          .build();
}
