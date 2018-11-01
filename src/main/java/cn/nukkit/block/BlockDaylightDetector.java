package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

/**
 * Created on 2015/11/22 by CreeperFace.
 * Package cn.nukkit.block in project Nukkit .
 */
public class BlockDaylightDetector extends BlockTransparent {

    public BlockDaylightDetector() {
    }

    @Override
    public int getId() {
        return DAYLIGHT_DETECTOR;
    }

    @Override
    public String getName() {
        return "Daylight Detector";
    }

    @Override
    public double getHardness() {
        return 0.2;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WOOD_BLOCK_COLOR;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        this.getLevel().setBlock(this, new BlockDaylightDetectorInverted());
        return true;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(this, 0);
    }
    
    @Override
    public boolean isPowerSource() {
        return true;
    }
    
    @Override
    public int getWeakPower(BlockFace face) {
        return 15;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }
}
