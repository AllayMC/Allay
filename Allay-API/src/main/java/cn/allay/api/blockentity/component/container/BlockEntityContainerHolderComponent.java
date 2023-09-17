package cn.allay.api.blockentity.component.container;

import cn.allay.api.blockentity.component.BlockEntityComponent;
import cn.allay.api.component.annotation.Inject;
import cn.allay.api.container.Container;
import cn.allay.api.container.ContainerHolder;
import cn.allay.api.container.FullContainerType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface BlockEntityContainerHolderComponent extends ContainerHolder, BlockEntityComponent {
    @Override
    @UnmodifiableView
    default Map<FullContainerType<?>, Container> getContainers() {
        Container container = getContainer();
        return Map.of(container.getContainerType(), getContainer());
    }

    @Override
    default <T extends Container> T getContainer(FullContainerType<T> type) {
        return getContainer();
    }

    @Override
    default void addContainer(Container container) {
        setContainer(container);
    }

    @Inject
    <T extends Container> T getContainer();

    @Inject
    void setContainer(Container container);
}
