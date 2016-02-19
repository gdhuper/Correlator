

/**
 * ALV tree class extends BST search class and implements DataCounter interface
 */

public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E>
		implements DataCounter<E> {
    /**
     * Constructs an AVL tree with super class constructor.
     */
	public AVLTree()
	{
		super();
		super.overallRoot = null;
	}

	/**
	 * Calculates the Height of the given node
	 * @return Returns the height of node t, or -1 if null.
	 */

	protected int height(BSTNode t) {

		return t == null ? -1 : t.height;

	}

	/**
	 * 
	 * Inserts the integer into the tree
	 * 
	 * @param x the integer to be inserted in the tree
	 */

	public void insert(E x) {
		super.overallRoot = balance(insert(x, super.overallRoot)); // Recursive call to the helper method
		}

	/**
	 * Helper method for insert method.
	 * @param x the integer to be inserted
	 * @param t the node to insert the value in
	 * @return the node with value inserted
	 */

	public BSTNode insert(E x, BSTNode t) {

		if (t == null)

		{
			t = new BSTNode(x);
			return t;
		}
		int compareResult = x.compareTo(t.data);
		if (compareResult < 0) {
			t.left = insert(x, t.left);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
		} else {
			t.count++;
		}
		return balance(t);
	}

	/**
	 * Checks if the tree is unbalanced and then balances it
	 * @param t the node to check
	 * @return the balanced node of the tree
	 */

	private BSTNode balance(BSTNode t) {

		if (t == null)

		{

			return t;

		}

		if (Math.abs(height(t.left) - height(t.right)) > 1)

		{

			if (height(t.left.left) >= height(t.left.right))

			{

				t = singleRightRotation(t);

			}

			else

			{

				t = doubleLeftRightRotation(t);

			}

		}

		else

		{

			if (Math.abs(height(t.right) - height(t.left)) > 1)

			{

				if (height(t.right.right) >= height(t.right.left))

				{

					t = singleLeftRotation(t);

				}

				else

				{

					t = doubleRightLeftRotation(t);

				}

			}

		}

		return t;

	}

	/**
	 * Rotates the binary node with right child.
	 * Single rotation for case 2. updates height and then returns it.
	 * @param k2 the node to be rotated
	 * @return the rotated tree
	 */

	private BSTNode singleRightRotation(BSTNode k2) {

		BSTNode k1 = k2.left;

		k2.left = k1.right;

		k1.right = k2;

		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;

		k1.height = Math.max(height(k1.left), k2.height) + 1;

		return k1;

	}

	/**
	 * Performs the left-right rotation. Case 3.
	 * @param k3 the node to be rotated
	 * @return the balanced node
	 */

	private BSTNode doubleLeftRightRotation(BSTNode k3) {

		k3.left = (singleLeftRotation(k3.left));

		return singleRightRotation(k3);

	}

	/**
	 * Performs the single left rotation. Case 
	 * @param k1 the node to be rotated
	 * @return the balanced node
	 */

	private BSTNode singleLeftRotation(BSTNode k1) {

		BSTNode k2 = k1.right;

		k1.right = k2;

		k2.left = k1;

		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;

		k2.height = Math.max(height(k2.right), k1.height) + 1;

		return k2;

	}

	/**
	 * Performs the right-left rotation of the given node
	 * @param k1 the node to be rotated
	 * @return the balanced node
	 */

	private BSTNode doubleRightLeftRotation(BSTNode k1) {

		k1.right = singleRightRotation(k1.right);

		return singleLeftRotation(k1);

	}

	@Override
	public void incCount(E data) {

		super.overallRoot = insert(data, super.overallRoot);

	}

	@Override
	public int getSize() {

		return super.size;

	}

	@Override
	public DataCount<E>[] getCounts() {

		return super.getCounts();

	}
}