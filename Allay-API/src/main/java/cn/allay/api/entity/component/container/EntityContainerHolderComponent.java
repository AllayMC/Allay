package cn.allay.api.entity.component.container;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.container.Container;
import cn.allay.api.container.ContainerHolder;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.entity.component.EntityComponent;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface EntityContainerHolderComponent extends ContainerHolder, EntityComponent {
    @Override
    @Inject
    @UnmodifiableView
    Map<FullContainerType<?>, Container> getContainers();

    @Override
    @Inject
    <T extends Container> @Nullable T getContainer(FullContainerType<T> type);

    @Override
    @Inject
    void addContainer(Container container);
}
