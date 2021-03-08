package com.company;

public class TruthTableGenerator {
    private int linhas;

    public int getLinhas() {
        return linhas;
    }

    public int[][] createTruthTable (int numLiterais){
        boolean flag1 = true, flag2 = true, flag3 = true, flag4 = true, flag5 = true;
        linhas = (int) Math.pow(2, numLiterais);
        int[][] truthTable = new int[linhas][numLiterais];

        for(int i = 0; i < linhas; i++){
            for (int j = 0; j < numLiterais; j++){
                if(j == numLiterais-1){
                    if (i%2 != 0) {
                        truthTable[i][j] = 1;
                    }else
                        truthTable[i][j] = 0;
                }
                else if(j == numLiterais-2) {
                    if(i%2 == 0){
                        if(flag1) {
                            for(int k = i; k < i+2; k++)
                                truthTable[k][j] = 0;
                            flag1 = false;
                        }else {
                            for(int k = i; k < i+2; k++)
                                truthTable[k][j] = 1;
                            flag1 = true;
                        }
                    }
                }

                else if( j == numLiterais-3) {
                    if (i%4 == 0) {
                        if (flag2) {
                            for(int k = i; k < i+4; k++)
                                truthTable[k][j] = 0;
                            flag2 = false;
                        }else {
                            for(int k = i; k < i+4; k++)
                                truthTable[k][j] = 1;
                            flag2 = true;
                        }
                    }
                }

                else if (j == numLiterais-4){
                    if(i%8 == 0){
                        if(flag3) {
                            for(int k = i; k < i+8; k++)
                                truthTable[k][j] = 0;
                            flag3 = false;
                        }else {
                            for(int k = i; k < i+8; k++)
                                truthTable[k][j] = 1;
                            flag3 = true;
                        }
                    }
                }
                else if (j == numLiterais-5){
                    if(i%16 == 0){
                        if(flag4) {
                            for(int k = i; k < i+16; k++)
                                truthTable[k][j] = 0;
                            flag4 = false;
                        }else {
                            for(int k = i; k < i+16; k++)
                                truthTable[k][j] = 1;
                            flag4 = true;
                        }
                    }
                }
                else if (j == numLiterais-6){
                    if(i%32 == 0){
                        if(flag5) {
                            for(int k = i; k < i+32; k++)
                                truthTable[k][j] = 0;
                            flag5 = false;
                        }else {
                            for(int k = i; k < i+32; k++)
                                truthTable[k][j] = 1;
                            flag5 = true;
                        }
                    }
                }

               // System.out.print(truthTable[i][j]+ " ");
            }
            //System.out.println();
        }
        return  truthTable;

    }

}
