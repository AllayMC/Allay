package cn.allay.component.interfaces;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/11 <br>
 * Allay Project <br>
 */
public interface ComponentInjectorFactory {
    <T> ComponentInjector<T> get();
}
