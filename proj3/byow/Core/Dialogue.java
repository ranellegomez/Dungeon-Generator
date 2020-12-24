package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;

/**
 * @source for image files: game-icons.net
 */

public class Dialogue implements Encounter {

    NPC _npc;
    Random _r;
    int _probability;
    Player _player;

    public Dialogue(NPC npc, Random r, int probability, Player p) {
        _npc = npc;
        _r = new Random();
        _probability = probability;
        _player = p;
    }

    @Override
    public boolean shouldTrigger() {
        return _r.nextInt(1000) < _probability;
    }

    public void setup() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
    }

    public void intro() {
        StdDraw.text(WIDTH / 2, HEIGHT / 4, _npc.introduce());
        StdDraw.picture(WIDTH / 2, HEIGHT * 2 / 3, _npc._imgFile, 10, 10);
        StdDraw.show();
    }

    @Override
    public void action() {
        setup();
        intro();
        StdDraw.pause(3000);
        char choice = playerChoice();
        drawPlayerChoice(choice);
        StdDraw.pause(500);
        npcResp(choice);
    }

    public char playerChoice() {
        StdDraw.clear(Color.BLACK);
        StdDraw.text(WIDTH / 2, HEIGHT - 10,
                     "Press (L) to lie. \n" +
                             "Press (T) to tell the truth.");
        StdDraw.picture(WIDTH / 2, HEIGHT * 2 / 3, _npc._imgFile, 10, 10);
        StdDraw.show();
        while (!StdDraw.hasNextKeyTyped()) {
        }
        char resp = StdDraw.nextKeyTyped();
        while (resp != 'l' && resp != 't') {
            if (StdDraw.hasNextKeyTyped()) {
                resp = StdDraw.nextKeyTyped();
            }
        }
        return resp;
    }

    private void npcResp(char resp) {
        StdDraw.clear(Color.BLACK);
        StdDraw.picture(WIDTH / 2, HEIGHT * 2 / 3, _npc._imgFile, 10, 10);
        if (resp == 'l') {
            if (playerLieSuccess()) {
                int charismaGain = _r.nextInt(20) + 1;
                _player.gainCharisma(charismaGain);
                StdDraw.text(WIDTH / 2, HEIGHT - 10,
                             "Your lie was successful. \n" +
                                     "You gained " + charismaGain
                                     + " charisma.");
                StdDraw.text(WIDTH / 2, HEIGHT / 4, _npc.getFriendlyResponse());
                StdDraw.show();
                StdDraw.pause(7000);
                return;
            } else {
                StdDraw.text(WIDTH / 2, HEIGHT - 10,
                             "They saw through your lie! \n" +
                                     "It's time to ddddduel!");
                StdDraw.text(WIDTH / 2, HEIGHT / 4, _npc.getAngryResponse());
            }
        } else {
            StdDraw.text(WIDTH / 2, HEIGHT - 10,
                         "Oh no! You made them angry! \n" +
                                 "It's time to ddddduel!");
            StdDraw.text(WIDTH / 2, HEIGHT / 4, _npc.getTruth());
        }
        StdDraw.show();
        StdDraw.pause(7000);
        new Fight(1, _player).action();
    }

    private void drawPlayerChoice(char playerChoice) {
        StdDraw.clear(Color.BLACK);
        StdDraw.picture(WIDTH / 2, HEIGHT * 2 / 3, _npc._imgFile, 10, 10);
        String choice = "lie";
        if (playerChoice == 't') {
            choice = "tell the truth";
        }
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "You chose to " + choice);
        StdDraw.pause(1000);
    }

    private boolean playerLieSuccess() {
        return _r.nextInt(100) < _player.getCharisma();
    }
}
