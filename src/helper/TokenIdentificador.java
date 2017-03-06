package helper;

import helper.Token;

/**
 * Created by fauno on 24/10/16.
 */
public class TokenIdentificador extends Token{

    protected Integer entrada;


    public TokenIdentificador(String tipo, String lexema,  Integer entrada, Integer linha, Integer coluna) {
        super(tipo, lexema, linha, coluna);
        this.entrada = entrada;
    }

    public Integer getEntrada() {
        return entrada;
    }

    public void setEntrada(Integer entrada) {
        this.entrada = entrada;
    }

    @Override
    public String toString() {
        return "<" + this.tipo + ", "+this.entrada+">";
    }
}
