package org.allaymc.server.component.event;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.event.Event;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class TestEvent extends Event {
    private String message;

    public TestEvent(String message) {
        this.message = message;
    }
}
