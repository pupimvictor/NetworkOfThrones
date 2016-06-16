import java.util.ArrayList;
import java.util.List;


/*
 * The list of Person called characters represents the vertices of the graph and
 * the list of Connection called relationships represents the edges of the graph
 */
public class NetworkOfThrones {
	
	List<Person> characters;
	List<Connection> relationships;
	
	List<Person> articulationPoints;
	List<Connection> bridges;
	
	static int numCharacters;
	
	private ArrayList<Integer> adjList[];
	private Integer adjMatrix[][];
	
	@SuppressWarnings("unchecked")
	public NetworkOfThrones(List<Person> characters, List<Connection> relationships) {
		super();
		this.characters = characters;
		this.relationships = relationships;
		NetworkOfThrones.numCharacters = characters.size();
		
		this.adjList = new ArrayList[numCharacters];
		for (int i = 0; i < numCharacters; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		this.adjMatrix = new Integer[numCharacters][numCharacters];
		
		for (int i = 0; i < relationships.size(); i++) {
			addAdjList(relationships.get(i).getSourceCharacter().getId(), relationships.get(i).getTargetCharacter().getId());
			addAdjMatrix(relationships.get(i).getSourceCharacter().getId(), relationships.get(i).getTargetCharacter().getId(), relationships.get(i).getWeight());
		}
	}
	
	private void addAdjMatrix(int source, int target, int weight) {
		this.adjMatrix[source][target] = weight;
		this.adjMatrix[target][source] = weight;
	}

	private void addAdjList(int source, int target) {
		this.adjList[source].add(target);
		this.adjList[target].add(source);
	}

	public void resetCharactersAttr(){
		for (Person person : characters) {
			person.setParent(null);
			person.setVisited(false);
		}
	}
	
	public ArrayList<Integer>[] getAdjList() {
		return adjList;
	}

	public void setAdjList(ArrayList<Integer>[] adjList) {
		this.adjList = adjList;
	}

	public Integer[][] getAdjMatrix() {
		return adjMatrix;
	}

	public void setAdjMatrix(Integer[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	public List<Person> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Person> characters) {
		this.characters = characters;
	}

	public List<Connection> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Connection> relationships) {
		this.relationships = relationships;
	}

	public static int getNumCharacters() {
		return numCharacters;
	}

	public static void setNumCharacters(int numCharacters) {
		NetworkOfThrones.numCharacters = numCharacters;
	}

	

	
}
