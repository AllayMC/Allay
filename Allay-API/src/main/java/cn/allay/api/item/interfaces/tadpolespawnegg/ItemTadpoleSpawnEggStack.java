package cn.allay.api.item.interfaces.tadpolespawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTadpoleSpawnEggStack extends ItemStack {
    ItemType<ItemTadpoleSpawnEggStack> TADPOLE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemTadpoleSpawnEggStack.class)
            .vanillaItem(VanillaItemId.TADPOLE_SPAWN_EGG)
            .build();
}
