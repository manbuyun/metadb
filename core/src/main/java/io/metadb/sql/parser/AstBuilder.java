package io.metadb.sql.parser;

import io.metadb.sql.tree.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author jinhai
 * @date 2021/10/31
 */
public class AstBuilder extends SqlBaseBaseVisitor<Node> {

    @Override
    public Node visitArithmeticBinary(SqlBaseParser.ArithmeticBinaryContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        ArithmeticBinary.Operator operator = getArithmeticBinaryOperator(ctx.operator);
        Expression right = (Expression) visit(ctx.right);
        return new ArithmeticBinary(getLocation(ctx), left, operator, right);
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
        return new LongLiteral(getLocation(ctx), ctx.getText());
    }

    @Override
    public Node visitString(SqlBaseParser.StringContext ctx) {
        String value = ctx.getText();
        return new StringLiteral(getLocation(ctx), value.substring(1, value.length() - 1));
    }

    private <T> List<T> visit(List<? extends ParserRuleContext> ctxs, Class<T> clazz) {
        return ctxs.stream().map(this::visit).map(clazz::cast).collect(Collectors.toList());
    }

    private static ArithmeticBinary.Operator getArithmeticBinaryOperator(Token operator) {
        switch (operator.getType()) {
            case SqlBaseLexer.PLUS:
                return ArithmeticBinary.Operator.ADD;
            case SqlBaseLexer.MINUS:
                return ArithmeticBinary.Operator.SUBTRACT;
            case SqlBaseLexer.ASTERISK:
                return ArithmeticBinary.Operator.MULTIPLY;
            case SqlBaseLexer.SLASH:
                return ArithmeticBinary.Operator.DIVIDE;
        }
        throw new UnsupportedOperationException("Unsupported operator: " + operator.getText());
    }

    private NodeLocation getLocation(ParserRuleContext ctx) {
        Objects.requireNonNull(ctx, "ParserRuleContext is null");
        return getLocation(ctx.getStart());
    }

    private NodeLocation getLocation(Token token) {
        Objects.requireNonNull(token, "Token is null");
        return new NodeLocation(token.getLine(), token.getCharPositionInLine() + 1);
    }
}
