public class Main {
    public static void main(String[] args) throws Exception {
        Giocattolo bilia = new Giocattolo("bilia", "vetro");
        Giocattolo bilia2 = new Giocattolo("bilia", "vetro");
        System.out.println(bilia.equals(bilia2));
    }
}
