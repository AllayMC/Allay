package cn.allay.api.item.interfaces.musicdiscfar;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscFarStack extends ItemStack {
    ItemType<ItemMusicDiscFarStack> MUSIC_DISC_FAR_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscFarStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_FAR)
            .build();
}
