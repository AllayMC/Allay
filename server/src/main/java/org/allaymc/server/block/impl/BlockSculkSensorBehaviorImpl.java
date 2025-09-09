package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockSculkSensorBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSculkSensorBehaviorImpl extends BlockBehaviorImpl implements BlockSculkSensorBehavior {
    public BlockSculkSensorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
