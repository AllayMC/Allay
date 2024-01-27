package org.allaymc.api.item.interfaces.chestplate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

import static org.allaymc.api.item.component.ItemComponentImplFactory.getFactory;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailChestplateStack extends ItemStack {
  ItemType<ItemChainmailChestplateStack> CHAINMAIL_CHESTPLATE_TYPE = ItemTypeBuilder
          .builder(ItemChainmailChestplateStack.class)
          .vanillaItem(VanillaItemId.CHAINMAIL_CHESTPLATE)
          .addComponent(getFactory().createItemArmorBaseComponent())
          .build();
}
