package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "level", blockRegex = ".*cauldron.?$")
public class CauldronLiquidLevelMapper extends StateMapper<Integer> {
    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("fill_level", switch (value) {
            case "1" -> 3;
            case "2" -> 4;
            case "3" -> 6;
            default -> throw new RuntimeException("Unknown cauldron liquid level!");
        });
    }
}
