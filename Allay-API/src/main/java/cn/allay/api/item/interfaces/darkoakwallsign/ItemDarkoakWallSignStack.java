package cn.allay.api.item.interfaces.darkoakwallsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkoakWallSignStack extends ItemStack {
    ItemType<ItemDarkoakWallSignStack> DARKOAK_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemDarkoakWallSignStack.class)
            .vanillaItem(VanillaItemId.DARKOAK_WALL_SIGN)
            .build();
}
