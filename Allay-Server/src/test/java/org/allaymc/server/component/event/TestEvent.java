package org.allaymc.server.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class TestEvent extends Event {
    private String message;
}
