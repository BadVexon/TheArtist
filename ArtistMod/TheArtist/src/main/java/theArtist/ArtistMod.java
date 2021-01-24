package theArtist;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.clapper.util.classutil.*;
import theArtist.relics.BlueBrush;
import theArtist.relics.BrokenBrush;
import theArtist.relics.FixedBrush;
import theArtist.util.DraggableBox;
import theArtist.util.NameGenerator;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"ConstantConditions", "unused", "WeakerAccess"})
@SpireInitializer
public class ArtistMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        StartGameSubscriber,
        PostBattleSubscriber,
        PostUpdateSubscriber,
        PostInitializeSubscriber,
        PreRoomRenderSubscriber {
    public static final String ARTIST_SHOULDER_1 = "artistmodResources/images/char/artistChar/shoulder.png";
    public static final String ARTIST_SHOULDER_2 = "artistmodResources/images/char/artistChar/shoulder2.png";
    public static final String ARTIST_CORPSE = "artistmodResources/images/char/artistChar/corpse.png";
    private static final String ARTIST_ATTACK_ART = "artistmodResources/images/512/canvas_attack_s.png";
    private static final String ARTIST_SKILL_ART = "artistmodResources/images/512/canvas_skill_s.png";
    private static final String ARTIST_POWER_ART = "artistmodResources/images/512/canvas_power_s.png";
    private static final String ARTIST_CARD_ENERGY_ORB = "artistmodResources/images/512/card_default_gray_orb.png";
    private static final String ARTIST_TEXT_ENERGY_ORB = "artistmodResources/images/512/card_small_orb.png";
    private static final String BIG_ARTIST_ATTACK_ART = "artistmodResources/images/1024/canvas_attack.png";
    private static final String BIG_ARTIST_SKILL_ART = "artistmodResources/images/1024/canvas_skill.png";
    private static final String BIG_ARTIST_POWER_ART = "artistmodResources/images/1024/canvas_power.png";
    private static final String BIG_ARTIST_CARD_ENERGY_ORB = "artistmodResources/images/1024/card_default_gray_orb.png";
    private static final String ARTIST_CHARSELECT_BUTTON = "artistmodResources/images/charSelect/ArtistCharacterButton.png";
    private static final String ARTIST_CHARSELECT_PORTRAIT = "artistmodResources/images/charSelect/ArtistCharacterBG.png";
    private static String modID;
    private static boolean thindone;

    public static DraggableBox newHitbox;
    public static AbstractCanvas theCanvas;

    public static String curName;

    public static boolean renderStuff;

    public static Color rainbow = new Color(1F, 0, 0, 1F);
    private static final float velocity = 0.25f;

    public ArtistMod() {

        BaseMod.subscribe(this);

        modID = "artistmod";

        BaseMod.addColor(TheArtist.Enums.COLOR_RAINBOW, rainbow, rainbow, rainbow,
                rainbow, rainbow, rainbow, rainbow,
                ARTIST_ATTACK_ART, ARTIST_SKILL_ART, ARTIST_POWER_ART, ARTIST_CARD_ENERGY_ORB,
                BIG_ARTIST_ATTACK_ART, BIG_ARTIST_SKILL_ART, BIG_ARTIST_POWER_ART,
                BIG_ARTIST_CARD_ENERGY_ORB, ARTIST_TEXT_ENERGY_ORB);

    }

    public void receivePostInitialize() {
        theCanvas = new AbstractCanvas();
        newHitbox = new DraggableBox();
        curName = NameGenerator.returnRandomName();
    }

    public void receivePostUpdate() {
        if (rainbow.g < 1 && rainbow.r >= 1 && rainbow.b <= 0) {
            rainbow.g += velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.g > 1.0F) {
                rainbow.g = 1.0F;
            }
        } else if (rainbow.g >= 1 && rainbow.r > 0 && rainbow.b <= 0) {
            rainbow.r -= velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.r < 0.0F) {
                rainbow.r = 0.0F;
            }
        } else if (rainbow.g >= 1 && rainbow.r <= 0 && rainbow.b < 1) {
            rainbow.b += velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.b > 1.0F) {
                rainbow.b = 1.0F;
            }
        } else if (rainbow.g > 0 && rainbow.r <= 0 && rainbow.b >= 1) {
            rainbow.g -= velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.g < 0.0F) {
                rainbow.g = 0.0F;
            }
        } else if (rainbow.g <= 0 && rainbow.r < 1 && rainbow.b >= 1) {
            rainbow.r += velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.r > 1.0F) {
                rainbow.r = 1.0F;
            }
        } else if (rainbow.g <= 0 && rainbow.r >= 1 && rainbow.b > 0) {
            rainbow.b -= velocity * Gdx.graphics.getDeltaTime();
            if (rainbow.b < 0.0F) {
                rainbow.b = 0.0F;
            }
        }

        Colors.put("oobley", rainbow);
    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        ArtistMod artistMod = new ArtistMod();
        Colors.put("oobley", rainbow);
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheArtist("the Artist", TheArtist.Enums.THE_ARTIST),
                ARTIST_CHARSELECT_BUTTON, ARTIST_CHARSELECT_PORTRAIT, TheArtist.Enums.THE_ARTIST);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new BlueBrush(), TheArtist.Enums.COLOR_RAINBOW);
        BaseMod.addRelicToCustomPool(new BrokenBrush(), TheArtist.Enums.COLOR_RAINBOW);
        BaseMod.addRelicToCustomPool(new FixedBrush(), TheArtist.Enums.COLOR_RAINBOW);
    }

    @Override
    public void receiveEditCards() {
        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | NotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void autoAddCards()
            throws URISyntaxException, IllegalAccessException, InstantiationException, NotFoundException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        URL url = ArtistMod.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC),
                        new CardFilter()
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass cls = Loader.getClassPool().get(classInfo.getClassName());
            if (cls.hasAnnotation(CardIgnore.class)) {
                continue;
            }
            boolean isCard = false;
            CtClass superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.getName().equals(AbstractCard.class.getName())) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }
            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) Loader.getClassPool().getClassLoader().loadClass(cls.getName()).newInstance();
            BaseMod.addCard(card);
            if (cls.hasAnnotation(CardNoSeen.class)) {
                UnlockTracker.hardUnlockOverride(card.cardID);
            } else {
                UnlockTracker.unlockCard(card.cardID);
            }
        }
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/ArtistMod-Card-Strings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, getModID() + "Resources/localization/eng/ArtistMod-Power-Strings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/ArtistMod-Relic-Strings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/ArtistMod-Character-Strings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class, getModID() + "Resources/localization/eng/ArtistMod-UI-Strings.json");
    }

    @Override
    public void receiveStartGame() {
        if (!thindone) {
            newHitbox.hb.resize(500, 380);
            newHitbox.hb.move(Settings.WIDTH / 2F, Settings.HEIGHT / 2F);
            thindone = true;
        }
        theCanvas.shapeList.clear();
    }

    public void receivePostBattle(AbstractRoom room) {
        theCanvas.shapeList.clear();
        theCanvas.unlock();
        if (renderStuff) {
            renderStuff = false;
        }
    }

    public void receivePreRoomRender(SpriteBatch sb) {
        if ((AbstractDungeon.player instanceof TheArtist || renderStuff) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            ArtistMod.theCanvas.render(sb, ArtistMod.newHitbox.hb.x, ArtistMod.newHitbox.hb.y);
        }
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/ArtistMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
