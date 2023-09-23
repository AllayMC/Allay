package cn.allay.api.item.interfaces.netheriteupgradesmithingtemplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

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
