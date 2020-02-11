package ru.denku.csv;

import java.util.ArrayList;
import java.util.List;

public class CsvParser implements Parser {

    private static final char DEFAULT_SEPARATOR = ',';

    private static final char DEFAULT_QUOTE = '"';

    private char separator;

    private char quote;

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public char getQuote() {
        return quote;
    }

    public void setQuote(char quote) {
        this.quote = quote;
    }

    public CsvParser() {
        separator = DEFAULT_SEPARATOR;
        quote = DEFAULT_QUOTE;
    }

    public CsvParser(char separator, char quote) {
        this.quote = quote;
        this.separator = separator;
    }

    @Override
    public List<String> parse(String string) {
        List<String> result = new ArrayList<>();
        if (string == null || string.trim().isEmpty()) {
            return result;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = string.toCharArray();
        for (char ch : chars) {
            if (inQuotes) {
                startCollectChar = true;
                if (ch == quote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (ch == quote) {
                    inQuotes = true;

                    if (chars[0] != '"' && quote == '\"') {
                        curVal.append('"');
                    }

                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == separator) {
                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\r') {
                    continue;
                } else if (ch == '\n') {
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }

        result.add(curVal.toString());

        return result;
    }
}
