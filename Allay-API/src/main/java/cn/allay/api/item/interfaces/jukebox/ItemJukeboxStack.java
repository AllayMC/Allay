package cn.allay.api.item.interfaces.jukebox;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJukeboxStack extends ItemStack {
    ItemType<ItemJukeboxStack> JUKEBOX_TYPE = ItemTypeBuilder
            .builder(ItemJukeboxStack.class)
            .vanillaItem(VanillaItemId.JUKEBOX)
            .build();
}
