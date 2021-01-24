package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArtist.ArtistMod.theCanvas;

public class ColoredCollision extends AbstractArtistCard {

    public static final String ID = makeID(ColoredCollision.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;

    public ColoredCollision() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 16;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (theCanvas.chromatic()) {
            atb(new GainEnergyAction(1));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
            initializeDescription();
        }
    }
}