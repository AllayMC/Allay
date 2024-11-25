package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSuspiciousGravelBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSuspiciousGravelBehaviorImpl extends BlockBehaviorImpl implements BlockSuspiciousGravelBehavior {
    public BlockSuspiciousGravelBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
