package org.allaymc.api.entity.component.event;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public record PlayerLoggedInEvent() {
    public static PlayerLoggedInEvent INSTANCE = new PlayerLoggedInEvent();
}
