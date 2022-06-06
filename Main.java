package Zajecia11;

public class Main {
    public static void main(String[] args) {
        Model model = new Model("Prosty edytor");
        View view = new View();
        String workAddress = "Warszawa, złota 12";
        String homeAddress = "Warszawa, al. Jerozolimskie 320";
        String schoolAddress = "Warszawa, Koszykowa 86";
        Controller controller = new Controller(view,model,workAddress,homeAddress,schoolAddress);
        controller.initView();
        controller.initController();
    }
}
