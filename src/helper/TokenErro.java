package helper;

/**
 * Created by fauno on 23/10/16.
 */
public class TokenErro extends Token {

    String valor;

    public TokenErro(String tipo, String valor, Integer linha, Integer coluna) {

        //Cnstroi um Token  com o valor da entra de simbolos igual a -1
        super(tipo, valor, linha, coluna);
        this.valor = valor;

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ERRO: ");
        builder.append(this.getTipo());
        builder.append(" '" + this.valor+ "'");
        builder.append(". ");
        builder.append("Erro encontrado na linha ");
        builder.append(this.getLinha());
        builder.append(" coluna ");
        builder.append(this.getColuna());
        return builder.toString();
    }
}
