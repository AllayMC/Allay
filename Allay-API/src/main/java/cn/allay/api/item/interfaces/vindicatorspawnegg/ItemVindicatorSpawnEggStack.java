package cn.allay.api.item.interfaces.vindicatorspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVindicatorSpawnEggStack extends ItemStack {
    ItemType<ItemVindicatorSpawnEggStack> VINDICATOR_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemVindicatorSpawnEggStack.class)
            .vanillaItem(VanillaItemId.VINDICATOR_SPAWN_EGG)
            .build();
}
