package cn.allay.component.interfaces;

import cn.allay.api.component.annotation.Inject;
import cn.allay.component.event.TestEvent;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/6 <br>
 * Allay Project <br>
 */
public interface TestEventTriggerComponent {
    @Inject
    TestEvent triggerEvent(String message);
}
