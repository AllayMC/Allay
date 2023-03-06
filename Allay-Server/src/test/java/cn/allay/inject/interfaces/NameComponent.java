package cn.allay.inject.interfaces;


import cn.allay.inject.annotation.Inject;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public interface NameComponent {
    @Inject
    String getName();
}
