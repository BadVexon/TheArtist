package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.AbstractCanvas;

import static theArtist.ArtistMod.theCanvas;

public class AmberAttack extends AbstractArtistCard {

    public static final String ID = makeID(AmberAttack.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 3;

    public AmberAttack() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 16;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (theCanvas.clear()) {
            atb(paint(AbstractCanvas.VexColor.YELLOW, 2));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(6);
            initializeDescription();
        }
    }
}