package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockElementBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockElementBehaviorImpl extends BlockBehaviorImpl implements BlockElementBehavior {
    public BlockElementBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
