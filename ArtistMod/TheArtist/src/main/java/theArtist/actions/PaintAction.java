package theArtist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArtist.AbstractCanvas;
import theArtist.ArtistMod;
import theArtist.TheArtist;

import static theArtist.ArtistMod.renderStuff;
import static theArtist.ArtistMod.theCanvas;

public class PaintAction extends AbstractGameAction {
    private AbstractCanvas.VexColor color;

    public PaintAction(AbstractCanvas.VexColor color1) {
        this(color1, 1);
    }

    public PaintAction(AbstractCanvas.VexColor color1, int amount) {
        this.amount = amount;
        this.color = color1;
        this.target = AbstractDungeon.player;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (!(AbstractDungeon.player instanceof TheArtist) && !ArtistMod.renderStuff) {
            renderStuff = true;
        }
        for (int i = 0; i < amount; i++) {
            theCanvas.paint(color);
        }
        this.isDone = true;
    }
}