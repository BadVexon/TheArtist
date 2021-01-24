package theArtist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArtist.ArtistMod.theCanvas;

public class DualDash extends AbstractArtistCard {

    public static final String ID = makeID(DualDash.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public DualDash() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        if (theCanvas.chromatic()) {
            atb(applyToEnemy(m, autoWeak(m, 1)));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }
}