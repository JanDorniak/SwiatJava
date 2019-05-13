package projektswiat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import projektswiat.zwierzeta.Zwierze;
import projektswiat.Rosliny.Roslina;
import projektswiat.zwierzeta.Czlowiek;

public class Okno extends JFrame implements ActionListener{
    
    final private Swiat swiat;
    final private int size_x;
    final private int size_y;
    
    private JButton przycisk_tura; //
    private JButton przycisk_zapisz;//
    private JButton przycisk_wczytaj;//
    private JButton[][] przycisk_element; //.
    private PrzyciskKierunek[] przycisk_kierunek; //.
    private JLabel info_ruch; //.
    private JComboBox jakie_dodac;//
    private JTextPane dziennik_zdarzen;//
    
    private Kierunki wybrany_kierunek;
    
    public Okno(Swiat swiat)
    {
        this.swiat = swiat;
        this.wybrany_kierunek = null;
        this.size_x = swiat.plansza.getX();
        this.size_y = swiat.plansza.getY();
        
        setSize(size_x*50 + 300,size_y*50 + 220);
        setTitle("Jan Dorniak 175959");
        setLayout(null);
        
        przycisk_tura = new JButton("Nastepna tura");
        przycisk_tura.setBounds(size_x*50 + 70, 330, 140, 30);
        przycisk_tura.addActionListener(this);
        add(przycisk_tura);
        
        przycisk_zapisz = new JButton("Zapisz");
        przycisk_zapisz.setBounds(size_x*50 + 70,370,140,30);
        przycisk_zapisz.addActionListener(this);
        add(przycisk_zapisz);
        
        przycisk_wczytaj = new JButton("Wczytaj");
        przycisk_wczytaj.setBounds(size_x*50 + 70,410,140,30);
        przycisk_wczytaj.addActionListener(this);
        add(przycisk_wczytaj);
        
        info_ruch = new JLabel("Wybierz kierunek ruchu czlowieka");
        info_ruch.setBounds(50*size_x + 44,240 , 300, 100);
        add(info_ruch);
        
        dziennik_zdarzen = new JTextPane();
        dziennik_zdarzen.setBounds(0, size_y*50 + 20 ,50 * size_x , 135);//220
        dziennik_zdarzen.setEditable(false);
        add(dziennik_zdarzen);
        
        dodajListeDodawania();
        
        utworzPrzyciskiPlanszy();
        utworzPrzyciskiCzlowieka();
    }
    
    private void utworzPrzyciskiCzlowieka()
    {       
        przycisk_kierunek = new PrzyciskKierunek[5];
        przycisk_kierunek[0] = new PrzyciskKierunek("GORA");
        przycisk_kierunek[1] = new PrzyciskKierunek("LEWO");
        przycisk_kierunek[2] = new PrzyciskKierunek("DOL");
        przycisk_kierunek[3] = new PrzyciskKierunek("PRAWO");
        przycisk_kierunek[4] = new PrzyciskKierunek("SKILL");
        
        przycisk_kierunek[4].setBounds(50*size_x + 100,20,80,80);
        przycisk_kierunek[0].setBounds(50*size_x + 100,120,80,80);
        
        for (int i = 0; i < 5; i++)
        {
            if (i > 0 && i < 4)
                przycisk_kierunek[i].setBounds(size_x*50 + 20 + (i-1)*80, 200, 80,80);//(444+80*i, 200, 80, 80);
            przycisk_kierunek[i].setBackground(java.awt.Color.magenta);
            przycisk_kierunek[i].addActionListener(this);
            add(przycisk_kierunek[i]);
        }
    }
    
    private void utworzPrzyciskiPlanszy()
    {
        przycisk_element = new JButton[swiat.plansza.getX()][swiat.plansza.getY()];
        for (int i = 0; i < swiat.plansza.getX(); i++)
            for (int j = 0; j < swiat.plansza.getY(); j++)
            {
                przycisk_element[i][j] = new JButton(".");
                przycisk_element[i][j].setBounds(50*i, 50*j, 50, 50);
                przycisk_element[i][j].setBackground(java.awt.Color.WHITE);
                przycisk_element[i][j].addActionListener(this);
                add(przycisk_element[i][j]);
            }
    }
    
    private void dodajListeDodawania()
    {
        String[] nazwy = new String[10];
        int i = 0;
        for (Organizmy k : Organizmy.values())
        {
            if (k == Organizmy.CZLOWIEK)
                continue;
            nazwy[i] = k.toString();
            i++;
        }
        jakie_dodac = new JComboBox(nazwy);
        jakie_dodac.setBounds(size_x*50 + 70, 450, 140, 30);
        jakie_dodac.addActionListener(this);
        add(jakie_dodac);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        
        boolean flaga = false;
        
        if (zrodlo == przycisk_tura && czyKierunek())
        {
            swiat.wykonajTure();
        }
        else if (zrodlo == przycisk_zapisz)
        {
            try {
                swiat.zapiszGre();
            } catch (IOException ex) {
                
            }
        }
        else if (zrodlo == przycisk_wczytaj)
        {
            try {
                swiat.wczytajGre();
            } catch (IOException ex) {
                
            }
        }
        else if (zrodlo instanceof PrzyciskKierunek)
        {
            Kierunki kierunek;
            PrzyciskKierunek przycisk = (PrzyciskKierunek)zrodlo;
            kierunek = przycisk.getKierunek();
            if (kierunek == null)
            {
                swiat.aktywujUmiejetnosc();
                rysuj();
                return;
            }
            wybrany_kierunek = kierunek;
            if (swiat.czyZyjeCzlowiek())
                swiat.wykonajTure();
        }
        else
        {
            JButton przycisk = null;
            int x = -1, y = -1;
            
            for (int i = 0; i < swiat.plansza.getX(); i++)
                for (int j = 0; j < swiat.plansza.getY(); j++)
                {
                    if (zrodlo == przycisk_element[i][j])
                    {
                        przycisk = (JButton)zrodlo;
                        x = i;
                        y = j;
                        flaga = true;            
                    }
                }
            if (!flaga)
                return;
            if (swiat.plansza.czyWolne(x, y))
            {
                Organizmy nazwa = Organizmy.valueOf((String)jakie_dodac.getSelectedItem());
                swiat.dodajOrganizm(nazwa, x, y);
                rysuj();
            }
        }
    }
    
    public void rysuj()
    {
        for (int i = 0; i < swiat.plansza.getX(); i++)
            for (int j = 0; j < swiat.plansza.getY(); j++)
            {
                Organizm organizm = swiat.plansza.getOrganizm(i, j);
                if (organizm == null)
                {
                    przycisk_element[i][j].setText(".");
                    przycisk_element[i][j].setBackground(Color.WHITE);
                }
                else
                {
                    przycisk_element[i][j].setText(String.valueOf(organizm.getSymbol()));
                    if (organizm instanceof Zwierze && !(organizm instanceof Czlowiek))
                        przycisk_element[i][j].setBackground(new Color(255,153,0));
                    else if (organizm instanceof Roslina)
                        przycisk_element[i][j].setBackground(new Color(51,204,51));
                    else 
                        przycisk_element[i][j].setBackground(Color.RED);
                }
            }
        if (!swiat.czyZyjeCzlowiek())
        {
            info_ruch.setText(null);
        }
        try {
            wpiszKomentarze();
        } catch (BadLocationException ex) {
            
        }
    }
    
    public boolean czyKierunek()
    {
       if (wybrany_kierunek == null && swiat.czyZyjeCzlowiek())
           return false;
       return true;
    }   
    
    public Kierunki getKierunek()
    {
        Kierunki kierunek = wybrany_kierunek;
        wybrany_kierunek = null;
        return kierunek;
    }
    
    public void wpiszKomentarze() throws BadLocationException
    {
        Vector<String> rejestr = swiat.komentator.getRejestr();
        Document doc = dziennik_zdarzen.getDocument();
        dziennik_zdarzen.setText("");
        while(rejestr.size() > 0)
        {
            String zdanie = rejestr.remove(0);
            zdanie += "\n";
            doc.insertString(doc.getLength(), zdanie, null);
        }
    }
    
    public void wyczyscKomentarze()
    {
        dziennik_zdarzen.setText("");
    }
}
