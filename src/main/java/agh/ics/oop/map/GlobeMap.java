package agh.ics.oop.map;

import agh.ics.oop.Grave;
import agh.ics.oop.Vector2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GlobeMap extends AbstractWorldMap{
    public GlobeMap(int width, int height, int grassnum, GeneratorType generator){
        this.height=height;
        this.width=width;
        this.grassField=generator.getGenerator(width,height,grassnum);
        this.grassnum=grassnum;
        this.graveyard= new Grave[width*height];
        int k=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                graveyard[k]=new Grave(new Vector2d(i,j));
                k++;
            }
        }
        Arrays.sort(graveyard, new Comparator<Grave>() {
            @Override
            public int compare(Grave o1, Grave o2) {
                if(o1.corpses> o2.corpses){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        this.plants=this.grassField.generateGrass(new ArrayList<>(this.plants.keySet()),grassnum,graveyard);

    }
    public void generateGrass(int n){
        this.plants=this.grassField.generateGrass(new ArrayList<>(this.plants.keySet()),n,graveyard);
    }
    public Vector2d canMoveTo(Vector2d position, Vector2d movement){
        Vector2d newposition=position.add(movement);
        if ((newposition.y>=this.height) || (newposition.y<0)){
            return position;
        }
        return  new Vector2d((newposition.x+this.width)%this.width,newposition.y);

    }
}
