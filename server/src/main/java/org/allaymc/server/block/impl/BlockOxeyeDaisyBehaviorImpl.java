package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockOxeyeDaisyBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockOxeyeDaisyBehaviorImpl extends BlockBehaviorImpl implements BlockOxeyeDaisyBehavior {
    public BlockOxeyeDaisyBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
