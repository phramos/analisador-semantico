grammar  CMinus;

//TODO: Tirar minusculo de identifier

// Unidade de compilacao usada com ponto de entrada do parser
compilationUnit
    :   (localVariableDeclarationStatement | methodDeclaration | structDeclarationStatement)* EOF
    ;

//Declaracao de metodo. Pode ser um metodo que retorna um tipo ou um void
methodDeclaration
    :   (typeType|'void') Identifier formalParameters ('[' ']')?
        (   methodBody |   ';')
    ;

//Declarador de variavel. eg.: int a = 666;
variableDeclarators
    :   variableDeclarator
    ;

//Uma um identificador, contentado ou nao uma quantidade de [], podendo conter ou nao uma inicializacao
variableDeclarator
    :   variableDeclaratorId ('['IntegerLiteral']')* ('=' variableInitializer)?
    ;

//Identificador
variableDeclaratorId
    :   Identifier
    ;

//inicializador de variavel eh uma expressao
variableInitializer
    :   expression
    ;

//tipo eh um tipo primitivo
typeType
    :   primitiveType
    ;

//tipos primiticos: int, float, char
primitiveType
    :   'int'
    |   'float'
    |   'char'
    ;

//parametros de funcao: aceita apenas () ou uma lista de parametros (int param1, int param2)
formalParameters
    :   '(' formalParameterList? ')'
    ;

//parametros formais separados por virgula: int param1, char param2
formalParameterList
    :   formalParameter (',' formalParameter)*
    ;

//Apenas outro alias para identificador
formalParameterDeclaratorId
    :   Identifier
    ;

//declaracao formal de um id de parametro: identificador contendo ou nao []
formalParameter
    :   typeType formalParameterDeclaratorId ('[' ']')*
    ;

//corpo de metodo eh composto por um body
methodBody
    :   block
    ;

//literais: valores para int, float, e char
literal
    :   IntegerLiteral
    |   FloatingPointLiteral
    |   CharacterLiteral
    ;

//Struct simples criada para brincar um pouco
structDeclaration
    :   'struct' Identifier '{' (structVaribleDeclaration)+ '}'
    ;

structVaribleDeclaration
    :   primitiveType Identifier ('=' structVariableInitializer)? ';'
    ;

structVariableInitializer
    :   literal
    ;

structDeclarationStatement
    :   structDeclaration ';'
    ;

// bloco pode conter 00 ou mais statements de bloco dentro de chaves {}
block
    :   '{' blockStatement* '}'
    ;

//block statement pode ser uma declaracao de variave, um struct, ou um statement simples
blockStatement
    :   localVariableDeclarationStatement
    |   structDeclarationStatement
    |   statement
    ;

//uma declaracao de variavel com ; no final
localVariableDeclarationStatement
    :    localVariableDeclaration ';'
    ;

localVariableDeclaration
    :   typeType variableDeclarators
    ;

//statement pode ser um novo bloco, um statement de if, de while, retorno, ;, ou uma statmentExpression em si
statement
    :   block #blockStatementLabel
    |   'if' parExpression statement ('else' statement)? #ifStatementLabel
    |   'while' parExpression statement #whileStatementLabel
    |   'return' expression? ';' #returnStatementLabel
    |   ';' #pvStatementLabel
    |   statementExpression ';' #statementExpressionLabel
    ;


/*EXPRESSOES*/

//expressao entre parenteses
parExpression
    :   '(' expression ')'
    ;

//expressao ou lista de expressoes separadas por virgula
expressionList
    :   expression (',' expression)*
    ;

statementExpression
    :   expression
    ;

expression
    :   primary #primExpressionLabel//Alternativa necessaria para regras com recursividade a esquerda
    |   expression ('*'|'/') expression #multDivExpressionLabel
    |   expression ('+'|'-') expression #sumDiffExpressionLabel
    |   expression ('<=' | '>=' | '>' | '<') expression #relExpressionLabel
    |   expression ('==' | '!=') expression #relEqDiffExpressionLabel
    |   <assoc=right> expression '=' expression #attrExpressionLabel
    ;

primary
    :   '(' expression ')'
    |   literal
    |   Identifier
    ;

////argumentos de uma funcao. Obs: Nao utilizados na linguagem CMinus
//arguments
//    :   '(' expressionList? ')'
//    ;

////Testes para exibicao de erros
//localVariableDeclarationStatementErro
//    :    localVariableDeclaration ~(';') {System.out.println("Faltou ponto e virgula");}
//    ;

// LEXER
//Palavras reservadas da linguagem
INT     : 'int';
FLOAT   : 'float';
CHAR   : 'char';
STRUCT  : 'struct';
IF      : 'if';
ELSE    : 'else';
WHILE   : 'while';
VOID    : 'void';
RETURN  : 'return';


/*Literal de Inteiros*/
IntegerLiteral
    :   DecimalIntegerLiteral
    ;

fragment
DecimalIntegerLiteral
    :   DecimalNumeral+
    ;

fragment
DecimalNumeral
    :   '0'
    |   NonZeroDigit
    ;

fragment
Digits
    :   Digit+
    ;

fragment
Digit
    :   '0'
    |   NonZeroDigit
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;

/*Literal de ponto flutuante*/

FloatingPointLiteral
    :   DecimalFloatingPointLiteral
    ;

fragment
DecimalFloatingPointLiteral
    :   Digits '.' Digits? ExponentPart?
    |   Digits ExponentPart
    |   Digits
    ;

fragment
ExponentPart
    :   ExponentIndicator SignedInteger
    ;

fragment
ExponentIndicator
    :   [E]
    ;

fragment
SignedInteger
    :   Sign? Digits
    ;

fragment
Sign
    :   [+-]
    ;

//Caracter: um unico valor dentro entre aspas simples 'c'
CharacterLiteral
    :   '\'' SingleCharacter '\''
    ;

fragment
SingleCharacter
    :   ~['\\]
    ;

//Strings: Definidos porem nao utilizados
StringLiteral
    :   '"' StringCharacters? '"'
    ;

fragment
StringCharacters
    :   StringCharacter+
    ;

fragment
StringCharacter
    :   ~["\\]
    ;

//Separadores
LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
LBRACK          : '[';
RBRACK          : ']';
SEMI            : ';';

//Operadores
ASSIGN          : '=';
LT              : '<';
EQUAL           : '==';
LE              : '<=';
GE              : '>=';
NOTEQUAL        : '!=';
ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';

//Identificador eh qualquer cadeida de caracteres comecando com uma letra e podendo ter numeros depois desde que nao
//seja equivalente a alguma palavra reservada
Identifier
    :   Letter (Letter | Digit)*
    ;

fragment
Letter
    :   [a-z] // letras de a-z. Obs.: Apenas minusculas
    ;


//
// Whitespace and comments
//

WS  :  [ \t\r\n\u000C]+ -> channel(HIDDEN)
    ;

COMMENT
    :   '/*' .*? '*/' -> channel(HIDDEN)
    ;
//
//COMENTARIO
//    :    '/*' .*? '*/' {skip();}
//    ;
//WS  :   ( ' '
//        | '\t'
//        | '\r'
//        | '\n'
//        ) {skip();}
