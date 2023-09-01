package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "candles", blockRegex = ".*candle$")
public class CandleCountMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        // Java index starts at 1; Bedrock index starts at 0
        return Pair.of("candles", Integer.parseInt(value) - 1);
    }
}
