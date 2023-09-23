package cn.allay.api.item.interfaces.soulcampfire;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulCampfireStack extends ItemStack {
    ItemType<ItemSoulCampfireStack> SOUL_CAMPFIRE_TYPE = ItemTypeBuilder
            .builder(ItemSoulCampfireStack.class)
            .vanillaItem(VanillaItemId.SOUL_CAMPFIRE)
            .build();
}
