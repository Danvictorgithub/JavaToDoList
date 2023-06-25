import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

class ToDoList {
    ArrayList<String> TodoList = new ArrayList<>(List.of("Memes"));
    boolean toggle = true;
    JFrame frame;
    JPanel topPanel;
        JLabel topPanelText;
    JPanel todoBody;
        JPanel createTodo;
            JPanel createTodoForm;
                JLabel createTodoFormText;
                JTextField createTodoFormField;
                JButton createTodoFormSubmit;
        JPanel todoInstances;
            // JPanel todoInstance;
                // JButton delTodo;
                // JLabel textTodo;
    Color themeColor = new Color(36, 113, 255);
    protected class RoundedBorder implements Border {
          private int radius;
          private Color color;
          RoundedBorder(int radius, Color color) {
                 this.radius = radius;
                 this.color = color;
          }
          @Override
          public Insets getBorderInsets(Component c) {
                 return new Insets(this.radius + 1, this.radius + 1,
                              this.radius + 2, this.radius);
          }
          @Override
          public boolean isBorderOpaque() {
                 return true;
          }
          @Override
          public void paintBorder(Component c, Graphics g, int x, int y,
                       int width, int height) {
                 g.setColor(color);
                 g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
          }
    }
    protected JPanel todoInstanceMaker(String TodoText) {
        // JPanel todoInstance = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        JPanel todoInstance = new JPanel(new BorderLayout());
                    todoInstance.setBorder(new LineBorder(Color.BLACK));
                    // todoInstance.setPreferredSize(new Dimension(frame.getWidth(),50));
                        // JPanel componentGroup = new JPanel();
                        // componentGroup.setPreferredSize(new Dimension(todoInstances.getWidth(),50));
                        if (toggle) {
                            // componentGroup.setBackground(Color.WHITE);
                            todoInstance.setBackground(new Color(130,196,229));
                        }
                        else {
                            // componentGroup.setBackground(Color.GRAY);
                            todoInstance.setBackground(new Color(104,168,203));
                        }
                            JButton delTodo = new JButton("Delete");
                            delTodo.setBackground(Color.WHITE);
                            delTodo.addActionListener( e-> {
                                TodoList.remove(TodoText);
                                loadTodosFromList();
                            });
                            JLabel textTodo = new JLabel(TodoText);
                            textTodo.setBorder(new EmptyBorder(0, 20, 0, 0));
                        // componentGroup.add(delTodo);
                        // componentGroup.add(textTodo);
                    // todoInstance.add(componentGroup);
                    todoInstance.add(delTodo,BorderLayout.WEST);
                     todoInstance.add(textTodo,BorderLayout.CENTER);
        this.toggle = (toggle == true) ? false : true;
        return todoInstance;
   }
   protected void AddToList() {
    if (createTodoFormField.getText().length()!= 0 && TodoList.size() < 5) {
        TodoList.add(createTodoFormField.getText());
        System.out.println(createTodoFormField.getText());
        loadTodosFromList();
        createTodoFormField.setText("");
    }
   }
    protected void guiInit() {
     //Frame[BorderLayout] [Default]
        frame = new JFrame("Java To Do List");
        frame.setSize(400,600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //topPanel => Frame.BorderLayout.NORTH
        //topPanel[FlowLayout] [Default]
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,40));
        topPanel.setPreferredSize(new Dimension(frame.getWidth(),100));
        topPanel.setBackground(themeColor);
            topPanelText = new JLabel("To-Do-List");
            topPanelText.setFont(new Font("Roboto",Font.BOLD,26));
            topPanelText.setForeground(Color.WHITE);
        topPanel.add(topPanelText);

        todoBody = new JPanel(new BorderLayout());
        todoBody.setBackground(Color.WHITE);
            createTodo = new JPanel();
            createTodo.setBackground(todoBody.getBackground());
            createTodoForm = new JPanel();
            createTodoForm.setBackground(todoBody.getBackground());
            createTodoForm.setBorder(new RoundedBorder(12,Color.BLACK));
            createTodoForm.setPreferredSize(new Dimension(frame.getWidth()-10, 55));
                createTodoFormText = new JLabel("Add Task: ");
                createTodoFormField = new JTextField(20);
                createTodoFormSubmit = new JButton("+");
                createTodoFormSubmit.addActionListener(e -> {
                    AddToList();
                });
                createTodoFormSubmit.setBackground(themeColor);
            createTodoForm.add(createTodoFormText);
            createTodoForm.add(createTodoFormField);
            createTodoForm.add(createTodoFormSubmit);
            createTodo.add(createTodoForm);

            todoInstances = new JPanel();
            todoInstances.setLayout(new GridLayout(5, 1));
            todoInstances.setBackground(Color.WHITE);

            todoBody.add(createTodo,BorderLayout.NORTH);
            todoBody.add(todoInstances,BorderLayout.CENTER);
            frame.getContentPane().add(todoBody,BorderLayout.CENTER);
            frame.getContentPane().add(topPanel,BorderLayout.NORTH);
    }
    protected void loadTodosFromList(){
        todoInstances.removeAll();
        for (String todoContent: TodoList) {
                todoInstances.add(todoInstanceMaker(todoContent));
            }
        frame.repaint();
        frame.revalidate();
    }

    public void go() {
        guiInit();
        loadTodosFromList();
        frame.repaint();
        frame.revalidate();
    }
}
public class Main {
    public static void main(String[] args) {
        ToDoList gui = new ToDoList();
        gui.go();
    }
}
