package org.allaymc.server.item.impl.terracotta;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.terracotta.ItemSilverGlazedTerracottaStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemSilverGlazedTerracottaStackImpl extends ItemStackImpl implements ItemSilverGlazedTerracottaStack {
    public ItemSilverGlazedTerracottaStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
