package cn.allay.api.item.interfaces.hostarmortrimsmithingtemplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHostArmorTrimSmithingTemplateStack extends ItemStack {
    ItemType<ItemHostArmorTrimSmithingTemplateStack> HOST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemHostArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.HOST_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
}
