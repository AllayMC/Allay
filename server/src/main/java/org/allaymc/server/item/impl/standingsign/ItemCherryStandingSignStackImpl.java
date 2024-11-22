package org.allaymc.server.item.impl.standingsign;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.standingsign.ItemCherryStandingSignStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemCherryStandingSignStackImpl extends ItemStackImpl implements ItemCherryStandingSignStack {
    public ItemCherryStandingSignStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
