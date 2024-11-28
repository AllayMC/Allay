package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockShroomlightBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockShroomlightBehaviorImpl extends BlockBehaviorImpl implements BlockShroomlightBehavior {
    public BlockShroomlightBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
