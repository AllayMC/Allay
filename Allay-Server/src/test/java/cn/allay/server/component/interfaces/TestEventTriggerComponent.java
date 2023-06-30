package cn.allay.server.component.interfaces;

import cn.allay.api.component.annotation.Inject;
import cn.allay.server.component.event.TestEvent;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
public interface TestEventTriggerComponent {
    @Inject
    TestEvent triggerEvent(String message);
}
