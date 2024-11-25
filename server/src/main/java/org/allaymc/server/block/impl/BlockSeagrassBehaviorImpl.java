package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSeagrassBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSeagrassBehaviorImpl extends BlockBehaviorImpl implements BlockSeagrassBehavior {
    public BlockSeagrassBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
