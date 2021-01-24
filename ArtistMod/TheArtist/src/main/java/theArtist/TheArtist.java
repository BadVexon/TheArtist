package theArtist;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import theArtist.cards.*;
import theArtist.relics.BlueBrush;
import theArtist.util.RainbowOrb;

import java.util.ArrayList;
import java.util.List;

import static theArtist.ArtistMod.*;
import static theArtist.TheArtist.Enums.COLOR_RAINBOW;

public class TheArtist extends CustomPlayer {
    private static final String[] orbTextures = {
            "artistmodResources/images/char/artistChar/orb/layer1.png",
            "artistmodResources/images/char/artistChar/orb/layer2.png",
            "artistmodResources/images/char/artistChar/orb/layer3.png",
            "artistmodResources/images/char/artistChar/orb/layer4.png",
            "artistmodResources/images/char/artistChar/orb/layer5.png",
            "artistmodResources/images/char/artistChar/orb/layer6.png",
            "artistmodResources/images/char/artistChar/orb/layer1d.png",
            "artistmodResources/images/char/artistChar/orb/layer2d.png",
            "artistmodResources/images/char/artistChar/orb/layer3d.png",
            "artistmodResources/images/char/artistChar/orb/layer4d.png",
            "artistmodResources/images/char/artistChar/orb/layer5d.png",};
    private static final String ID = makeID("theArtist");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    public TheArtist(String name, PlayerClass setClass) {
        super(name, setClass, new RainbowOrb(orbTextures, "artistmodResources/images/char/artistChar/orb/vfx.png", null), new SpriterAnimation(
                "artistmodResources/images/char/artistChar/artist.scml"));
        initializeClass(null,
                ARTIST_SHOULDER_1,
                ARTIST_SHOULDER_2,
                ARTIST_CORPSE,
                getLoadout(), 20.0F, -10.0F, 200.0F, 300.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                71, 71, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(StrokeStrike.ID);
        retVal.add(StrokeStrike.ID);
        retVal.add(StrokeStrike.ID);
        retVal.add(StrokeStrike.ID);
        retVal.add(DaringDefend.ID);
        retVal.add(DaringDefend.ID);
        retVal.add(DaringDefend.ID);
        retVal.add(DaringDefend.ID);
        retVal.add(BrushBash.ID);
        //TODO:new card here
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(BlueBrush.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 7;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_RAINBOW;
    }

    @Override
    public Color getCardTrailColor() {
        return rainbow.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new PalettePick();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }


    @Override
    public AbstractPlayer newInstance() {
        return new TheArtist(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return rainbow.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return rainbow.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList<>();
        panels.add(new CutscenePanel("artistmodResources/images/charSelect/thingy.png"));
        return panels;
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_ARTIST;
        @SpireEnum(name = "ARTIST_RAINBOW_COLOR")
        public static AbstractCard.CardColor COLOR_RAINBOW;
        @SpireEnum(name = "ARTIST_RAINBOW_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
