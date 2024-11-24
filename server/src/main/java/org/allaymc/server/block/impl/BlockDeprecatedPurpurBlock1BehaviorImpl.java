package org.allaymc.server.block.impl;

import java.util.List;
import org.allaymc.api.block.interfaces.BlockDeprecatedPurpurBlock1Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeprecatedPurpurBlock1BehaviorImpl extends BlockBehaviorImpl implements BlockDeprecatedPurpurBlock1Behavior {
    public BlockDeprecatedPurpurBlock1BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
