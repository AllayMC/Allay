package cn.allay.server.component.interfaces;

import cn.allay.api.component.annotation.Inject;
import cn.allay.server.component.event.TestEvent;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/6 <br>
 * Allay Project <br>
 */
public interface TestEventTriggerComponent {
    @Inject
    TestEvent triggerEvent(String message);
}
