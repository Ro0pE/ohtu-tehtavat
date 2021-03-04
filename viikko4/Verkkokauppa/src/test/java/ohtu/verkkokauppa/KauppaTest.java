package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;



public class KauppaTest {
    
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Ostoskori ostoskori;
    Kauppa kauppa;
    
    @Before
    public void setUp(){       
         pankki = mock(Pankki.class);
         viite = mock(Viitegeneraattori.class);
         varasto = mock(Varasto.class);
         ostoskori = mock(Ostoskori.class);
         kauppa = new Kauppa(varasto, pankki, viite);
         
    }

    @Test
    public void koriinLisataanTuoteJotaOnVarastossaJaSuoritetaanOstos() {
        
        when(varasto.saldo(1)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), anyString(), anyString(),anyInt());


    }
    @Test
    public void koriinLisataanKaksiEriTuotettaJotkaOvatVarastosta(){
        when(varasto.saldo(1)).thenReturn(5);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kalja", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("jari", "54321");    
        
        verify(pankki).tilisiirto(eq("jari"), anyInt(), anyString(), anyString(),anyInt());   


        
        
    }
    @Test
    public void lisaaKaksiSamaaTuotettaKoriinJotkaOvatVarastossa(){
        when(varasto.saldo(1)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        when(varasto.saldo(1)).thenReturn(5);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pertsa", "1234");
        
        verify(pankki).tilisiirto(eq("pertsa"), anyInt(), anyString(), anyString(),anyInt());  
    }
        
    @Test
    public void listaaKoriinTuoteJotaLoytyyJaTuoteMitaEiLoydyVarastosta(){
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "jallu", 15));
        when(varasto.saldo(1)).thenReturn(5);
        when(varasto.saldo(2)).thenReturn(0);
    
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pertsaJari", "12345");
        
        verify(pankki).tilisiirto(eq("pertsaJari"), anyInt(), anyString(), anyString(),anyInt()); 
        
 
    }
    @Test
    public void aloitaAsiointiNollaaKorinHinnan(){
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "jallu", 15));
        when(varasto.saldo(1)).thenReturn(515);
        when(varasto.saldo(2)).thenReturn(15);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("tero2", "5211");
        
        
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(15));

        
    }
    @Test
    public void uusiViitenumeroJokaiselleMaksutapahtumalle(){
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "jallu", 15));
        when(varasto.saldo(1)).thenReturn(515);
        when(varasto.saldo(2)).thenReturn(15);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(5);
        kauppa.tilimaksu("jartsa", "1111");

   
        verify(viite, times(1)).uusi();

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(5);
        kauppa.tilimaksu("jartsa2", "1111555");

 
        verify(viite, times(2)).uusi();

        
    }
    @Test
    public void poistaOstoskorista(){
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "jallu", 15));
        when(varasto.saldo(1)).thenReturn(515);
        when(varasto.saldo(2)).thenReturn(15);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.poistaKorista(1);
        varasto.palautaVarastoon(varasto.haeTuote(1));
        
        
        
        
      
        
        
        
    }
}