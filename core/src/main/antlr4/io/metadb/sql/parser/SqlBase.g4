grammar SqlBase;

tokens {
    DELIMITER
}

singleStatement
    : statement EOF
    ;

statement
    : query
    ;

query
    : queryTerm
    ;

queryTerm
    : queryPrimary
    ;

queryPrimary
    : querySpecification
    ;

querySpecification
    : SELECT selectItem (',' selectItem)*
      (FROM relation (',' relation)*)?
      (WHERE booleanExpression)?
    ;

selectItem
    : expression (AS? identifier)?
    | ASTERISK
    ;

relation
    : relationPrimary (AS? identifier)?
    ;

relationPrimary
    : qualifiedName
    ;

expression
    : booleanExpression
    ;

booleanExpression
    : valueExpression predicate?
    ;

predicate
    : comparisonOperator valueExpression
    ;

valueExpression
    : primaryExpression
    ;

primaryExpression
    : qualifiedName
    ;

qualifiedName
    : identifier ('.' identifier)*
    ;

comparisonOperator
    : EQ | NEQ | LT | LTE | GT | GTE
    ;

identifier
    : IDENTIFIER
    ;

AS: 'AS';
SELECT: 'SELECT';
FROM: 'FROM';
WHERE: 'WHERE';

EQ: '=';
NEQ: '<>' | '!=';
LT: '<';
LTE: '<=';
GT: '>';
GTE: '>=';

ASTERISK: '*';

IDENTIFIER
    : (LETTER | '_') (LETTER | DIGIT | '_')*
    ;

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [A-Z]
    ;