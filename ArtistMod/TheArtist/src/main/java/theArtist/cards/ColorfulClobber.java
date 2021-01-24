package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArtist.ArtistMod.theCanvas;

public class ColorfulClobber extends AbstractArtistCard {

    public static final String ID = makeID(ColorfulClobber.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public ColorfulClobber() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (theCanvas.clear()) {
            atb(paint(getRandomColor()));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }
}