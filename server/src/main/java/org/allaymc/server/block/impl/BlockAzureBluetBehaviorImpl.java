package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockAzureBluetBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockAzureBluetBehaviorImpl extends BlockBehaviorImpl implements BlockAzureBluetBehavior {
    public BlockAzureBluetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
