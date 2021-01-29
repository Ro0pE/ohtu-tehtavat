/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ruoko
 */
public class StatisticTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka k‰ytt‰‰ "stubia"
        stats = new Statistics(readerStub);
    } 
    

    @Test
    public void findPlayer(){
        Player name = stats.search("Semenko");
        Player nullName = stats.search("saggs");
        assertEquals(nullName,null);
        assertEquals(name.getName(), readerStub.getPlayers().get(0).getName());
    }
    @Test
    public void findTeam(){
        List<Player> EDM = stats.team("EDM");
        assertEquals(EDM.contains("EDM"), false);
    }
    @Test
    public void findTopScorer(){
        List<Player> topsu = stats.topScorers(0);
        
        assertEquals(topsu.get(0).getName(), readerStub.getPlayers().get(4).getName());
    }

}