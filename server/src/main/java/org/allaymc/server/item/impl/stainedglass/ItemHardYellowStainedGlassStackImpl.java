package org.allaymc.server.item.impl.stainedglass;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardYellowStainedGlassStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemHardYellowStainedGlassStackImpl extends ItemStackImpl implements ItemHardYellowStainedGlassStack {
    public ItemHardYellowStainedGlassStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
