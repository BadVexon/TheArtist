package theArtist.actions;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArtist.cards.AbstractPaintingCard;

public class CascadeAction extends AbstractXAction {

    public CascadeAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        if (!AbstractDungeon.player.drawPile.isEmpty())
        {
            AbstractCard c = AbstractDungeon.player.drawPile.getTopCard();
            AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
            if (c instanceof AbstractPaintingCard) {
                AbstractDungeon.actionManager.addToBottom(new CascadeAction());
            }
        }
        this.isDone = true;
    }
}