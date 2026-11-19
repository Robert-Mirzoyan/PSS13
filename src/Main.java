/** Runs tests only for the coding problems in PSS13. */
public class Main {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> finalTree = buildFinalTreeFromProblem1();

        testProblem2(finalTree);
        testProblem3(finalTree);
        testProblem4(finalTree);
        testProblem5(finalTree);
        testOptionalProblem1();

        System.out.println("All coding-problem tests passed.");
    }

    private static LinkedBinaryTree<Integer> buildFinalTreeFromProblem1() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        Position<Integer> seventy = tree.addRoot(70);
        Position<Integer> twenty = tree.addLeft(seventy, 20);
        Position<Integer> eighty = tree.addRight(seventy, 80);
        tree.addLeft(twenty, 10);
        Position<Integer> forty = tree.addRight(twenty, 40);
        tree.addLeft(forty, 35);
        tree.addRight(eighty, 90);
        return tree;
    }

    private static void testProblem2(BinaryTree<Integer> tree) {
        check(Integer.valueOf(40).equals(
                        Problem2Solution.floorKey(tree, 65)),
                "Problem 2: floorKey(65) should be 40.");
        check(Integer.valueOf(70).equals(
                        Problem2Solution.floorKey(tree, 70)),
                "Problem 2: floorKey(70) should be 70.");
        check(Problem2Solution.floorKey(tree, 5) == null,
                "Problem 2: floorKey(5) should be null.");
        check(Problem2Solution.floorKey(
                        new LinkedBinaryTree<Integer>(), 10) == null,
                "Problem 2: an empty tree should return null.");
        System.out.println("Problem 2 tests passed.");
    }

    private static void testProblem3(BinaryTree<Integer> validTree) {
        check(Problem3Solution.isValidBST(validTree),
                "Problem 3: the final tree from Problem 1 is a valid BST.");

        LinkedBinaryTree<Integer> invalidTree = new LinkedBinaryTree<>();
        Position<Integer> ten = invalidTree.addRoot(10);
        Position<Integer> five = invalidTree.addLeft(ten, 5);
        invalidTree.addRight(ten, 15);
        invalidTree.addRight(five, 12);

        check(!Problem3Solution.isValidBST(invalidTree),
                "Problem 3: the counterexample tree is not a valid BST.");
        check(Problem3Solution.isValidBST(new LinkedBinaryTree<Integer>()),
                "Problem 3: an empty tree should be a valid BST.");
        System.out.println("Problem 3 tests passed.");
    }

    private static void testProblem4(BinaryTree<Integer> tree) {
        check(Problem4Solution.rangeSum(tree, 35, 75) == 145,
                "Problem 4: range [35,75] should sum to 145.");
        check(Problem4Solution.rangeSum(tree, -100, 1000) == 345,
                "Problem 4: the full tree should sum to 345.");
        check(Problem4Solution.rangeSum(tree, 41, 69) == 0,
                "Problem 4: a range containing no keys should sum to 0.");
        System.out.println("Problem 4 tests passed.");
    }

    private static void testProblem5(BinaryTree<Integer> tree) {
        Position<Integer> first =
                Problem5Solution.lowestCommonAncestor(tree, 10, 35);
        check(first != null && first.getElement() == 20,
                "Problem 5: LCA of 10 and 35 should be 20.");

        Position<Integer> second =
                Problem5Solution.lowestCommonAncestor(tree, 35, 90);
        check(second != null && second.getElement() == 70,
                "Problem 5: LCA of 35 and 90 should be 70.");

        Position<Integer> third =
                Problem5Solution.lowestCommonAncestor(tree, 80, 90);
        check(third != null && third.getElement() == 80,
                "Problem 5: LCA of 80 and 90 should be 80.");
        System.out.println("Problem 5 tests passed.");
    }

    private static void testOptionalProblem1() {
        check(OptionalProblem1Solution.minimumAVL(0) == null,
                "Optional Problem 1: height 0 should return null.");

        AVLNode root = OptionalProblem1Solution.minimumAVL(4);
        check(height(root) == 4,
                "Optional Problem 1: the tree should have height 4.");
        check(countNodes(root) == 7,
                "Optional Problem 1: a minimum height-4 AVL has 7 nodes.");
        check(isBalanced(root),
                "Optional Problem 1: the result should be AVL-balanced.");
        check(isBST(root, null, null),
                "Optional Problem 1: the result should satisfy BST ordering.");
        System.out.println("Optional Problem 1 tests passed.");
    }

    private static int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private static int countNodes(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private static boolean isBalanced(AVLNode node) {
        if (node == null) {
            return true;
        }
        return Math.abs(height(node.left) - height(node.right)) <= 1
                && isBalanced(node.left)
                && isBalanced(node.right);
    }

    private static boolean isBST(
            AVLNode node,
            Integer lowerBound,
            Integer upperBound) {
        if (node == null) {
            return true;
        }
        if (lowerBound != null && node.item <= lowerBound) {
            return false;
        }
        if (upperBound != null && node.item >= upperBound) {
            return false;
        }
        return isBST(node.left, lowerBound, node.item)
                && isBST(node.right, node.item, upperBound);
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
