package theArtist.actions;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArtist.cards.AbstractPaintingCard;

public class PaintbrushPanicAction extends AbstractXAction {
    private int bonusAmt;

    public PaintbrushPanicAction(int bonusAmt) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
        this.bonusAmt = bonusAmt;
    }

    public void initialize(int totalAmount) {
        super.initialize(totalAmount);
        this.amount += bonusAmt;
    }

    public void update() {
        if (amount > 0) {
            for (int i = 0; i < amount; i++) {
                AbstractDungeon.actionManager.addToBottom(new PaintAction(AbstractPaintingCard.getRandomColor()));
            }
        }

        this.isDone = true;
    }
}