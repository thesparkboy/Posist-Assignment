import java.util.HashSet;

public class OwnerNode {

	String name;
	int ownerId;
	static int ownerCount = 0;
	static long totalSum;
	HashSet<Integer> owned;

	OwnerNode(String name) {
		this.name = name;
		this.ownerId = ++ownerCount;
		this.owned = new HashSet<Integer>();
	}

	float getRem(Node n) {
		float sum = 0;
		for (Node x : n.childNodes) {
			sum += x.data.value;
		}
		return n.data.value - sum;
	}

	public boolean authenticateOwner(Node x) {
		if (this.owned.contains(x.data.hash)) {
			return true;
		}
		return false;
	}

	Node addNode(Node p, Node g, float d) {
		if ((p == null && g == null) || (d <= getRem(p) && d <= g.data.value)) {
			totalSum += d;
			return new Node(this, p, g, d);
		}
		System.out.println("Please check the value entered!!");
		return null;
	}

}
