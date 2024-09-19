package org.allaymc.server.component.impl;

import org.allaymc.server.component.annotation.Identifier;
import org.allaymc.server.component.interfaces.TestAnonymousClassComponent;

/**
 * @author daoge_cmd
 */
public class SimpleTestAnonymousClassComponent implements TestAnonymousClassComponent {
    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:test_anonymous_class_component");
}
