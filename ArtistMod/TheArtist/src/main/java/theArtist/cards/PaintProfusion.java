package theArtist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArtist.ArtistMod.theCanvas;

public class PaintProfusion extends AbstractArtistCard {

    public static final String ID = makeID(PaintProfusion.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 2;

    public PaintProfusion() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (theCanvas.chromatic()) {
            atb(paint(getRandomColor()));
            atb(new WaitAction(0.1F));
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    for (int i = 0; i < 2; i++) {
                        AbstractDungeon.actionManager.addToBottom(shuffleIn(theCanvas.dispense(false)));
                    }
                    isDone = true;
                }
            });
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