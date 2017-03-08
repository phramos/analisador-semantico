import enums.LiteralType;
import enums.OperatorType;
import helper.ThreeCodeAddresHelper;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by fauno on 23/02/17.
 */
public class CustomListener extends CMinusBaseListener{

    private final Boolean DEBUG = false;
    private final Boolean SHOW_3AC = true;
    private CMinusParser parser;
    private ArrayList<ItemTabelaSimbolo> tabelaSimbolos;

    private Stack<String> escopos;
    //TODO: Melhorar o controle de escopo... escopo de if e escopo de else
    Vocabulary vocabulary = CMinusLexer.VOCABULARY;
    private String escopoAtual;

    private Integer labelCount = 0;
    private Stack<ItemIfElse> ifs;
    private Stack<ItemWhile> whiles;

    public CustomListener(CMinusParser parser) {
        this.parser = parser;
        tabelaSimbolos = new ArrayList<ItemTabelaSimbolo>();
        escopos = new Stack<String>();
        ifs = new Stack<ItemIfElse>();
        whiles = new Stack<ItemWhile>();
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

            //Verificacao de inicializacao logo apos declaracao de variavel
            if (ctx.variableDeclarators().variableDeclarator().variableInitializer() != null ) {
                Token tokenA = ctx.variableDeclarators().variableDeclarator().variableDeclaratorId().getStart();
                Token tokenB = ctx.variableDeclarators().variableDeclarator().variableInitializer().getStart();
                if (DEBUG) System.out.println("DEBUG: Possui inicializador com valor "
                        + ctx.variableDeclarators().variableDeclarator().variableInitializer().getText());
                verificarCompatibilidade(tokenA, tokenB, OperatorType.Attribuition);

            }


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
//        verificarCompatibilidade(tokenA, tokenB);

        //TODO: colocar 3DC para declaracao de variavel ja inicializada
        String[] tacExpression = ThreeCodeAddresHelper.process(ctx.expression(1).getText(), tokenA.getText());
        if (SHOW_3AC) ThreeCodeAddresHelper.print3ac(tacExpression);

        //TODO: ver como exibir a quadrupla
//        ThreeCodeAddresHelper.printQuadrupla(tacExpression);
        verificarCompatibilidade(tokenA, tokenB, OperatorType.Attribuition);
    }

    @Override
    public void enterSumMinusExpressionLabel(CMinusParser.SumMinusExpressionLabelContext ctx) {
        if (DEBUG) System.out.println("DEBUG: Expressao de +- encontrada: " + ctx.getText());
//        CMinusParser.ExpressionContext expressionLeftContext = ctx.expression().get(0);
//        CMinusParser.ExpressionContext expressionRightContext = ctx.expression().get(1);
        Token tokenA = ctx.expression(0).getStop();
        Token tokenB = ctx.expression(1).getStart();
//        verificarCompatibilidade(tokenA, tokenB);
        verificarCompatibilidade(tokenA, tokenB, OperatorType.SumDiff);
    }

    @Override
    public void enterMultDivExpressionLabel(CMinusParser.MultDivExpressionLabelContext ctx) {
        if (DEBUG) System.out.println("DEBUG: Expressao de */ encontrada: " + ctx.getText());
        Token tokenA = ctx.expression(0).getStop();
        Token tokenB = ctx.expression(1).getStart();
//        verificarCompatibilidade(tokenA, tokenB);
        verificarCompatibilidade(tokenA, tokenB, OperatorType.MultDiv);
    }

    @Override
    public void enterIfStatementLabel(CMinusParser.IfStatementLabelContext ctx) {
        String labelIf = "L"+labelCount+":";
        String labelElse = "L"+labelCount+":";

        if (DEBUG) System.out.println("DEBUG: Inicio if " + labelIf);

        if (SHOW_3AC) System.out.println("ifZ " + ctx.parExpression().getText() + " goto "+ labelElse);

        //verifica se tera um else para decidir onde colocar o label
        Boolean observeElse = false;
        if (ctx.elseStatement() != null) {
            observeElse = true;
        }

        ItemIfElse itemIfElse = new ItemIfElse(labelIf, labelElse, observeElse);
        ifs.push(itemIfElse);
        labelCount++;
    }

    @Override
    public void enterElseStatement(CMinusParser.ElseStatementContext ctx) {
        ItemIfElse itemIfElse = ifs.peek();
        if (itemIfElse.getObserveElse()) {
            if (SHOW_3AC)  System.out.println(itemIfElse.getLabelIf());
        }
    }

    @Override
    public void exitIfStatementLabel(CMinusParser.IfStatementLabelContext ctx) {
        ItemIfElse itemIfElse = ifs.pop();
        if (!itemIfElse.getObserveElse()) {
            if (SHOW_3AC) System.out.println(itemIfElse.getLabelIf());
        }
        if (DEBUG) System.out.println("DEBUG: Fim if " + itemIfElse.getLabelIf());
    }

    @Override
    public void enterWhileStatementLabel(CMinusParser.WhileStatementLabelContext ctx) {
        String labelBack = "L"+labelCount+":";
        labelCount++;
        String labelAfter = "L"+labelCount+":";

        ItemWhile itemWhile = new ItemWhile(labelBack, labelAfter);
        whiles.push(itemWhile);
        if (SHOW_3AC) System.out.println(itemWhile.getLabelBack());
        if (SHOW_3AC) System.out.println("ifZ " + ctx.parExpression().getText() + " goto " + itemWhile.getLabelAfter());
        labelCount++;

    }

    @Override
    public void exitWhileStatementLabel(CMinusParser.WhileStatementLabelContext ctx) {
        ItemWhile itemWhile = whiles.pop();
        if (SHOW_3AC) System.out.println("goto " +  itemWhile.getLabelBack());
        if (SHOW_3AC) System.out.println(itemWhile.getLabelAfter());
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
                                          Token tokenB,
                                          OperatorType operatorType) {
        if (DEBUG) System.out.println("DEBUG: Tokens testados:" + tokenA.getText() + " & " + tokenB.getText());
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
                        + tokenA.getText()
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

        } else {
            //Caso a expressionA seja um literal
            expressionBType = literalTypeB.type();
        }

        //TODO: separar verificacao de identificador e verificacao de compatibilidade
        //Ja foi verificado se as expressoes eram identificadores e estavam presentes nas tabelas de simbolos
        //Os codigos daqui para baixo verificam imcompatibilidade de tipos
        if (expressionAType != null && expressionBType != null ) {
            //Verifcacao de erros caso algum dos tokens seja um char e a operacao eh diferente de atribuicao
            if (expressionAType.equals(LiteralType.CharacterLiteral.type())
                    || expressionBType.equals(LiteralType.CharacterLiteral.type())) {

                if (!operatorType.equals(OperatorType.Attribuition)) {
                    System.out.println("ERRO: Operação inválida sobre o tipo char."
                            + "Tipo char permite apenas a opeção de atribuição(=), "
                            + "mas foi econtrada uma operação ("
                            + operatorType.type()
                            + ") em " + getTextPosition(tokenB));
                }

            }

            if (!expressionAType.equals(expressionBType)) {

                //Testa casting de algum tipo com char
                if ((expressionAType.equals(LiteralType.CharacterLiteral.type())
                        && !expressionBType.equals(LiteralType.CharacterLiteral.type()))
                        || (expressionBType.equals(LiteralType.CharacterLiteral.type())
                        && !expressionAType.equals(LiteralType.CharacterLiteral.type()))) {

                    System.out.println("ERRO: Conversão entre tipos de "
                            + tokenA.getText() + " e " + tokenB.getText()
                            + ". Esperado " + expressionAType
                            + " mas foi encontrado " + expressionBType
                            + " em " + getTextPosition(tokenB));
                } else {
                    System.out.println("ERRO: Tipo encontrado em " + tokenB.getText()
                            + " não compativel com " + tokenA.getText()
                            + ". Esperado " + expressionAType
                            + " mas foi encontrado " + expressionBType
                            + " em " + getTextPosition(tokenB));
                }

            }
        }
    }

    private String getTextPosition(Token token) {
        return "L" + token.getLine()
                + "C" + token.getCharPositionInLine();
    }
}
