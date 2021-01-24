package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.AbstractCanvas;
import theArtist.actions.PaintAction;

import static theArtist.ArtistMod.theCanvas;

public class DoubleDemolition extends AbstractArtistCard {

    public static final String ID = makeID(DoubleDemolition.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;

    public DoubleDemolition() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 6;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        atb(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (theCanvas.chromatic()) {
            for (AbstractCanvas.VexColor c : theCanvas.colorList()) {
                atb(new PaintAction(c));
            }
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }
}