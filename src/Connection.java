
public class Connection {
	
	private Person sourceCharacter;
	private Person targetCharacter;
	private int weight;
	
	public Connection(Person sourceCharacter, Person targetCharacter, int weight) {
		super();
		this.sourceCharacter = sourceCharacter;
		this.targetCharacter = targetCharacter;
		this.weight = weight;
	}
	public Person getSourceCharacter() {
		return sourceCharacter;
	}
	public void setSourceCharacter(Person sourceCharacter) {
		this.sourceCharacter = sourceCharacter;
	}
	public Person getTargetCharacter() {
		return targetCharacter;
	}
	public void setTargetCharacter(Person targetCharacter) {
		this.targetCharacter = targetCharacter;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sourceCharacter == null) ? 0 : sourceCharacter.hashCode());
		result = prime * result + ((targetCharacter == null) ? 0 : targetCharacter.hashCode());
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
		Connection other = (Connection) obj;
		if (sourceCharacter == null) {
			if (other.sourceCharacter != null)
				return false;
		} else if (!sourceCharacter.equals(other.sourceCharacter))
			return false;
		if (targetCharacter == null) {
			if (other.targetCharacter != null)
				return false;
		} else if (!targetCharacter.equals(other.targetCharacter))
			return false;
		return true;
	}
}
