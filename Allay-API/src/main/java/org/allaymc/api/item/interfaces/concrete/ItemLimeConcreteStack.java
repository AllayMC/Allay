package org.allaymc.api.item.interfaces.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeConcreteStack extends ItemStack {
  ItemType<ItemLimeConcreteStack> LIME_CONCRETE_TYPE = ItemTypeBuilder
          .builder(ItemLimeConcreteStack.class)
          .vanillaItem(VanillaItemId.LIME_CONCRETE)
          .build();
}
