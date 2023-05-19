package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.acaciaDoor extends ItemStack{
        ItemType<ItemItem.acaciaDoor>TYPE=ItemTypeBuilder
        .builder(ItemItem.acaciaDoor.class)
        .vanillaItem(VanillaItemId.ACACIA_DOOR,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
