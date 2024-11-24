package org.allaymc.server.block.impl.lightblock;

import java.util.List;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock3Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLightBlock3BehaviorImpl extends BlockBehaviorImpl implements BlockLightBlock3Behavior {
    public BlockLightBlock3BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
