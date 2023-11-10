package edu.hw6.task3;

import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * checks that the file name matches the format
 */
public final class GlobMatchFilter extends AbstractFilterImpl {
    private final Pattern pattern;

    public GlobMatchFilter(String globPattern) {
        this.pattern = Pattern.compile(
            createRegexFromGlob(globPattern)
        );
    }

    /**
     * Converts glob string to regexp string
     */
    private static String createRegexFromGlob(String glob) {
        StringBuilder out = new StringBuilder("^");
        for (int i = 0; i < glob.length(); ++i) {
            final char c = glob.charAt(i);
            switch (c) {
                case '*':
                    out.append(".*");
                    break;
                case '?':
                    out.append('.');
                    break;
                case '.':
                    out.append("\\.");
                    break;
                case '\\':
                    out.append("\\\\");
                    break;
                default:
                    out.append(c);
            }
        }
        out.append('$');
        return out.toString();
    }

    /**
     * Filter path using only current filter
     */
    @Override
    protected boolean filterPath(Path entry) {
        return this.pattern.matcher(entry.getFileName().toString()).matches();
    }
}
