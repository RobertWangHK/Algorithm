package String;

import Graph.In;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rober on 7/24/2017.
 * Performance cannot be guaranteed, but mostly linear
 */
public class REApplication {
    public static void grep(String[] args) {
        String regexp = "(.*" + args[0] + ".*)";
        NFA nfa = new NFA(regexp);

        while(StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            if (nfa.recognize(line))
                StdOut.println(line);
        }
    }

    // Use String built-in methods to check if given string
    // matches with regular expression
    public static void validate(String[] args) {
        String regexp = args[0];
        String input = args[1];
        StdOut.println(input.matches(regexp));
    }

    // Print all substrings of input that match with a regular expression
    public static void harvest(String[] args) {
        String regexp = args[0];
        In in = new In(args[1]);
        String input = in.readAll();

        // It in fact is a NFA, but with more sophisticated functions
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);

        // But this NFA not only checks match, but also keeps track
        // of recently found substring that causes the match and return
        // by group method
        while(matcher.find()) {
            StdOut.println(matcher.group());
        }

    }
}
