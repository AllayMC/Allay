package org.allaymc.server.block.impl.lightblock;

import java.util.List;
import org.allaymc.api.block.interfaces.lightblock.BlockLightBlock13Behavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLightBlock13BehaviorImpl extends BlockBehaviorImpl implements BlockLightBlock13Behavior {
    public BlockLightBlock13BehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
