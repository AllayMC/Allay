package cn.allay.mapping.generator.state.type.wall;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "west", blockRegex = ".*_wall.?$")
public class WallWestMapper extends StateMapper<String> {

    @Override
    public Pair<String, String> translateState(String fullIdentifier, String value) {
        if (value.equals("low")) value = "short";
        return Pair.of("wall_connection_type_west", value);
    }
}