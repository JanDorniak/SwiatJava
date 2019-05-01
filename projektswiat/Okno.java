package projektswiat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

import projektswiat.Organizm;
import projektswiat.zwierzeta.Zwierze;

public class Okno extends JFrame implements ActionListener{
    
    Swiat swiat;
    
    JButton przycisk_tura;
    JButton[][] przycisk_element;
    //JButton test;
    
    public Okno(Swiat swiat)
    {
        this.swiat = swiat;
        
        setSize(800,800);
        setTitle("Jan Dorniak 175959");
        setLayout(null);
        
        przycisk_element = new JButton[swiat.plansza.getX()][swiat.plansza.getY()];
        
        przycisk_tura = new JButton("Nastepna tura");
        przycisk_tura.setBounds(500, 500, 140, 30);
        przycisk_tura.addActionListener(this);
        add(przycisk_tura);
        
        utworzPrzyciski();
        //przycisk_element[0][0] = new JButton("hjsahjas");
        //przycisk_element[0][0].setBounds(10, 10, 100, 100);
        //add(przycisk_element[0][0]);
    }
    
    private void utworzPrzyciski()
    {
        for (int i = 0; i < swiat.plansza.getX(); i++)
            for (int j = 0; j < swiat.plansza.getY(); j++)
            {
                przycisk_element[i][j] = new JButton(".");
                przycisk_element[i][j].setBounds(50*i, 50*j, 50, 50);
                przycisk_element[i][j].setBackground(java.awt.Color.WHITE);
                add(przycisk_element[i][j]);
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        
        if (zrodlo == przycisk_tura)
        {
            swiat.wykonajTure();
            //rysuj();
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
                    przycisk_element[i][j].setBackground(java.awt.Color.WHITE);
                }
                else
                {
                    przycisk_element[i][j].setText(String.valueOf(organizm.getSymbol()));
                    if (organizm instanceof Zwierze)
                        przycisk_element[i][j].setBackground(new Color(255,153,0));
                    else
                        przycisk_element[i][j].setBackground(new Color(51,204,51));
                }
            }
    }
    
    
}
