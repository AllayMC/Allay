package org.allaymc.api.scoreboard;

import lombok.Getter;
import org.allaymc.api.scoreboard.scorer.Scorer;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Allay Project 2024/2/27
 *
 * @author daoge_cmd
 *
 * 计分板上的单个行 <br>
 * 由{@link Scorer}和分数组成
 */
@Getter
public final class ScoreboardLine {

    private static final AtomicLong LINE_ID_COUNTER = new AtomicLong(0);

    /**
     * 此计分行所属的计分板
     */
    private final Scoreboard scoreboard;
    /**
     * 追踪对象
     */
    private final Scorer scorer;
    /**
     * 行id, 客户端通过此id标识计分板上的每个行
     */
    private final long lineId;
    /**
     * 分数
     */
    private int score;

    public ScoreboardLine(Scoreboard scoreboard, Scorer scorer) {
        this(scoreboard, scorer, 0);
    }

    public ScoreboardLine(Scoreboard scoreboard, Scorer scorer, int score) {
        this.scoreboard = scoreboard;
        this.scorer = scorer;
        this.score = score;
        this.lineId = LINE_ID_COUNTER.incrementAndGet();
    }

    public boolean setScore(int score) {
        if (scoreboard.wouldCallEvent()) {
            // TODO: event
//            var event = new ScoreboardLineChangeEvent(scoreboard, this, score, this.score, ScoreboardLineChangeEvent.ActionType.SCORE_CHANGE);
//            Server.getInstance().getPluginManager().callEvent(event);
//            if (event.isCancelled()) {
//                return false;
//            }
//            score = event.getNewValue();
        }
        this.score = score;
        updateScore();
        return true;
    }

    /**
     * 增加分数
     * @param addition 增加量
     * @return 是否成功（事件被撤回就会false）
     */
    public boolean addScore(int addition) {
        return setScore(getScore() + addition);
    }

    /**
     * 减少分数
     * @param reduction 减少量
     * @return 是否成功（事件被撤回就会false）
     */
    public boolean removeScore(int reduction) {
        return setScore(getScore() - reduction);
    }

    /**
     * 转换到network信息
     * @return network信息
     */
    public ScoreInfo toNetworkInfo() {
        return getScorer().toNetworkInfo(getScoreboard(), this);
    }

    /**
     * 通知所属计分板对象更新此行信息
     */
    private void updateScore() {
        scoreboard.updateScore(this);
    }
}
