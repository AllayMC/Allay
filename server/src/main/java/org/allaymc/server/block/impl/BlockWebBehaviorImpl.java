package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockWebBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockWebBehaviorImpl extends BlockBehaviorImpl implements BlockWebBehavior {
    public BlockWebBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
