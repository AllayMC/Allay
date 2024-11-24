package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemItemStorableComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.shulkerbox.*;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemShulkerBoxStackImpl extends ItemStackImpl implements ItemBlackShulkerBoxStack, ItemBlueShulkerBoxStack, ItemBrownShulkerBoxStack, ItemCyanShulkerBoxStack, ItemGrayShulkerBoxStack, ItemGreenShulkerBoxStack, ItemLightBlueShulkerBoxStack, ItemLightGrayShulkerBoxStack, ItemLimeShulkerBoxStack, ItemMagentaShulkerBoxStack, ItemOrangeShulkerBoxStack, ItemPinkShulkerBoxStack, ItemPurpleShulkerBoxStack, ItemRedShulkerBoxStack, ItemShulkerBoxStack, ItemUndyedShulkerBoxStack, ItemWhiteShulkerBoxStack, ItemYellowShulkerBoxStack {
    @Delegate
    protected ItemItemStorableComponent itemStorableComponent;

    public ItemShulkerBoxStackImpl(ItemStackInitInfo initInfo,
                                   List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
