package Zajecia11;

public class Main {
    public static void main(String[] args) {
        Model model = new Model("Prosty edytor");
        View view = new View();
        Controller controller = new Controller(view,model);
        controller.initView();
        controller.initController();
    }
}
