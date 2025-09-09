package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeadbushBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDeadbushBehaviorImpl extends BlockBehaviorImpl implements BlockDeadbushBehavior {
    public BlockDeadbushBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
