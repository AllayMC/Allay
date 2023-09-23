package cn.allay.api.item.interfaces.camelspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCamelSpawnEggStack extends ItemStack {
    ItemType<ItemCamelSpawnEggStack> CAMEL_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemCamelSpawnEggStack.class)
            .vanillaItem(VanillaItemId.CAMEL_SPAWN_EGG)
            .build();
}
