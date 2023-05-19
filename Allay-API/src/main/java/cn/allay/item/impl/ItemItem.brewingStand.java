package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.brewingStand extends ItemStack{
        ItemType<ItemItem.brewingStand>TYPE=ItemTypeBuilder
        .builder(ItemItem.brewingStand.class)
        .vanillaItem(VanillaItemId.BREWING_STAND,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
