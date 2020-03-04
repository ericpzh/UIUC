/**
 * @author 233
 *
 */
public class Person
{
private final String name;
private final int age;
private final char gender;
private final Person child1; //left child
private final Person child2; //right child

public Person(String name,int age, char gender, Person child1,Person child2){
	this.name = name;
	this.age = age;
	this.gender = gender;
	this.child1 = child1;
	this.child2 = child2;
}
//TODO constructor

public String toString()
{
    return name+"*"+age+"*"+gender;
}
public String getName() 
{
	return name;
}
public int getAge() 
{
	return age;
}
public char getGender() 
{
	return gender;
}

public boolean equals(Person p)
{
	return (this.name.equals(p.name)) &&
			(this.age==p.age) &&
			(this.gender==p.gender);
}


public void print() 
{
   System.out.println(this);
   if(child1 != null)
       child1.print();
   if(child2 != null)
       child2.print();
   
}

public int count() // total person count including this object
{
	if(child1 == null && child2 == null){
		return 1;//YOUR CODE HERE
	}
	if(child1 == null) return 1 + child2.count();
	if(child2 == null) return 1 + child1.count();
	return child1.count()+child2.count()+1;
}

public int countGrandChildren() // but not greatGrandChildren
{
	if(child1 == null && child2 == null) return 0;//YOUR CODE HERE
	if(child1 == null && child2 != null && child2.child1 == null && child2.child2 != null) return 1;
	if(child1 == null && child2 != null && child2.child1 != null && child2.child2 == null) return 1;
	if(child1 == null && child2 != null && child2.child1 != null && child2.child2 != null) return 2;
	if(child1 != null && child2 == null && child1.child1 == null && child1.child2 != null) return 1; 
	if(child1 != null && child2 == null && child1.child1 != null && child1.child2 == null) return 1;
	if(child1 != null && child2 == null && child1.child1 != null && child1.child2 != null) return 2;
	//
	if(child1 != null && child2 != null && child1.child1 == null && child1.child2 != null && child2.child1 == null && child2.child2 == null) return 1;
	if(child1 != null && child2 != null && child1.child1 == null && child1.child2 == null && child2.child1 != null && child2.child2 == null) return 1;
	if(child1 != null && child2 != null && child1.child1 == null && child1.child2 == null && child2.child1 == null && child2.child2 != null) return 1;
	if(child1 != null && child2 != null && child1.child1 == null && child1.child2 == null && child2.child1 == null && child2.child2 == null) return 0;
	if(child1 != null && child2 != null && child1.child1 == null && child1.child2 != null && child2.child1 != null && child2.child2 == null) return 2;
	if(child1 != null && child2 != null && child1.child1 == null && child1.child2 == null && child2.child1 != null && child2.child2 != null) return 2;
	if(child1 != null && child2 != null && child1.child1 == null && child1.child2 != null && child2.child1 == null && child2.child2 != null) return 2;
	if(child1 != null && child2 != null && child1.child1 == null && child1.child2 != null && child2.child1 != null && child2.child2 != null) return 3;
	//
	if(child1 != null && child2 != null && child1.child1 != null && child1.child2 != null && child2.child1 == null && child2.child2 == null) return 2;
	if(child1 != null && child2 != null && child1.child1 != null && child1.child2 == null && child2.child1 != null && child2.child2 == null) return 2;
	if(child1 != null && child2 != null && child1.child1 != null && child1.child2 == null && child2.child1 == null && child2.child2 != null) return 2;
	if(child1 != null && child2 != null && child1.child1 != null && child1.child2 == null && child2.child1 == null && child2.child2 == null) return 1;
	if(child1 != null && child2 != null && child1.child1 != null && child1.child2 != null && child2.child1 != null && child2.child2 == null) return 3;
	if(child1 != null && child2 != null && child1.child1 != null && child1.child2 == null && child2.child1 != null && child2.child2 != null) return 3;
	if(child1 != null && child2 != null && child1.child1 != null && child1.child2 != null && child2.child1 == null && child2.child2 != null) return 3;
	if(child1 != null && child2 != null && child1.child1 != null && child1.child2 != null && child2.child1 != null && child2.child2 != null) return 4;
	return 0;

}

public int countMaxGenerations()
{
	if(this.child1 == null && this.child2 == null) return 1;//YOUR CODE HERE
	if(this.child1 == null && this.child2 != null) return 1+child2.countMaxGenerations();
	if(this.child1 != null && this.child2 == null) return 1+child1.countMaxGenerations();
	if(child1.countMaxGenerations() >= child2.countMaxGenerations()) return 1+child1.countMaxGenerations();
	else return 1+child2.countMaxGenerations();

}

public int countGender(char gen)
{
	
	if (child1 == null && child2 == null && this.getGender() == gen) return 1;
	if (child1 == null && child2 == null && this.getGender() != gen) return 0;
	if (child1 == null && this.getGender() == gen) return 1+child2.countGender(gen);
	if (child2 == null && this.getGender() == gen) return 1+child1.countGender(gen);	
	if (child1 == null && this.getGender() != gen) return 0+child2.countGender(gen);
	if (child2 == null && this.getGender() != gen) return 0+child1.countGender(gen);
	if (this.getGender() == gen) return 1+child1.countGender(gen)+child2.countGender(gen);
	else  return 0+child1.countGender(gen)+child2.countGender(gen);
			//YOUR CODE HERE

}

public Person search(String name, int maxGeneration)
{
	if(child1 == null && child2 == null && !this.getName().equals(name)) return null;
	if(child1 == null && child2 == null && this.countMaxGenerations() > maxGeneration) return null;
	if(this.countMaxGenerations() > maxGeneration){ return null;}
	if(this.getName().equals(name)){ return this;}//YOUR CODE HERE
	
	if(child1 == null) return child2.search(name, maxGeneration);
	else if(child2 == null) return child1.search(name, maxGeneration);
	else if(child1.search(name, maxGeneration) == null) return child2.search(name, maxGeneration);
	else if(child2.search(name, maxGeneration) == null) return child1.search(name, maxGeneration);
	else return null;
	
}


} // end of class