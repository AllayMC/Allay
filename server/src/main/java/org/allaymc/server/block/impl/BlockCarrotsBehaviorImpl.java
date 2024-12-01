package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCarrotsBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCarrotsBehaviorImpl extends BlockBehaviorImpl implements BlockCarrotsBehavior {
    public BlockCarrotsBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
