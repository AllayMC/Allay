package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

import static org.allaymc.api.item.component.ItemComponentImplFactory.getFactory;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCauldronStack extends ItemStack {
  ItemType<ItemCauldronStack> CAULDRON_TYPE = ItemTypeBuilder
          .builder(ItemCauldronStack.class)
          .vanillaItem(VanillaItemId.CAULDRON)
          .addComponent(getFactory().createItemCauldronBaseComponent())
          .build();
}
