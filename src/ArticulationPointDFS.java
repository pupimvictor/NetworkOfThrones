import java.util.ArrayList;
import java.util.List;

public class ArticulationPointDFS {
	
	private NetworkOfThrones G;
	public List<Person> articulationPoints;
	private int time;
	
	public ArticulationPointDFS(NetworkOfThrones network){
		G = network;
		articulationPoints = new ArrayList<>();
	}
	
	public List<Person> findArticulationPoints(){
		
		for (Person p : G.getCharacters()) {
			if (!p.isVisited()) {
				ArticulationPointVisit(p);
			}
		}
		
		return articulationPoints;
	}
	
	private void ArticulationPointVisit(Person u) {
		int children = 0;
		
		u.setVisited(true);
		
		u.setDisc(incTime());
		u.setLow(getTime());
		
		for (int i : G.getAdjList()[u.getId()]) {
			Person v = G.getCharacters().get(i);
			
			if (!v.isVisited()) {
				children++;
				v.setParent(u);
				
				ArticulationPointVisit(v);
				
				u.setLow(Math.min(u.getLow(), v.getLow()));
				
				if (u.getParent() == null && children > 1){ 
					if (!articulationPoints.contains(u)) 
						articulationPoints.add(u);
				}	
				
				if (u.getParent() != null && v.getLow() >= u.getDisc()){ 
					if (!articulationPoints.contains(u)) 
						articulationPoints.add(u);
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
