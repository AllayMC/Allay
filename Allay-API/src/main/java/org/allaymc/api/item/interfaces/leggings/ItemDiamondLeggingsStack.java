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
public interface ItemDiamondLeggingsStack extends ItemStack {
  ItemType<ItemDiamondLeggingsStack> DIAMOND_LEGGINGS_TYPE = ItemTypeBuilder
          .builder(ItemDiamondLeggingsStack.class)
          .vanillaItem(VanillaItemId.DIAMOND_LEGGINGS)
          .addComponent(getFactory().createItemArmorBaseComponent())
          .build();
}
