package cn.allay.api.item.interfaces.mooshroomspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMooshroomSpawnEggStack extends ItemStack {
    ItemType<ItemMooshroomSpawnEggStack> MOOSHROOM_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemMooshroomSpawnEggStack.class)
            .vanillaItem(VanillaItemId.MOOSHROOM_SPAWN_EGG)
            .build();
}
