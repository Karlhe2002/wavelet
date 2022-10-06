import java.io.IOException;
import java.net.URI;
import java.util.*;

class addstring implements URLHandler {
    ArrayList<String> arrlst = new ArrayList<String>();

    public String addstr(URI url) {

        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("add")) {
                arrlst.add(parameters[1]);
                for (int i = 0; i < arrlst.size(); i++) {
                    System.out.println(arrlst[i]);
                }

            }
        } else if (url.getPath().contains("/search")) {

        }

        {
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new addstring());
    }
}
