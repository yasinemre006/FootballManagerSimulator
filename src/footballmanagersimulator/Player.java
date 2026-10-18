/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package footballmanagersimulator;

/**
 *
 * @author Lenovo
 */
public class Player {
private int id;
private String name;
private Position position;
private int age;
private int overall;
private double marketValue;
public Player(int id, String name, Position position,int age, int overall, double marketValue ){
this.id = id;
if(name==null || name.trim().isEmpty()){
throw new IllegalArgumentException("Name can not null or empty.");    
}
this.name=name;
if(position==null){
throw new IllegalArgumentException("Position can not null.");    
}
this.position=position;
if(age<15){
throw new IllegalArgumentException("Age can not smaller than 15.");    
}
this.age=age;
if(overall<1 || overall>99){
throw new IllegalArgumentException("Overall must be between 1 and 99.");    
}
this.overall=overall;
if(marketValue<0){
throw new IllegalArgumentException("Market Value can not be negative.");    
}
this.marketValue=marketValue;
}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getOverall() {
        return overall;
    }

    public double getMarketValue() {
        return marketValue;
    }
    public String toString(){
return "Id: "+id+ " | "+ "Name: "+name+" | "+"Position: "+position+" | "+"Age: "+age+" | "+"Overall: "+overall+" | "+"Market Value: "+marketValue;       
    }

}
