public class controller {
    static mainFrame curFrame;
    public static void main(String args[]) {
        login x = new login();
    }

    public static void createGui(User cur) throws Exception {
        curFrame = new mainFrame(cur);
    }

    public static void reload(User cur) throws Exception {
        curFrame.dispose();
        curFrame = new mainFrame(cur);
    }
}