package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockGlowLichenBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockGlowLichenBehaviorImpl extends BlockBehaviorImpl implements BlockGlowLichenBehavior {
    public BlockGlowLichenBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
