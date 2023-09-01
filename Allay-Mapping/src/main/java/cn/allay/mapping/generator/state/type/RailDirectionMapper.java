package cn.allay.mapping.generator.state.type;

import cn.allay.mapping.generator.state.StateMapper;
import cn.allay.mapping.generator.state.StateRemapper;
import org.apache.commons.lang3.tuple.Pair;


@StateRemapper(value = "shape", blockRegex = ".*rail.?$")
public class RailDirectionMapper extends StateMapper<Integer> {
    @Override
    public Pair<String, Integer> translateState(String fullIdentifier, String value) {
        int direction = switch (value) {
            case "north_south" -> 0;
            case "east_west" -> 1;
            case "ascending_east" -> 2;
            case "ascending_west" -> 3;
            case "ascending_north" -> 4;
            case "ascending_south" -> 5;
            case "south_east" -> 6;
            case "south_west" -> 7;
            case "north_west" -> 8;
            case "north_east" -> 9;
            default -> throw new RuntimeException("Unknown rail state found!: " + value);
        };
        return Pair.of("rail_direction", direction);
    }
}
