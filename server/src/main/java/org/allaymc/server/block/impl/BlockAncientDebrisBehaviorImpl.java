package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAncientDebrisBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockAncientDebrisBehaviorImpl extends BlockBehaviorImpl implements BlockAncientDebrisBehavior {
    public BlockAncientDebrisBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
