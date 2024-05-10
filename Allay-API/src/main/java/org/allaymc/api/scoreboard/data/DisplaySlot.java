package org.allaymc.api.scoreboard.data;

import lombok.Getter;

/**
 * Allay Project 2024/2/27
 *
 * @author daoge_cmd
 * <p>
 * 计分板显示槽位枚举
 */
public enum DisplaySlot {
    //玩家屏幕右侧
    SIDEBAR("sidebar"),
    //玩家列表
    LIST("list"),
    //玩家名称标签下方
    BELOW_NAME("belowname");

    @Getter
    private final String slotName;

    DisplaySlot(String slotName) {
        this.slotName = slotName;
    }
}
