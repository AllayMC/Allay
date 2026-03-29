package org.allaymc.server.ddui;

import org.allaymc.api.ddui.DDUI;
import org.allaymc.api.ddui.internal.ObjectProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Tests for DDUI path resolution and cache invalidation behavior.
 *
 * @author Miroshka
 */
class DataDrivenScreenPathResolutionTest {

    @Test
    void shouldResolveNamedAndIndexedSegments() {
        var screen = DDUI.customForm().title("Profile");
        screen.label("Name");
        var label = (ObjectProperty<?>) screen.getLayout().getProperty("0");

        assertSame(screen, screen.resolvePath(""));
        assertSame(screen.getLayout(), screen.resolvePath("layout"));
        assertSame(label, screen.resolvePath("layout[0]"));
        assertSame(label.getProperty("text"), screen.resolvePath("layout[0].text"));
    }

    @Test
    void shouldRejectMalformedPaths() {
        var screen = DDUI.customForm().title("Profile");
        screen.label("Name");

        assertNull(screen.resolvePath(".title"));
        assertNull(screen.resolvePath("layout."));
        assertNull(screen.resolvePath("layout["));
        assertNull(screen.resolvePath("layout[]"));
        assertNull(screen.resolvePath("layout[abc]"));
        assertNull(screen.resolvePath("layout.[0]"));
        assertNull(screen.resolvePath("layout[0]."));
        assertNull(screen.resolvePath("layout[0]]"));
        assertNull(screen.resolvePath("layout..text"));
    }
}
