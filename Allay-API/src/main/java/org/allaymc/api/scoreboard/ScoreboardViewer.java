package org.allaymc.api.scoreboard;


import org.allaymc.api.scoreboard.data.DisplaySlot;

/**
 * Allay Project 2024/2/27
 *
 * @author daoge_cmd
 * <p>
 * 描述一个计分板观察者 (eg: EntityPlayer) <br>
 * 此接口用于抽象服务端到客户端的协议层方法 <br>
 * 刻意在方法名中使用了“Scoreboard”防止和EntityPlayer中的其他接口混淆
 */
public interface ScoreboardViewer {
    /**
     * 在指定槽位显示计分板
     *
     * @param scoreboard 目标计分板
     * @param slot       目标槽位
     */
    void displayScoreboard(Scoreboard scoreboard, DisplaySlot slot);

    /**
     * 清除指定槽位的显示内容
     *
     * @param slot 目标槽位
     */
    void hideScoreboardSlot(DisplaySlot slot);

    /**
     * 通知观察者计分板已删除（若计分板在任意显示槽位中，则会一并清除槽位）
     *
     * @param scoreboard 目标计分板
     */
    void removeScoreboard(Scoreboard scoreboard);

    /**
     * 通知观察者指定计分板上的指定行已删除
     *
     * @param line 目标行
     */
    void removeScoreboardLine(ScoreboardLine line);

    /**
     * 向观察者发送指定行的新分数
     *
     * @param line 目标行
     */
    void updateScore(ScoreboardLine line);

    boolean isScoreboardViewerValid();
}
