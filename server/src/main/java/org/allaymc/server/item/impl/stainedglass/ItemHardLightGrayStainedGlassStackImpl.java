package org.allaymc.server.item.impl.stainedglass;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardLightGrayStainedGlassStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemHardLightGrayStainedGlassStackImpl extends ItemStackImpl implements ItemHardLightGrayStainedGlassStack {
    public ItemHardLightGrayStainedGlassStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
