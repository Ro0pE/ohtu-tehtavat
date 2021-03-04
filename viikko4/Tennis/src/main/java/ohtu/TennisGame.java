package ohtu;

public class TennisGame {
    
    private int player1_pisteet = 0;
    private int player2_pisteet = 0;
    final String player1Name;
    final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1")){
            player1_pisteet += 1;
        } else {
            player2_pisteet += 1;
        }
    }
    public String playerOnePoints(){
        return checkPointsOrWinner(player1_pisteet);
    }
    
    public String playerTwoPoints(){
        if (player1_pisteet < 4 && player1_pisteet == player2_pisteet){
            return "All";               
        } else {
           return checkPointsOrWinner(player2_pisteet);
        }
    }
    public String getScore() {
        String score = "";  
        if (player1_pisteet < 4 && player2_pisteet < 4){
            score = playerOnePoints() + "-" + playerTwoPoints();
            return score;
        } else {
            if (player1_pisteet > player2_pisteet){
                return playerOnePoints();
            } else {
                return playerTwoPoints();
            }             
        }
        }
    
    public String checkWinner(){
        if (player1_pisteet - player2_pisteet == 1){
                return "Advantage player1";
            }
            if (player1_pisteet - player2_pisteet == -1){
                return "Advantage player2";
            }
            if (player1_pisteet-player2_pisteet == 2 || player1_pisteet-player2_pisteet == 3 || player1_pisteet-player2_pisteet == 4){
                return "Win for player1";
            } 
            if (player1_pisteet - player2_pisteet == -2 || player1_pisteet-player2_pisteet == -3 || player1_pisteet-player2_pisteet == -4);
                return "Win for player2";
            }     

    public String checkPointsOrWinner(int player){
        if (player == 0){
            return "Love";
        } else if (player == 1){
            return "Fifteen";
        } else if (player == 2){
            return "Thirty";
        } else if (player == 3){
            return "Forty";          
        } else if (player1_pisteet == 4 && player1_pisteet == player2_pisteet){
            return "Deuce";     
        } else {
           return checkWinner();          
        }      
    }
  
    public static void main(String[] args) {
    TennisGame game = new TennisGame("player1", "player2");

    System.out.println(game.getScore());

    game.wonPoint("player1");
    System.out.println(game.getScore());

    game.wonPoint("player1");
    System.out.println(game.getScore());

    game.wonPoint("player2");
    System.out.println(game.getScore());

    game.wonPoint("player1");
    System.out.println(game.getScore());

    game.wonPoint("player1");
    System.out.println(game.getScore());
}
}
