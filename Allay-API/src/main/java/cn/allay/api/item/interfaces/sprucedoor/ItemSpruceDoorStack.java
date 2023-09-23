package cn.allay.api.item.interfaces.sprucedoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceDoorStack extends ItemStack {
    ItemType<ItemSpruceDoorStack> SPRUCE_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemSpruceDoorStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_DOOR)
            .build();
}
