package theArtist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import theArtist.patches.ConfirmationGridCardSelectCallback;
import theArtist.patches.CenterGridCardSelectScreen;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static theArtist.ArtistMod.theCanvas;

public class DecideToDispenseAction extends AbstractGameAction {

    public DecideToDispenseAction() {
        actionType = ActionType.SPECIAL;
        duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_MED) {
            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            AbstractCard c = theCanvas.dispense(false);
            group.addToTop(c);

            CenterGridCardSelectScreen.centerGridSelect = true;
            ConfirmationGridCardSelectCallback.callback = cardGroup -> {
                ConfirmationGridCardSelectCallback.callback = null;
                CenterGridCardSelectScreen.centerGridSelect = false;

                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c));
                theCanvas.dispense(true);
            };
            AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, "Add this Painting into your hand?");
            try {
                Method m = GridCardSelectScreen.class.getDeclaredMethod("updateCardPositionsAndHoverLogic");
                m.setAccessible(true);
                m.invoke(AbstractDungeon.gridSelectScreen);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignore) {
            }
            AbstractDungeon.overlayMenu.cancelButton.show(GridCardSelectScreen.TEXT[1]);
        }
        tickDuration();
    }
}