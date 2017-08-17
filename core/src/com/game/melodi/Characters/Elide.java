package com.game.melodi.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.game.melodi.Animations.AnimatedImage;
import com.game.melodi.Melodi;
import com.game.melodi.Physics.BodyEditorLoader;
import com.game.melodi.Physics.GameWorld;
import com.codeandweb.physicseditor.*;

/**
 * Created by Paradox on 5/11/2017.
 */

public class Elide extends Image {

    //public final Body body; // bob's box2d body
    Image elide;
    Image board;
    BodyDef bd;
    PhysicsShapeCache phybod;
    FixtureDef fd;
    Body elideModel;


    public Elide(GameWorld world, Melodi game){
        // char is an Image, so we load the graphics from the assetmanager
        //TODO create Asset manager
        //Texture tex = Assets.manager.get("characters.png", Texture.class);
        //this.setDrawable(new TextureRegionDrawable(new TextureRegion(tex, 0, 256, 128, 128)));
        elide = new Image(game.manager.get("elideonboard.png",Texture.class));
        board = new Image(game.manager.get("boardmove.png",Texture.class));

        elide.setSize(.75f,.75f);
        //elide.setPosition(0,game.world.body.getPosition().y+.5f);

        board.setSize(.75f,.75f);
        //board.setPosition(elide.getX(),elide.getY()-.5f);
        game.world.stage.addActor(elide);
        game.world.stage.addActor(board);

        //TODO Make Elide's body
        bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(world.body.getPosition().x+1,world.body.getPosition().y+1f);
        elide.setPosition(bd.position.x,bd.position.y);
        phybod = new PhysicsShapeCache(Gdx.files.internal("physics/elidephy.xml"));
        fd = new FixtureDef();
        fd.density = 2.5f;
        fd.restitution = .75f;
        fd.friction = 0.25f;
        elideModel = game.world.world.createBody(bd);
        elideModel.setUserData(elide);
        phybod.createBody("elidephy",game.world.world,bd,1,1);
    }

    @Override
    public void act(float delta) {
        // here we override Actor's act() method to make the actor follow the box2d body
        super.act(delta);
        //setPosition(body.getPosition().x-RADIUS, body.getPosition().y-RADIUS);
        elide.setPosition(elideModel.getPosition().x,elideModel.getPosition().y);
        board.setPosition(elide.getX(),elide.getY()-.35f);
    }

}
