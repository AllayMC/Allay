package org.allaymc.server.component.interfaces;

import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.event.TestEvent;

/**
 * @author daoge_cmd
 */
public interface TestEventTriggerComponent extends Component {
    TestEvent triggerEvent(String message);
}
