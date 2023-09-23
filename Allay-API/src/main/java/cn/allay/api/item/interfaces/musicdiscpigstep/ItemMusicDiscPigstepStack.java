package cn.allay.api.item.interfaces.musicdiscpigstep;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscPigstepStack extends ItemStack {
    ItemType<ItemMusicDiscPigstepStack> MUSIC_DISC_PIGSTEP_TYPE = ItemTypeBuilder
            .builder(ItemMusicDiscPigstepStack.class)
            .vanillaItem(VanillaItemId.MUSIC_DISC_PIGSTEP)
            .build();
}
