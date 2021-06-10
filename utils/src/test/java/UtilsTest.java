
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

    public class UtilsTest {

        @Test
        void simpleGETReturnsUrl() {
            String url = Utils.parseUrl("""
                GET / HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                 \r\n \
                 """);
           assertThat(url).isEqualTo("/");
        }

        @Test
        void filePathGETReturnsUrl() {
            String url = Utils.parseUrl("""
                GET /Index.html HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                 \r\n \
                 """);

            assertThat(url).isEqualTo("/Index.html");
        }

        @Test
        void filePathHEADReturnsHEADAndUrl() {
            Request.HTTPType url = Utils.parseHttpRequestType("""
                    HEAD /Index.html HTTP/1.1\r\n \
                    Host: www.example.com\r\n \
                     \r\n \
                     """);

            assertThat(url).isEqualTo(Request.HTTPType.HEAD);
        }


    }
