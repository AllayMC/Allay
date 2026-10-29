package org.allaymc.server.terminal;

import org.allaymc.api.server.Server;
import org.allaymc.server.command.AllayCommandSuggester;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.List;

/**
 * Simple command completer backed by Allay's command tree metadata.
 */
public final class AllayCommandCompleter implements Completer {
    private final AllayCommandSuggester suggester;

    public AllayCommandCompleter(Server server) {
        this.suggester = new AllayCommandSuggester(server);
    }

    @Override
    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
        suggester.complete(line.line(), line.cursor()).candidates().stream()
                .map(Candidate::new)
                .forEach(candidates::add);
    }
}
