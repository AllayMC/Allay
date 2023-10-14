package cn.allay.api.entity.interfaces.player;

import cn.allay.api.component.interfaces.ComponentEvent;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public record PlayerLoggedInEvent() implements ComponentEvent {
    static PlayerLoggedInEvent INSTANCE = new PlayerLoggedInEvent();
}
