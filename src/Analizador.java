public class Analizador {
    public static final int[] finalState = {7, 8, 6}; 
    // public static final int[] invalidTransitions = {13};  /*si se va aqui se detiene el automata y rechaza la cadena */
    public static final int[][] transitionTable = {
    //operadores ==>    /, *
    //mason ==>    +, -,
     //       [a-zA-Z],    [0-9],    operador,    (,       ),      =,     mason    
     /*q0*/   {1,              0,        0,       0,       0,      0,        0},
     /*q1*/   {1,              1,        0,       0,       0,      2,        0},
     /*q2*/   {8,              7,        0,       3,       0,      0,        9},
     /*q3*/   {5,              4,        0,       0,       0,      0,       10},
     /*q4*/   {0,              4,        0,       0,       6,      0,        0},
     /*q5*/   {5,              5,        3,       0,       7,     13,        0},
     /*q6*/   {6,              6,        6,      13,       7,     13,        0},
     /*q7*/   {13,            13,       13,      13,      13,      8,        0},
     /*q8*/   {13,            13,       13,      13,      13,     13,        0},
     /*q9*/   {13,            13,       13,      13,      13,     13,        0},
    };  
    public static final int[] invalidTransitions = {3};

    public static final int[] finalState_id = {1};
    public static final int[][] id = {
    //operadores ==>    +, -,
     //       [a-zA-Z],    [0-9],    operador,   
     /*q0*/   {1,              3,        2 },
     /*q1*/   {1,              1,        3 },
     /*q2*/   {2,              3,        3 },
    };  
    public static final int[] finalState_num = {1};
    public static final int[][] num = {
    //operadores ==>    +, -,
     //       [0-9],    operador,   
     /*q0*/   {2,              1 },
     /*q1*/   {2,              3 },
     /*q2*/   {2,              0 },
    };  

    public static final int[] finalState_op = {1};
    public static final int[][] op = {
    //operadores ==>    *, / +, -,
     //       operador,   
     /*q0*/   { 1},
     /*q1*/   { 3},
    };

    public static final int[] finalState_parenthesis = {1};
    public static final int[][] parenthesis = {
    //par ==>    (, )
     //       par,   
     /*q0*/   { 1},
     /*q1*/   { 3},
    };

    public static void main(String[] args) {
        int a= +9;
        System.out.println(a);
    }




}
