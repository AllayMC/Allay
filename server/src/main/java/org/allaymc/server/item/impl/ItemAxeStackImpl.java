package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.axe.*;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemAxeStackImpl extends ItemStackImpl implements ItemDiamondAxeStack, ItemGoldenAxeStack, ItemIronAxeStack, ItemNetheriteAxeStack, ItemStoneAxeStack, ItemWoodenAxeStack {
    public ItemAxeStackImpl(ItemStackInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
