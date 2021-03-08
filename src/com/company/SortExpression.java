package com.company;

import java.util.ArrayList;

public class SortExpression {

    public String bubbleSort(String expression){
        String temp;
        boolean literalAlreadyDefined = false;
        String[] expVect = new String[expression.length()];
        for (int i = 0; i < expression.length(); i++){
            expVect[i] = String.valueOf(expression.charAt(i));
        }

        for (int i = 0; i < expVect.length; i++){
            if(!expVect[i].equals("!") && !expVect[i].equals("+") &&  !expVect[i].equals("*") && !expVect[i].equals(" ")){ //se for um literal, entra no if
                for (int j = i+1; j < expVect.length; j++){
                    if(!expVect[j].equals("!") && !expVect[j].equals("+") && !expVect[j].equals("*") && !expVect[j].equals(" ")){
                        if(expVect[i].compareTo(expVect[j]) > 0 && !expVect[j].equals("\n")){
                            for(int k = 0; k < i; k++){
                                if (expVect[k].equals(expVect[j])) {
                                    literalAlreadyDefined = true;
                                    break;
                                }
                            }
                            if (!literalAlreadyDefined){
                                String x = expVect[i], y = expVect[j];
                                //todo: talvez adicionar a negação somente nas variaveis X e Y resolva o problema
                                for (int m = i; m < expVect.length; m++) {
                                    if(expVect[m].equals(y)){
                                        expVect[m] = x;
                                     }
                                    else if (expVect[m].equals(x)){
                                        expVect[m] = y;
                                    }
                                }
                            }
                            literalAlreadyDefined = false;
                        }
                    }
                }
            }
        }
       StringBuilder sb = new StringBuilder();
        for (String s : expVect) {
            sb.append(s);
        }
        System.out.println(sb);
        return String.valueOf(sb);
    }
}

