package cn.allay.api.item.interfaces.vexspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVexSpawnEggStack extends ItemStack {
    ItemType<ItemVexSpawnEggStack> VEX_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemVexSpawnEggStack.class)
            .vanillaItem(VanillaItemId.VEX_SPAWN_EGG)
            .build();
}
