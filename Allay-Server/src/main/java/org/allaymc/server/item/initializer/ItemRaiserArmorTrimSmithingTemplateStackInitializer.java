package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRaiserArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRaiserArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemRaiserArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
