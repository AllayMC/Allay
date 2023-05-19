package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.ironDoor extends ItemStack{
        ItemType<ItemItem.ironDoor>TYPE=ItemTypeBuilder
        .builder(ItemItem.ironDoor.class)
        .vanillaItem(VanillaItemId.IRON_DOOR,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
