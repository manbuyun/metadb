package io.metadb.sql.parser;

import io.metadb.sql.tree.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author jinhai
 * @date 2021/10/31
 */
public class AstBuilder extends SqlBaseBaseVisitor<Node> {

    @Override
    public Node visitComparison(SqlBaseParser.ComparisonContext ctx) {
        Token symbol = ((TerminalNode) ctx.comparisonOperator().getChild(0)).getSymbol();

        return new Comparison(
                getLocation(ctx),
                (Expression) visit(ctx.left),
                getOperator(symbol),
                (Expression) visit(ctx.right));
    }

    @Override
    public Node visitQualifiedName(SqlBaseParser.QualifiedNameContext ctx) {
        List<Identifier> identifiers = visit(ctx.identifier(), Identifier.class);
        return new QualifiedName(getLocation(ctx), identifiers);
    }

    @Override
    public Node visitIdentifier(SqlBaseParser.IdentifierContext ctx) {
        return new Identifier(getLocation(ctx), ctx.getText());
    }

    @Override
    public Node visitIntegerLiteral(SqlBaseParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(getLocation(ctx), ctx.getText());
    }

    @Override
    public Node visitString(SqlBaseParser.StringContext ctx) {
        String value = ctx.getText();
        return new StringLiteral(getLocation(ctx), value.substring(1, value.length() - 1));
    }

    private <T> List<T> visit(List<? extends ParserRuleContext> ctxs, Class<T> clazz) {
        return ctxs.stream().map(this::visit).map(clazz::cast).collect(Collectors.toList());
    }

    private NodeLocation getLocation(ParserRuleContext ctx) {
        Objects.requireNonNull(ctx, "ParserRuleContext is null");
        return getLocation(ctx.getStart());
    }

    private NodeLocation getLocation(Token token) {
        Objects.requireNonNull(token, "Token is null");
        return new NodeLocation(token.getLine(), token.getCharPositionInLine() + 1);
    }

    private static Comparison.Operator getOperator(Token symbol) {
        switch (symbol.getType()) {
            case SqlBaseLexer.EQ:
                return Comparison.Operator.EQUAL;
            case SqlBaseLexer.NEQ:
                return Comparison.Operator.NOT_EQUAL;
            case SqlBaseLexer.LT:
                return Comparison.Operator.LESS_THAN;
            case SqlBaseLexer.LTE:
                return Comparison.Operator.LESS_THAN_OR_EQUAL;
            case SqlBaseLexer.GT:
                return Comparison.Operator.GREATER_THAN;
            case SqlBaseLexer.GTE:
                return Comparison.Operator.GREATER_THAN_OR_EQUAL;
        }

        throw new IllegalArgumentException("Unsupported operator: " + symbol.getText());
    }
}
