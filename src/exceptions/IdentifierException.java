package exceptions;

/**
 * Created by fauno on 05/03/17.
 */
public class IdentifierException extends RuntimeException{

    //Parameterless Constructor
    public IdentifierException() {}

    //Constructor that accepts a message
    public IdentifierException(String message)
    {
        super(message);
    }
}
