package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBannerBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBanner;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityBannerImpl} delegates banner appearance operations to
 * injected components while inheriting common block-entity behavior. Component
 * assembly initializes the delegation fields before completion hooks run; this
 * class contributes the public capability shape while its configured providers
 * supply the underlying state and implementation.
 *
 * @author daoge_cmd
 */
public class BlockEntityBannerImpl extends BlockEntityImpl implements BlockEntityBanner {

    /**
     * {@code bannerBaseComponent} supplies the injected
     * {@link BlockEntityBannerBaseComponent} capability. Component assembly
     * requires exactly one provider result assignable to this field before
     * delegated operations are available.
     */
    @Delegate
    private BlockEntityBannerBaseComponent bannerBaseComponent;

    /**
     * Constructs the composed banner using the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base and every declared delegation
     * capability.
     */
    public BlockEntityBannerImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
