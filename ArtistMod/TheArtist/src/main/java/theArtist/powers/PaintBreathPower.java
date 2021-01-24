package theArtist.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArtist.ArtistMod;
import theArtist.actions.PaintAction;
import theArtist.cards.AbstractPaintingCard;
import theArtist.util.TextureLoader;

import java.util.Iterator;

public class PaintBreathPower extends AbstractPower {
    public static final String POWER_ID = ArtistMod.makeID("PaintBreathPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("artistmodResources/images/powers/PaintBreath84.png");
    private static final Texture tex32 = TextureLoader.getTexture("artistmodResources/images/powers/PaintBreath32.png");

    public PaintBreathPower(int amount) {
        name = NAME;
        ID = POWER_ID;
        this.amount = amount;
        this.owner = AbstractDungeon.player;
        type = PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        int count = 0;// 36
        Iterator var3 = AbstractDungeon.actionManager.cardsPlayedThisTurn.iterator();// 37

        while (var3.hasNext()) {
            AbstractCard c = (AbstractCard) var3.next();
            if (c instanceof AbstractPaintingCard) {// 38
                ++count;// 39
            }
        }

        if (count > 0) {// 42
            this.flash();// 43

            for (int i = 0; i < count; ++i) {// 44
                AbstractDungeon.actionManager.addToBottom(new PaintAction(AbstractPaintingCard.getRandomColor()));
            }
        }

    }// 54
}