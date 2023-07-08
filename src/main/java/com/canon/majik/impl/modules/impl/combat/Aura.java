package com.canon.majik.impl.modules.impl.combat;

import com.canon.majik.api.utils.player.PlayerUtils;
import com.canon.majik.impl.modules.api.Category;
import com.canon.majik.impl.modules.api.Module;
import com.canon.majik.impl.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

public class Aura extends Module {

    Setting players = setting("Players", true);
    Setting mobs = setting("Mobs", false);
    Setting range = setting("Range", 5, 1, 6);
    Setting cooldown = setting("Cooldown", true);
    Entity targ;

    public Aura() {
        super("Aura", Category.COMBAT);
        add(players);
        add(mobs);
        add(range);
        add(cooldown);
    }


    @Override
    public void onDisable() {
        targ = null;
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onUpdate() {
        if(nullCheck()) return;
        for(Entity target : mc.world.loadedEntityList){
            if(mc.player.getDistance(target) >= range.getNumber().floatValue() && !target.isDead)
                continue;
            if(target.isDead)
                targ = null;
            if(target instanceof EntityAnimal){
                targ = target;
            }

            attack(cooldown.getBoolean());

        }
        super.onUpdate();
    }

    public void attack(boolean cooldown){
        if(targ != null){
            if(cooldown){
                if(mc.player.getCooledAttackStrength(0) == 1){
                    mc.playerController.attackEntity(mc.player, targ);
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }else {
                mc.playerController.attackEntity(mc.player, targ);
                mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
    }
}
