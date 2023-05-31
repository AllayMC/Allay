package cn.allay.api.component.interfaces;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/6 <br>
 * Allay Project <br>
 */
public interface ComponentManager {
    <T extends ComponentEvent> T callEvent(T event);
}
