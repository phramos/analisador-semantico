package helper;

public class ThreeCodeAddresHelper {
    private static Boolean DEBUG = true;

    private static final char[][] precedence = {
            {'/', '1'},
            {'*', '1'},
            {'+', '2'},
            {'-', '2'},
    };


    private static int precedenceOf(String t) {
        char token = t.charAt(0);
        for (int i=0; i < precedence.length; i++) {
            if (token == precedence[i][0]) {
                return Integer.parseInt(precedence[i][1]+"");
            }
        }
        return -1;
    }

    public static void process(String expr) {
//        String []values = expr.split("[-+*/]");
        expr = expr.replace("+", " + ");
        expr = expr.replace("-", " - ");
        expr = expr.replace("*", " * ");
        expr = expr.replace("/", " / ");
        String []values = expr.split(" ");

        Integer temporaryCounter = 0;
        System.out.println(expr);

        Integer totalTempValues = Math.floorDiv(values.length, 2)+1;
        String []tempValues = new String[totalTempValues];

        //Ordena com base na prioridade
        Integer totalOperators = Math.floorDiv(values.length, 2);

        Integer[][] sortedOperators = new Integer[totalOperators][1]; //armazena a ordem das posicoes dos operadores a serem processados

        Boolean[] processedOperators = new Boolean[values.length]; //Operador ja foi processado?

        String[] threeAdressCodes = new String[totalOperators];

        Integer opNumber = 0;
        //Passa por todos os operadores
        for (int i = 1; i< values.length; i+=2) {
            if (values[i].equals("*") || values[i].equals("/")) {
                sortedOperators[opNumber][0] = i;
                processedOperators[i] = false;

                opNumber++;
            }
        }
        for (int i = 1; i< values.length; i+=2) {
            if (values[i].equals("+") || values[i].equals("-")) {
                sortedOperators[opNumber][0] = i;
                processedOperators[i] = false;
                opNumber++;
            }
        }
        for (int i = 0; i < totalOperators; i++) {
            String tempId = "T" + i;
            threeAdressCodes[i] = tempId + "="
                    +values[sortedOperators[i][0]-1]
                    +values[sortedOperators[i][0]]
                    +values[sortedOperators[i][0]+1];

            values[sortedOperators[i][0]-1] = tempId;
            values[sortedOperators[i][0]+1] = tempId;

            Integer referenceToChangePrevious = sortedOperators[i][0]-2;
            Integer referenceToChangeNext = sortedOperators[i][0]+2;
            //propaga a alteracao da referencia
            while (referenceToChangeNext+2 < values.length && processedOperators[referenceToChangeNext]){
                values[referenceToChangeNext+1] = tempId;
                values[referenceToChangeNext-1] = tempId;
                referenceToChangeNext = referenceToChangeNext + 2;
            }

            while (referenceToChangePrevious-2 > 0 && processedOperators[referenceToChangePrevious]){
                values[referenceToChangePrevious+1] = tempId;
                values[referenceToChangePrevious-1] = tempId;
                referenceToChangePrevious = referenceToChangePrevious-2;
            }
            //Marca como processado
            processedOperators[sortedOperators[i][0]] = true;

            System.out.println(threeAdressCodes[i]);
        }

        System.out.println(sortedOperators);


//        String []operators =
    }

    public static void process3AC( String expr) {
        int i, j, opc=0;
        char token;
        boolean processed[];
        String[][] operators = new String[10][2];
        String temp;

        processed = new boolean[expr.length()];
        for (i=0; i < processed.length; i++)
        {
            processed[i] = false;
        }
        for (i=0; i < expr.length(); i++)
        {
            token = expr.charAt(i);
            for (j=0; j < precedence.length; j++)
            {
                if (token==precedence[j][0])
                {
                    operators[opc][0] = token+"";
                    operators[opc][1] = i+"";
                    opc++;
                    break;
                }
            }
        }
        System.out.println("\nOperators:\nOperator\tLocation");
        for (i=0; i < opc; i++)
        {
            System.out.println(operators[i][0] + "\t\t" + operators[i][1]);
        }
        //sort
        for (i=opc-1; i >= 0; i--)
        {
            for (j=0; j < i; j++)
            {
                if (precedenceOf(operators[j][0]) > precedenceOf(operators[j+1][0]))
                {
                    temp = operators[j][0];
                    operators[j][0] = operators[j+1][0];
                    operators[j+1][0] = temp;
                    temp = operators[j][1];
                    operators[j][1] = operators[j+1][1];
                    operators[j+1][1] = temp;
                }
            }
        }
        System.out.println("\nOperators sorted in their precedence:\nOperator\tLocation");
        for (i=0; i < opc; i++)
        {
            System.out.println(operators[i][0] + "\t\t" + operators[i][1]);
        }
        System.out.println();
        for (i=0; i < opc; i++)
        {
            j = Integer.parseInt(operators[i][1]+"");
            String op1="", op2="";
            if (processed[j-1]==true)
            {
                if (precedenceOf(operators[i-1][0]) == precedenceOf(operators[i][0]))
                {
                    op1 = "t"+i;
                }
                else
                {
                    for (int x=0; x < opc; x++)
                    {
                        if ((j-2) == Integer.parseInt(operators[x][1]))
                        {
                            op1 = "t"+(x+1)+"";
                        }
                    }
                }
            }
            else
            {
                op1 = expr.charAt(j-1)+"";
            }
            if (processed[j+1]==true)
            {
                for (int x=0; x < opc; x++)
                {
                    if ((j+2) == Integer.parseInt(operators[x][1]))
                    {
                        op2 = "t"+(x+1)+"";
                    }
                }
            }
            else
            {
                op2 = expr.charAt(j+1)+"";
            }
            System.out.println("t"+(i+1)+" = "+op1+operators[i][0]+op2);
            processed[j] = processed[j-1] = processed[j+1] = true;
        }
    }
}
