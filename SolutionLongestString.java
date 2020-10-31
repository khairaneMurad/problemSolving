import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 Given an array of Object that could be either String or array of String, find the longest string
 The array does not contain two string that have the same length

 Example
    input:
        new Object[] { "a", "ab", "adddddddddddddddddbc", (new Object[]{ "abcd" , "cdcdsc"}), (new Object[]{ "abcdfsdddddddddd" , "dsfsdfdsfds"})}
    output:
        adddddddddddddddddbc
 */
class SolutionLongestString {
    public static void main(String[] args) {
        System.out.println(
                getLongestString(new Object[] { "a", "ab", "adddddddddddddddddbc",
                        (new Object[]{ "abcd" , "cdcdsc"}),
                        (new Object[]{ "abcdfsdddddddddd" , "dsfsdfdsfds"})  })
        );
    }

    public static String getLongestString(Object[] array) {
        if (array == null) {
            return null;
        }

        Map<Integer, String> stringsWithAssociatedLength =
                Arrays.asList(array)
                        .stream()
                        .flatMap( i -> {
                            if(i instanceof String)
                                return Stream.of(i);
                            else
                                return Arrays.stream(((Object[]) i));
                        })
                        .map(s -> (String) s)
                        .collect(Collectors.toMap(
                                s -> s.length(),
                                s -> s)
                        );

        return stringsWithAssociatedLength.get(
                stringsWithAssociatedLength.keySet()
                        .stream()
                        .max(Integer::compareTo)
                        .get()
        );
    }
}