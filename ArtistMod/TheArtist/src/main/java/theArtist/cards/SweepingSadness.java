package theArtist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.powers.CursedPower;

public class SweepingSadness extends AbstractArtistCard {

    public static final String ID = makeID(SweepingSadness.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 2;

    public SweepingSadness() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 10;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        atb(applyToEnemy(m, new CursedPower(m, magicNumber)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}