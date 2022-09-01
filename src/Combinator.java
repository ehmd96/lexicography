import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Combinator {

    static String[] differentFlagPermutations(int X, String[] arr)
    {
        // Initialisation d'une copie de l'array contenant les caractères a, b, c, d
        String[] backupArray = arr;

        // Initialisation du compteur
        int count = backupArray.length;

        // Boucle lançant le traitement pour toutes les longueurs de chaînes (1 à 4 pour ce cas)
        for(int z = 1; z < X; z++)
        {

            // Déclaration d'un vecteur pour garder les différentes combinaisons (un vecteur peut contenir un array extensible ou réductible d'objets)
            Vector<String> tmp = new Vector<String>();

            // Pour chaque élément de l'array {a, b, c, d}
            for(int i = 0; i < arr.length; i++)
            {
                // Pour chaque élément de l'array miroir (backupArray) {a, b, c, d}
                for(int k = 0; k < backupArray.length; k++)
                {
                        //On ajoute au vecteur chaque combinaison
                        tmp.add(backupArray[k] + arr[i]);
                        count += 1;
                }
            }

            // On affecte le contenu du vecteur converti en array de combinaisons
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

        // Génération des permutations
        String[] combinations = differentFlagPermutations(X, arr);

        // Convertion de l'array des combinaisons en liste de chaînes de caractères
        List<String> combinationsList = Arrays.asList(combinations);

        // On extrait de cette liste une sous-liste ne contenant que les combinaisons respectant les conditions de validité
        List<String> validCombinations = combinationsList
                                        .stream()
                                        .filter(element -> passConditions(element) == true)

                                        .collect(Collectors.toList());
        // On ordonne la liste
        validCombinations.sort(Comparator.comparing(String::toLowerCase));

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
