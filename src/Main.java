import helper.LexerOnSteroids;
import helper.ThreeCodeAddresHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by rafaeldurelli on 9/15/16.
 * Modified by Pedro Henrique Ramos Souza
 */
public class Main {

    public static void main (String... args) throws IOException {

        String fileName = "arquivos/teste_simples.c";
//        String fileName = "arquivos/teste_correto.c";
//        String fileName = "arquivos/teste_errado_colchete_main.c";
//        String fileName = "arquivos/teste_errado_parentese_if.c";
//        String fileName = "arquivos/teste_errado_ponto_e_virgula.c";
//        String fileName = "arquivos/teste_errado_tipo_sem_identificador1.c";
//        String fileName = "arquivos/teste_errado_tipo_sem_identificador2.c";
//        String fileName = "arquivos/teste_errado_identificador_identificador.c";
//        String fileName = "arquivos/teste_errado_argumentos1.c";
//        String fileName = "arquivos/teste_errado_argumentos2.c";
//        String fileName = "arquivos/teste_errado_struct1.c";
//        String fileName = "arquivos/teste_errado_struct2.c";

        File file = new File(fileName);

        FileInputStream fis = null;

        fis = new FileInputStream(file);

        ANTLRInputStream input = new ANTLRInputStream(fis);


//        CMinusLexer lexer = new CMinusLexer(input);

//       LexerOnSteroids les = new LexerOnSteroids(lexer);

//        les.print();


        CMinusLexer scanner = new CMinusLexer(input);

        CommonTokenStream allTokensGenerated = new CommonTokenStream(scanner);

        CMinusParser parser = new CMinusParser(allTokensGenerated);

        CMinusParser.CompilationUnitContext compilationUnitContext = parser.compilationUnit();

        ParseTreeWalker treeWalker = new ParseTreeWalker();

        CustomListener listener = new CustomListener(parser);


        treeWalker.walk(listener, compilationUnitContext);

//        System.out.println("Modificado -->" +listener.tokenStreamRewriter.getText());

//        ThreeCodeAddresHelper.process("2*66.0+b-c*5/12.0+belu");
//        ThreeCodeAddresHelper.process("66.0*b-c*5/12+belu");
        ThreeCodeAddresHelper.process("15");

    }

}
