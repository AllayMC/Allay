package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDuneArmorTrimSmithingTemplateStack extends ItemStack {
  ItemType<ItemDuneArmorTrimSmithingTemplateStack> DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
          .builder(ItemDuneArmorTrimSmithingTemplateStack.class)
          .vanillaItem(VanillaItemId.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE)
          .build();
}
