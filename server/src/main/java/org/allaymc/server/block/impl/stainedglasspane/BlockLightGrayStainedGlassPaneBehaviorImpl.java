package org.allaymc.server.block.impl.stainedglasspane;

import java.util.List;
import org.allaymc.api.block.interfaces.stainedglasspane.BlockLightGrayStainedGlassPaneBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockLightGrayStainedGlassPaneBehaviorImpl extends BlockBehaviorImpl implements BlockLightGrayStainedGlassPaneBehavior {
    public BlockLightGrayStainedGlassPaneBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
