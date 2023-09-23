package cn.allay.api.item.interfaces.fermentedspidereye;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFermentedSpiderEyeStack extends ItemStack {
    ItemType<ItemFermentedSpiderEyeStack> FERMENTED_SPIDER_EYE_TYPE = ItemTypeBuilder
            .builder(ItemFermentedSpiderEyeStack.class)
            .vanillaItem(VanillaItemId.FERMENTED_SPIDER_EYE)
            .build();
}
