package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Genotype {
    private final int length;
    private final List<Integer> genotype = new ArrayList<>();

    public Genotype(int length, Animal parent1, Animal parent2, int mutationType){
        this.length = length;
        int side = (int) ((Math.random() * (0 - 1)) + 0);
        Animal strongerParent;
        Animal weakerParent;
        Genotype strongerGenotype;
        Genotype weakerGenotype;
        int numOfMutation = (int) ((Math.random() * (0 - this.length)) + 0);

        if( parent1.getHealth() > parent2.getHealth() ){
            strongerParent = parent1;
            weakerParent = parent2;
            strongerGenotype = parent1.getGenotype();
            weakerGenotype = parent2.getGenotype();
        }
        else{
            strongerParent = parent2;
            weakerParent = parent1;
            strongerGenotype = parent2.getGenotype();
            weakerGenotype = parent1.getGenotype();
        }

        int sumHealth = strongerParent.getHealth() + weakerParent.getHealth();
        float strongerGenesRatio = strongerParent.getHealth() / sumHealth;
        float weakerGenesRatio = 1 - strongerGenesRatio;
        int numOfStrongerGenes = (int) Math.ceil(this.length * strongerGenesRatio);
        int numOfWeakerGenes = (int) Math.floor(this.length * weakerGenesRatio);

        if( side == 0 ){
            for( int i = 0; i < numOfStrongerGenes; i++){
                this.genotype.add(strongerGenotype.getGene(i));
            }
            for( int i = 0; i < numOfWeakerGenes; i++ ){
                this.genotype.add(weakerGenotype.getGene(weakerGenotype.getLength()-i-1));
            }
        }
        else{
            for( int i = 0; i < numOfStrongerGenes; i++){
                this.genotype.add(strongerGenotype.getGene(strongerGenotype.getLength()-i-1));
            }
            for( int i = 0; i < numOfWeakerGenes; i++ ){
                this.genotype.add(weakerGenotype.getGene(i));
            }
        }

        int pickedGene = 0;
        int newGene = 0;

        for( int i = 0; i < numOfMutation; i++){
            pickedGene = (int) ((Math.random() * (0 - (this.length-1))) + 0);

            if( mutationType == 0 ){
                newGene = (int) ((Math.random() * (0 - 7)) + 0);
            }
            else{
               int addOrSub = (int) ((Math.random() * (0 - 1)) + 0);

               if( addOrSub == 0 ){
                   newGene = (this.genotype.get(pickedGene) + 1) % 8;
               }
               else{
                   if( this.genotype.get(pickedGene) == 0 )
                       newGene = 7;
                   else{
                       newGene = this.genotype.get(pickedGene)-1;
                   }
               }
            }

            this.genotype.add(pickedGene, newGene);
        }


    }

    public Genotype(int length){
        this.length = length;

        for( int i = 0; i < length; i++ ) {
            int gene = (int) ((Math.random() * (0 - 7)) + 0);
            genotype.add(gene);
        }
    }

    public Integer getGene(int index){
        return this.genotype.get(index);
    }

    public int getLength(){
        return this.length;
    }

}
