package org.allaymc.server.component.impl;

import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.event.TestEvent;
import org.allaymc.server.component.interfaces.TestEventTriggerComponent;

/**
 * @author daoge_cmd
 */
public class SimpleTestEventTriggerComponent implements TestEventTriggerComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:test_event_trigger_component");
    @Manager
    protected ComponentManager manager;

    @Override
    public TestEvent triggerEvent(String message) {
        return manager.callEvent(new TestEvent(message));
    }
}
