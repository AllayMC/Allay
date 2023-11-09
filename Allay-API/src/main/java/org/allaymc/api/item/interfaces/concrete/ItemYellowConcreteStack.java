package org.allaymc.api.item.interfaces.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowConcreteStack extends ItemStack {
  ItemType<ItemYellowConcreteStack> YELLOW_CONCRETE_TYPE = ItemTypeBuilder
          .builder(ItemYellowConcreteStack.class)
          .vanillaItem(VanillaItemId.YELLOW_CONCRETE)
          .build();
}
