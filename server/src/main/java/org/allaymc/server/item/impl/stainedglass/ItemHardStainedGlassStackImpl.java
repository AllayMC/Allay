package org.allaymc.server.item.impl.stainedglass;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardStainedGlassStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemHardStainedGlassStackImpl extends ItemStackImpl implements ItemHardStainedGlassStack {
    public ItemHardStainedGlassStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
