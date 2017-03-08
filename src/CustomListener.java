import enums.LiteralType;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by fauno on 23/02/17.
 */
public class CustomListener extends CMinusBaseListener{

    private final Boolean DEBUG = true;
    private CMinusParser parser;
    private ArrayList<ItemTabelaSimbolo> tabelaSimbolos;

    private Stack<String> escopos;
    //TODO: Melhorar o controle de escopo... escopo de if e escopo de else
    Vocabulary vocabulary = CMinusLexer.VOCABULARY;
    private String escopoAtual;

    public CustomListener(CMinusParser parser) {
        this.parser = parser;
        tabelaSimbolos = new ArrayList<ItemTabelaSimbolo>();
        escopos = new Stack<String>();
        //TODO: implementar verficacao de escopo global
        escopos.push("global");
        escopoAtual = "global";
    }


    @Override
    public void enterMethodDeclaration(CMinusParser.MethodDeclarationContext ctx) {
        escopoAtual = ctx.Identifier().getText();
        escopos.push(escopoAtual);
        ItemTabelaSimbolo item = new ItemTabelaSimbolo("function", ctx.Identifier().getText(), escopoAtual);
        tabelaSimbolos.add(item);
        if (DEBUG) System.out.println("DEBUG: Item adicionado:" + item);
        if (DEBUG) System.out.println("DEBUG: Entrando no escopo: "+ escopoAtual);
    }

    @Override
    public void exitMethodDeclaration(CMinusParser.MethodDeclarationContext ctx) {
        if (DEBUG) System.out.println("DEBUG: Saindo do escopo: "+ escopos.pop());
        escopoAtual = escopos.pop();
        if (DEBUG) System.out.println("DEBUG: Voltando para o escopo: "+ escopoAtual);
        escopos.push(escopoAtual);
        escopoAtual = ctx.Identifier().getText();
    }

    @Override
    public void enterFormalParameter(CMinusParser.FormalParameterContext ctx) {
        ItemTabelaSimbolo item = buscarNaTabelaDeSimbolos(ctx.formalParameterDeclaratorId().getText());

        if (item == null) {
            item = new ItemTabelaSimbolo(ctx.typeType().getText(),
                    ctx.formalParameterDeclaratorId().getText(),
                    escopoAtual);
            tabelaSimbolos.add(item);
            if (DEBUG)  System.out.println("DEBUG: Item adicionado:" + item);
        } else {
            //ERRO:
            String msg = "ERRO: "
                    + ctx.formalParameterDeclaratorId().getText()
                    + " já declarado no escopo "
                    + escopoAtual
                    +" em "
                    + getTextPosition(ctx.formalParameterDeclaratorId().getStart());
            //TODO: Decidir se usar print msg ou throw exception
//            throw new IdentifierException(msg);
            System.out.println(msg);
        }
    }

    @Override
    public void enterLocalVariableDeclaration(CMinusParser.LocalVariableDeclarationContext ctx) {
        ItemTabelaSimbolo item = buscarNaTabelaDeSimbolos(ctx.variableDeclarators()
                .variableDeclarator().variableDeclaratorId().getText());

        if (item == null) {
            item = new ItemTabelaSimbolo(ctx.typeType().getText(),
                    ctx.variableDeclarators().variableDeclarator().variableDeclaratorId().getText(),
                    escopoAtual);
            tabelaSimbolos.add(item);
            if (DEBUG) System.out.println("DEBUG: Item adicionado:" + item);
        } else {
            //ERRO:
            String msg = "ERRO: "
                    + ctx.variableDeclarators().variableDeclarator().variableDeclaratorId().getText()
                    + " já declarado no escopo "
                    + escopoAtual
                    +" em "
                    + getTextPosition(ctx.variableDeclarators().variableDeclarator().variableDeclaratorId().getStart());

            //TODO: Decidir se usar print msg ou throw exception
//            throw new IdentifierException(msg);
            System.out.println(msg);
        }
    }

//    @Override
//    public void enterVariableDeclarator(CMinusParser.VariableDeclaratorContext ctx) {
//        //TODO: verificar tipo da declaracao e atribuicao da variavel
//        System.out.println("Variable Declarator");
//    }


    //verifica os tipos nas atribuicoes a = x;
    @Override
    public void enterAttrExpressionLabel(CMinusParser.AttrExpressionLabelContext ctx) {
        if (DEBUG) System.out.println("DEBUG: Atribuição encontrada: " + ctx.getText());
//        CMinusParser.ExpressionContext expressionLeftContext = ctx.expression().get(0);
//        CMinusParser.ExpressionContext expressionRightContext = ctx.expression().get(1);
        Token tokenA = ctx.expression(0).getStop();
        Token tokenB = ctx.expression(1).getStart();
        verificarCompatibilidade(tokenA, tokenB);
//        verificarCompatibilidade(expressionLeftContext, expressionRightContext);
    }

    @Override
    public void enterSumMinusExpressionLabel(CMinusParser.SumMinusExpressionLabelContext ctx) {
        if (DEBUG) System.out.println("DEBUG: Expressao de +- encontrada: " + ctx.getText());
//        CMinusParser.ExpressionContext expressionLeftContext = ctx.expression().get(0);
//        CMinusParser.ExpressionContext expressionRightContext = ctx.expression().get(1);
        Token tokenA = ctx.expression(0).getStop();
        Token tokenB = ctx.expression(1).getStart();
        verificarCompatibilidade(tokenA, tokenB);
    }

    @Override
    public void enterMultDivExpressionLabel(CMinusParser.MultDivExpressionLabelContext ctx) {
        if (DEBUG) System.out.println("DEBUG: Expressao de */ encontrada: " + ctx.getText());
        Token tokenA = ctx.expression(0).getStop();
        Token tokenB = ctx.expression(1).getStart();
        verificarCompatibilidade(tokenA, tokenB);
    }

    private ItemTabelaSimbolo buscarNaTabelaDeSimbolos(String identificador) {
        for (ItemTabelaSimbolo item:
                tabelaSimbolos) {
            //TODO: verificar necessidade do escopo aqui;
            if (item.getIdentificador().equals(identificador)
                    && item.getEscopo().equals(escopoAtual)) {
                return item;
            }
        }
        return null;
    }

  //Acusa erro em compatibilidade de 2 identificadores. Ex. int = float
    private void verificarCompatibilidade(Token tokenA,
                                          Token tokenB) {
        if (DEBUG) System.out.println("DEBUG: Tokens testados:" + tokenA.getText()+ " & " + tokenB.getText());
        ItemTabelaSimbolo itemTsA = null;
        ItemTabelaSimbolo itemTsB = null;

        String expressionAType = null;
        String expressionBType = null;

        LiteralType literalTypeA = LiteralType.valueOf(vocabulary.getSymbolicName(tokenA.getType()));
        LiteralType literalTypeB = LiteralType.valueOf(vocabulary.getSymbolicName(tokenB.getType()));
//        LiteralType literalTypeB = LiteralType.valueOf(vocabulary.getSymbolicName(expressionBCtx.getStart().getType()));

        //TODO: Tentar mdularizar isso
        //Verifica se expressionACtx eh um identifier
        if (literalTypeA == LiteralType.Identifier) {
            ItemTabelaSimbolo itemTbA = buscarNaTabelaDeSimbolos(tokenA.getText());
            //ExpressioA eh um identificador e nao esta na tabela de simbolos
            if (itemTbA == null) {
                System.out.println("ERRO: Identificador "
                        +tokenA.getText()
                        + " não declarado em "
                        + getTextPosition(tokenB));

            } else {
                expressionAType = itemTbA.getTipo();
            }

        } else {
            //Caso a expressionA seja um literal
            expressionAType = literalTypeA.type();
        }

        //Verifica se expressionBCtx eh um identifier
        if (literalTypeB == LiteralType.Identifier) {
            ItemTabelaSimbolo itemTbB = buscarNaTabelaDeSimbolos(tokenB.getText());
            //ExpressioB eh um identificador e nao esta na tabela de simbolos
            if (itemTbB == null) {
                System.out.println("ERRO: Identificador "
                        + tokenB.getText()
                        + " não declarado em "
                        + getTextPosition(tokenB));

            } else {
                expressionBType = itemTbB.getTipo();
            }

        }  else {
            //Caso a expressionA seja um literal
            expressionBType = literalTypeB.type();
        }

        //TODO: verificar se essa expressao esta correta
        if (expressionAType!= null && expressionBType!= null && !expressionAType.equals(expressionBType)) {

            //Testa casting de algum tipo com char
            if ( ( expressionAType.equals(LiteralType.CharacterLiteral.type())
                    && !expressionBType.equals(LiteralType.CharacterLiteral.type()) )
                    || ( expressionBType.equals(LiteralType.CharacterLiteral.type())
                    && !expressionAType.equals(LiteralType.CharacterLiteral.type()) ) ) {

                System.out.println("ERRO: Conversão entre tipos de "
                        + tokenA.getText() + " e " + tokenB.getText()
                        +". Esperado " + expressionAType
                        + " mas foi encontrado " + expressionBType
                        + " em " + getTextPosition(tokenB));
            } else {
                System.out.println("ERRO: Tipo encontrado em " + tokenB.getText()
                        + " não compativel com " +tokenA.getText()
                        +". Esperado " + expressionAType
                        + " mas foi encontrado " + expressionBType
                        + " em " + getTextPosition(tokenB));
            }

        }

    }

    private String getTextPosition(Token token) {
        return "L" + token.getLine()
                + "C" + token.getCharPositionInLine();
    }
//
//    private void verificarCompatibilidade(String identA, LiteralType IdentB) {
//
//    }
}
