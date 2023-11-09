package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSilenceArmorTrimSmithingTemplateStack extends ItemStack {
  ItemType<ItemSilenceArmorTrimSmithingTemplateStack> SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
          .builder(ItemSilenceArmorTrimSmithingTemplateStack.class)
          .vanillaItem(VanillaItemId.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)
          .build();
}
