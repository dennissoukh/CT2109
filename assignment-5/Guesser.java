import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Guesser {
    private static String answer = "";
    private static BinaryTree<String> tree = new BinaryTree<String>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        tree = createDefaultTree();

        while (true) {
            System.out.println("\nThink of an animal or a plant. I will try and guess it...");

            BinaryNodeInterface<String> currentNode = tree.getRootNode();

            // Run while the current node is not a leaf
            while (!currentNode.isLeaf()) {
                System.out.println(currentNode.getData());
                answer = in.nextLine();

                if (answer.toLowerCase().startsWith("y")) {
                    currentNode = currentNode.getLeftChild();
                } else {
                    currentNode = currentNode.getRightChild();
                }
            }

            System.out.println("Is it a " + currentNode.getData() + "?");
            answer = in.nextLine();

            if (answer.toLowerCase().startsWith("y")) {
                System.out.println("\nI guessed it! What you would like to do now? Choose a number...");
                System.out.println("1. Load the default tree");
                System.out.println("2. Load a custom tree");
                System.out.println("3. Save the current tree");
                System.out.println("4. Quit");

                String action = in.nextLine();

                if (action.equals("4")) break;

                switch (action) {
                    case "1":
                        tree = createDefaultTree();
                        break;
                    case "2":
                        tree = deserialiseTree();
                        break;
                    case "3":
                        createSerialisedTree();
                        break;
                    default:
                        tree = createDefaultTree();
                        break;
                }

                System.out.println("Would you like to play again? (y or n)");

                action = in.nextLine();
                if (!action.toLowerCase().startsWith("y")) break;
            } else {
                // Answer is incorrect -> need to expand tree
                System.out.println("I don’t know: what is the correct answer?");
                String newItem = in.nextLine();
                String oldItem = currentNode.getData();

                // Ask the user for a new question
                System.out.println("Enter a question for which the answer is YES for " + newItem + " and NO for " + oldItem);
                String newQuestion = in.nextLine();

                // Clean up string and add question mark
                newQuestion = newQuestion.trim();
                if (!newQuestion.endsWith("?")) newQuestion += "?";

                // Update the binary node structure
                currentNode.setData(newQuestion);
                currentNode.setLeftChild(new BinaryNode<String>(newItem));
                currentNode.setRightChild(new BinaryNode<String>(oldItem));
            }
        }
    }

    public static BinaryTree<String> createDefaultTree() {
        BinaryTree<String> tree = new BinaryTree<String>();

        BinaryTree<String> h = new BinaryTree<String>("whale");
        BinaryTree<String> i = new BinaryTree<String>("human");
        BinaryTree<String> j = new BinaryTree<String>("hummingbird");
        BinaryTree<String> k = new BinaryTree<String>("snail");
        BinaryTree<String> l = new BinaryTree<String>("hazelnut");
        BinaryTree<String> m = new BinaryTree<String>("olive");
        BinaryTree<String> n = new BinaryTree<String>("cactus");
        BinaryTree<String> o = new BinaryTree<String>("ash");

        BinaryTree<String> d = new BinaryTree<String>("Does it swim?", h, i);
        BinaryTree<String> e = new BinaryTree<String>("Can it fly?", j, k);
        BinaryTree<String> f = new BinaryTree<String>("Does it grow seeds?", l, m);
        BinaryTree<String> g = new BinaryTree<String>("Can it grow in the desert?", n, o);

        BinaryTree<String> b = new BinaryTree<String>("Is it a mammal?", d, e);
        BinaryTree<String> c = new BinaryTree<String>("Is it a plant?", f, g);

        tree.setTree("Are you thinking of an animal?", b, c);

        return tree;
    }

    public static void createSerialisedTree() {
        System.out.println("Enter name of the new tree file to save");
        String path = in.nextLine();

        try {
            // Create new file and object output streams
            FileOutputStream fs = new FileOutputStream(path);
            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(tree);

            os.close();
            fs.close();

            System.out.println("The tree has been saved successfully\n");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static BinaryTree<String> deserialiseTree() {
        System.out.println("Enter name of the tree file to load");
        String path = in.nextLine();

        BinaryTree<String> serialised = new BinaryTree<String>();

        try {
            FileInputStream fs = new FileInputStream(path);
            ObjectInputStream os = new ObjectInputStream(fs);

            // Cast the object that's in the file into a binary tree. (Type unchecked)
            serialised = (BinaryTree<String>)os.readObject();

            os.close();
            fs.close();

            System.out.println("The tree has been successfully loaded\n");
            return serialised;
        } catch (IOException e) {
            System.out.println(e);
            return serialised;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return serialised;
        }
    }
}
