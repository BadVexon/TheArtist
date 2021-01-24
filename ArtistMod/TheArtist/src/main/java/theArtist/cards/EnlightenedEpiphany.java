package theArtist.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class EnlightenedEpiphany extends AbstractArtistCard {

    public static final String ID = makeID(EnlightenedEpiphany.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public EnlightenedEpiphany() {
        super(ID, COST, TYPE, RARITY, TARGET);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardList = new ArrayList<>();
        cardList.add(new RedRage());
        cardList.add(new BlueBurst());
        cardList.add(new AquaAnger());
        cardList.add(new MagentaMadness());
        cardList.add(new GreenGreed());
        cardList.add(new YellowYearning());
        cardList.add(new AquaAnnoyance());
        cardList.add(new ArdentAqua());
        cardList.add(new BlueBarrier());
        cardList.add(new BrushBash());
        cardList.add(new ColorCopy());
        cardList.add(new ContrastingClash());
        cardList.add(new DeepDream());
        cardList.add(new DualDebuffs());
        cardList.add(new GreenGreatness());
        cardList.add(new IndigoIncapitation());
        cardList.add(new MagentaMist());
        cardList.add(new MeanMagenta());
        cardList.add(new PaintbrushPanic());
        cardList.add(new PalettePick());
        cardList.add(new RainbowRazor());
        cardList.add(new RainbowReality());
        cardList.add(new RedReaper());
        cardList.add(new ResourceRampage());
        cardList.add(new VibrantVision());

        AbstractCard c = cardList.get(AbstractDungeon.cardRandomRng.random(cardList.size() - 1));
        c.setCostForTurn(0);
        atb(makeInHand(c));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}