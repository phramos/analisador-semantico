import enums.LiteralType;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by fauno on 23/02/17.
 */
public class CustomListener extends CMinusBaseListener{

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
        System.out.println("Entrando no escopo: "+ escopoAtual);
    }

    @Override
    public void exitMethodDeclaration(CMinusParser.MethodDeclarationContext ctx) {
        System.out.println("Saindo do escopo: "+ escopos.pop());
        escopoAtual = escopos.pop();
        System.out.println("Voltando para o escopo: "+ escopoAtual);
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
            System.out.println("Item adicionado:" + item);
        } else {
            //ERRO:
            String msg = "ERRO: "
                    + ctx.formalParameterDeclaratorId().getText()
                    + " já declarado no escopo."
                    + "L" + ctx.formalParameterDeclaratorId().getStart().getLine()
                    + "C" + ctx.formalParameterDeclaratorId().getStart().getCharPositionInLine();
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
            System.out.println("Item adicionado:" + item);
        } else {
            //ERRO:
            String msg = "ERRO: "
                    + ctx.variableDeclarators().variableDeclarator().variableDeclaratorId().getText()
                    + " já declarado no escopo."
                    + "L" + ctx.variableDeclarators().variableDeclarator().variableDeclaratorId().getStart().getLine()
                    + "C" + ctx.variableDeclarators().variableDeclarator().variableDeclaratorId().getStart().getCharPositionInLine();
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
        System.out.println("Expressao encontrada: " + ctx.getText());
        CMinusParser.ExpressionContext expressionLeftContext = ctx.expression().get(0);
        CMinusParser.ExpressionContext expressionRightContext = ctx.expression().get(1);
//        verificarCompatibilidade(expressionLeftContext, expressionRightContext);
        verificarCompatibilidade(expressionLeftContext, expressionRightContext);
    }

    @Override
    public void enterSumMinusExpressionLabel(CMinusParser.SumMinusExpressionLabelContext ctx) {
        System.out.println("Expressao de soma encontrada: " + ctx.getText());
        CMinusParser.ExpressionContext expressionLeftContext = ctx.expression().get(0);
        CMinusParser.ExpressionContext expressionRightContext = ctx.expression().get(1);
        verificarCompatibilidade(expressionLeftContext, expressionRightContext);
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
    private void verificarCompatibilidade(CMinusParser.ExpressionContext expressionACtx,
                                          CMinusParser.ExpressionContext expressionBCtx) {
        ItemTabelaSimbolo itemTsA = null;
        ItemTabelaSimbolo itemTsB = null;

        String expressionAType = null;
        String expressionBType = null;

        LiteralType literalTypeA = LiteralType.valueOf(vocabulary.getSymbolicName(expressionACtx.getStart().getType()));
        LiteralType literalTypeB = LiteralType.valueOf(vocabulary.getSymbolicName(expressionBCtx.getStart().getType()));
//        LiteralType literalTypeB = LiteralType.valueOf(vocabulary.getSymbolicName(expressionBCtx.getStart().getType()));

        //TODO: Tentar mdularizar isso
        //Verifica se expressionACtx eh um identifier
        if (literalTypeA == LiteralType.Identifier) {
            ItemTabelaSimbolo itemTbA = buscarNaTabelaDeSimbolos(expressionACtx.getText());
            //ExpressioA eh um identificador e nao esta na tabela de simbolos
            if (itemTbA == null) {
                System.out.println("ERRO: Identificador "
                        +expressionACtx.getText()
                        + " não declarado em "
                        + getTextPosition(expressionACtx.getStart()));

            } else {
                expressionAType = itemTbA.getTipo();
            }

        } else {
            //Caso a expressionA seja um literal
            expressionAType = literalTypeA.type();
        }

        //Verifica se expressionBCtx eh um identifier
        if (literalTypeB == LiteralType.Identifier) {
            ItemTabelaSimbolo itemTbB = buscarNaTabelaDeSimbolos(expressionBCtx.getStart().getText());
            //ExpressioB eh um identificador e nao esta na tabela de simbolos
            if (itemTbB == null) {
                System.out.println("ERRO: Identificador "
                        + expressionBCtx.getText()
                        + " não declarado em "
                        + getTextPosition(expressionBCtx.getStart()));

            } else {
                expressionBType = itemTbB.getTipo();
            }

        }  else {
            //Caso a expressionA seja um literal
            expressionBType = literalTypeB.type();
        }

        //TODO: verificar se essa expressao esta correta
        if (expressionAType!= null && expressionBType!= null && !expressionAType.equals(expressionBType)) {
            System.out.println("ERRO: Tipo encontrado em " + expressionBCtx.getText()
                    + " não compativel com " +expressionACtx.getText()
                    +". Esperado " + expressionAType
                    + " mas foi encontrado " + expressionBType
                    + " em " + getTextPosition(expressionBCtx.getStart()));
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
