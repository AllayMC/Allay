package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndermiteSpawnEggStack extends ItemStack {
    ItemType<ItemEndermiteSpawnEggStack> ENDERMITE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemEndermiteSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ENDERMITE_SPAWN_EGG)
            .build();
}
