import java.util.HashSet;

public class OwnerNode {

	private String name;
	private int ownerId;
	private static int ownerCount = 0;
	private static long totalSum;
	private HashSet<Integer> owned;
	
	private static final String WRONG_VALUE_ADDEDD "Please check the value entered!!"

	public OwnerNode(final String name) {
		this.name = name;
		this.ownerId = ++ownerCount;
		this.owned = new HashSet<Integer>();
	}

	float getRem(final Node n) {
		float sum = 0;
		for (Node x : n.childNodes) {
			sum += x.data.value;
		}
		return n.data.value - sum;
	}

	public boolean authenticateOwner(final Node x) {
		return this.owned.contains(x.data.hash) ? true: false;
	}

	Node addNode(Node p, Node g, float d) {
		if ((p == null && g == null) || (d <= getRem(p) && d <= g.data.value)) {
			totalSum += d;
			return new Node(this, p, g, d);
		}
		System.out.println(WRONG_VALUE_ADDEDD);
		return null;
	}

}
