package cn.allay.api.item.interfaces.huskspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHuskSpawnEggStack extends ItemStack {
    ItemType<ItemHuskSpawnEggStack> HUSK_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemHuskSpawnEggStack.class)
            .vanillaItem(VanillaItemId.HUSK_SPAWN_EGG)
            .build();
}
