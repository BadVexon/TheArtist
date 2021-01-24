package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ScrapeEffect;
import theArtist.actions.ScrapePaintingsAction;
import theArtist.actions.ScrapePaintingsFollowUpAction;

public class WatercolorWhap extends AbstractArtistCard {

    public static final String ID = makeID(WatercolorWhap.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public WatercolorWhap() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {// 44
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new ScrapeEffect(m.hb.cX, m.hb.cY), 0.1F));// 45
        }

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));// 48
        AbstractDungeon.actionManager.addToBottom(new ScrapePaintingsAction(p, this.magicNumber));// 50
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.4F));// 51
        AbstractDungeon.actionManager.addToBottom(new ScrapePaintingsFollowUpAction());// 52
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}