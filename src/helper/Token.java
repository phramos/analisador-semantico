package helper;

/**
 * Created by fauno on 21/10/16.
 * Model criada para armazenar o tipo do token e a entrda desse item na tabela de simbolos
 * conforme foi aprendido na disciplina de compiladores na Universidade Federal de Lavras
 * no semestre de 2016/2 com o Professor Rafael Durelli
 *
 * Obs.: A estrutura nao foi feita de maneira otimizada, porem aparentemente esta ok para a
 * linguagem alguma que que foi passada durantes as aulas praticas
 */


public class Token {

    protected String tipo;
    protected String lexema;
    protected Integer linha;
    protected Integer coluna;

//    public Token(String tipo, Integer entrada, Integer linha, Integer coluna) {
//        this.tipo = tipo;
//        this.entrada = entrada;
//        this.linha = linha;
//        this.coluna = coluna;
//    }

    public Token(String tipo, String lexema, Integer linha, Integer coluna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.linha = linha;
        this.coluna = coluna;
    }



    public String getTipo() {
        return tipo;
    }

    public Integer getLinha() {
        return linha;
    }

    public Integer getColuna() {
        return coluna;
    }

    public String getLexema() {
        return lexema;
    }

    @Override
    public String toString() {
//        return "<" + this.tipo + ", "+this.entrada+">";
        return "<" + this.tipo + ">";
    }
}
