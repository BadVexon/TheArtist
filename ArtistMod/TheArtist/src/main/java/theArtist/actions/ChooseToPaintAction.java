package theArtist.actions;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import theArtist.AbstractCanvas;
import theArtist.ArtistMod;
import theArtist.TheArtist;
import theArtist.patches.CenterGridCardSelectScreen;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static theArtist.ArtistMod.makeCardPath;
import static theArtist.ArtistMod.theCanvas;

public class ChooseToPaintAction extends AbstractGameAction {
    private boolean pickCard = false;
    private boolean canvasOnly1;

    public ChooseToPaintAction(boolean canvasOnly, int amt) {
        this.canvasOnly1 = canvasOnly;
        this.amount = amt;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.WAIT;
        target = AbstractDungeon.player;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_XFAST) {
            pickCard = true;
            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            if (canvasOnly1) {
                boolean red = false;
                boolean blue = false;
                boolean green = false;
                boolean yellow = false;
                boolean aqua = false;
                boolean magenta = false;
                boolean rainbow = false;
                for (AbstractCanvas.VexColor color : theCanvas.colorList()) {
                    switch (color) {
                        case RED:
                            red = true;
                            break;
                        case BLUE:
                            blue = true;
                            break;
                        case GREEN:
                            green = true;
                            break;
                        case YELLOW:
                            yellow = true;
                            break;
                        case AQUA:
                            aqua = true;
                            break;
                        case MAGENTA:
                            magenta = true;
                            break;
                        case RAINBOW:
                            rainbow = true;
                            break;
                    }
                }
                if (red) group.addToTop(new PaintChoiceCard("Red", makeCardPath("RED.png"), "Paint 1 Red."));
                if (blue) group.addToTop(new PaintChoiceCard("Blue", makeCardPath("BLUE.png"), "Paint 1 Blue."));
                if (green) group.addToTop(new PaintChoiceCard("Green", makeCardPath("GREEN.png"), "Paint 1 Green."));
                if (yellow)
                    group.addToTop(new PaintChoiceCard("Yellow", makeCardPath("YELLOW.png"), "Paint 1 Yellow."));
                if (aqua) group.addToTop(new PaintChoiceCard("Aqua", makeCardPath("AQUA.png"), "Paint 1 Aqua."));
                if (magenta)
                    group.addToTop(new PaintChoiceCard("Magenta", makeCardPath("MAGENTA.png"), "Paint 1 Magenta."));
                if (rainbow)
                    group.addToTop(new PaintChoiceCard("Rainbow", makeCardPath("RAINBOW.png"), "Paint 1 Rainbow."));
            } else {
                group.addToTop(new PaintChoiceCard("Red", makeCardPath("RED.png"), "Paint 1 Red."));
                group.addToTop(new PaintChoiceCard("Blue", makeCardPath("BLUE.png"), "Paint 1 Blue."));
                group.addToTop(new PaintChoiceCard("Green", makeCardPath("GREEN.png"), "Paint 1 Green."));
                group.addToTop(new PaintChoiceCard("Yellow", makeCardPath("YELLOW.png"), "Paint 1 Yellow."));
                group.addToTop(new PaintChoiceCard("Aqua", makeCardPath("AQUA.png"), "Paint 1 Aqua."));
                group.addToTop(new PaintChoiceCard("Magenta", makeCardPath("MAGENTA.png"), "Paint 1 Magenta."));
                group.addToTop(new PaintChoiceCard("Pink", makeCardPath("PINK.png"), "Paint 1 Pink."));
                group.addToTop(new PaintChoiceCard("Purple", makeCardPath("PURPLE.png"), "Paint 1 Purple."));
                group.addToTop(new PaintChoiceCard("Rainbow", makeCardPath("RAINBOW.png"), "Paint 1 Rainbow."));
            }

            CenterGridCardSelectScreen.centerGridSelect = true;
            AbstractDungeon.gridSelectScreen.open(group, amount, "Choose a Color to Paint.", false, false, false, false);
            try {
                Method m = GridCardSelectScreen.class.getDeclaredMethod("updateCardPositionsAndHoverLogic");
                m.setAccessible(true);
                m.invoke(AbstractDungeon.gridSelectScreen);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignore) {
            }
        } else {
            if (pickCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                pickCard = false;
                CenterGridCardSelectScreen.centerGridSelect = false;

                for (AbstractCard cardChoice : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if (cardChoice.name.equals("Red")) {
                        AbstractDungeon.actionManager.addToTop(new PaintAction(AbstractCanvas.VexColor.RED));
                    }
                    if (cardChoice.name.equals("Blue")) {
                        AbstractDungeon.actionManager.addToTop(new PaintAction(AbstractCanvas.VexColor.BLUE));
                    }
                    if (cardChoice.name.equals("Green")) {
                        AbstractDungeon.actionManager.addToTop(new PaintAction(AbstractCanvas.VexColor.GREEN));
                    }
                    if (cardChoice.name.equals("Yellow")) {
                        AbstractDungeon.actionManager.addToTop(new PaintAction(AbstractCanvas.VexColor.YELLOW));
                    }
                    if (cardChoice.name.equals("Aqua")) {
                        AbstractDungeon.actionManager.addToTop(new PaintAction(AbstractCanvas.VexColor.AQUA));
                    }
                    if (cardChoice.name.equals("Magenta")) {
                        AbstractDungeon.actionManager.addToTop(new PaintAction(AbstractCanvas.VexColor.MAGENTA));
                    }
                    if (cardChoice.name.equals("Rainbow")) {
                        AbstractDungeon.actionManager.addToTop(new PaintAction(AbstractCanvas.VexColor.RAINBOW));
                    }
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                CenterGridCardSelectScreen.centerGridSelect = false;
                isDone = true;
            }
        }
        tickDuration();
    }

    private static class PaintChoiceCard extends CustomCard {
        private static final int COST = -2;

        PaintChoiceCard(String name, String IMG, String description) {
            super(makeID(name), name, IMG, COST, description, CardType.POWER, TheArtist.Enums.COLOR_RAINBOW, CardRarity.SPECIAL, CardTarget.NONE);
        }

        private static String makeID(String id) {
            return ArtistMod.makeID("Shifting" + id);
        }


        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        }

        @Override
        public void upgrade() {

        }
    }
}