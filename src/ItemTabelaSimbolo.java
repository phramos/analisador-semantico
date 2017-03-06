/**
 * Created by fauno on 05/03/17.
 */
public class ItemTabelaSimbolo {
    private String tipo;
    private String identificador;
    private String escopo;

    public ItemTabelaSimbolo(String tipo, String identificador, String escopo) {
        this.tipo = tipo;
        this.identificador = identificador;
        this.escopo = escopo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    @Override
    public String toString() {
        return ("<" + tipo + ", " + identificador + ", " + escopo + ">");
    }
}
