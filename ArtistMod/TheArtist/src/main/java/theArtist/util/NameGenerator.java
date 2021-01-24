package theArtist.util;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;
import java.util.Arrays;

public class NameGenerator {
    public static String returnRandomName() {
        int rng = MathUtils.random(8);
        switch (rng) {
            case 0:
                // Angry Man, Sad Vase
                return getAdjective() + " " + getNoun();
            case 1:
                // The Man, My Vase
                return getArticle() + " " + getNoun();
            case 2:
                // Man, Vase
                return getNoun();
            case 3:
                // Vex's Man, Bowser's Vase
                return getName() + "'s " + getNoun();
            case 4:
                // Walking, Crying
                return getVerb();
            case 5:
                // Walking Sadly, Crying Madly
                return getVerb() + " " + getAdverb();
            case 6:
                // Man Walking, Vase Crying
                return getNoun() + " " + getVerb();
            case 7:
                // Angry, Sad
                return getAdjective();
            case 8:
                return getName() + " " + getVerb();
        }
        return null;
    }

    private static String getAdverb() {
        ArrayList<String> adverbList = new ArrayList<>(Arrays.asList(CardCrawlGame.languagePack.getUIString("NAME_GENERATOR_ADVERBS").TEXT));
        return adverbList.get(MathUtils.random(adverbList.size() - 1));
    }

    private static String getVerb() {
        ArrayList<String> verbList = new ArrayList<>(Arrays.asList(CardCrawlGame.languagePack.getUIString("NAME_GENERATOR_VERBS").TEXT));
        return verbList.get(MathUtils.random(verbList.size() - 1));
    }

    private static String getName() {
        ArrayList<String> nameList = new ArrayList<>(Arrays.asList(CardCrawlGame.languagePack.getUIString("NAME_GENERATOR_NAMES").TEXT));
        nameList.add(CardCrawlGame.playerName);
        return nameList.get(MathUtils.random(nameList.size() - 1));
    }

    private static String getArticle() {
        ArrayList<String> articleList = new ArrayList<>();
        articleList.add("The");
        articleList.add("My");
        articleList.add("His");
        articleList.add("Her");
        articleList.add("Their");
        articleList.add("Your");
        return articleList.get(MathUtils.random(articleList.size() - 1));
    }

    private static String getNoun() {
        ArrayList<String> nounList = new ArrayList<>(Arrays.asList(CardCrawlGame.languagePack.getUIString("NAME_GENERATOR_NOUNS").TEXT));
        return nounList.get(MathUtils.random(nounList.size() - 1));
    }

    private static String getAdjective() {
        ArrayList<String> adjectiveList = new ArrayList<>(Arrays.asList(CardCrawlGame.languagePack.getUIString("NAME_GENERATOR_ADJECTIVES").TEXT));
        return adjectiveList.get(MathUtils.random(adjectiveList.size() - 1));
    }
}