package helper;

import helper.listener.ThrowingErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fauno on 21/10/16.
 * Wrapper criado para ajudar na exibição de informações sobre a gramática
 */
public class LexerOnSteroids {

    public Lexer lexer;

    //A entrada na tabela é um Integer, e o Lexema é uma String
    public List<Simbolo> tabelaDeSimbolos;

    //String é o token, e o Integer é a entrada na tabemaDeSimbolos
    public List<helper.Token> tokens;

    //String é o token, e o Integer é a entrada na tabemaDeSimbolos
    public List<helper.TokenErro> erros;

    public LexerOnSteroids(Lexer lexer) {
        //lexer provido pelo antlr4
        this.lexer = lexer;

        //lista para
        this.tabelaDeSimbolos = new ArrayList<>();

        //tokens encontrados no lexer
        this.tokens = new ArrayList<>();

        //tokens com o prefixo ERRO_ encontrados no lerxer
        this.erros = new ArrayList<>();

        //Deixa as mensagens de erro mais específicas
        this.lexer.removeErrorListeners();
        this.lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        //função responsável realizar a computação dos tokens do lexer
        this.computarTokens();
    }

    public void computarTokens() {

        System.out.println("------------Computando Tokens------------");
        Token t = null;
        //Obtem o vocabulario que possui uma refencia de nome para cara tipo de token
        Vocabulary v = this.lexer.getVocabulary();

        while ((t = this.lexer.nextToken()).getType() != Token.EOF) {

            //separa todos os Tokens que comecem com ERRO_ dos demais
            if (v.getSymbolicName(t.getType()).contains("ERRO_")){
                //System.out.println(t.getText() + v.getSymbolicName(t.getType()));
                this.erros.add(new TokenErro(v.getSymbolicName(t.getType()), t.getText(),
                        t.getLine(), (t.getCharPositionInLine() + 1)));
            }else {
                //Separa os Tokens identificadores para pode criar uma referência para a tabela
                //de símbolos
                if (v.getSymbolicName(t.getType()).equals("Identifier")){

                    //Verifica a existencia do lexema na tabela de simbolos
                    Integer entrada = findSimboloPorLexema(t.getText());

                    //caso não exista na tabela de simbolos ele insere na tabela de simbolos e obter a enntrada na tabela
                    if (entrada < 0) {
                        Simbolo s = new Simbolo(t.getText());
                        entrada = this.adicionarSimbolo(s);
                    }

                    this.tokens.add(new helper.TokenIdentificador(v.getSymbolicName(t.getType()),
                            t.getText(), entrada, t.getLine(), (t.getCharPositionInLine() + 1)));
                } else {
                    //Executa esse bloco caso o Token não seja nem de erro nem de identificação
                    this.tokens.add(new helper.Token(v.getSymbolicName(t.getType()), t.getText(),
                            t.getLine(), (t.getCharPositionInLine() + 1)));
                }
            }
        }
        System.out.println("------------------------------------------------------");
        System.out.print("\n\n\n");

    }

    /**
     *Função que retorna a entrada do lexema na tabela de simbolos ou retorna -1 caso o lexema não exista na tabela de simbolos
     * @param lexema lexema a ser verificado na tabela de simbolos
     * @return a entrada na tabela de simbolos para o lexema ou -1 caso o lexema não exista ta tabela
     */
    private Integer findSimboloPorLexema(String lexema) {

        Integer entrada = -1;
        for (Simbolo s: tabelaDeSimbolos) {

            //busca pelo lexema na tabela de simbolos
            if (s.getLexema().equals(lexema)){
                return s.getEntrada();
            }

        }

        return entrada;

    }

    /**
     * Adiciona um simbolo à tabela de simbolos e retorna a sua entrada
     * @param simbolo simbolo a ser adiciono na tabela
     * @return entrada do simbolo na tabela
     */
    private Integer adicionarSimbolo(Simbolo simbolo) {
        this.tabelaDeSimbolos.add(simbolo);
        return simbolo.getEntrada();
    }

    public void printTokens(){

        System.out.println("\n\n\n------------Tokens------------");
        System.out.println("<tipo, entradaNaTabelaDeSimbolos>");
        for (helper.Token t: this.tokens
                ) {
            System.out.println(t);
        }
    }

    public void printSimbolos(){

        System.out.println("\n\n\n------------Tabela de Simbolos------------");
        System.out.println("<entradaNaTabelaDeSimbolos, lexema>");
        for (Simbolo s: this.tabelaDeSimbolos
                ) {
            System.out.println(s);
        }
    }
    public void printErros(){

        System.out.println("\n\n\n------------Erros Encontrados------------");
//        System.out.println("<entradaNaTabelaDeSimbolos, valor>");
        for (TokenErro te: this.erros
                ) {
            System.out.println(te);
        }
    }

    public void testarLexema(){

        System.out.println("\n\n\n------------Testando Lexemas------------");
        System.out.println("\n<linha, coluna, lexema, tipo>");


        for (helper.Token t:
                this.tokens) {
                System.out.println(getTokenInfoAndLexema(t));
        }
    }

    /**
     *Funcao que recebe um token e retorna uma string no seguinte formato: <linha, coluna, lexema, tipo>
     * @param token token a ser encontrado
     * @return string no seguinte formato: <linha, coluna, lexema, tipo>
     */
    public String getTokenInfoAndLexema(helper.Token token) {
        String tipo = token.getTipo();
        String info;

        if(token instanceof TokenIdentificador){

            Simbolo s = this.findSimboloPorEntrada(((TokenIdentificador) token).getEntrada());
            return "<" + token.getLinha() +", "+ token.getColuna() +", "+ s.getLexema() +", "+ token.getTipo()+">";
        }else {
            return "<" + token.getLinha() +", "+ token.getColuna() +", "+ token.getLexema() +", "+ token.getTipo()+">";
        }

    }

    private Simbolo findSimboloPorEntrada(Integer entrada) {
        for (Simbolo s: tabelaDeSimbolos) {

            //busca pelo lexema na tabela de simbolos
            if (s.getEntrada().equals(entrada)){
                return s;
            }

        }
        return null;
    }

    public void print(){
        printErros();
        printTokens();
        printSimbolos();
        testarLexema();
    }




}
