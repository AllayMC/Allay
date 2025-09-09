package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockEndPortalBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockEndPortalBehaviorImpl extends BlockBehaviorImpl implements BlockEndPortalBehavior {
    public BlockEndPortalBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
