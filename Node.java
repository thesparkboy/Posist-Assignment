import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;

// This class is used to provide information about a node.
// This class itself contains the nodeData class which contains information about the data of the node.

public class Node {

	Date TimeStamp;
	dataNode data;
	int nodeNumber;
	static int nodeCount = 0;
	String nodeId;
	Node referenceNode, genesisReferenceNode;
	ArrayList<Node> childNodes = new ArrayList<>();
	int hashValue;
	String combinedData;

	class dataNode {
		int ownerId;
		float value;
		String name;
		int hash;

		dataNode(OwnerNode o, float val) {
			this.ownerId = o.ownerId;
			this.value = val;
			this.name = o.name;
			this.hash = hash();
			o.owned.add(this.hash);
		}
	}

	Node(OwnerNode o, Node genesis, Node parent, float value) {
		if (parent != null && value > getRem(parent)) {
			System.out.println("Error, Node Can't be created!");
		}
		this.nodeNumber = ++nodeCount;
		this.TimeStamp = new Date();
		this.data = new dataNode(o, value);
		this.nodeId = hash() + "";
		this.referenceNode = parent;
		this.genesisReferenceNode = genesis;
		this.combinedData = TimeStamp + "" + data.value + "" + data.ownerId + "" + nodeNumber + "" + referenceNode + ""
				+ genesisReferenceNode + "" + childNodes;
		this.hashValue = combinedData.hashCode();
	}

	float getRem(Node p) {
		float sum = 0;
		for (Node x : p.childNodes) {
			sum += x.data.value;
		}
		return p.data.value - sum;
	}

	float changeValue(float x, Node parent) {
		if (getRem(parent) < x - this.data.value) {
			System.out.println("Error!!");
			System.out
					.println("The value can't be changed because it is less than the remaining value of the parent!!");
			return 0;
		}
		return this.data.value = x;
	}

	public void transferOwnership(OwnerNode o1, OwnerNode o2) {
		this.data.ownerId = o2.ownerId;
		this.data.name = o2.name;
		o1.owned.remove(this.data.hash);
		o2.owned.add(this.data.hash);
	}

	public Node addChild(OwnerNode o, Node genesis, float val) {
		Node newNode = new Node(o, this, genesis, val);
		this.childNodes.add(newNode);
		return newNode;
	}

	int getMaxHeight() {
		return getLongest(this, 0);
	}

	int getLongest(Node x, int h) {
		if (x == null)
			return 0;
		else {
			int max = 0;
			for (Node n : x.childNodes) {
				max = Math.max(max, getLongest(n, h + 1));
			}
			return max + 1;
		}
	}

	Node merge(Node n2) {
		Node n1 = this;
		int h1 = getLongest(n1, 0);
		int h2 = getLongest(n2, 0);
		if (h1 >= h2) {
			n1.data.value += n2.data.value;
			n1.childNodes.addAll(n2.childNodes);
			return n1;
		} else {
			n2.data.value += n1.data.value;
			n2.childNodes.addAll(n1.childNodes);
			return n2;
		}
	}

	public int hash() {
		SecureRandom s = new SecureRandom();
		int t = s.nextInt();
		if (t < 0) {
			t *= -1;
		}
		return t;
	}
}
