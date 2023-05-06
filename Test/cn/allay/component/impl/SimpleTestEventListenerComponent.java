package cn.allay.component.impl;

import cn.allay.component.annotation.ComponentEventListener;
import cn.allay.component.event.TestEvent;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.TestEventListenerComponent;
import cn.allay.identifier.Identifier;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/6 <br>
 * Allay Project <br>
 */
public class SimpleTestEventListenerComponent implements TestEventListenerComponent, ComponentImpl {

    protected static final Identifier IDENTIFIER = new Identifier("minecraft:test_event_listener_component");
    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }

    @ComponentEventListener
    protected void testListener(TestEvent event) {
        event.setMessage("testListener() accepted to the event!");
    }
}
