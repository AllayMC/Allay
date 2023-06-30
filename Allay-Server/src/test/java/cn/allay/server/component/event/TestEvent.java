package cn.allay.server.component.event;

import cn.allay.api.component.interfaces.ComponentEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class TestEvent implements ComponentEvent {
    private String message;

    public TestEvent(String message) {
        this.message = message;
    }
}
