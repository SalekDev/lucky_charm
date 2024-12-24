package salek664.lucky_charm.client.gui.screen.loot;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import salek664.lucky_charm.perk.AttributeEquipmentPerk;
import salek664.lucky_charm.perk.LuckyCharmPerks;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class LootTableOrganizer {
    public final ResourcePackManager resourcePackManager;
    private List<ResourcePackProfile> datapacks;
    private final Function<ResourcePackProfile, Identifier> iconIdSupplier;
    private final Runnable updateCallback;
    private final Consumer<ResourcePackManager> applier;
    public LootTableOrganizer(ResourcePackManager resourcePackManager,
                              Runnable updateCallback,
                              Function<ResourcePackProfile, Identifier> iconIdSupplier,
                              Consumer<ResourcePackManager> applier) {
        this.resourcePackManager = resourcePackManager;
        this.updateCallback = updateCallback;
        this.iconIdSupplier = iconIdSupplier;
        this.applier = applier;
        this.datapacks = new ArrayList<>(resourcePackManager.getEnabledProfiles());
    }
    public void refresh() {
        this.resourcePackManager.scanPacks();
        this.datapacks.retainAll(this.resourcePackManager.getEnabledProfiles());
    }
    public void acceptInfo(List<String> s) {
        s = s;
        s = s;
    }
    public Stream<InfoPack> getInfoPacks() {
        return this.datapacks.stream()
                .filter(profile -> !profile.getId().startsWith("fabric"))
                .map(profile -> new InfoPack() {
                    @Override
                    public Identifier getIconID() {
                        return iconIdSupplier.apply(profile);
                    }
                    @Override
                    public String getName() {
                        return profile.getId();
                    }
                    @Override
                    public Text getDisplayName() {
                        return profile.getDisplayName();
                    }
                    @Override
                    public String getNamespace() throws NoSuchElementException {
                        return profile.getInfo().knownPackInfo().orElseThrow().namespace();
                    }
                });
    }
    public Stream<CatVariant> getLootTableNames(String namespace, @Nullable MinecraftClient client) throws NullPointerException {
        return client.getNetworkHandler().getRegistryManager().getOrThrow(RegistryKeys.CAT_VARIANT).stream();
                //.filter(registryKey -> registryKey.getValue().getNamespace().equals(namespace))
                //.filter(registryKey -> registryKey.isOf(RegistryKeys.LOOT_TABLE));
                //.map(registryKey -> registryKey.getValue().getPath());
    }
    public interface InfoPack {
        Identifier getIconID();
        String getName();
        Text getDisplayName();
        String getNamespace();
    }
}
