package cn.allay.api.item.interfaces.silverfishspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSilverfishSpawnEggStack extends ItemStack {
    ItemType<ItemSilverfishSpawnEggStack> SILVERFISH_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSilverfishSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SILVERFISH_SPAWN_EGG)
            .build();
}
