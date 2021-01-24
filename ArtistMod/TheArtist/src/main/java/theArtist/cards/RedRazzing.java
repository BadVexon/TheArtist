package theArtist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.AbstractCanvas;

import static theArtist.ArtistMod.theCanvas;

public class RedRazzing extends AbstractArtistCard {

    public static final String ID = makeID(RedRazzing.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public RedRazzing() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            atb(applyToEnemy(q, autoVuln(q, magicNumber)));
        }
        if (theCanvas.clear()) {
            atb(paint(AbstractCanvas.VexColor.RED));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}