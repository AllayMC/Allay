package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockButtonBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockButtonBehaviorImpl extends BlockBehaviorImpl implements BlockButtonBehavior {
    public BlockButtonBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
