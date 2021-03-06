package theArtist.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArtist.ArtistMod;
import theArtist.cards.AbstractPaintingCard;
import theArtist.util.TextureLoader;

public class PaintballPeltPower extends AbstractPower {
    public static final String POWER_ID = ArtistMod.makeID("PaintballPeltPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("artistmodResources/images/powers/FantasyForm84.png");
    private static final Texture tex32 = TextureLoader.getTexture("artistmodResources/images/powers/FantasyForm32.png");

    public PaintballPeltPower(int magic) {
        name = NAME;
        ID = POWER_ID;
        this.amount = magic;
        this.owner = AbstractDungeon.player;
        type = PowerType.BUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public void onCardDraw(AbstractCard card) {
        if (card instanceof AbstractPaintingCard) {// 36
            this.flash();// 37
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        }
    }// 40
}