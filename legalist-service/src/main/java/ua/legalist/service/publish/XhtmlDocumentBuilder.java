package ua.legalist.service.publish;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

/**
 * Parses XHTML Template, binds personalized data and builds XHTML representing
 * final Document.
 */
public class XhtmlDocumentBuilder {

    public static void main(String[] args) {

        String xhtml
                = "<html>"
                + "<head>"
                + "<link rel='stylesheet' type='text/css' href='styles.css'/>" //.css is in the project folder
                + "</head>"
                + "<body>"
                + "<div class='header'>"
                + "<p>До <span id='courtNameR'>назва_органу</span></p>"
                + "<p>Адреса: <span id='courtAddress'>адреса_органу</span></p>"
                + "<p>Позивач: <span id='surnameUser'>прізвище_позивача</span></p>"
                + "</div>"
                + "<h1> ПОЗОВНА ЗАЯВА"
                + "про траляляля</h1>"
                + "</body>"
                + "</html>";

        Document doc = Jsoup.parse(xhtml);
        printNodesRecusrively(doc);

    }

    private static void printNodesRecusrively(Node rootNode) {
        System.out.println(rootNode);
        System.out.println(
            rootNode.attributes()
        );
        System.out.println();
        for (Node node : rootNode.childNodes()) {
            printNodesRecusrively(node);
        }
    }

}
