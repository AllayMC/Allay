package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityDispenserBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityDropper;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityDropperImpl} implements the composed block entity's dropper
 * inventory and ejection capabilities through injected components. Component
 * assembly assigns the delegation fields before completion hooks run, and the
 * inherited base component supplies identity, position, naming, and NBT
 * serialization for the same object.
 *
 * @author daoge_cmd
 */
public class BlockEntityDropperImpl extends BlockEntityImpl implements BlockEntityDropper {

    /**
     * {@code containerHolderComponent} supplies dropper inventory operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;
    /**
     * {@code dispenserBaseComponent} supplies dropper ejection operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityDispenserBaseComponent dispenserBaseComponent;

    /**
     * Creates the composed block entity from the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base capability and every declared
     * delegation capability.
     */
    public BlockEntityDropperImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
