package cn.allay.api.item.interfaces.turtlespawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTurtleSpawnEggStack extends ItemStack {
    ItemType<ItemTurtleSpawnEggStack> TURTLE_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemTurtleSpawnEggStack.class)
            .vanillaItem(VanillaItemId.TURTLE_SPAWN_EGG)
            .build();
}
