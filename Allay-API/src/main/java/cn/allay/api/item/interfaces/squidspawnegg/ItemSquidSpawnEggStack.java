package cn.allay.api.item.interfaces.squidspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSquidSpawnEggStack extends ItemStack {
    ItemType<ItemSquidSpawnEggStack> SQUID_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSquidSpawnEggStack.class)
            .vanillaItem(VanillaItemId.SQUID_SPAWN_EGG)
            .build();
}
