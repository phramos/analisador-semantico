/**
 * Classe baseada na solução proporta por http://stackoverflow.com/users/1796523/mouagip
 * na página http://stackoverflow.com/questions/18132078/handling-errors-in-antlr4
 * e modificada por Pedro Souza a.k.a Fauno
 *
 * Obs.: Pura gambiarra minha, mas ja dizia meu caro amigo Matheus nos tempos de LEDUC: "Se funciona não é gambiarra"
 */

package helper.listener;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class ThrowingErrorListener extends BaseErrorListener {

    public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
            throws ParseCancellationException {

        //Cria uma mensagem customizada para exibir a localizacao e o
        String customExceptionMsg = "\n---------------ATENÇÃO---------------" +
                "\nErro Encontrado" +
                "\nMotivo:" + msg +
                "\nLinha:"+ line +
                //Fator de ajuste de +1 para facilitar encontrar o erro com o Intellij(ctrl+g r:c vai exatamente no erro)
                "\nColuna:" + (charPositionInLine+1) +
                "\n-------------------------------------\n";
        //Apenas imprime a mensagem, porem continua o fluxo exibindo todas as tokens
        System.out.println(customExceptionMsg);
//        throw new ParseCancellationException(customExceptionMsg);
    }
}