package cn.allay.api.item.interfaces.rednetherbrick;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedNetherBrickStack extends ItemStack {
    ItemType<ItemRedNetherBrickStack> RED_NETHER_BRICK_TYPE = ItemTypeBuilder
            .builder(ItemRedNetherBrickStack.class)
            .vanillaItem(VanillaItemId.RED_NETHER_BRICK)
            .build();
}
