package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.shovel.*;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemShovelStackImpl extends ItemStackImpl implements ItemDiamondShovelStack, ItemGoldenShovelStack, ItemIronShovelStack, ItemNetheriteShovelStack, ItemStoneShovelStack, ItemWoodenShovelStack {
    public ItemShovelStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
