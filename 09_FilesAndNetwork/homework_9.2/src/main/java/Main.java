public class Main {
    private static final String SRC_DIR = "F:/TEST";
    private static final String DEST_DIR = "F:/TEST1";

    public static void main(String[] args) {

            try {

                FileUtils.copyFolder(SRC_DIR, DEST_DIR);
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
    }
