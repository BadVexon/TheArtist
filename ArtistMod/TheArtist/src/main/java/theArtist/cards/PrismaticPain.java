package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArtist.ArtistMod.theCanvas;

public class PrismaticPain extends AbstractArtistCard {

    public static final String ID = makeID(PrismaticPain.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public PrismaticPain() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < theCanvas.colorList().size(); i++) {
            atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.FIRE));
            atb(new DrawCardAction(p, 1));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            initializeDescription();
        }
    }
}