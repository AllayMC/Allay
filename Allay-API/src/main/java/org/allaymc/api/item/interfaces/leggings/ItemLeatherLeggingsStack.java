package org.allaymc.api.item.interfaces.leggings;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

import static org.allaymc.api.item.component.ItemComponentImplFactory.getFactory;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherLeggingsStack extends ItemStack {
  ItemType<ItemLeatherLeggingsStack> LEATHER_LEGGINGS_TYPE = ItemTypeBuilder
          .builder(ItemLeatherLeggingsStack.class)
          .vanillaItem(VanillaItemId.LEATHER_LEGGINGS)
          .addComponent(getFactory().createItemArmorBaseComponent())
          .build();
}
