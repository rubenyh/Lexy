import java.util.List;
public class Lex {
    private String line;
    private List<String> tokens;

    public static final int[] finalState_all = {1, 2, 3, 4, 5};
    public static final int[][] all = {
    //operadores ==>   *, /
    //mason ==> +,-
     //       [a-zA-Z],    [0-9],    operador,      mason,    ()=,
     /*q0*/   {1,              2,        3,           5,      4  },
     /*q1*/   {1,              1,        0,           0,      0  },
     /*q2*/   {0,              2,        0,           0,      0  },
     /*q3*/   {0,              0,        0,           0,      0  },
     /*q4*/   {0,              0,        0,           0,      0  },
     /*q5*/   {1,              2,        0,           0,      0  },
    };  


    public Lex(String line){
        this.line = line = line.toLowerCase().replaceAll("\\s+","");
        this.tokens = generateTokens(line);
    }

    public static List<String> generateTokens(String line){
        List<String> tokenList = new java.util.ArrayList<>();
        int state = 0;
        StringBuilder sb = new StringBuilder();
        int prevState = 0;
        for(char current : line.toCharArray()){
            int transition = transitionType(current, state);
            if(transition == -1){
                return List.of("Invalid character: " + current);
            }

            state = all[state][transition];

            System.out.println(sb.toString());
            if (state == 0) {
                if(prevState == 1){
                    tokenList.add("id");
                    sb.setLength(0);

                }
                if(prevState == 2){
                    tokenList.add("num");
                    sb.setLength(0);
                }
                if(current == '=') {
                    tokenList.add("=");
                    sb.setLength(0);
                }
                if(current == '+') {
                    tokenList.add("+");
                    sb.setLength(0);
                }
                if(current == '-') {
                    tokenList.add("-");
                    sb.setLength(0);
                }
                
            } 
            sb.append(current);
        
            
            System.out.println("Current: " + current + ", State: " + state + ", Transition: " + transition + ", Previous State: " + prevState);
            
            System.out.println(tokenList);
            if (current != line.charAt(line.length() - 1)) {
                prevState = state;
            }
        }   
        
        if(prevState == 1){
            tokenList.add("id");
        }
        if(prevState == 2){
            tokenList.add("num");
        }
        if(prevState == 3 || prevState == 4 || prevState == 5){
            tokenList.add(sb.toString());
        }

        return tokenList;
    }

    public static int transitionType(char current, int state){
        if (Character.isLowerCase(current) || Character.isUpperCase(current)) return 0;
        if (Character.isDigit(current)) return 1;
        if (current == '/' || current == '*') return 2;
        if (current == '+' || current == '-' ) return 3;
        if (current == '(' || current == ')' || current == '=') return 4;
        return -1;
    }


    public String getline(){
        return line;
    }
    public void setline(String line){
        this.line = line;
    }
    public List<String> getTokens() {
        return tokens;
    }
    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }


}
