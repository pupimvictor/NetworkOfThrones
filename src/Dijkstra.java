import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class Dijkstra {
	
	private NetworkOfThrones G;
	private PriorityQueue<Person> queue;
	
	public Dijkstra(NetworkOfThrones network){
		this.G = network;
		queue = new PriorityQueue<Person>();
	}
	
	public void execute(Person source, Person target) {
		List<Person> visited = new ArrayList<>();
		queue.add(source);
		while (!queue.isEmpty()) {
			Person u = queue.remove();
			visited.add(u);
			if (u.equals(target)) {
				break;
			}
			for (Integer i : G.getAdjList()[u.getId()]) {
				if (i != null) {
					Person v = G.getCharacters().get(i);
					relax(u, v);
				}
			}
			
		}
	
	}
	
	private void relax(Person u, Person v) {
		if (v.getDistance() > u.getDistance() + G.getAdjMatrix()[u.getId()][v.getId()]) {
			v.setDistance(u.getDistance() + 1);
			v.setPred(u);
			queue.add(v);
		}
	}

}
