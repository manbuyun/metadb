grammar SqlBase;

singleStatement
    : statement EOF
    ;

statement
    : query
    ;

query
    : SELECT selectItem (',' selectItem)*
    ;

selectItem
    : expression (AS? identifier)?
    ;

expression
    : primaryExpression                                                                  #expressionDefault
    | left=expression operator=(ASTERISK | SLASH) right=expression                       #arithmeticBinary
    | left=expression operator=(PLUS | MINUS) right=expression                           #arithmeticBinary
    ;

primaryExpression
    : qualifiedName
    | number
    | string
    ;

qualifiedName
    : identifier ('.' identifier)*
    ;

identifier
    : IDENTIFIER
    ;

number
    : INTEGER                                                                            #integerLiteral
    | DOUBLE                                                                             #doubleLiteral
    ;

string
    : STRING
    ;

SELECT: 'SELECT';
AS: 'AS';

PLUS: '+';
MINUS: '-';
ASTERISK: '*';
SLASH: '/';

IDENTIFIER
    : (LETTER | '_') (LETTER | DIGIT | '_')*
    ;

INTEGER
    : DIGIT+
    ;

DOUBLE
    : DIGIT+ ('.' DIGIT+)?
    ;

STRING
    : '\'' (~'\'')* '\''
    ;

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [A-Z]
    ;