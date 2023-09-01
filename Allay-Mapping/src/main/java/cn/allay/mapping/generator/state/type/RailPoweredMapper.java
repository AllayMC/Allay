package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "powered", blockRegex = "^minecraft:detector_rail|^minecraft:powered_rail|^minecraft:activator_rail")
public class RailPoweredMapper extends StateMapper<Boolean> {
    @Override
    public Pair<String, Boolean> translateState(String fullIdentifier, String value) {
        return Pair.of("rail_data_bit", value.equals("true"));
    }
}
