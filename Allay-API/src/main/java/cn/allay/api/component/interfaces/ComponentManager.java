package cn.allay.api.component.interfaces;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
public interface ComponentManager {
    <T extends ComponentEvent> T callEvent(T event);
}
