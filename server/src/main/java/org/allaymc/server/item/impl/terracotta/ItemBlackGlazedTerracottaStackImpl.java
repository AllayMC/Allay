package org.allaymc.server.item.impl.terracotta;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.terracotta.ItemBlackGlazedTerracottaStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemBlackGlazedTerracottaStackImpl extends ItemStackImpl implements ItemBlackGlazedTerracottaStack {
    public ItemBlackGlazedTerracottaStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}