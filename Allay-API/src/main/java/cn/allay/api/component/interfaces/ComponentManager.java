package cn.allay.api.component.interfaces;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/6 <br>
 * Allay Project <br>
 */
public interface ComponentManager {
    <T extends ComponentEvent> T callEvent(T event);
}
