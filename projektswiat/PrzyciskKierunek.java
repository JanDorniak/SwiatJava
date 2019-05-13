package projektswiat;

import javax.swing.JButton;

public class PrzyciskKierunek extends JButton {
    
    final private Kierunki kierunek;
    
    public PrzyciskKierunek(String kierunek)
    {
        super(kierunek);
        if (kierunek == "GORA")
            this.kierunek = Kierunki.DOL;
        else if (kierunek == "LEWO")
            this.kierunek = Kierunki.LEWO;
        else if (kierunek == "DOL")
            this.kierunek = Kierunki.GORA;
        else if (kierunek == "PRAWO")
            this.kierunek = Kierunki.PRAWO;
        else
            this.kierunek = null;
    }
    
    public Kierunki getKierunek()
    {
        return kierunek;
    }
    
}
