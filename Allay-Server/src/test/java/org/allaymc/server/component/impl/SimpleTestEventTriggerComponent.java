package org.allaymc.server.component.impl;

import org.allaymc.api.client.data.Identifier;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.server.component.event.TestEvent;
import org.allaymc.server.component.interfaces.TestEventTriggerComponent;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
public class SimpleTestEventTriggerComponent implements TestEventTriggerComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_event_trigger_component");
    @Manager
    protected ComponentManager<?> manager;

    @Override
    public TestEvent triggerEvent(String message) {
        return manager.callEvent(new TestEvent(message));
    }
}
