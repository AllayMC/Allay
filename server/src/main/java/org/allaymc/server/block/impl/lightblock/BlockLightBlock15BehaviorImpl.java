package org.allaymc.server.block.impl.lightblock;

import java.util.List;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock15Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLightBlock15BehaviorImpl extends BlockBehaviorImpl implements BlockLightBlock15Behavior {
    public BlockLightBlock15BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
