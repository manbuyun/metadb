package io.metadb.sql.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/**
 * @author jinhai
 * @date 2021/10/31
 */
public class SqlParser {

    public Object parse(String sql) {
        SqlBaseLexer lexer = new SqlBaseLexer(new UpperCaseCharStream(CharStreams.fromString(sql)));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(tokenStream);

        parser.addParseListener(new PostProcessor());

        lexer.removeErrorListeners();
        parser.removeErrorListeners();

        ParserRuleContext tree;
        try {
            parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
            tree = parser.singleStatement();
        } catch (ParseCancellationException e) {
            // if we fail, parse with LL mode
            tokenStream.seek(0); // rewind input stream
            parser.reset();

            parser.getInterpreter().setPredictionMode(PredictionMode.LL);
            tree = parser.singleStatement();
        }
        return new AstBuilder().visit(tree);
    }

    private static class PostProcessor extends SqlBaseBaseListener {
    }
}
