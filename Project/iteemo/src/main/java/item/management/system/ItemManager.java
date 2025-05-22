package item.management.system;

import java.io.*;

class Item {
    private Integer ID;
    private String name, desc, category;
    private int priority;

    public Item(Integer id, String name, String desc, String category, int priority) {
        this.ID = id;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.priority = priority;
    }

    public Integer getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String newDesc) {
        this.desc = newDesc;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String newCategory) {
        this.category = newCategory;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int newPriority) {
        this.priority = newPriority;
    }
}

public class ItemManager {
    private DLL<Item> itemsDll = new DLL<Item>();
    private BinarySearchTree<Integer, DLLNode<Item>> itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
    private MyStack<DLLNode<Item>> undoStack;
    private LinkedPriorityQueue<Item> itemsPQ = new LinkedPriorityQueue<>();

    public ItemManager() {
        this.itemsDll = new DLL<Item>();
        this.itemsBST = new BinarySearchTree<Integer, DLLNode<Item>>();
        this.undoStack = new MyStack<DLLNode<Item>>();
    }

    public void addItem(int ID, String name, String description, String category, int priority) {
        Item newItem = new Item(ID, name, description, category, priority);
        DLLNode<Item> newNode = itemsDll.add(newItem);
        itemsBST.insert(newItem.getID(), newNode);
        itemsPQ.insert(priority, newItem);

    }

    public void viewItemById(int ID) {
        DLLNode<Item> curr = itemsBST.get(ID);
        if (curr != null) {
            System.out.println("--------------------------------------------------");
            System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
            System.out.println("--------------------------------------------------");
            System.out.printf("| %-4d | %-15s | %-20s | %-15s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
            System.out.println("--------------------------------------------------");
        } else {
            System.out.println("Item not found.");
        }
    }

    public void viewAllItems() {
        System.out.println("--------------------------------------------------");
        System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
        System.out.println("--------------------------------------------------");
        PQNode<Item> curr = itemsPQ.getHead();
        while (curr != null) {
            System.out.printf("| %-4d | %-15s | %-20s | %-15s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
        }
        System.out.println("--------------------------------------------------\n");
    }
        
    

    public void updateItem(int ID, String newName, String newDescription, String newCategory, String newPriority) {

    }

    public void deleteItem(int ID) {

    }

    public void undoLastDeletion() {

    }

    public void searchItemByName(String name) {
        DLLNode<Item> curr = itemsDll.getHead();
        int ResultsCount = 0;
        while (curr != null) {
            if (name.equals(curr.getElement().getName())) {
                if (ResultsCount == 0) {
                    System.out.println("------------------Search Results------------------");
                    System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
                    System.out.println("--------------------------------------------------");
                }
                ResultsCount++;
                System.out.printf("| %-4d | %-15s | %-20s | %-15s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
            }
        }
        if (ResultsCount == 0) {
            System.out.println("No items found with the name: " + name);
        } else {
            System.out.println("--------------------------------------------------");
        }
    }

    public void searchItemByCategory(String category) {
        DLLNode<Item> curr = itemsDll.getHead();
        int ResultsCount = 0;
        while (curr != null) {
            if (category.equals(curr.getElement().getCategory())) {
                if (ResultsCount == 0) {
                    System.out.println("------------------Search Results------------------");
                    System.out.printf("| %-4s | %-15s | %-20s | %-15s |\n", "ID", "Name", "Description", "Category");
                    System.out.println("--------------------------------------------------");
                }
                ResultsCount++;
                System.out.printf("| %-4d | %-15s | %-20s | %-15s |\n", curr.getElement().getID(), curr.getElement().getName(), curr.getElement().getDesc(), curr.getElement().getCategory());
            }
        }
        if (ResultsCount == 0) {
            System.out.println("No items found in the category: " + category);
        } else {
            System.out.println("--------------------------------------------------");
        }
    }

    public void saveToFile(String filename) throws IOException {
        File csv = new File("Items.csv");
        BufferedReader br = new BufferedReader(new FileReader(csv));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            addItem(Integer.parseInt(values[0]), values[1], values[2], values[3], Integer.parseInt(values[4]));
        }
        br.close();
    }

    public void loadFromFile(String filename) throws FileNotFoundException {
        File csv = new File("Items.csv");
        DLLNode<Item> curr = itemsDll.getHead();
        PrintWriter out = new PrintWriter(csv);
        while (curr != null) {
            out.printf("%D,%S,%S,%S,%D", curr.getElement().getID(), curr.getElement().getName(),
                    curr.getElement().getDesc(), curr.getElement().getCategory(), curr.getElement().getPriority());
        }
        out.close();
    }
}
