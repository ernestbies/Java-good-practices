// [TiJO Lecture] :: Przykłady
package com.ernestbies;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Klasa 'ExampleRunner' odpowiedzialna jest za uruchamianie poszczególnego przykładu.
public class ExampleRunner {
    // Prywatny konstruktor, dzięki któremu nie jest możliwe utworzenie instancji klasy 'ExampleRunner'.
    private ExampleRunner() {

    }

    // Metoda, która powoduje uruchomienie dango przykładu.
    public static void runExample(int exampleNumber) throws EmptyDataException {
        System.out.println("[TiJO Lecture] :: Code style, dobre praktyki, code review. Ernest Bieś, PWSZ Tarnów 2020");
        switch (exampleNumber) {
            case 0:
                System.out.println("Przykład 1) Liczby parzyste/nieparzyste w zależności od typu");
                ArrayList<Integer> listOfNumbers = new ArrayList<>();
                listOfNumbers.add(1);
                listOfNumbers.add(2);
                listOfNumbers.add(3);
                listOfNumbers.add(4);
                listOfNumbers.add(5);
                System.out.println("Lista liczb: "+ listOfNumbers);
                System.out.println("Liczby parzyste: " + getEvenOddNumbers(listOfNumbers, 0));
                System.out.println("Liczby nieparzyste: " + getEvenOddNumbers(listOfNumbers, 1));
                break;
            case 1:
                System.out.println("Przykład 2) Liczba cyfr zapisanych jako łańcuch znakowy rozdzielony przecinkiem");
                String numbers = "1,2,3,4,5,6,7";
                System.out.println("Cyfry: " + numbers + ", liczba cyfr: " + getNumbersCountNullCheckLast(numbers));
                try {
                    System.out.println("Wariant I. Sprawdzenie nulla po metodzie isEmpty() -> NullPointerException");
                    getNumbersCountNullCheckLast(null);
                }catch (NullPointerException npe) {
                    System.out.println("Złapano wyjątek: " + npe.toString());
                }

                try {
                    System.out.println("Wariant II. Sprawdzenie nulla przed isEmpty() -> EmptyDataException");
                    getNumbersCountNullCheckFirst(null);
                }catch (EmptyDataException ede) {
                    System.out.println("Złapano wyjątek: " + ede.toString());
                }

                System.out.println("Kompilacja zawsze od lewej do prawej strony!");
                break;
            case 2:
                System.out.println("Przykład 3) Funkcja zwraca listę wszystkich słów zawierających 'A'");
                String words = "Ala,Jan,Anita,Agata,Janusz,Mateusz";
                System.out.println("Lista słów: " + words);
                System.out.println("Bez użycia strumieni: " + getWordsWithA(words));
                System.out.println("Z użyciem strumieni: " + streamsGetWordsWithA(words));
                break;
            case 3:
                System.out.println("Przykład 4) Lista słów rozpoczynających się na literę podaną w parametrze metody");
                ArrayList<String> wordsToCheck = new ArrayList<>();
                String param = "A";
                wordsToCheck.add("Ala");
                wordsToCheck.add("Tomasz");
                wordsToCheck.add("aAgata");
                wordsToCheck.add("Amadeusz");
                System.out.println("Lista wszystkich słów: " + wordsToCheck);
                System.out.println("Przekazany parametr: " + param);
                System.out.println("Lista wynikowa: " + getWordsStartsAt(wordsToCheck, param));
                break;
            case 4:
                System.out.println("Przykład 5) Lista słów, które zawierają łańcuch znakowy i długość > podanej");
                ArrayList<String> listOfWords = new ArrayList<>();
                listOfWords.add("Ala");
                listOfWords.add("Tomasz");
                listOfWords.add("aAgata");
                listOfWords.add("Amadeusz");
                System.out.println("Lista wszystkich słów: " + listOfWords);
                System.out.println(getWordsContainsStringWithLength(listOfWords, "A", 5));
                break;
            case 5:
                System.out.println("Przykład 6) Metoda eliminuje duplikaty z listy za pomocą strumieni");
                ArrayList<String> listWithDuplicates = new ArrayList<>();
                listWithDuplicates.add("Ala");
                listWithDuplicates.add("Jan");
                listWithDuplicates.add("Ala");
                listWithDuplicates.add("Mateusz");
                System.out.println("Lista wszystkich elementów: " + listWithDuplicates);
                System.out.println("Lista bez duplikatów: " + getDistinctWords(listWithDuplicates));
                break;
            case 6:
                System.out.println("Przykład 7) Strumienie - sortowanie");
                String unsortedNumbers = "0,2,10,5,3,0,11,6,15";
                System.out.println("Lista liczb (zapisana jako String): " + unsortedNumbers);
                System.out.println("Lista posortowana: " + getSortedList(unsortedNumbers));
                break;
            case 7:
                System.out.println("Przykład 8) Strumienie - otrzymywanie największej parzystej wartości");
                String nums = "5,6,10,4,2,1,-100,20,-105,40,-34,23,101";
                System.out.println("Lista liczb (zapisana jako String): " + nums);
                System.out.println("Największa parzysta wartość: " + getMaximumNumber(nums));
                break;
            case 8:
                System.out.println("Przykład 9) Optional / Operacje na strumieniu - najmniejsza liczba");
                System.out.println(getMinimumNumber());
                break;
            case 9:
                System.out.println("Przykład 10) Optional - przykład działania ofNullable");
                ofNullableExample();
                break;
            default:
                System.out.println("Wybierz poprawny przykład");
                break;
        }
    }


    /**
     * JavaDoc Example - Metoda zwraca listę liczb parzystych lub nieparzystych w zależności od przekazanego typu
     * @param listOfNumbers przekazywana lista liczb
     * @param type          przekazywany typ (0 - liczby parzyste, 1 - liczby nieparzyste)
     * @return              zwracana lista liczb parzystych/nieparzystych
     * */
    private static List<Integer> getEvenOddNumbers(ArrayList<Integer> listOfNumbers, int type) {
        if (type == 0) {
            return listOfNumbers.stream().filter(num -> num %2 == 0).collect(Collectors.toList());
        } else {
            return listOfNumbers.stream().filter(num -> num %2 != 0).collect(Collectors.toList());
        }
    }


    // Metoda zwraca liczbę cyfr zapisanych jako łańcuch znakowy rozdzielony przecinkiem
    private static long getNumbersCountNullCheckLast(String numbers) throws EmptyDataException {
        // kompilator podpowiada nam, że warunek nigdy nie zostanie spełniony
        if (numbers.isEmpty() || numbers == null) {
            throw new EmptyDataException();
        } else {
            return Arrays.stream(numbers.split(",")).count();
        }
    }

    // Metoda zwraca ilość liczb zapisanych jako łańcuch znakowy rozdzielony przecinkiem
    private static long getNumbersCountNullCheckFirst(String numbers) throws EmptyDataException {
        if (numbers == null || numbers.isEmpty()) {
            throw new EmptyDataException();
        } else {
            return Arrays.stream(numbers.split(",")).count();
        }
    }

    // [TiJO Lecture] :: Przykłady operacji na strumieniach

    // Metoda zwraca listę wszystkich słów zawierających 'A' - przykład bez użycia strumieni
    public static List<String> getWordsWithA(String words) {
        String[] listOfWords = words.split(",");
        List<String> newList = new ArrayList<>();
        for(String word: listOfWords){
            if(word.contains("A")){
                newList.add(word);
            }
        }
        return newList;
    }

    // Metoda zwraca listę wszystkich słów zawierających 'A' - przykład z użyciem strumieni
    // Całość możemy zapisać teraz w jednej linijce, kod staje się czytelny i łatwiejszy do analizy.
    private static List<String> streamsGetWordsWithA(String words) {
        // Wybranie wszystkich elementów dla których word.contains("A") == true
        // oraz zamiana na listę -> collect(Collectors.toList());
        return Arrays.stream(words.split(","))
                .filter(word -> word.contains("A"))
                .collect(Collectors.toList());
    }

    // Metoda zwraca listę wszystkich słów rozpoczynających się na literę podaną w parametrze metody
    private static List<String> getWordsStartsAt(List<String> words, String firstLetter) {
        // Wybranie wszystkich elementów dla których t.substring(0, 1).equals(firstLetter) == true
        // oraz zamiana na listę -> collect(Collectors.toList());
        return words.stream()
                .filter(t -> t.substring(0, 1).equals(firstLetter))
                .collect(Collectors.toList());
    }

    // Metoda zwraca listę wszystkich słów, które zawierają łańcuch znakowy oraz mają długość większą od przekazanej
    private static List<String> getWordsContainsStringWithLength(List<String> words, String containsString, int length) {
        return words.stream()
                .filter(s -> s.length() > length)
                .filter(s -> s.contains(containsString))
                .collect(Collectors.toList());
    }

    // Metoda eliminuje duplikaty z listy za pomocą strumieni
    private static List<String> getDistinctWords(List<String> words) {
        return words.stream().distinct().collect(Collectors.toList()); // Użycie funkcji dinstinct
    }

    // Strumienie - sortowanie
    private static List<Integer> getSortedList(String numbers) {
        return Arrays.stream(numbers.split(","))
                .map(Integer::parseInt) // Zamiana na typ Integer
                .sorted() // Posortowanie za pomocą funkcji sorted
                .collect(Collectors.toList()); // Zamiana na listę
    }

    // Strumienie - ustawienie limitu wyników (otrzymywanie największej parzystej wartości)
    private static int getMaximumNumber(String numbers) {
        return Arrays.stream(numbers.split(","))
                .map(Integer::parseInt) // Zamiana na typ Integer
                .sorted(Comparator.reverseOrder()) // Użycie Comparatora w odwróconej kolejności - reverseOrder
                .filter(t -> t%2 == 0) // Przefiltrowanie listy
                .limit(1) // Ograniczenie wyniku do 1
                .collect(Collectors.toList()) // Zamiana na listę
                .get(0); // Zwrócenie pierwszego elementu
    }

    // Optional - minimalna liczba, funkcja zwraca Optional<Integer>
    private static Optional<Integer> getMinimumNumber() {
        Stream<Integer> intStream = Stream.of(111,-50,21,5553,24,5); // --> Utworzenie strumienia liczb
        // Optional<Integer> minimumValue = intStream.min(Comparator.naturalOrder());
        // Jeżeli na tym samym strumieniu chcielibyśmy wyszukać liczbę maksymalną:
        // Optional<Integer> maximumValue = intStream.max(Comparator.naturalOrder());
        // to dostaniemy wyjątek: java.lang.IllegalStateException: stream has already been operated upon or closed
        // UWAGA :: Na strumieniu można wykonać tylko jedną operację kończącą, potem zostaje on zamknięty!
        return intStream.min(Comparator.naturalOrder());
    }

    // Optional - przykład działania ofNullable
    private static void ofNullableExample(){
        // Użycie Optional.ofNullable(kolekcja)
        Collection<Integer> collection = Arrays.asList(1,2,3);
        // W przypadku podania poprawnych wartości zostanie zwrócona lista liczb
        System.out.println(new ArrayList<>(Optional.ofNullable(collection).orElse(Collections.emptyList())));
        // W przypadku podania null-a zostanie zwrócona pusta lista
        collection = null;
        System.out.println(new ArrayList<>(Optional.ofNullable(collection).orElse(Collections.emptyList())));
    }
}
