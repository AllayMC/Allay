package cn.allay.api.blockentity.component.container;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.container.BaseContainerHolder;
import cn.allay.api.container.Container;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.identifier.Identifier;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public class BlockEntityContainerHolderComponentImpl extends BaseContainerHolder implements BlockEntityContainerHolderComponent {
    @ComponentIdentifier
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_inventory_holder_component");

    public BlockEntityContainerHolderComponentImpl() {}

    public BlockEntityContainerHolderComponentImpl(Container... containers) {
        super(containers);
    }

    @Override
    @Impl
    public @UnmodifiableView Map<FullContainerType<?>, Container> getContainers() {
        return super.getContainers();
    }

    @Override
    @Nullable
    @Impl
    public <T extends Container> T getContainer(FullContainerType<T> type) {
        return super.getContainer(type);
    }

    @Override
    @Impl
    public void addContainer(Container container) {
        super.addContainer(container);
    }
}
