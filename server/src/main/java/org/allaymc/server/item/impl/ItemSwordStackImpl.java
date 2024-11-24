package org.allaymc.server.item.impl;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.sword.*;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class ItemSwordStackImpl extends ItemStackImpl implements ItemDiamondSwordStack, ItemGoldenSwordStack, ItemIronSwordStack, ItemNetheriteSwordStack, ItemStoneSwordStack, ItemWoodenSwordStack {
    public ItemSwordStackImpl(ItemStackInitInfo initInfo,
                              List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
        ;
    }
}
