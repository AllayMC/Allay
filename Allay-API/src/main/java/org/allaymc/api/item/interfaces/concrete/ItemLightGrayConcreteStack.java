package org.allaymc.api.item.interfaces.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayConcreteStack extends ItemStack {
  ItemType<ItemLightGrayConcreteStack> LIGHT_GRAY_CONCRETE_TYPE = ItemTypeBuilder
          .builder(ItemLightGrayConcreteStack.class)
          .vanillaItem(VanillaItemId.LIGHT_GRAY_CONCRETE)
          .build();
}
