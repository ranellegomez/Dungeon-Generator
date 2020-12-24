package byow.Core;

import java.io.File;
import java.util.Random;

public class NPC {
    final static File dir = new File("./imgs/");
    String _truth;
    String _friendlyResponse;
    String _angryResponse;
    String _name;
    String _imgFile;
    String _intro;

    public NPC(String intro, String name, String truth, String lieSuccess,
               String lieFail) {
        _intro = intro;
        _truth = truth;
        _friendlyResponse = lieSuccess;
        _angryResponse = lieFail;
        _name = name;
        _imgFile = randomFile();
    }

    static String randomFile() {
        File[] files = dir.listFiles();
        Random rand = new Random();
        File file = files[rand.nextInt(files.length)];
        return file.toString();
    }

    String introduce() {
        return _intro;
    }

    String getTruth() {
        return _truth;
    }

    String getFriendlyResponse() {
        return _friendlyResponse;
    }

    String getAngryResponse() {
        return _angryResponse;
    }

    String getName() {
        return _name;
    }
}
