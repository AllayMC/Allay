package org.allaymc.server.item.impl.candle;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.candle.ItemPinkCandleStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemPinkCandleStackImpl extends ItemStackImpl implements ItemPinkCandleStack {
    public ItemPinkCandleStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}