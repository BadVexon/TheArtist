package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RainbowRazor extends AbstractArtistCard {

    public static final String ID = makeID(RainbowRazor.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;

    public RainbowRazor() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageRandomEnemyAction(makeInfo(), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        atb(paint(getRandomColor()));
        if (upgraded) {
            atb(paint(getRandomColor()));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}