package byow.Core;

import edu.princeton.cs.algs4.StdRandom;


public class NPCSet {
    /**
     * Player makes a lie about his 19th birthday party taking place in
     * Lomita. He's a hitchiker who somehow ended up in an abandoned Madera,
     * California. He pleads for his life, telling the stranger that he knows
     * both of his daughters (giving both names) and says he will update them
     * with their father's status of wellbeing if he let's him proceed. If he
     * kills him, he will never see his daughters again, the player threatens.
     * <p>
     * Truth: He shows no fear to El Machete's threats and Machete pulls out
     * his brass knuckles.
     * By Ranelle Gomez
     */
    private static final NPC ElMACHETE = new NPC("Excuse me, sir?! Have you "
                                                         + "seen my "
                                                         + "daughters? I "
                                                         + "haven't seen them"
                                                         + " since the Great "
                                                         + "Plague. Their "
                                                         + "names are Karen "
                                                         + "and Butters"
                                                         + " McCorn.",
                                                 "El Machete",
                                                 "How dare you call my "
                                                         + "daughters fat! "
                                                         + "They were on "
                                                         + "America's Next "
                                                         + "Top Model. "
                                                         + "This will be your"
                                                         + " last humbling "
                                                         + "experience.",
                                                 "You're from Lomita, eh?"
                                                         + " Carry on! You "
                                                         + "didn't come off as"
                                                         + " someone from there"
                                                         + " with them "
                                                         + "Avatiors shades"
                                                         + " you is wearin'.",
                                                 "You lie! Karen actually "
                                                         + "went to "
                                                         + "Riverside, not "
                                                         + "Berkeley, and she"
                                                         + " definitely "
                                                         + "wouldn't be "
                                                         + "friends with a "
                                                         + "lowlife."
                                                         + " [Pulls out brass "
                                                         + "knuckles.]");
    private static final NPC GIMP =
            new NPC("[The Gimp approaches. Hisses.]", "Gimp", "[Hisses. "
                    + "Jumps on you.]", "[Hisses. Crawls away.]", "[Hisses. "
                            + "Slaps your face.]");
    /**
     * Scenario 1. Player contrives a story of his seeing his languishing
     * mother in Saint Petersburg. Guard believes him and empathizes as his
     * sister died from the plague a few months earlier and he, too, is from
     * Saint Petersburg.
     * <p>
     * Scenario 2. Player continues speaking English. He shows the guard his
     * false certification of travel. The guard (hardly) understands English
     * but initially believes the player and is about to let him go. At the
     * last minute, he realizes the documentation is forged and duels the
     * player.
     * By Ranelle Gomez
     */
    private static final NPC VLADIVOSTOK = new NPC("Немедленно остановитесь. "
                                                           + "Где ваши "
                                                           + "документы об "
                                                           + "освобождении от"
                                                           + " комендантского"
                                                           + " часа?",
                                                   "Владивосто́к", "How "
                                                           + "stupid are "
                                                           + "you to tell the"
                                                           + " truth for "
                                                           + "once, Yankee?"
                                                           + " You will "
                                                           + "feel Lenin's "
                                                           + "hammer against "
                                                           + "your skull "
                                                           + "faster "
                                                           + "than "
                                                           + "you can say "
                                                           + "'Coca Cola.'",
                                                   "Мне жаль это слышать. Моя"
                                                           + " сестра "
                                                           + "Александра в "
                                                           + "августе приехала"
                                                           + " в "
                                                           + "Санкт-Петербург."
                                                           + " Продолжать. "
                                                           + "Возьми лишний "
                                                           + "бутерброд, "
                                                           + "который у меня "
                                                           + "есть.",
                                                   "These papers are"
                                                           + " fake. Say "
                                                           + "'hi' to Tolstoy "
                                                           + "for me after I'm"
                                                           + " done with you! "
                                                           + "[Pulls out "
                                                           + "Matryoshka dolls"
                                                           + " with concealed "
                                                           + "boxing gloves.]");
    /**
     * A Feral Ghoul whose nihilism pervades his personality.
     * By Ranelle Gomez
     */
    private static final NPC FERALGHOUL = new NPC("Hey, smoothskin, "
                                                          + "where do you "
                                                          + "think "
                                                          + "you're going?",
                                                  "Feral Ghoul",
                                                  "Who "
                                                          + "are you calling "
                                                          + "burnt? "
                                                          + "You're going to "
                                                          + "get it,"
                                                          + " punk. Err, I "
                                                          + "mean, smoothskin"
                                                          + ".",
                                                  "Sorry to hear that. I "
                                                          + "don't wanna "
                                                          + "waste your "
                                                          + "time. Safe "
                                                          + "travels, "
                                                          + "smoothskin.",
                                                  "You're a goddamn lie! "
                                                          + "Megaton was "
                                                          + "destroyed last "
                                                          + "month. Give me "
                                                          + "your bottle "
                                                          + "caps, "
                                                          + "smoothskin!");
    /**
     * A fun encounter with Gregor from Franz Kafka's The Metamorphosis.
     * By Ranelle Gomez
     */
    private static final NPC GREGORTHEBEETLE =
            new NPC("[Gregor the Beetle approaches. Speaks inaudible beetle "
                            + "English.]",
                    "Gregor "
                            + "Kafka",
                    "[Screams inaudible beetle English in a high pitch. "
                            + "Expands wings.]",
                    "[Mumbles inaudible beetle English. "
                            + "Takes food from your hands. Walks away.]",
                    "[Roars inaudible beetle noise. Lunges at you.]");
    /**
     * Karen is a cynical lady. A human pressure-cooker if you will. The
     * ultimate irony is that one of the most spiteful people holds a
     * career requiring the highest level of compassion.
     * By Francisco De La Torre.
     */
    private static final NPC KAREN =
            new NPC("Excuse me? Do you think you can just come up and talk to"
                            + " ME? RUDE! I’m gonna escort you to the manager"
                            + "... in the sky!", "Karen Smith", "I'm calling "
                            + "the police when I'm done with you"
                            + "!",
                    "Oh my! Kaiser is that way. I'm an RN there. You "
                            + "have "
                            + "30 minutes until your body succumbs to"
                            + " the blood loss"
                            + ". Good luck!",
                    "You must think I’m dumb as a WHO researcher. You "
                            + "aren’t bleeding, but you’re about to be.");

    /**
     * Ivan is a peculiar man. He's a Croatian pirate who loots gold
     * points from his unlucky encounters on the road. He speaks in the third
     * person
     * Nobody knows why his vernacular is in this fashion, but to each his
     * own.
     * By Ranelle Gomez
     */
    private static final NPC IVAN =
            new NPC("I am Ivan Kovačević of Zagreb. Ivan "
                            + "collects "
                            + "gold points for his "
                            + "legion."
                            + " Give "
                            + "up "
                            + "your gold "
                            + "points or face "
                            + "destruction by Ivan Kovačević, "
                            + "peasant.",
                    "Ivan Kovačević", "Foolish "
                            + "American. Ivan has 50 "
                            + "men coming strong from "
                            + "Berkeley if you dare "
                            + "fight me. They're on "
                            + "their way right now. I"
                            + " shall punish you "
                            + "before their arrival.",
                    "Sorry to hear "
                            + "about your stray "
                            + "peregrine falcon. Pretend this "
                            + "never happened. Ivan will "
                            + "spread word to his "
                            + "legion so you remain "
                            + "unbothered.",
                    "Did you really "
                            + "think Ivan would believe "
                            + "that? Peregrine "
                            + "falcons are illegal to"
                            + " own in your land, American. "
                            + "Face extermination!");
    /**
     * Sasquatch is lonely and by happenstance overslept in a cave in by
     * modern-day Fremont, California by 300 years. His only encounters with
     * human were with the friendly Native American tribes near his cave, He is
     * the last of his kind alive in the North American continent (and Planet
     * Earth). His angst grew with the time he's been asleep. One thing he
     * noticed from his return from shut-eye is a huge car factory. He spent
     * his days observing what comes out of it, and it was love at first site
     * for him.
     * By Ranelle Gomez
     */
    private static final NPC SASQUATCH =
            new NPC("Hello, human, I am Sasquatch. What brings you to this "
                            + "part of Fremont?",
                    "Sasquatch", "Nobody ever comes to Fremont to see "
                            + "Sasquatch. Tesla this. Tesla that. After I "
                            + "take care of you, humans will never "
                            + "neglect Sasquatch again!", "Wow, your mother "
                            + "in law bought you a Cybertruck as an 18th "
                            + "birthday present? We can have small talk over "
                            + "tea another time. Go get your car!", "The "
                            + "pandemic "
                            + "forced the factory to cease operations. "
                            + "For "
                            + "lying to me, I'm bringing you down with my "
                            + "bare ape hands! [140-decibel Sasquatch roar "
                            + "ensues.]");

    /**
     * Al is delirious, as usual. His family let him go outside for his good
     * for the past four weeks. Then this transpires...
     * By Francisco De La Torre and Ranelle Gomez
     */
    private static final NPC AL =
            new NPC("Keep away from me and my family. I see the way you’re "
                            + "looking at my Ford F-150 Limited with them "
                            + "glowing eyes.",
                    "Al Fordsen", "You honest-er than you "
                            + "smart–just like all them other Toyota drivers. "
                            + "After I'm "
                            + "done with you, you'll be sayin' 'hi' to your "
                            + "king Kiichiro Toyoda.",
                    "As "
                            + "long as you keep them "
                            + "dirty "
                            + "paws off the truck, you can look at her from "
                            + "two and a quarter steps closer. No funny "
                            + "business!", "False! Your "
                            + "jealousy is your death, Toyo-Boyo. I see them "
                            + "Tundra keys dangling from your back "
                            + "pocket. "
                            + "[Pulls out concealed sharpened Ford keys.]");

    /**
     * Apartment Manager Master Skeet
     * By Francisco De La Torre
     */
    private static final NPC SKEET = new NPC("The Lomita Flats are private "
                                                     + "property. You will "
                                                     + "feel our wrath!",
                                             "Skeet Skeet Skipper, Jr.",
                                             "I'll be danged. "
                                                     + "I'm about to turn ye "
                                                     + "lookin' like the beef"
                                                     + " I just grounded.",
                                             "You must be the visitor the "
                                                     + "Mayor’s been "
                                                     + "yammerin' on about. "
                                                     + "The "
                                                     + "Scientist lives in "
                                                     + "that direction. See "
                                                     + "that small "
                                                     + "green house? That's "
                                                     + "your guy.",
                                             "Carson hasn’t had "
                                                     + "water in 15 years. "
                                                     + "All they drink out "
                                                     + "there is tea with "
                                                     + "balls in 'em. I’m "
                                                     + "about to whoop you "
                                                     + "worse than Skeet "
                                                     + "Senior!");

    /**
     * Little Fordsen
     * By Francisco De La Torre
     */
    private static final NPC MANSON = new NPC("I’m not supposed to talk to "
                                                      + "strangers! If you get "
                                                      + "any closer to me, I’m "
                                                      + "gonna have to get Mr"
                                                      + ". John Fordsen on "
                                                      + "you!",
                                              "Manson Fordsen", "Mr. Fordsen, "
                                                      + "HELP!!! He's trying "
                                                      + "to "
                                                      + "take your truck!",
                                              "That’s right. I’m "
                                                      + "the "
                                                      + "biggest and bravest "
                                                      + "boy"
                                                      + " in town. You "
                                                      + "must be talkin' of "
                                                      + "Pa."
                                                      + "Take "
                                                      + "good"
                                                      + " care of Papa John’s "
                                                      + "truck.",
                                              "Liar! Liar! I"
                                                      + " don’t have a papa. I "
                                                      + "have two mamas. "
                                                      + "Fordsen "
                                                      + "attack!");
    /**
     * French Flattery
     * One should look to an English translation of the French national
     * anthem to see where the author drew his motivation.
     * By Ranelle Gomez
     */
    private static final NPC MEURSAULT = new NPC("Arrêtez! What brings you "
                                                         + "here to South "
                                                         + "Side? We're in a"
                                                         + "quarantine! "
                                                         + "People's Park is "
                                                         + "closed to the "
                                                         + "public.",
                                                 "Meursault Fracois",
                                                 "You will "
                                                         + "eat your words "
                                                         + "and your impure "
                                                         + "blood will water "
                                                         + "my fields!",
                                                 "Merci. I wake up at 5am "
                                                         + "every "
                                                         + "day for a "
                                                         + "run to embellish "
                                                         + "my 6-pack "
                                                         + ". Safe "
                                                         + "travels, gaston!",
                                                 "Your flattery is faker than"
                                                         + " a "
                                                         + "Louis Vuitton "
                                                         + "purse bought on "
                                                         + "eBay. For "
                                                         + "underestimating "
                                                         + "my "
                                                         + "intelligence, I "
                                                         + "will kill you!");
    private static NPC[] _npcArray;
    private static int _size;
    private static int _nextIndex;
    public NPC[] _publicNPCArray;


    public NPCSet() {
        _npcArray = new NPC[]{ElMACHETE, GIMP, VLADIVOSTOK, FERALGHOUL,
                GREGORTHEBEETLE, IVAN, SASQUATCH, KAREN, AL, SKEET, MANSON,
                MEURSAULT};
        _size = _npcArray.length;
        _nextIndex = 0;
        _publicNPCArray = _npcArray.clone();
        StdRandom.shuffle(_npcArray);
    }

    public int getSize() {
        return _size;
    }

    public NPC getRandomNPC() {
        return _npcArray[_nextIndex++];
    }

}
