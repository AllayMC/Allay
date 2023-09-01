package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "level", blockRegex = "^minecraft:light")
public class LightBlockMapper extends StateMapper<Integer> {
    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("block_light_level", Integer.parseInt(value));
    }
}
