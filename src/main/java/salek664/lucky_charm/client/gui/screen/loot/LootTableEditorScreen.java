package salek664.lucky_charm.client.gui.screen.loot;

import com.google.common.hash.Hashing;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import salek664.lucky_charm.LuckyCharm;
import salek664.lucky_charm.networking.payload.LootTablesRequestC2SPacket;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class LootTableEditorScreen extends Screen {
    private static final Identifier UNKNOWN_PACK = Identifier.ofVanilla("textures/misc/unknown_pack.png");
    public ButtonWidget button1;
    public ButtonWidget button2;
    public final Screen parent;
    public final LootTableOrganizer lootTableOrganizer;
    private final Map<String, Identifier> iconTextures = new HashMap<>();
    public LootTableEditorScreen(Screen parent, ResourcePackManager resourcePackManager, Consumer<ResourcePackManager> applier) {
        super(Text.literal("dsds"));
        this.parent = parent;
        this.lootTableOrganizer = new LootTableOrganizer(resourcePackManager, this::updatePacks, r -> Identifier.of(""), applier);
    }
    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("Button 1"), button -> {
                    LuckyCharm.LOGGER.info("You clicked button1!");
                })
                .dimensions(width / 2 - 205, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
                .build();
        button2 = ButtonWidget.builder(Text.literal("Button 2"), button -> {
                    LuckyCharm.LOGGER.info("You clicked button2!");
                })
                .dimensions(width / 2 + 5, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();
        addDrawableChild(button1);
        addDrawableChild(button2);
        this.lootTableOrganizer.refresh();
        List<LootTableOrganizer.InfoPack> d = this.lootTableOrganizer.getInfoPacks().toList();
        try {
            LuckyCharm.LOGGER.info(this.lootTableOrganizer.getLootTableNames("minecraft", this.client).toList().toString());
        } catch (NullPointerException e) {
            LuckyCharm.LOGGER.info("dsdsds");
        }
    }
    @Override
    public void close() {
        client.setScreen(parent);
    }
    public void updatePacks() {

    }
    private Identifier getPackIconTexture(ResourcePackProfile resourcePackProfile) {
        return this.iconTextures.computeIfAbsent(resourcePackProfile.getId(), profileName -> this.loadPackIcon(this.client.getTextureManager(), resourcePackProfile));
    }
    private Identifier loadPackIcon(TextureManager textureManager, ResourcePackProfile resourcePackProfile) {
        try {
            Identifier var9;
            try (ResourcePack resourcePack = resourcePackProfile.createResourcePack()) {
                InputSupplier<InputStream> inputSupplier = resourcePack.openRoot("pack.png");
                if (inputSupplier == null) {
                    return UNKNOWN_PACK;
                }
                String string = resourcePackProfile.getId();
                Identifier identifier = Identifier.ofVanilla(
                        "pack/" + Util.replaceInvalidChars(string, Identifier::isPathCharacterValid) + "/" + Hashing.sha1().hashUnencodedChars(string) + "/icon"
                );
                InputStream inputStream = inputSupplier.get();
                try {
                    NativeImage nativeImage = NativeImage.read(inputStream);
                    textureManager.registerTexture(identifier, new NativeImageBackedTexture(nativeImage));
                    var9 = identifier;
                } catch (Throwable var12) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable var11) {
                            var12.addSuppressed(var11);
                        }
                    }
                    throw var12;
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return var9;
        } catch (Exception var14) {
            LuckyCharm.LOGGER.warn("Failed to load icon from pack {}", resourcePackProfile.getId(), var14);
            return UNKNOWN_PACK;
        }
    }
}
