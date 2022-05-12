package com.example.demo.controller;
import java.util.*;
import java.util.stream.Collectors;


public class Intern {

    private final String name;
    public final int globe1, globe2, globe3, globe4, globe5, sports, music1, music2, movie, workout, read, photo, extroverted, energetic, impulsive, cautious, persistent, perceptive, creative;

    public Intern(String name, int globe1, int globe2, int globe3, int globe4, int globe5, int sports,
                  int music1, int music2, int movie, int workout, int read, int photo, int extroverted,
                  int energetic, int impulsive, int cautious, int persistent, int perceptive, int creative)
    {
        this.name = name;
        this.globe1 = globe1;
        this.globe2 = globe2;
        this.globe3 = globe3;
        this.globe4 = globe4;
        this.globe5 = globe5;
        this.sports = sports;
        this.music1 = music1;
        this.music2 = music2;
        this.movie = movie;
        this.workout = workout;
        this.read = read;
        this.photo = photo;
        this.extroverted = extroverted;
        this.energetic = energetic;
        this.impulsive = impulsive;
        this.cautious = cautious;
        this.persistent = persistent;
        this.perceptive = perceptive;
        this.creative = creative;
    }

    public String getName()
    {
        return this.name;
    }

    public String generateEmail()
    {
        String email = "";
        String s = this.name;
        //get Index of first whitespace
        int space = s.indexOf(" ");
        //eliminate accidental preceding whitespace
        while (space == 0) {
            System.out.println(space);
            s = s.substring(1);
            space = s.indexOf(" ");
        }
        //invalid if no space found or there is only a first name given
        if(space == -1 || space == s.length()-1)
        {
            return ("Invalid Email");
        }
        //build email string
        email = email.concat(s.substring(0, space));
        email = email.concat(".");
        email = email.concat(s.substring(space + 1));
        email = email.replaceAll(" ", "");
        email = email.concat("@ukg.com");
        email = email.toLowerCase();
        return email;
    }

    public double distanceToOtherIntern(Intern other) {
        /**
         *  Calculates the Euclidean/L2 distance between this Intern and the
         *  provided Intern, using their 5 "globe" attributes as dimensions.
         **/

        return Math.sqrt(Math.pow(this.globe1 - other.globe1, 2) +
                Math.pow(this.globe2 - other.globe2, 2) +
                Math.pow(this.globe3 - other.globe3, 2) +
                Math.pow(this.globe4 - other.globe4, 2) +
                Math.pow(this.globe5 - other.globe5, 2) +
                Math.pow(this.sports - other.sports, 2) +
                Math.pow(this.music1- other.music1, 2) +
                Math.pow(this.music2 -other.music2, 2) +
                Math.pow(this.movie - other.movie, 2) +
                Math.pow(this.workout - other.workout, 2) +
                Math.pow(this.read - other.read, 2) +
                Math.pow(this.photo - other.photo, 2) +
                Math.pow(this.extroverted - other.extroverted, 2) +
                Math.pow(this.energetic - other.energetic, 2) +
                Math.pow(this.impulsive - other.impulsive, 2) +
                Math.pow(this.cautious - other.cautious, 2) +
                Math.pow(this.persistent - other.persistent, 2) +
                Math.pow(this.perceptive - other.perceptive, 2) +
                Math.pow(this.creative - other.creative, 2));
    }

    public List<Intern> getClosestInterns(List<Intern> others, int howMany) {
        /**
         * Given a list of other Interns (which may include this Intern), find the closest
         * Interns to this one.
         *
         * If the provided List includes this Intern, that item isn't considered.
         */
        // Set up a comparator to compare by distance to this Intern
        Comparator<Intern> comparator = (i1, i2) -> Double.compare(this.distanceToOtherIntern(i1), this.distanceToOtherIntern(i2));
        return others.stream()
                // Filter out any list entries that correspond to this Intern (their names will match)
                .filter(other -> !other.name.equals(this.name))
                // Sort by distance to this Intern
                .sorted(comparator)
                // Take only the first few Interns, depending on the parameter
                .limit(howMany)
                // Convert the result to a List
                .collect(Collectors.toList());
    }

    public String toString() {
        return String.format("%s, attributes [%s %s %s %s %s]", name, globe1, globe2, globe3, globe4, globe5);
    }
}
