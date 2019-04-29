package projektswiat;

public class ProjektSwiat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Swiat swiat = new Swiat(10,20);
        
        while (true)
        {
            swiat.wykonajTure();
            System.out.println("Press Any Key To Continue...");
            new java.util.Scanner(System.in).nextLine();
        }
    }
    
}
