package cn.allay.api.command.data;

import lombok.Getter;

import java.util.Objects;

@Getter
public class CommandPath {

    private final String name;
    private final String[] aliases;

    public CommandPath(String name) {
        this(name, new String[0]);
    }

    public CommandPath(String... nameAndAliases) {
        this.name = nameAndAliases[0];

        System.arraycopy(nameAndAliases, 1, nameAndAliases, 0, nameAndAliases.length - 1);
        this.aliases = nameAndAliases;
    }

    public CommandPath(String name, String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandPath that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
