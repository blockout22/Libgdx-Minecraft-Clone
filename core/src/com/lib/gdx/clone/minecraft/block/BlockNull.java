package com.lib.gdx.clone.minecraft.block;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

public class BlockNull extends Block{

    public BlockNull(Model model, Vector3 position) {
        super(model, position);
        setAir(true);
    }

    @Override
    public void update() {

    }
}
