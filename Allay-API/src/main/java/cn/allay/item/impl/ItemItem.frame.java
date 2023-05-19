package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.frame extends ItemStack{
        ItemType<ItemItem.frame>TYPE=ItemTypeBuilder
        .builder(ItemItem.frame.class)
        .vanillaItem(VanillaItemId.FRAME,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
