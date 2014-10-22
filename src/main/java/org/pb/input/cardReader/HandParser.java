package org.pb.input.cardReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: AROS
 * Date: 10/22/14
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HandParser {

    private static Map<String, Integer> cardCodesOrdinaryAceMap = new LinkedHashMap<String, Integer>() {
        {
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("1", 10);
            put("J", 11);
            put("Q", 12);
            put("K", 13);
            put("A", 14);
        }
    };
    private static Map<String, Integer> cardCodesAceAsOneMap = new LinkedHashMap<String, Integer>() {
        {
            put("A", 1);
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("1", 10);
            put("J", 11);
            put("Q", 12);
            put("K", 13);
        }
    };

    public static void main(String[] args) {

        System.out.println("RESULT 1 PAIR =#" + pokerHandParser("1c4c5cJcJd", "R[=2]") + "#");
        System.out.println("RESULT 2 PAIRS =#" + pokerHandParser("JcQcQcAcAd", "R[=2]R[=2]") + "#");
        System.out.println("RESULT SET =#" + pokerHandParser("JcQcAcAcAd", "R[=3]") + "#");
        System.out.println("RESULT KARE =#" + pokerHandParser("JcAcAcAcAd", "R[=4]") + "#");
        System.out.println("RESULT FULL HOUSE =#" + pokerHandParser("JcJcAcAcAd", "R[=3]R[=2]") + "#");
        System.out.println("RESULT FLUSH =#" + pokerHandParser("2c4c5c3c6c", "S[=5]") + "#");
        System.out.println("RESULT STRAIGHT =#" + pokerHandParser("2c3c4c5c6c", "R[*>>]") + "#");
        System.out.println("RESULT ROYAL FLUSH =#" + pokerHandParser("1cJcQcKcAc", "R[1>>]S[=5]") + "#");

    }

    public enum CardHand {

        PAIR("([\\dJQKA])\\1", 1),
        THREE_OF_A_KIND("([\\dJQKA])\\1\\1", 3),
        FOUR_OF_A_KIND("([\\dJQKA])\\1\\1\\1", 7),
        FLUSH("(\\w)\\1\\1\\1\\1", 5);

        private String regExp;
        private int power;

        private CardHand(String regExp, int power) {
            this.regExp = regExp;
            this.power = power;
        }

        public String getRegExp() {
            return regExp;
        }

        public int getPower() {
            return power;
        }
    }

    public enum PokerRegExpParser {

        RANK_ANYTHING("R\\[(\\*)\\]"), RANK_EQUAL("R\\[=(\\d)\\]"),
        RANK_STRAIGHT("R\\[([\\d\\*])>>\\]"), SUIT_EQUAL("S\\[=(\\d)\\]");

        private String regExp;

        private PokerRegExpParser(String regExp) {
            this.regExp = regExp;
        }

        public String getRegExp() {
            return regExp;
        }

    }

    public static String pokerHandParser(String cards, String regexp) {
        List<String> rPredicates = new ArrayList(Arrays.asList(matchString(regexp, PokerRegExpParser.RANK_EQUAL.getRegExp(), true).split(" ")));
        List<String> sPredicates = new ArrayList(Arrays.asList(matchString(regexp, PokerRegExpParser.SUIT_EQUAL.getRegExp(), true).split(" ")));
        Collections.sort(rPredicates, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        Collections.sort(sPredicates, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        String cardRanks = cards.replaceAll("[cshd]", "");
        String cardSuits = cards.replaceAll("[^cshd]", "");
        String result = "";
        String intermediateResult = "";
        for (String rPredicate : rPredicates) {

            if (rPredicate.equals("4")) {
                intermediateResult = matchString(cardRanks, CardHand.FOUR_OF_A_KIND.getRegExp(), false);
                result += intermediateResult + " ";
                cardRanks = cardRanks.replace(intermediateResult, "");

            }
            if (rPredicate.equals("3")) {
                intermediateResult = matchString(cardRanks, CardHand.THREE_OF_A_KIND.getRegExp(), false);
                result += intermediateResult + " ";
                cardRanks = cardRanks.replace(intermediateResult, "");

            }
            if (rPredicate.equals("2")) {
                intermediateResult = matchString(cardRanks, CardHand.PAIR.getRegExp(), false);
                result += intermediateResult + " ";
                cardRanks = cardRanks.replace(intermediateResult, "");

            }

        }
        rPredicates = new ArrayList(Arrays.asList(matchString(regexp, PokerRegExpParser.RANK_STRAIGHT.getRegExp(), true).split(" ")));
        Collections.sort(rPredicates);
        String straightResult = "";
        List<String> cardRanksArray = new ArrayList(Arrays.asList(cardRanks.split("")));
        for (String rPredicate : rPredicates) {
            if (rPredicate.equals("*")) {
                straightResult = getStraightResult(cardRanksArray);
            } else {
                if (cardRanksArray.get(0).equals(rPredicate)) {
                    straightResult = getStraightResult(cardRanksArray);
                }
            }
        }
        System.out.println("STRAIGHT_RESULT === " + straightResult);
        result += straightResult;
        sortCardsFromTwoToAce(cardRanksArray);
        for (String sPredicate : sPredicates) {
            if (sPredicate.equals("5")) {
                result += matchString(cardSuits, CardHand.FLUSH.getRegExp(), false) + " ";
            }
        }
        result = result.trim();
        result = result.replaceAll("(\\s){2,}", " ");
        return result;
    }

    private static String matchString(String stringToMatch, String regExp, boolean isResultConcatanation) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(stringToMatch);
        String result = "";
        if (isResultConcatanation) {
            while (matcher.find()) {
                result += matcher.group(1) + " ";
            }
        } else {
            while (matcher.find()) {
                result = matcher.group(1);
            }
        }
        System.out.println("RESULT === " + result);
        return result;
    }

    private static void sortCardsFromTwoToAce(List<String> cards) {
        Collections.sort(cards, new Comparator<String>() {
            public int compare(String card1, String card2) {
                return cardCodesOrdinaryAceMap.get(card1).compareTo(cardCodesOrdinaryAceMap.get(card2));
            }
        });
    }

    private static void sortCardsFromAceToKing(List<String> cards) {
        Collections.sort(cards, new Comparator<String>() {
            public int compare(String card1, String card2) {
                return cardCodesAceAsOneMap.get(card1).compareTo(cardCodesAceAsOneMap.get(card2));
            }
        });
    }

    private static String getStraightResult(List<String> cardRanksArray) {
        String straightResult = "";
        if (cardRanksArray.contains("A")) {
            sortCardsFromTwoToAce(cardRanksArray);
            for (int i = 0; i < cardRanksArray.size() - 1; i++) {
                if (cardCodesOrdinaryAceMap.get(cardRanksArray.get(i)) == cardCodesOrdinaryAceMap.get(cardRanksArray.get(i + 1)) - 1) {
                    straightResult += cardRanksArray.get(i);
                } else {
                    straightResult = "";
                }
            }
            if (straightResult.length() < 4) {
                straightResult = "";
                sortCardsFromAceToKing(cardRanksArray);
                for (int i = 0; i < cardRanksArray.size() - 1; i++) {
                    if (cardCodesAceAsOneMap.get(cardRanksArray.get(i)) == cardCodesAceAsOneMap.get(cardRanksArray.get(i + 1)) - 1) {
                        straightResult += cardRanksArray.get(i);
                    } else {
                        straightResult = "";
                    }
                }
                if (straightResult.length() < 4) {
                    straightResult = "";
                }
            }
        } else {
            for (int i = 0; i < cardRanksArray.size() - 1; i++) {
                sortCardsFromTwoToAce(cardRanksArray);
                if (cardCodesOrdinaryAceMap.get(cardRanksArray.get(i)) == cardCodesOrdinaryAceMap.get(cardRanksArray.get(i + 1)) - 1) {
                    straightResult += cardRanksArray.get(i);
                } else {
                    straightResult = "";
                }
            }
            if (straightResult.length() < 4) {
                straightResult = "";
            }
        }
        return straightResult;
    }
}
