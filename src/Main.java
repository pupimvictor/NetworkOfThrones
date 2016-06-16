import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {

	List<Person> characters;
	List<Connection> relationships;
	NetworkOfThrones networkOfThrones;

	private static final String FILE_NAME = "stormofswords.csv";

	public static void main(String[] args) {

		Main main = new Main();
		main.characters = new ArrayList<Person>();
		main.relationships = new ArrayList<Connection>();
		
		main.run();

	}

	public void run() {
		this.readData(FILE_NAME);
		
		networkOfThrones = new NetworkOfThrones(characters, relationships);

		findBridges();
		
		networkOfThrones.resetCharactersAttr();
		findArticulationPoints();
		
		while(true){
			String sourceChar = JOptionPane.showInputDialog("Type the name of the first character");
			String targetChar = JOptionPane.showInputDialog("Type the name of the second character");
			
			findPathBetweenCharacters(getCharacterByName(sourceChar), getCharacterByName(targetChar));
		}
		
	
	}
	
	public void findPathBetweenCharacters(Person charSource, Person charTarget){
		
		charSource.setDistance(0);
		
		Dijkstra dijkstra = new Dijkstra(networkOfThrones);
		dijkstra.execute(charSource, charTarget);
		
		System.out.println("Relationship Distance between "
				+ charSource.getName() + " and " 
				+ charTarget.getName() + ": "
				+ charTarget.getDistance());
		
		System.out.println("Path:");
		System.out.println(charTarget.getName());
		Person aux = charTarget.getPred();
		while (aux.getPred() != null) {
			System.out.println("   |");
			System.out.println(aux.getName());
			aux = aux.getPred();
		}
		System.out.println("   |");
		System.out.println(aux.getName());
		System.out.println();
		
	}
	
	public void findArticulationPoints() {
		ArticulationPointDFS articulationPointDFS = new ArticulationPointDFS(networkOfThrones);
		networkOfThrones.articulationPoints = articulationPointDFS.findArticulationPoints();
		
		System.out.println("-Articulation characters-");
		for (Person person : networkOfThrones.articulationPoints) {
			System.out.println("Articulator: " + person.getName());
		}
		System.out.println();
		
	}
	
	public void findBridges(){
		BridgesDFS bridgesDFS = new BridgesDFS(networkOfThrones);
		networkOfThrones.bridges = bridgesDFS.findBridges();
		
		System.out.println("-Bridges of relationship-");
		for (Connection bridge : networkOfThrones.bridges) {
			System.out.println("Bridge: "+ bridge.getSourceCharacter().getName() + " ----- " + bridge.getTargetCharacter().getName());
		}
		System.out.println();
	}

	private void readData(String fileName) {
		String line;
		String csvSeparator = ",";
		String[] data;

		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));

			// drop header
			in.readLine();

			while (in.ready()) {
				line = in.readLine();
				data = line.split(csvSeparator);
				processData(data[0], data[1], data[2]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processData(String source, String target, String weight) {
		Person characterSource, characterTarget;

		if (!characters.contains(new Person(source))) {
			characterSource = new Person(characters.size(), source, Integer.MAX_VALUE);
			characters.add(characterSource);
		} else {
			characterSource = this.getCharacterByName(source);
		}
		
		if (!characters.contains(new Person(target))) {
			characterTarget = new Person(characters.size(), target, Integer.MAX_VALUE);
			characters.add(characterTarget);
		} else {
			characterTarget = this.getCharacterByName(target);
		}
		
		relationships.add(new Connection(characterSource, characterTarget, Integer.parseInt(weight)));
	}

	private Person getCharacterByName(String source) {
		Person returnPerson;
		returnPerson = characters.stream()
				.filter(c -> c.getName().equals(source))
				.findFirst().orElse(new Person());
		return returnPerson;
	}
}
