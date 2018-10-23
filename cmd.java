import java.util.*;

class Node
{
	public String name;
	public Node child[] = new Node[4];
	public Node parent;
	public String path;
// -------------------------------------------------------------	
	public Node(String dirname, Node par)
	{
		name = dirname;
		for (int i=0; i<4; i++) { child[i] = null; }
		parent = par;
	}
// -------------------------------------------------------------
	public String getname() { return name; }
// -------------------------------------------------------------
	public void printname()
	{
		System.out.print("/");
		System.out.print(name);
	}
}  
////////////////////////////////////////////////////////////////
class Tree
{
	public Node current;
	private Node root;
	private Node temp;	
// -------------------------------------------------------------
	public Tree()                  
	{ 	
		root = new Node("root", null);
		root.path = "root";		
		current = root;
	}    
// -------------------------------------------------------------
	public void dir()
	{
		String names[] = new String[4];
		int length=0;
		
		if (current.child[0] != null) 
		{
			names[0] = current.child[0].getname();
			length++;
		}
		if (current.child[1] != null) 
		{
			names[1] = current.child[1].getname();
			length++;
		}
		if (current.child[2] != null) 
		{
			names[2] = current.child[2].getname();
			length++;
		}
		if (current.child[3] != null) 
		{
			names[3] = current.child[3].getname();
			length++;
		}
		
		BubbleSort(names,length);
				
		for (int i=0; i<length; i++)
			System.out.print("/" + names[i] + "\n");
	}
	
	private static void BubbleSort(String[] array, int length) 
	{
		String t;
		for(int i=0; i<length; i++) 
		{
			for(int j=0; j<length-1-i; j++) 
			{
				if(array[j].compareTo(array[j+1])>0) 
				{
					t= array[j];
					array[j] = array[j+1];
					array[j+1] = t;
				}
			}
		}
	}
// -------------------------------------------------------------	
	public void makeDir(String dirname)
	{
		boolean done = false;
	
		for (int i=0; i<4; i++)
		{
			if (current.child[i] != null && current.child[i].name.equals(dirname))
			{
				System.out.println("Folder with wthis name already exists");
				return;
			}
		}	
	
		for (int i=0; i<4; i++)
		{
			if (current.child[i] == null)
			{
				Node newNode = new Node(dirname, current);
				current.child[i] = newNode;
				current = current.child[i];
				current.path = current.parent.path + "/" + dirname;
				done = true;
				break;
			}
		}
		if (done == false)
			System.out.println("No more space for new directory (MAX 4)");
	}
// -------------------------------------------------------------
	public void dirAll(Node cur, int s)
	{
		if (cur == null) return;
		for (int i=0; i<s; i++)
			System.out.print("   |");
		cur.printname();
		System.out.print("\n");
		for (int i=0; i<4; i++)
			dirAll(cur.child[i],s+1);
	}
// -------------------------------------------------------------
	public void cdUp()
	{
		if (current == root)
			System.out.println("No folder above root");
		else
			current = current.parent;
	}
// -------------------------------------------------------------
	public void delete(String dirname)
	{
		if ((current.child[0] != null) && (current.child[0].name.equals(dirname)))
		{
			current.child[0] = null;
			System.out.println("Folder deleted");
			return;
		}
		if ((current.child[1] != null) && (current.child[1].name.equals(dirname)))
		{
			current.child[1] = null;
			System.out.println("Folder deleted");
			return;
		}
		if ((current.child[2] != null) && (current.child[2].name.equals(dirname)))
		{
			current.child[2] = null;
			System.out.println("Folder deleted");
			return;
		}
		if ((current.child[3] != null) && (current.child[3].name.equals(dirname)))
		{
			current.child[3] = null;
			System.out.println("Folder deleted");
			return;
		}
		System.out.println("Folder not found");
	}
//--------------------------------------------------------------
	public void cd(String dirname)
	{
		if ((current.child[0] != null) && (current.child[0].name.equals(dirname)))
		{
			current = current.child[0];
			return;
		}
		if ((current.child[1] != null) && (current.child[1].name.equals(dirname)))
		{
			current = current.child[1];
			return;
		}
		if ((current.child[2] != null) && (current.child[2].name.equals(dirname)))
		{
			current = current.child[2];
			return;
		}
		if ((current.child[3] != null) && (current.child[3].name.equals(dirname)))
		{
			current = current.child[3];
			return;
		}
		System.out.println("Folder not found");
	}
//--------------------------------------------------------------
	public void copy(String dirname)
    { 
        if (current.child[0]!=null && current.child[0].name.equals(dirname)) temp = current.child[0];
        else if (current.child[1]!=null && current.child[1].name.equals(dirname)) temp = current.child[1];
        else if (current.child[2]!=null && current.child[2].name.equals(dirname)) temp = current.child[2];
        else if (current.child[3]!=null && current.child[3].name.equals(dirname)) temp = current.child[3];      
        else System.out.println("Folder not found");       
    }
//--------------------------------------------------------------
    public void cut(String dirname)
    { 
        if(current.child[0]!=null && current.child[0].name.equals(dirname))
        {
			temp=current.child[0]; 
			current.child[0]=null; 
		}
        else if(current.child[1]!=null && current.child[1].name.equals(dirname))
		{
			temp=current.child[1]; 
			current.child[1]=null; 
		}
        else if(current.child[2]!=null && current.child[2].name.equals(dirname))
        {
			temp=current.child[2]; 
			current.child[2]=null; 
		}
        else if(current.child[3]!=null && current.child[3].name.equals(dirname))
		{
			temp=current.child[3]; 
			current.child[3]=null;
		}       
        else  System.out.println("Folder not found");       
    }
//--------------------------------------------------------------
    public void paste()
    {
		if(temp == null) 
		{
			System.out.println("Copy/Cut before Paste");
			return;
		}
       
        if(current.child[0] == null)
        {
			current.child[0] = temp; 
			current.child[0].parent = current; 
		}
        else if(current.child[1] == null)
        { 
			current.child[1] = temp; 
			current.child[1].parent = current; 
		}
        else if(current.child[2] == null)
        { 
			current.child[2] = temp; 
			current.child[2].parent = current; 
		}
        else if(current.child[3] == null)
        { 
			current.child[3] = temp; 
			current.child[3].parent = current; 
		}
        else System.out.println("No more space for new directory (MAX 4)") ;
    }    
}
////////////////////////////////////////////////////////////////
class cmd
{
	public static void main (String args[])
	{
		Scanner input = new Scanner(System.in);
		Tree myTree = new Tree();
		String command = "not command set";
		System.out.print("\nAvailable commands:\ndir,dirall,make,delete,cdup,cd,copy,cut,paste,help\n--------------------------------------------------\n");
		
		
		while (command != "exit")
		{
			System.out.print("\n");
			System.out.print(myTree.current.path);
			System.out.print(">");
			command = input.nextLine();
			switch (command) 
			{
				case "dir":
					myTree.dir();
					break;
				case "dirall":
					myTree.dirAll(myTree.current,0);
					break;
				case "make":
					System.out.print(" ");
					command = input.nextLine();
					myTree.makeDir(command);
					break;
				case "delete":
					System.out.print(" ");
					command = input.nextLine();
					myTree.delete(command);
					break;
				case "cdup":
					myTree.cdUp();
					break;
				case "cd":
					System.out.print(" ");
					command = input.nextLine();
					myTree.cd(command);
					break;
				case "copy":
					System.out.print(" ");
					command = input.nextLine();
					myTree.copy(command);
					break;
				case "cut":
					System.out.print(" ");
					command = input.nextLine();
					myTree.cut(command);
					break;
				case "paste":
					myTree.paste();
					break;
				case "exit":
					command = "exit";
					break;
				default:
					System.out.println("Unknown command!");
					break;
			}
		}
	}
}