package de.blackout.watturnier;

import java.util.*;

public class Main {

    private static Random random = new Random();

    public static void main(final String[] teams) {

        // check if amount of teams is uneven
        if (teams.length % 2 != 0) {
            throw new IllegalArgumentException("amount of teams must be even");
        }

        // split teams into two equal pots
        final int len = teams.length;
        final String[] a = Arrays.copyOfRange(teams, 0, len/2);
        final String[] b = Arrays.copyOfRange(teams, (len/2), len);

        final List<Integer> usedOffsetsTeam = fillAndShuffle(a.length);

        final Random randomA = new Random(45);
        final Random randomB = new Random(45);

        for (int i = 0; i < 3; i++) {
            final int offset = usedOffsetsTeam.get(i);
            System.out.println("Round " + (i+1) + " with offset: " + offset);

            final List<String> aTeams = Arrays.asList(a);
            Collections.shuffle(aTeams, randomA);
            final List<String> bTeams = shuffle(Arrays.asList(b), offset);
            Collections.shuffle(bTeams, randomB);

            // flip sides of the table
            for (int i1 = 0; i1 < aTeams.size(); i1++) {
                if (random.nextBoolean()) {
                    final String teamA = aTeams.get(i);
                    final String teamB = bTeams.get(i);
                    aTeams.set(i, teamB);
                    bTeams.set(i, teamA);
                }
            }

            System.out.println(aTeams);
            System.out.println(bTeams);
            System.out.println();
        }
    }

    private static LinkedList<Integer> fillAndShuffle(int size) {
        final LinkedList<Integer> integers = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            integers.add((i+1));
        }
        Collections.shuffle(integers);
        return integers;
    }


    public static List<String> shuffle(final List<String> strings, final int offset) {
        final LinkedList<String> modifiableStrings = new LinkedList<>(strings);

        for (int i = 0; i < offset; i++) {
            final String remove = modifiableStrings.remove(0);
            modifiableStrings.add(remove);
        }
        return modifiableStrings;
    }




}
