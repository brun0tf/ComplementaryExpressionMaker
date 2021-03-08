package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int mintermos = 0, maxtermos = 0;
        ArrayList<String> complementaryExpression = new ArrayList<>();
        ArrayList<Integer> product = new ArrayList<>();
        ArrayList<Integer> ttOutput = new ArrayList<>();
        ArrayList<Integer> expressionValue = new ArrayList<>();
        ArrayList<Integer> complTToutput = new ArrayList<>();
        String fileExpression = Readfile.LoadFileToString("NSP1_pd.txt");

        //StringTokenizer expression = new StringTokenizer(fileExpression, "+");
        ArrayList<Literal> element = new ArrayList<>();
        boolean literalAlreadyExists = false;
        int numOfLiterals = 0;

        FileOutputStream buffer, buffer2;
        buffer = new FileOutputStream("OutputFile/" + "NSP53_compl" + ".txt");
        buffer2 = new FileOutputStream("OutputFile/" + "quine-mcclusley" + ".txt");
        PrintWriter arch = new PrintWriter(buffer);
        PrintWriter quine = new PrintWriter(buffer2);

        assert fileExpression != null;
        Scanner scanner = new Scanner(fileExpression);
        String fileExp;

        while (scanner.hasNextLine()) {
            fileExp = scanner.nextLine();

            StringTokenizer expression = new StringTokenizer(fileExp, "+");

            //SortExpression sort = new SortExpression();
           // fileExp = sort.bubbleSort(fileExp);

            //adiciona os literais ao arraylist element
            for (int i = 0; i < fileExp.length(); i++) {
                if ((fileExp.charAt(i) != '*') && (fileExp.charAt(i) != '+') && (fileExp.charAt(i) != ' ') && (fileExp.charAt(i) != '!')) {
                    for (Literal literal : element) {
                        if (fileExp.charAt(i) == literal.getName()) {
                            literalAlreadyExists = true;
                            break;
                        }
                    }
                    if (!literalAlreadyExists) {
                        element.add(new Literal(fileExp.charAt(i)));
                        numOfLiterals++;
                    }
                    literalAlreadyExists = false;
                }
            }

            quine.println(numOfLiterals);

            SortInput sort = new SortInput();
            element = sort.sort(element);


            TruthTableGenerator tt = new TruthTableGenerator();
            int[][] truthTable = tt.createTruthTable(numOfLiterals);

            for (int j = 0; j < numOfLiterals; j++) {
                for (int i = 0; i < tt.getLinhas(); i++) {
                    element.get(j).setValue(truthTable[i][j]);
                }
            }
            //for (int j = 0 ; j < tt.getLinhas(); j++) System.out.println(element.get(5).getValue(j) +" "+ element.get(5).getName() );


            ArrayList<String> allCubes = new ArrayList<>();
            while (expression.hasMoreTokens()) {
                allCubes.add(expression.nextToken());
            }
            int cube;
            for (int line = 0; line < tt.getLinhas(); line++) {
                cube = 0;
                for (int k = 0; k < allCubes.size(); k++) {//percorre os cubos
                    for (int i = 0; i < element.size(); i++) { //percorre os literais
                        for (int j = 0; j < allCubes.get(cube).length(); j++) { // percorre o cubo selecionado
                            if (element.get(i).getName() == allCubes.get(cube).charAt(j)) {
                                if(j != 0) {
                                    if (allCubes.get(cube).charAt(j - 1) == '!') {
                                        if (element.get(i).getValue(line) == 0) {
                                            expressionValue.add(1);
                                        } else {
                                            expressionValue.add(0);
                                        }
                                    } else {
                                        expressionValue.add(element.get(i).getValue(line));
                                    }

                                    break;
                                }else expressionValue.add(element.get(i).getValue(line));
                            }
                        }
                    }
                    //aqui, percorreu to.do o cubo selecionado com todos os literais possiveis
                    cube++;
                    int temp = expressionValue.get(0);
                    for (int i = 0; i < expressionValue.size(); i++) {// faz os produtos (um em cada laço)
                        temp = temp & expressionValue.get(i);
                    }
                    expressionValue.clear();
                    product.add(temp);
                }

                int temp = product.get(0);
                for (int i = 0; i < product.size(); i++) { //faz a soma dos produtos
                    temp = temp | product.get(i);
                }
                product.clear();
                ttOutput.add(temp);
            }
       /* for (int i = 0; i < ttOutput.size(); i++){
            System.out.println(ttOutput.get(i));
        }*/

            //gera tabela da expressão complementar
            for (Integer integer : ttOutput) {
                if (integer == 0) {
                    complTToutput.add(1);
                    mintermos++;
                } else if (integer == 1) {
                    complTToutput.add(0);
                    maxtermos++;
                }
            }
        /*for (int i = 0; i < ttOutput.size(); i++){
            System.out.println(complTToutput.get(i));
        }*/


            ArrayList<String> temp = new ArrayList<>();
            for (int i = 0; i < tt.getLinhas(); i++) {
                for (int k = 0; k < numOfLiterals; k++) {

                    if (complTToutput.get(i) == 1) {
                        if (element.get(k).getValue(i) == 0) {
                            temp.add("!" + element.get(k).getName());
                        } else {
                            temp.add(String.valueOf((element.get(k).getName())));
                        }
                        if (k != numOfLiterals - 1) temp.add("*");
                    }
                }

                StringBuilder sb = new StringBuilder();
                for (String s : temp) {
                    sb.append(s);
                }

                if(complTToutput.get(i) == 1) quine.println(i);

                temp.clear();
                if (complTToutput.get(i) == 1) complementaryExpression.add(String.valueOf(sb));
                //if(i < mintermos-1) complementaryExpression.add(" + ");
            }

            StringBuilder sb = new StringBuilder();
        /*for (String s : complementaryExpression) {

            sb.append(s).append(" + ");
        }*/
            for (int n = 0; n < complementaryExpression.size(); n++) {
                if (!complementaryExpression.get(n).equals(complementaryExpression.get(complementaryExpression.size() - 1))) {
                    sb.append(complementaryExpression.get(n)).append(" + ");
                } else
                    sb.append(complementaryExpression.get(n));
            }


            quine.println(-1);
            if (scanner.hasNextLine()) quine.println(-1);
            arch.println(sb);
            System.out.println(sb);
            complementaryExpression.clear();
            element.clear();
            complTToutput.clear();
            ttOutput.clear();
            numOfLiterals = 0;

        }
        quine.println(-2);
        quine.close();
        arch.close();
    }
}










