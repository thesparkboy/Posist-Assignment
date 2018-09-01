
public class App {

	public static void main(String[] args) {

		OwnerNode o1 = new OwnerNode("o1");

		// Task 1
		// created genesis node
		Node genesis = o1.addNode(null, null, 30);

		// Task 2
		// an empty set of child nodes is created immediately when a node is
		// generated.
		// Check Node.java

		// Task 3
		// Child nodes are generated from the genesis node
		Node b = genesis.addChild(o1, genesis, 17);
		Node c = genesis.addChild(o1, genesis, 10);
		// creating Node d will generate an error because the sum will exceed
		// the genesis value.
		// Node d = genesis.addChild(o1, genesis, 7);

		// Task 4
		// the encryption key is stored in the hash variable
		System.out.println(b.data.hash);

		// Task 5
		// the owner of the node is verified using the hash present in the data
		// node
		System.out.println(o1.authenticateOwner(genesis));

		// Task 6
		// the value of a node is edited using chageValue function
		b.changeValue(15, genesis);
		System.out.println(b.data.value);
		// if the value changed will be greater than the parent,then error will
		// be shown. For eg - 
		// b.changeValue(30, genesis);

		// Task 7
		// The ownership is transferred and verified
		OwnerNode o2 = new OwnerNode("o2");
		genesis.transferOwnership(o1, o2);
		System.out.println(o2.authenticateOwner(genesis));

		// Task 8
		// Longest Chain of genesis node
		System.out.println(genesis.getMaxHeight());

		// Task 9
		// Longest Chain of any random node
		System.out.println(b.getMaxHeight());

		// Task 10
		// Merging of two nodes
		// data.value of b = 15, c = 10
		b = c.merge(b);
		System.out.println(c.data.value);
		System.out.println(b.data.value);
	}

}
