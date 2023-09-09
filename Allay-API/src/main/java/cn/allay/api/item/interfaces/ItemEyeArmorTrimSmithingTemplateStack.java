package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEyeArmorTrimSmithingTemplateStack extends ItemStack {
    ItemType<ItemEyeArmorTrimSmithingTemplateStack> EYE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemEyeArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.EYE_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
}
