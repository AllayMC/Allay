package org.allaymc.api.scoreboard.scorer;

import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;

/**
 * Allay Project 2024/2/27
 *
 * @author daoge_cmd
 *
 * 描述一个计分板追踪对象
 */
public sealed interface Scorer permits EntityScorer, PlayerScorer, FakeScorer {

    /**
     * 获取追踪对象类型
     * @return 追踪对象类型
     */
    ScoreInfo.ScorerType getScorerType();

    /**
     * 获取名称
     * @return 追踪对象类型
     */
    String getName();

    /**
     * 内部方法
     * 转换到network信息
     * @param scoreboard 所属计分板
     * @param line 所属行
     * @return network信息
     */
    ScoreInfo toNetworkInfo(Scoreboard scoreboard, ScoreboardLine line);
}
