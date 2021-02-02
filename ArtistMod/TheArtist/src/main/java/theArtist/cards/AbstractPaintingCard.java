package theArtist.cards;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArtist.ArtistMod;
import theArtist.CardIgnore;
import theArtist.actions.PaintAction;
import theArtist.powers.CursedPower;
import theArtist.util.BardHelper;
import theArtist.util.NameGenerator;

@CardIgnore
public class AbstractPaintingCard extends AbstractArtistCard {

    Texture tex;
    int red;
    int blue;
    int green;
    int yellow;
    int aqua;
    int magenta;
    int brown;
    int purple;
    int black;
    int rainbow;
    int greenCards;
    int yellowEnergy;
    int aquaWeak;
    int magentaVuln;
    int brownTemp;
    int purpCurse;
    int rainbowPaint;
    int black2;
    Color hex;

    public static final String ID = makeID(AbstractPaintingCard.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;

    public AbstractPaintingCard(Texture texture, int red1, int blue1, int green1, int yellow1, int aqua1, int magenta1, int brown1, int purple1, int black1, int rainbow1, boolean purge) {
        super(ID, black1, CardType.SKILL, RARITY, TARGET);
        if (red > 0) {
            this.type = CardType.ATTACK;
        }
        loadPaintingCardImage(texture);
        initializeTitle();
        initializeDescription();
        hex = ArtistMod.rainbow;
        black2 = black1;
        red = red1;
        blue = blue1;
        green = green1;
        yellow = yellow1;
        aqua = aqua1;
        magenta = magenta1;
        brown = brown1;
        purple = purple1;
        black = black1;
        rainbow = rainbow1;
        baseDamage = 4 * red1 * (black1 + 1);
        baseBlock = 3 * blue1 * (black1 + 1);
        greenCards = green1 * (black1 + 1);
        yellowEnergy = yellow1 * (black1 + 1);
        aquaWeak = aqua1 * (black1 + 1);
        magentaVuln = magenta1 * (black1 + 1);
        brownTemp = (brown1 * 2) * (black1 + 1);
        purpCurse = (purple1 * 2) * (black1 + 1);
        rainbowPaint = rainbow1 * (black1 + 1);
        tex = texture;
        this.name = ArtistMod.curName;
        if (purge) {
            ArtistMod.curName = NameGenerator.returnRandomName();
        }
        reDescription();
        if (red > 0) {
            this.type = CardType.ATTACK;
        }
        this.initializeTitle();
        if (Loader.isModLoaded("bard")) {
            if (red > 0) {
                this.tags.add(BardHelper.attackTag());
            }
            if (blue > 0) {
                this.tags.add(BardHelper.blockTag());
            }
            if (aqua > 0 || magenta > 0 || purple > 0) {
                this.tags.add(BardHelper.debuffTag());
            }
            if (green > 0 || yellow > 0 || brown > 0) {
                this.tags.add(BardHelper.buffTag());
            }
            if (rainbow > 0) {
                this.tags.add(BardHelper.wildTag());
            }
        }
        if (yellow > 0 && red == 0 && blue == 0 && green == 0 && aqua == 0 && magenta == 0 && brown == 0 && purple == 0 && rainbow == 0)
            exhaust = true;
    }

    private void reDescription() {
        this.rawDescription = "";
        if (red > 0) {
            rawDescription += "[#b0120a]Наносит !D! [#b0120a]урона. NL ";
        }
        if (blue > 0) {
            rawDescription += "[#303f9f]Даёт !B! [#303f9f]Защиты. NL ";
        }
        if (greenCards > 0) {
            if (greenCards == 1) {
                rawDescription += "[#42bd41]Возьмите " + greenCards + " [#42bd41]карту. NL ";
            } else {
                rawDescription += "[#42bd41]Возьмите " + greenCards + " [#42bd41]карт. NL ";
            }
        }
        if (yellowEnergy > 0) {
            rawDescription += "[#ffeb3b]Даёт " + yellowEnergy + " [#ffeb3b]Энергии. NL ";
        }
        if (aquaWeak > 0) {
            rawDescription += "[#26a69a]Накладывает " + aquaWeak + " [#26a69a]Слабости. NL ";
        }
        if (magentaVuln > 0) {
            rawDescription += "[#880e4f]Накладывает " + magentaVuln + " [#880e4f]Уязвимости. NL ";
        }
        if (brownTemp > 0) {
            rawDescription += "[#f0749e]Даёт " + brownTemp + " [#f0749e]Временные [#f0749e]ОЗ. NL ";
        }
        if (purpCurse > 0) {
            rawDescription += "[#673ab7]Накладывает " + purpCurse + " [#673ab7]Проклятия. NL ";
        }
        if (rainbowPaint > 0) {
            if (rainbowPaint == 1) {
                rawDescription += "[oobley]Нарисуйте " + rainbowPaint + " [oobley]случайный " + "[oobley]Цвет. NL ";
            } else {
                rawDescription += "[oobley]Нарисуйте " + rainbowPaint + " [oobley]случайных " + "[oobley]Цветов. NL ";
            }
        }
        if (yellow > 0 && red == 0 && blue == 0 && green == 0 && aqua == 0 && magenta == 0 && brown == 0 && purple == 0 && rainbow == 0)
            rawDescription += "Exhaust.";
        if (red > 0 || aquaWeak > 0 || magentaVuln > 0 || purpCurse > 0) {
            if (blue > 0 || greenCards > 0 || yellowEnergy > 0 || brownTemp > 0 || rainbowPaint > 0) {
                this.target = CardTarget.SELF_AND_ENEMY;
            } else {
                this.target = CardTarget.ENEMY;
            }
        } else {
            if (blue > 0 || greenCards > 0 || yellowEnergy > 0 || brownTemp > 0 || rainbowPaint > 0) {
                this.target = CardTarget.SELF;
            }
        }
        this.initializeDescription();
    }

    @SpireOverride
    protected void renderBannerImage(SpriteBatch sb, float x, float y) {
        Color blah = (Color) ReflectionHacks.getPrivate(this, AbstractCard.class, "renderColor");
        ReflectionHacks.setPrivate(this, AbstractCard.class, "renderColor", ArtistMod.rainbow);
        SpireSuper.call(sb, x, y);
        ReflectionHacks.setPrivate(this, AbstractCard.class, "renderColor", blah);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (baseDamage > 0) {
            atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
        if (baseBlock > 0) {
            atb(blck());
        }
        if (greenCards > 0) {
            atb(new DrawCardAction(p, greenCards));
        }
        if (yellowEnergy > 0) {
            atb(new GainEnergyAction(yellowEnergy));
        }
        if (aquaWeak > 0) {
            atb(applyToEnemy(m, autoWeak(m, aquaWeak)));
        }
        if (magentaVuln > 0) {
            atb(applyToEnemy(m, autoVuln(m, magentaVuln)));
        }
        if (brownTemp > 0) {
            atb(new AddTemporaryHPAction(p, p, brownTemp));
        }
        if (purpCurse > 0) {
            atb(applyToEnemy(m, new CursedPower(m, purpCurse)));
        }
        if (rainbowPaint > 0) {
            for (int i = 0; i < rainbowPaint; i++) {
                atb(new PaintAction(getRandomColor()));
            }
        }
        if (yellowEnergy > 0) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    yellowEnergy = 0;
                    reDescription();
                    isDone = true;
                }
            });
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new AbstractPaintingCard(tex, red, blue, green, yellow, aqua, magenta, brown, purple, black, rainbow, false);
    }


}
