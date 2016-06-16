
public class Person implements Comparable<Person>{
	
	//Person identifier attributes
	private int id;
	private String name;
	
	//Dijkstra attributes
	private int distance;
	private Person pred;
	
	//AP and Bridges attributes
	private boolean visited;
	private int disc;
	private int low;
	private Person parent;
	
	
	public Person(int id, String name, int d) {
		super();
		this.id = id;
		this.name = name;
		pred = null;
		
		parent = null;
		visited = false;
		
		setDistance(d);
	}
	public Person() {
	}
	
	public Person(String source) {
		this.name = source;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Person getPred() {
		return pred;
	}
	public void setPred(Person pred) {
		this.pred = pred;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public int getDisc() {
		return disc;
	}
	public void setDisc(int disc) {
		this.disc = disc;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public Person getParent() {
		return parent;
	}
	public void setParent(Person parent) {
		this.parent = parent;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public int compareTo(Person p) {
		if (this.distance < p.distance) {
			return -1;
		}
		if (this.distance > p.distance) {
			return 1;
		}
		return 0;
	}
	
	
	
}
