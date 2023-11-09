package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteUpgradeSmithingTemplateStack extends ItemStack {
  ItemType<ItemNetheriteUpgradeSmithingTemplateStack> NETHERITE_UPGRADE_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
          .builder(ItemNetheriteUpgradeSmithingTemplateStack.class)
          .vanillaItem(VanillaItemId.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
          .build();
}
