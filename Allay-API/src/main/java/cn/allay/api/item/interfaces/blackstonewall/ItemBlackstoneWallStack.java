package cn.allay.api.item.interfaces.blackstonewall;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneWallStack extends ItemStack {
    ItemType<ItemBlackstoneWallStack> BLACKSTONE_WALL_TYPE = ItemTypeBuilder
            .builder(ItemBlackstoneWallStack.class)
            .vanillaItem(VanillaItemId.BLACKSTONE_WALL)
            .build();
}
