package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArtist.ArtistMod.theCanvas;

public class ChromaCrash extends AbstractArtistCard {

    public static final String ID = makeID(ChromaCrash.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public ChromaCrash() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SMASH));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (theCanvas.chromatic()) {
                    AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));// 23
                    AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));// 24
                }
                isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }
}