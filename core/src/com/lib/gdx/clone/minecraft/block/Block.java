package com.lib.gdx.clone.minecraft.block;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public abstract class Block {

    private Vector3 position;
    private ModelInstance modelInstance;
    private boolean isAir = false;
    private BoundingBox boundingBox;

    public Block(Model model, Vector3 position){
        this.position = position;
        modelInstance = new ModelInstance(model);
        modelInstance.transform.setToTranslation(position);
        boundingBox = new BoundingBox(new Vector3(position.x - 0.5f, position.y - 0.5f, position.z - 0.5f), new Vector3(position.x + 0.5f, position.y + 0.5f, position.z + 0.5f));
    }

    public void setColor(Color color){
        modelInstance.materials.get(0).set(ColorAttribute.createDiffuse(color));
    }


    public abstract void update();

    public void render(ModelBatch batch, Environment environment)
    {
        batch.render(modelInstance, environment);
    }

    //can be used for any block but only really being used to make empty spaces
    public boolean isAir()
    {
        return isAir;
    }


    public BoundingBox getBoundingBox()
    {
        return boundingBox;
    }

    public void setAir(boolean value){
        isAir = value;
    }

}
