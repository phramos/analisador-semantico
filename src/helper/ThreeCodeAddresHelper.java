package helper;

public class ThreeCodeAddresHelper {
    private static Boolean DEBUG = false;


    public static String[] process(String expr, String varName) {
//        String []values = expr.split("[-+*/]");
        expr = expr.replace("+", " + ");
        expr = expr.replace("-", " - ");
        expr = expr.replace("*", " * ");
        expr = expr.replace("/", " / ");
        String []values = expr.split(" ");

        Integer temporaryCounter = 0;
        if (DEBUG) System.out.println(expr);

        Integer totalTempValues = Math.floorDiv(values.length, 2)+1;
        String []tempValues = new String[totalTempValues];

        //Ordena com base na prioridade
        Integer totalOperators = Math.floorDiv(values.length, 2);

        Integer[][] sortedOperators = new Integer[totalOperators][1]; //armazena a ordem das posicoes dos operadores a serem processados

        Boolean[] processedOperators = new Boolean[values.length]; //Operador ja foi processado?

        String[] threeAdressCodes;
        Integer opNumber = 0;

        if (values.length == 1){
            threeAdressCodes = new String[1];
            threeAdressCodes[0]=varName + "=" + values[0];
        } else {
            threeAdressCodes = new String[totalOperators];
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
                String tempId = "";
                if (i == totalOperators -1) {
                    tempId = varName;
                }else {
                    tempId = "T" + i;
                }
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

                if (DEBUG) System.out.println(threeAdressCodes[i]);
            }
        }


        return threeAdressCodes;

    }

    public static void print3ac(String[] threeAdressCodes) {
        for (String tac:
                threeAdressCodes) {
            System.out.println(tac);

        }
    }

    public static void printQuadrupla(String[] threeAdressCodes) {
        System.out.println("<op, arg1, arg2, result>");
        for (String tac:
                threeAdressCodes) {
            tac = tac.replace("+", " + ");
            tac = tac.replace("-", " - ");
            tac = tac.replace("*", " * ");
            tac = tac.replace("/", " / ");
            tac = tac.replace("=", " = ");
            String[] values = tac.split(" ");

            //Varifica se eh uma expressao de atribuicao simples. Ex.: a = 15;
            if (values.length == 3) {
                System.out.println("<"+ values[1]
                        + ", " + values[2]
                        + ", " + null
                        + ", " +values[0]
                        + ">");
            } else {
                System.out.println("<"+ values[3]
                        + ", "+ values[2]
                        + ", "+ values[4]
                        + ", " + values[0]
                        + ">");
            }
//            System.out.println(tac);

        }
    }

}
