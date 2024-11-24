package org.allaymc.server.block.impl.liquid;

import java.util.List;
import org.allaymc.api.block.interfaces.liquid.BlockFlowingLavaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockFlowingLavaBehaviorImpl extends BlockBehaviorImpl implements BlockFlowingLavaBehavior {
    public BlockFlowingLavaBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
