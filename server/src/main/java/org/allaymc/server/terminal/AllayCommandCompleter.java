package org.allaymc.server.terminal;

import org.allaymc.api.server.Server;
import org.allaymc.server.command.AllayCommandSuggester;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.List;

/**
 * {@code AllayCommandCompleter} adapts command-tree suggestions to terminal completion candidates.
 * It passes the full parsed input and cursor position to the shared suggester, then appends each
 * returned string to the candidate list supplied by the terminal reader.
 */
public final class AllayCommandCompleter implements Completer {
    /**
     * Server-backed command metadata suggester.
     */
    private final AllayCommandSuggester suggester;

    /**
     * Creates a terminal completer using command metadata from the supplied server.
     */
    public AllayCommandCompleter(Server server) {
        this.suggester = new AllayCommandSuggester(server);
    }

    /**
     * Appends command suggestions for the parsed line and cursor to the supplied candidate list.
     * Existing entries remain in that list, and the {@code reader} argument does not affect suggestion
     * selection.
     */
    @Override
    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
        suggester.complete(line.line(), line.cursor()).candidates().stream()
                .map(Candidate::new)
                .forEach(candidates::add);
    }
}
