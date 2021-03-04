
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int taulukonKoko = 5; 
    private int[] lukujenJoukko;      
    private int alkioidenLukumaara;     

    public IntJoukko() {
        lukujenJoukko = new int[taulukonKoko];
        alkioidenLukumaara = 0;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujenJoukko = new int[kapasiteetti];
        nollaaLista();
        alkioidenLukumaara = 0;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (!(kapasiteetti < 0 || kasvatuskoko < 0)) {
        lukujenJoukko = new int[kapasiteetti];
        nollaaLista();
        alkioidenLukumaara = 0;
        } else {
            System.out.println("Virhe: kapasiteetti tai kasvatuskoko liian pieni" );
        }
    }
    public boolean lisaa(int luku) {

    int [] uusiTaulu = new int [lukujenJoukko.length+1];
        if (!kuuluu(luku)){
        
        lukujenJoukko[alkioidenLukumaara] = luku;
        alkioidenLukumaara++;
        kopioiTaulukko(lukujenJoukko, uusiTaulu);
        lukujenJoukko = uusiTaulu;
        
        return true;         
        }    
    return false;
    }

    public boolean kuuluu(int luku) {
        
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujenJoukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) { 
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenLukumaara; i++) {
            if (luku == lukujenJoukko[i]) {
                kohta = i; 
                lukujenJoukko[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLukumaara - 1; j++) {
                apu = lukujenJoukko[j];
                lukujenJoukko[j] = lukujenJoukko[j + 1];
                lukujenJoukko[j + 1] = apu;
            }
            alkioidenLukumaara--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];      
        }
    }

    public int mahtavuus() {
        return alkioidenLukumaara;
    }

    @Override
    public String toString() {
    String printti = "";
    if (lukujenJoukko.length == 0){
        return "{}";
    } else {
            for (int i = 0; i < lukujenJoukko.length; i++){  
                if (!(lukujenJoukko[i]== 0)){
                    if (i < (alkioidenLukumaara-1)) {
                        printti = printti + lukujenJoukko[i] + ", ";
                    } else {
                        printti = printti + lukujenJoukko[i];
                    }
                                      
                }
           }        
    }   
     return "{" + printti + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLukumaara];
        for (int i = 0; i < lukujenJoukko.length; i++) {
            if (!(lukujenJoukko[i] == 0)){
                taulu[i] = lukujenJoukko[i];
            }
            
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
        return z;
    }
    
    public void nollaaLista(){
        for (int i = 0; i < lukujenJoukko.length; i++){
            lukujenJoukko[i] = 0;
        }
        alkioidenLukumaara = 0;
    }       
}
