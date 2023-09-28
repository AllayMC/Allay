package cn.allay.server.component.interfaces;

import cn.allay.api.component.interfaces.Component;
import cn.allay.server.component.event.TestEvent;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
public interface TestEventTriggerComponent extends Component {
    TestEvent triggerEvent(String message);
}
