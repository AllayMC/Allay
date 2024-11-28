package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockEndPortalFrameBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockEndPortalFrameBehaviorImpl extends BlockBehaviorImpl implements BlockEndPortalFrameBehavior {
    public BlockEndPortalFrameBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
