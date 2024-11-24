package org.allaymc.server.block.impl.liquid;

import java.util.List;
import org.allaymc.api.block.interfaces.liquid.BlockFlowingWaterBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockFlowingWaterBehaviorImpl extends BlockBehaviorImpl implements BlockFlowingWaterBehavior {
    public BlockFlowingWaterBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
