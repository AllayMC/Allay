package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWardArmorTrimSmithingTemplateStack extends ItemStack {
  ItemType<ItemWardArmorTrimSmithingTemplateStack> WARD_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
          .builder(ItemWardArmorTrimSmithingTemplateStack.class)
          .vanillaItem(VanillaItemId.WARD_ARMOR_TRIM_SMITHING_TEMPLATE)
          .build();
}
