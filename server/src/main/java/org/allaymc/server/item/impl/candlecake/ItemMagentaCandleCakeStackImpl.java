package org.allaymc.server.item.impl.candlecake;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.candlecake.ItemMagentaCandleCakeStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemMagentaCandleCakeStackImpl extends ItemStackImpl implements ItemMagentaCandleCakeStack {
    public ItemMagentaCandleCakeStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
