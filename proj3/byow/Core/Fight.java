package byow.Core;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.Stopwatch;

import java.awt.*;
import java.util.Random;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;

public class Fight implements Encounter {
    private final int _probability;
    private final Random _r;
    private final Player _player;
    private int _playerHealth;
    private int _enemyHealth;

    public Fight(int probability, Player p) {
        _r = new Random();
        _probability = probability;
        _playerHealth = p.getHealth();
        _player = p;
    }

    public boolean shouldTrigger() {
        return _r.nextInt(100) < _probability;
    }

    public void action() {
        _enemyHealth = _r.nextInt(20) + 1;
        intro();
        StdDraw.pause(2000);
        while (true) {
            fightTurn();
            _player.setHealth(_playerHealth);
            if (_playerHealth <= 0) {
                playerLost();
                return;
            }
            if (_enemyHealth <= 0) {
                playerWon();
                return;
            }
        }
    }

    public void playerLost() {
        StdDraw.clear(Color.BLACK);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "You lost. ◉︵◉");
        StdDraw.show();
        StdDraw.pause(2000);
    }

    public void playerWon() {
        _player.wonFight();
        StdDraw.clear(Color.BLACK);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "You defeated the enemy! (◕‿◕)");
        StdDraw.show();
        StdDraw.pause(2000);
    }

    public void intro() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "FIGHT!");
        StdDraw.show();
    }

    public char solicitPlayerChoice() {
        Stopwatch sw = new Stopwatch();
        char choice = ' ';
        while (sw.elapsedTime() < 2) {
            if (StdDraw.hasNextKeyTyped()) {
                choice = StdDraw.nextKeyTyped();
            }
        }
        return choice;
    }

    public void drawHUD() {
        StdDraw.setPenColor(Color.WHITE);
        Font font = new Font("Monaco", Font.BOLD, 15);
        StdDraw.setFont(font);
        StdDraw.text(5, 2, "YOUR HEALTH : " + _playerHealth);
        StdDraw.line(0, 3, WIDTH, 3);
        StdDraw.text(WIDTH / 2, 2, "Press (A) to attack and (D) to defend.");
        StdDraw.text(WIDTH - 5, 2, "ENEMY HEALTH: " + _enemyHealth);
        font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
    }

    /**
     * Picks a random action for the enemy to take.
     */
    public String enemyAction() {
        int choice = _r.nextInt(10);
        if (choice < 3) {
            return "BLOCKED";
        } else {
            return "ATTACKED";
        }
    }

    public void fightTurn() {
        StdDraw.clear(Color.BLACK);
        drawHUD();
        String enemyAction = enemyAction();
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "Your enemy " + enemyAction);
        StdDraw.picture(WIDTH / 2, HEIGHT / 3, "./imgs/evil-eyes.png",
                        10, 10);
        StdDraw.show();
        char playerResponse = solicitPlayerChoice();
        String outcome = processPlayerChoice(enemyAction, playerResponse);
        StdDraw.clear(Color.RED);
        drawHUD();
        StdDraw.text(WIDTH / 2, HEIGHT / 2, outcome);
        StdDraw.show();
        StdDraw.pause(2000);
    }

    String processPlayerChoice(String enemyAction, char playerResponse) {
        int playerDmg = _r.nextInt(5) + 1;
        int enemyDmg = _r.nextInt(5) + 1;
        String result = "";
        if (playerResponse == 'a') {
            if (enemyAction.equals("ATTACKED")) {
                _playerHealth -= playerDmg;
                _enemyHealth -= enemyDmg;
                result = "You both attacked! \n" +
                        "You lost " + playerDmg + " health \n" +
                        "Your enemy lost " + enemyDmg + " health.";
            } else {
                _playerHealth -= playerDmg;
                result = "Your enemy deflected your attack! \n" +
                        "You lost " + playerDmg + " health.";
            }
        } else if (playerResponse == 'd') {
            _enemyHealth -= enemyDmg;
            if (enemyAction.equals("ATTACKED")) {
                result = "You deflected the attack! \n" +
                        "Your enemy lost " + enemyDmg + " health.";
            } else {
                _playerHealth -= 2;
                _enemyHealth -= 2;
                result = "You both deflected! \n";
            }
        } else {
            _playerHealth -= playerDmg;
            result = "Too slow! You lost " + playerDmg + " health.";
        }
        return result;
    }
}
