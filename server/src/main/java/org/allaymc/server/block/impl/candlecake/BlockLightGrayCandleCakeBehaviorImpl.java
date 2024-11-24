package org.allaymc.server.block.impl.candlecake;

import java.util.List;
import org.allaymc.api.block.interfaces.candlecake.BlockLightGrayCandleCakeBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLightGrayCandleCakeBehaviorImpl extends BlockBehaviorImpl implements BlockLightGrayCandleCakeBehavior {
    public BlockLightGrayCandleCakeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
