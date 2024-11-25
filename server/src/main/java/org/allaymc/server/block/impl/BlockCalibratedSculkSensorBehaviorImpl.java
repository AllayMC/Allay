package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockCalibratedSculkSensorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockCalibratedSculkSensorBehaviorImpl extends BlockBehaviorImpl implements BlockCalibratedSculkSensorBehavior {
    public BlockCalibratedSculkSensorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
