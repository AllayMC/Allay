package cn.allay.api.item.interfaces.raiserarmortrimsmithingtemplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRaiserArmorTrimSmithingTemplateStack extends ItemStack {
    ItemType<ItemRaiserArmorTrimSmithingTemplateStack> RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemRaiserArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
}
