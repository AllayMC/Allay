package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.campfire extends ItemStack{
        ItemType<ItemItem.campfire>TYPE=ItemTypeBuilder
        .builder(ItemItem.campfire.class)
        .vanillaItem(VanillaItemId.CAMPFIRE,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
