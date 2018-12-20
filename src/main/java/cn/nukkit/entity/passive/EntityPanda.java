package cn.nukkit.entity.passive;

import cn.nukkit.utils.EntityUtils;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class EntityPanda extends EntityWalkingAnimal {

    public static final int NETWORK_ID = 113;

    public EntityPanda(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public float getLength() {
        return 1.825f;
    }

    @Override
    public float getWidth() {
        return 1.125f;
    }

    @Override
    public float getHeight() {
        return 1.25f;
    }

    @Override
    public void initEntity() {
        super.initEntity();

        this.setMaxHealth(20);
    }

    @Override
    public int getKillExperience() {
        return EntityUtils.rand(1, 3);
    }
}
