package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "powered", blockRegex = ".*_pressure_plate.?$")
public class PressurePlatePoweredMapper extends StateMapper<Integer> {

    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        return Pair.of("redstone_signal", value.equals("true") ? 15 : 0);
    }
}
