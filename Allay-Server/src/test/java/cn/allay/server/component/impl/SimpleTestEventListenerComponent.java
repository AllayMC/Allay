package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.ComponentEventListener;
import cn.allay.api.component.interfaces.ComponentImpl;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.event.TestEvent;
import cn.allay.server.component.interfaces.TestEventListenerComponent;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
public class SimpleTestEventListenerComponent implements TestEventListenerComponent, ComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:test_event_listener_component");

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }

    @ComponentEventListener
    protected void testListener(TestEvent event) {
        event.setMessage("testListener() accepted to the event!");
    }
}
