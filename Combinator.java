import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Combinator {

    static String[] differentFlagPermutations(int X, String[] arr)
    {
        String[] backupArray = arr;

        int count = backupArray.length;

        // Traverse all possible lengths
        for(int z = 0; z < X - 1; z++)
        {

            // Stores all combinations
            // of length z
            Vector<String> tmp = new Vector<String>();

            // Traverse the array
            for(int i = 0; i < arr.length; i++)
            {
                for(int k = 0; k < backupArray.length; k++)
                {
                    if (arr[i] != backupArray[k])
                    {

                        // Generate all
                        // combinations of length z
                        tmp.add(backupArray[k] + arr[i]);
                        count += 1;
                    }
                }
            }

            backupArray = tmp.toArray(new String[tmp.size()]);;
        }

        return backupArray;
    }

    public static Boolean passConditions(String combination) {

        Boolean length = false, bFollowedByA = true, doesNotContainsBothDA = true;

        if (combination.length() == 4) length = true;
        if (combination.contains("d") && combination.contains("a")) doesNotContainsBothDA = false;

        char[] combi = combination.toCharArray();

        for (int i = 0; i < combi.length; i ++) {
            if (i < combi.length - 1) {
                if (combi[i] == 'b' && combi[i+1] != 'a') bFollowedByA = false;
            }
            else {
                if (combi[i] == 'b') bFollowedByA = false;
            }
        }

        Boolean validity = length && bFollowedByA && doesNotContainsBothDA;

        return validity;
    }

    public static void retrieveValidCombinations() {
        String []arr = { "a", "b", "c", "d" };

        // Longueur maximale des chaînes de caractère
        int X = 4;

        String[] combinations = differentFlagPermutations(X, arr);

        List<String> combinationsList = Arrays.asList(combinations);

        List<String> validCombinations = combinationsList
                                        .stream()
                                        .filter(element -> passConditions(element) == true)
                                        .collect(Collectors.toList());

        for (String combination: validCombinations) {
            System.out.println(combination);
        }

        System.out.println("Nombre de chaînes: " + validCombinations.size());

    }

    // Driver Code
    public static void main(String[] args) {
        retrieveValidCombinations();
    }
}
