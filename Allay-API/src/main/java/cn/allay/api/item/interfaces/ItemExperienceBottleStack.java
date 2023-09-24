package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExperienceBottleStack extends ItemStack {
    ItemType<ItemExperienceBottleStack> EXPERIENCE_BOTTLE_TYPE = ItemTypeBuilder
            .builder(ItemExperienceBottleStack.class)
            .vanillaItem(VanillaItemId.EXPERIENCE_BOTTLE)
            .build();
}
