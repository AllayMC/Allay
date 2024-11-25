package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeadbushBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockDeadbushBehaviorImpl extends BlockBehaviorImpl implements BlockDeadbushBehavior {
    public BlockDeadbushBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
