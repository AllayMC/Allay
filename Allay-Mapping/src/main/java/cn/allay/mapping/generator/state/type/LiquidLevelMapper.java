package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "level", blockRegex = "^minecraft:water|^minecraft:lava")
public class LiquidLevelMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("liquid_depth", Integer.parseInt(value));
    }
}
