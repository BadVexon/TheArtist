package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.AbstractCanvas;

public class RedReaper extends AbstractArtistCard {

    public static final String ID = makeID(RedReaper.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 3;

    public RedReaper() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 12;
        isMultiDamage = true;
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        atb(paint(AbstractCanvas.VexColor.RED, magicNumber));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}