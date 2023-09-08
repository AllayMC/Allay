package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.annotation.Manager;
import cn.allay.api.component.interfaces.Component;
import cn.allay.api.component.interfaces.ComponentManager;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.event.TestEvent;
import cn.allay.server.component.interfaces.TestEventTriggerComponent;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
public class SimpleTestEventTriggerComponent implements TestEventTriggerComponent, Component {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_event_trigger_component");
    @Manager
    protected ComponentManager<?> manager;

    @Impl
    @Override
    public TestEvent triggerEvent(String message) {
        return manager.callEvent(new TestEvent(message));
    }
}
