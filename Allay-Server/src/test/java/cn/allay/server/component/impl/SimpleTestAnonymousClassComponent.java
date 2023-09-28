package cn.allay.server.component.impl;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.component.interfaces.TestAnonymousClassComponent;

/**
 * Allay Project 2023/9/28
 *
 * @author daoge_cmd
 */
public class SimpleTestAnonymousClassComponent implements TestAnonymousClassComponent {
    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:test_anonymous_class_component");
}
