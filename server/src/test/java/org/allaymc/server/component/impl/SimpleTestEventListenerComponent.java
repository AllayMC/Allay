package org.allaymc.server.component.impl;

import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.event.TestEvent;
import org.allaymc.server.component.interfaces.TestEventListenerComponent;

/**
 * @author daoge_cmd
 */
public class SimpleTestEventListenerComponent implements TestEventListenerComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:test_event_listener_component");

    @EventHandler
    protected void testListener(TestEvent event) {
        event.setMessage("testListener() accepted to the event!");
    }
}
