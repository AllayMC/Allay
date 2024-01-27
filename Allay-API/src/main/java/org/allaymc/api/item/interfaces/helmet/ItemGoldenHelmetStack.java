package org.allaymc.api.item.interfaces.helmet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

import static org.allaymc.api.item.component.ItemComponentImplFactory.getFactory;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenHelmetStack extends ItemStack {
  ItemType<ItemGoldenHelmetStack> GOLDEN_HELMET_TYPE = ItemTypeBuilder
          .builder(ItemGoldenHelmetStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_HELMET)
          .addComponent(getFactory().createItemArmorBaseComponent())
          .build();
}
