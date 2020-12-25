package byow.Core;

import java.util.Random;

public class RandomStringFactory {
    private static final String[] _randomCharacterNames = {"Gimp",
            "Iron Rod",
            "Super Fortress",
            "Blackbird", "Hell Cat", "Zero",
            "Fherry San", "Sherry Fan",
            "Sasha Serafina", "Alexandra Putina",
            "Super Fan V", "Perrito de la Calle", "Reservoir Dog", "Sir "
            + "Gomez IV", "Francis From the Tower", "Choi Chon Kit",
            "Bowie '747' Lam", "任天堂株式会社", "樊雪儿", "Terror of "
            + "Tenochtitlan", "Oleg Gordievsky", "Oracle of "
            + "Oaxaca", "Janitor of Jieyang", "Viceroy of Berkeley", "Duke of "
            + "Fremont", "Sorcerer of 61B", "Lord of "
            + "the East"
            , "Vladivostok", "Czar of the Mazes",
            "Ranelle the Great", "Finito Fibonacci il Quarantesimo",
            "De Lomita", "Del Amo", "Playa "
            + "Redondo"
            , "San Pedro", "The Korean Bell"};
    private static final String[] _randomUnicodeFaces = {"( ͡° ͜ʖ ͡°)",
            "ʕ•ᴥ•ʔ", "(▀̿Ĺ̯▀̿ ̿)", "༼ つ ◕_◕ ༽つ", "ಠ_ಠ", "(ᵔᴥᵔ)", "ᕦʕ •ᴥ•ʔᕤ",
            "┏ʕ "
                    + "•ᴥ•ʔ┛", "┬┴┬┴┤•ᴥ•ʔ├┬┴┬┴", "ʅʕ•ᴥ•ʔʃ", "┴┬┴┤ᵒᴥᵒᶅ├┬┴┬┴",
            "ʕ "
                    + "•́؈•̀)", "ʕ •ᴥ•ʔゝ☆"};


    public String getRandomName() {
        long randomSeed = new Random().nextLong();
        Random rand = new Random(randomSeed);
        int randomIndex = rand.nextInt(_randomCharacterNames.length);
        return _randomCharacterNames[randomIndex];
    }

    public String getRandomUnicodeFace() {
        long randomSeed = new Random().nextLong();
        Random rand = new Random(randomSeed);
        int randomIndex = rand.nextInt(_randomUnicodeFaces.length);
        return _randomUnicodeFaces[randomIndex];
    }
}
