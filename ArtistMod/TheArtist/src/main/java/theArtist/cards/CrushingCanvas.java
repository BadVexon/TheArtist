package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;

import java.util.Iterator;

public class CrushingCanvas extends AbstractArtistCard {

    public static final String ID = makeID(CrushingCanvas.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;

    public CrushingCanvas() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 2;
    }

    public static int countCards() {
        int count = 0;// 43
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();// 44

        AbstractCard c;
        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (c instanceof AbstractPaintingCard) {// 45
                ++count;// 46
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();// 49

        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (c instanceof AbstractPaintingCard) {// 50
                ++count;// 51
            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();// 54

        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (c instanceof AbstractPaintingCard) {// 55
                ++count;// 56
            }
        }

        return count;// 59
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;// 81
        this.baseDamage += this.magicNumber * countCards();// 82
        super.calculateCardDamage(mo);// 84
        this.baseDamage = realBaseDamage;// 86
        this.isDamageModified = this.damage != this.baseDamage;// 89
    }// 90

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;// 96
        this.baseDamage += this.magicNumber * countCards();// 97
        super.applyPowers();// 99
        this.baseDamage = realBaseDamage;// 101
        this.isDamageModified = this.damage != this.baseDamage;// 104
    }// 105

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new ViceCrushEffect(m.hb.cX, m.hb.cY)));
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.NONE));
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