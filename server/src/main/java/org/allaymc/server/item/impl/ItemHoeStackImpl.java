package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.hoe.*;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemHoeStackImpl extends ItemStackImpl implements ItemDiamondHoeStack, ItemGoldenHoeStack, ItemIronHoeStack, ItemNetheriteHoeStack, ItemStoneHoeStack, ItemWoodenHoeStack {
    public ItemHoeStackImpl(ItemStackInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
