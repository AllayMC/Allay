package cn.allay.server.component.event;

import cn.allay.api.component.interfaces.ComponentEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/6 <br>
 * Allay Project <br>
 */
@Getter
@Setter
public class TestEvent implements ComponentEvent {
    private String message;

    public TestEvent(String message) {
        this.message = message;
    }
}
