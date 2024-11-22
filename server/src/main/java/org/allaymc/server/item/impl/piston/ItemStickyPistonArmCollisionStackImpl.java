package org.allaymc.server.item.impl.piston;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.piston.ItemStickyPistonArmCollisionStack;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

public class ItemStickyPistonArmCollisionStackImpl extends ItemStackImpl implements ItemStickyPistonArmCollisionStack {
    public ItemStickyPistonArmCollisionStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);;
    }
}
