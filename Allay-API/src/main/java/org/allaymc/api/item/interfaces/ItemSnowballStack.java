package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.base.ItemBaseComponentImpl;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnowballStack extends ItemStack {
  ItemType<ItemSnowballStack> SNOWBALL_TYPE = ItemTypeBuilder
          .builder(ItemSnowballStack.class)
          .vanillaItem(VanillaItemId.SNOWBALL)
          .addComponent(initInfo -> new ItemBaseComponentImpl<>(initInfo) {

          }, ItemBaseComponentImpl.class)
          .build();
}
