package cn.allay.api.component.interfaces;

import cn.allay.api.component.annotation.DoNotInject;

/**
 * Describe the implementation of a component <br>
 * Any component implementation needs to implement this interface, otherwise obviously you can't pass it into the injector <br>
 * <p>
 * The namespace ID of this component implementation will be used when injecting component dependencies <br>
 * Which should be a 'static' constant annotated with {@link cn.allay.api.component.annotation.ComponentIdentifier} <br>
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface Component {
    @DoNotInject
    default void onInitFinish(ComponentInitInfo initInfo) {
    }
}
