package enums;

/**
 * Created by fauno on 06/03/17.
 */
public enum LiteralType {
    IntegerLiteral("int"),
    FloatingPointLiteral("float"),
    CharacterLiteral("char"),
    Identifier("identifier");

    LiteralType(String type) {
        this.type = type;
    }

    private String type;

    public String type() {
        return type;
    }
}
