package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSilenceArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSilenceArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemSilenceArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}