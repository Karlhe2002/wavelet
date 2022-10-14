import java.io.IOException;
import java.net.URI;
import java.util.*;

class Search implements URLHandler {
    ArrayList<String> arrlst = new ArrayList<String>();

    public String handleRequest(URI url) {

        if (url.getPath().equals("/")) {
            return "Word List : " + arrlst.toString();
        }

        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                arrlst.add(parameters[1]);
                return String.format("A new string (%s) is Added! There are %d strings in the word list", parameters[1],
                        arrlst.size());
            }
        }

        else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                ArrayList<String> results = new ArrayList<>();
                for (String ele : arrlst) {
                    if (ele.contains(parameters[1])) {
                        results.add(ele);
                        System.out.println(ele);
                    }
                }
                return "reuslt:" + results.toString();
            }
        }

        else {
            return "404 Not Found!";
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
