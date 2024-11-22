package org.allaymc.server.item.impl.terracotta;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.terracotta.ItemLightBlueGlazedTerracottaStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemLightBlueGlazedTerracottaStackImpl extends ItemStackImpl implements ItemLightBlueGlazedTerracottaStack {
    public ItemLightBlueGlazedTerracottaStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
