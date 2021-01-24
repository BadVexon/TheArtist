package theArtist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArtist.cards.AbstractPaintingCard;

public class ScrapePaintingsFollowUpAction extends AbstractGameAction {
    public ScrapePaintingsFollowUpAction() {
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER) {
            for (AbstractCard c : ScrapePaintingsAction.scrapedCards) {
                if (!(c instanceof AbstractPaintingCard)) {
                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
            }
            ScrapePaintingsAction.scrapedCards.clear();
        }

        this.tickDuration();
    }
}