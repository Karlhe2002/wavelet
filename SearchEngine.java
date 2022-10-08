import java.io.IOException;
import java.net.URI;
import java.util.*;

class Search implements URLHandler {
    ArrayList<String> arrlst = new ArrayList<String>();

    public String handleRequest(URI url) {

        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("add")) {
                arrlst.add(parameters[1]);
            }
            return String.format("A new string (%s) is Added! There are %d strings in the word list", parameters[1],
                    arrlst.size());
        } else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for (String ele : arrlst) {
                    ArrayList<String> results = new ArrayList<>();
                    if (ele.contains(parameters[1])) {
                        results.add(ele);
                    }
                }
            }
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Search());
    }
}
