
public class Utils {

    public static String parseUrl(String input){

       // int firstSpace = input.indexOf(' ') + 1;
       // int secondSpace= input.indexOf(' ',firstSpace);
        //return input.substring(firstSpace,secondSpace);

        String[] result = input.split(" ");
        return result[1];
    }

    public static Request.HTTPType parseHttpRequestType(String input) {
        if(input.startsWith("G"))
            return Request.HTTPType.GET;
        else if (input.startsWith("H"))
            return Request.HTTPType.HEAD;
        else if (input.startsWith("PO"))
            return Request.HTTPType.POST;
        throw new RuntimeException("Invalid");

    }

    public Request parseHttpHeader(String input){
        var request = new Request();
        request.type = parseHttpRequestType(input);
        request.url = parseUrl(input);
        return request;

    }

    public static boolean handleRequest(Request request){
        return switch (request.type) {
            case GET -> true;
            case HEAD -> false;
            case POST -> true;
        };
    }

    public String message(){
        return "Hello From Utils";
    }
}
