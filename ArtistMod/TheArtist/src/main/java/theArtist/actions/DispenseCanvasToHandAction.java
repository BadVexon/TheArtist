package theArtist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theArtist.ArtistMod.theCanvas;

public class DispenseCanvasToHandAction extends AbstractGameAction {
    private boolean clear;

    public DispenseCanvasToHandAction(boolean clearance) {
        this.clear = clearance;
        this.target = AbstractDungeon.player;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(theCanvas.dispense(clear)));
        this.isDone = true;
    }
}