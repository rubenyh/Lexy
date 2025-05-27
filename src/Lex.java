    import java.util.ArrayList;
    import java.util.List;

    public class Lex {
        private String line;
        private List<String> tokens;

        public static final int[] finalState_all = {1, 2, 3, 4, 5};
        public static final int[][] all = {
            //       letra  dígito  */   +-   ()=
            /*q0*/ {   1,      2,    3,   5,    4},
            /*q1*/ {   1,      1,    0,   0,    0},
            /*q2*/ {   0,      2,    0,   0,    0},
            /*q3*/ {   0,      0,    0,   0,    0},
            /*q4*/ {   0,      0,    0,   0,    0},
            /*q5*/ {   1,      0,    0,   0,    0},
        };

        public Lex(String line) {
            this.line = line;
            this.tokens = generateTokens(line);
        }

        public static List<String> generateTokens(String raw) {
            String line = raw.replaceAll("\\s+", "");
            List<String> tokens = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            int state = 0;

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                int t = transitionType(ch, state);
                if (t < 0) {
                    throw new RuntimeException("Token invalido: " + ch);
                }
                int next = all[state][t];


                if (next == 0) {
                    if (isFinal(state)) {
                        tokens.add(mapToken(state, sb.toString()));
                    }
                    sb.setLength(0);
                    state = 0;
                    t = transitionType(ch, state);
                    next = all[state][t];
                    if (next == 0) {
                        throw new RuntimeException("Token invalido: " + ch);
                    }
                }

                sb.append(ch);
                state = next;
            }

            if (isFinal(state)) {
                tokens.add(mapToken(state, sb.toString()));
            }

            return tokens;
        }

        private static boolean isFinal(int state) {
            for (int fin : finalState_all) {
                if (fin == state) return true;
            }
            return false;
        }

        private static String mapToken(int state, String lex) {
            switch (state) {
                case 1: return "id";
                case 2: return "num";
                case 3: case 4: case 5:
                    return lex;
                default:
                    return "algo paso";
            }
        }

        public static int transitionType(char current, int state) {
            if (Character.isLowerCase(current) || Character.isUpperCase(current)) return 0;
            if (Character.isDigit(current)) return 1;
            if (current == '/' || current == '*') return 2;
            if (current == '+' || current == '-') return 3;
            if (current == '(' || current == ')' || current == '=') return 4;
            return -1;
        }

        public String getline() {
            return line;
        }
        public void setline(String line) {
            this.line = line;
        }
        public List<String> getTokens() {
            return tokens;
        }
        public void setTokens(List<String> tokens) {
            this.tokens = tokens;
        }
    }
