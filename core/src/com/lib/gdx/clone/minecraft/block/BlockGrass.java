package com.lib.gdx.clone.minecraft.block;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;

public class BlockGrass extends Block{

    public BlockGrass(Model model, Vector3 position) {
        super(model, position);
//        model.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse, Color.GREEN));
        setColor(Color.GREEN);
    }

    @Override
    public void update() {

    }
}
