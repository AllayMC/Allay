package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockDeprecatedPurpurBlock1Behavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockDeprecatedPurpurBlock1BehaviorImpl extends BlockBehaviorImpl implements BlockDeprecatedPurpurBlock1Behavior {
    public BlockDeprecatedPurpurBlock1BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
