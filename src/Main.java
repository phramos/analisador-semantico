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

//        String fileName = "arquivos/teste_erros_semanticos.c";
        String fileName = "arquivos/teste_3ac.c";

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


    }

}
