package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCartographyTableBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockCartographyTableBehaviorImpl extends BlockBehaviorImpl implements BlockCartographyTableBehavior {
    public BlockCartographyTableBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
