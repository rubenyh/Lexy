public class Main {
    public static void main(String[] args) {
        Lex lex = new Lex("aw = (-5) + 3");
        System.out.println("Input Line: " + lex.getline());
        System.out.println(lex.getTokens());
    }
}
