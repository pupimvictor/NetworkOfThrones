import java.util.ArrayList;
import java.util.List;

public class BridgesDFS {

	private NetworkOfThrones G;
	List<Connection> bridges;
	private int time;

	public BridgesDFS(NetworkOfThrones network) {
		G = network;
		bridges = new ArrayList<>();
	}

	public List<Connection> findBridges() {

		for (Person p : G.getCharacters()) {
			if (!p.isVisited()) {
				bridgeVisit(p);
			}
		}

		return bridges;
	}

	private void bridgeVisit(Person u) {
		u.setVisited(true);
		
		u.setDisc(incTime());
		u.setLow(getTime());
		
		for (int i : G.getAdjList()[u.getId()]) {
			Person v = G.getCharacters().get(i);
			
			if (!v.isVisited()) {
				v.setParent(u);
				
				bridgeVisit(v);
				
				u.setLow(Math.min(u.getLow(), v.getLow()));
				
				if (v.getLow() > u.getLow()) {
					bridges.add(new Connection(u, v, 1));
				}
			}else if (v != u.getParent()) {
				u.setLow(Math.min(u.getLow(), v.getDisc()));
			}
		}
	}

	public int getTime() {
		return time;
	}

	public int incTime() {
		time++;
		return time;
	}

}
