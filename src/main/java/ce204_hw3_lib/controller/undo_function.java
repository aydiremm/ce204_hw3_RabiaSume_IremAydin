package ce204_hw3_lib.controller;

import java.util.Stack;

import javax.swing.undo.UndoManager;

public class undo_function {
    private static Stack<Object> Undo = new Stack<Object>();
   

    public void execute( UndoManager manager  ) {
    	manager.undo();
    }
    
    

    public static void WRITE(Stack<Character> Undo, char X) {
        Undo.push(X);
    }

    public static void UNDO(Stack<Character> Undo) {
        if (!Undo.isEmpty()) {
            Undo.pop();
        }
    }

    public static void READ(Stack<Character> Undo) {
        Stack<Character> revOrder = new Stack<Character>();

        while (!Undo.isEmpty()) {
            revOrder.push(Undo.peek());
            Undo.pop();
        }

        while (!revOrder.isEmpty()) {
            System.out.print(revOrder.peek());
            Undo.push(revOrder.peek());
            revOrder.pop();
        }

        System.out.print(" ");
    }

}
