package org.allaymc.server.item.impl.anvil;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.anvil.ItemDeprecatedAnvilStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemDeprecatedAnvilStackImpl extends ItemStackImpl implements ItemDeprecatedAnvilStack {
    public ItemDeprecatedAnvilStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
