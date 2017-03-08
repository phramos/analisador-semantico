package enums;

/**
 * Created by fauno on 06/03/17.
 */
public enum OperatorType {
    SumDiff("+ | -"),
    MultDiv("+ | /"),
    Attribuition("="),
    Relational("== | != | < | <= | > | >=");

    OperatorType(String type) {
        this.type = type;
    }

    private String type;

    public String type() {
        return type;
    }
}
