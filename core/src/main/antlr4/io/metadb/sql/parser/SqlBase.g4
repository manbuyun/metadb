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
    : left=valueExpression comparisonOperator right=valueExpression            #comparison
    ;

valueExpression
    : primaryExpression
    ;

primaryExpression
    : qualifiedName
    | number
    | string
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

number
    : INTEGER_VALUE                                                            #integerLiteral
    | DOUBLE_VALUE                                                             #doubleLiteral
    ;

string
    : '\'' (~'\'')* '\''
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

INTEGER_VALUE
    : DIGIT+
    ;

DOUBLE_VALUE
    : DIGIT+ ('.' DIGIT+)?
    ;

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [A-Z]
    ;