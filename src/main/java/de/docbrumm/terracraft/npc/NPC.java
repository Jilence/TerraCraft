package de.docbrumm.terracraft.npc;

import de.docbrumm.terracraft.util.ConfigUtil;
import de.docbrumm.terracraft.util.Util;
import de.fllip.entity.api.EntityAPI;
import de.fllip.entity.api.configuration.FakePlayerEntityConfiguration;
import de.fllip.entity.api.entity.animation.EntityAnimationType;
import de.fllip.entity.api.entity.interact.EntityInteractAction;
import de.fllip.entity.api.entity.type.fakeplayer.SkinData;
import de.fllip.entity.api.entity.update.EntityUpdateType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

public class NPC {

    OfflinePlayer owner;
    String name;
    SkinData skin;
    Location location;
    ConfigUtil util;

    public NPC(OfflinePlayer owner,
               String name,
               Location location,
               SkinData skin,
               ConfigUtil configUtil) {
        this.owner = owner;
        this.name = name;
        this.location = location;
        this.skin = skin;
        this.util = configUtil;
    }

    public NPC(OfflinePlayer player) {
        owner = player;
        loadData();
    }

    public void save() {
        set("name", name);
        set("location", location);
        set("skin", skin);
    }

    public void loadData() {
        name = (String) get("name");
        skin = (SkinData) get("skin");
        location = (Location) get("location");
    }

    public void create(boolean bool, ItemStack mainHand){
        EntityAPI.getInstance().getCreator().createFakePlayer(new FakePlayerEntityConfiguration()
        .withDisplayName(name)
        .withLocation(location)
        .withLookAtPlayer(bool)
        .withMainHandItem(mainHand)
        .withSkinData(new SkinData("ewogICJ0aW1lc3RhbXAiIDogMTYwODQ2Mzk2MDIxNSwKICAicHJvZmlsZUlkIiA6ICJmNzg4YzU5ZGY2MzU0M2MxOGMzY2M5YjczMzM4NGZlNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJCd2VlZjY5IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2U3YjhiYjBlNmYyYWVjMDJmMTQ1OGI0MDM2NDg2YjFiMzcwODU2M2U3YTVlY2QxNjYzZWI3MWFlOGMxYWI3ZmIiCiAgICB9CiAgfQp9",
                "p35JRZPm3XS2JM4IH46OVcX6vTZ990YEAEWhhn4Qz8mKxy5rKEfE0mapcryABLMKOmVnyjspDPieHUqTREqoZcpO+hJA4PvolaF5Hb7i3VLeWOHDg5+tpIsXIs9Pr/hiZlGCHUG57Nc34Ejs8XHpxyPfxlRxl5X6D9mwYRL+w6LBLJfY4Fbx1OQug++oIbMAyn67YQ37ROFKhZhZTO4qQzmTKct5eeAzv+frrGQSv+wTWIk6H6IOcTevq1FNwLRcVgXRPbATi5ROllcSWfZvpNLntgt8+LBPd1mbnBWzNZpvYUu30eMKo0qpHXtcPCeX90xyGUsNVnfOUOOcf0GXhPZyY0HhX7481vzDReobsiYql5DcLafmzO+sn71TXC0PsmvTd11gJ/zavFwbWfqTbtYyJQgWxARxgHRUwIwqG23snDJk8Xoj5nDA9H8rNODJUl9gqhyGiGMT5la9A07ASCMIMH5zeUWR/oRMakA2jcwnYpBUvwOg3cO6Bwm6qxE+QAkJxOZbe13LDErYy63RagInqx2htQV2vc7PerVl1tn6kqKRJLxVLsNVcfd6VW1Zy2EXndFsBjfD4siTXMUWsJ5A2FKmEbBzFSaBU2Wit3UUUboaFWBP6swjWfY6Ye9ol1BCq0TZf0LyaBHYs/O8wNSt983wxLdoOFTdfaQO/4k="))
                .withInteractHandler(entityInteractResult -> {
                    if(entityInteractResult.getAction() == EntityInteractAction.ATTACK){
                        entityInteractResult.getEntity().update(entityInteractResult.getPlayer(), EntityUpdateType.ANIMATION, EntityAnimationType.TAKE_DAMAGE);
                    }
                })
        );
    }

    private Object get(String key) {
        return util.get(owner.getUniqueId() + "." + key);
    }

    private void set(String key, Object any) {
        util.set(owner.getUniqueId() + "." + key, any);
    }
}
