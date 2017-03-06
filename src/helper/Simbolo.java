package helper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fauno on 21/10/16.
 * Model criada para armazenar um lexema e a entrda desse item na tabela de simbolos
 * conforme foi aprendido na disciplina de compiladores na Universidade Federal de Lavras
 * no semestre de 2016/2 com o Professor Rafael Durelli
 *
 * Obs.: A estrutura nao foi feita de maneira otimizada, porem aparentemente esta ok para a
 * linguagem alguma que que foi passada durantes as aulas praticas
 */
public class Simbolo {

    private Integer entrada;

    private String lexema ;

    private static final AtomicInteger count = new AtomicInteger(0);

    public Simbolo(String lexema) {
        this.lexema = lexema;
        this.entrada = count.incrementAndGet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Simbolo simbolo = (Simbolo) o;

        return lexema != null ? lexema.equals(simbolo.lexema) : simbolo.lexema == null;

    }

    @Override
    public int hashCode() {
        return lexema != null ? lexema.hashCode() : 0;
    }

    public Integer getEntrada() {
        return entrada;
    }

    public String getLexema() {
        return lexema;
    }

    @Override
    public String toString() {
        return "<" + this.getEntrada() + ", " + this.getLexema()+">";
    }
}
