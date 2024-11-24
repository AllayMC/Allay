package org.allaymc.server.block.impl.lightblock;

import java.util.List;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock2Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLightBlock2BehaviorImpl extends BlockBehaviorImpl implements BlockLightBlock2Behavior {
    public BlockLightBlock2BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
