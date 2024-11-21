package org.allaymc.server.component.impl;

import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.component.event.TestEvent;
import org.allaymc.server.component.interfaces.TestEventListenerComponent;

/**
 * @author daoge_cmd
 */
public class SimpleTestEventListenerComponent implements TestEventListenerComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_event_listener_component");

    @EventHandler
    protected void testListener(TestEvent event) {
        event.setMessage("testListener() accepted to the event!");
    }
}
