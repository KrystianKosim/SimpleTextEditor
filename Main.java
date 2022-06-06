package Zajecia11;

public class Main {
    public static void main(String[] args) {
        Model model = new Model("Prosty edytor");
        View view = new View();
        String workAddress = args[0];
        String homeAddress = args[1];
        String schoolAddress = args[2];
        Controller controller = new Controller(view, model, workAddress, homeAddress, schoolAddress);
        controller.initView();
        controller.initController();
    }
}
