package cn.allay.mapping.generator.state.type.wall;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "south", blockRegex = ".*_wall.?$")
public class WallSouthMapper extends StateMapper<String> {

    @Override
    public Pair<String, String> translateState(String fullIdentifier, String value) {
        if (value.equals("low")) value = "short";
        return Pair.of("wall_connection_type_south", value);
    }
}