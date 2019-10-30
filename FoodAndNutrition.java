
/**
 * Program #8 
 * Take an information type and output information such as rating, location, ingredients depending on type
 * CS108-3 
 * 25-Apr-18
 * @author Tommy Cao and Jason Songvilay
 */

import corgis.food.FoodLibrary;
import corgis.food.domain.*;
import java.util.ArrayList;
import corgis.business_dynamics.BusinessDynamicsLibrary;
import corgis.business_dynamics.domain.*;
import java.util.Random;
import java.util.List;
import java.util.Scanner;

import realtimeweb.simplebusiness.SimpleBusiness;
import realtimeweb.simplebusiness.domain.Business;

public class FoodAndNutrition {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please choose between Mexican food, Chinese food, Italian food, French Food, or Fast Food");
		String foodTypeInput = scan.nextLine();
		String[] foodTypes = { "Mexican food", "Chinese food", "Italian food", "French Food", "Fast Food" };
		String[] places = { "San Diego", "Los Angeles", "San Francisco", "Riverside", "Santa Barbara", "Irvine" };
		String[] services = { "Plumbing", "Carpentry", "Painting", "Cleaning", "I.T." };
		String[] foreignPlaces = { "Berlin, Germany", "Paris, France", "Sydney, Australia", "London, England" };

		printHeader(); // print first header with restaurants' location, type, name, and rating

		description(places, foodTypes); // print random restaurants' location, type, name, and average rating

		printHeader2(); // print a business's location, service, and average rating
		printAdvDescrption(foreignPlaces, services); // print advDescription for 3 iterations
		foodTypeToNutritionFact(foodTypeInput); // prints random facts about food type chosen by user
	}

	private static void printAdvDescrption(String[] places, String[] services) {
		for (int i = 0; i < 3; i++) {
			advDescription(places, services); // print advDescription for 3 iterations
			System.out.println();
		}

	}

	public static void description(String[] places, String[] foodTypes) {
		FoodLibrary foodLibrary = new FoodLibrary(); // nutrition info
		ArrayList<Report> foodReport = foodLibrary.getReports(false); // live information

		SimpleBusiness yelp = new SimpleBusiness(); // initialize database
		String foodType = randomKind(foodTypes); // randomize food type dependent on arr.length
		String randCity = randomCity(places); // randomize place dependent on arr.length
		double avgRating = 0;
		List<Business> businesses = yelp.search(foodType, randCity); // add yelp to business list
		for (int i = 0; i < businesses.size(); i++) {
			System.out.printf("%15s", randCity);
			System.out.printf("%15s", foodType);
			System.out.printf("%40s", businesses.get(i).getName());
			System.out.printf("%15s", businesses.get(i).getRating()); // print city, food type, business's name and
																		// average
			System.out.println();
			avgRating += businesses.get(i).getRating();
		}
		System.out.printf("%15s", "Average Rating");
		System.out.printf("%70s", avgRating / businesses.size());
		System.out.println();
		System.out.println();

		foodTypeToNutritionFact(foodType); // print randomized food type facts
	}

	public static void printHeader2() {
		System.out.printf("%15s", "Place");
		System.out.printf("%35s", "Services");
		System.out.printf("%15s", "Average");
		System.out.println();
		for (int i = 0; i < 60; i++)
			System.out.print("-"); // print place, services, and average rating in formatted columns
		System.out.println();
	}

	public static void printHeader() {
		System.out.printf("%15s", "Place");
		System.out.printf("%15s", "Food Type");
		System.out.printf("%40s", "Business Name");
		System.out.printf("%15s", "Rating");
		System.out.println();
		for (int i = 0; i < 85; i++)
			System.out.print("-"); // print place, food type, business name, and average rating in formatted
									// columns
		System.out.println();
	}

	public static void advDescription(String[] places, String[] services) {
		FoodLibrary foodLibrary = new FoodLibrary(); // nutrition info
		ArrayList<Report> foodReport = foodLibrary.getReports(false);

		SimpleBusiness yelp = new SimpleBusiness();
		String service = randomKind(services);
		String randCity = randomCity(places);
		List<Business> businesses = yelp.search(service, randCity);

		double avgRating = 0;
		int size = businesses.size() - 1;

		System.out.printf("%15s", randCity);
		System.out.printf("%35s", service);

		for (int i = 0; i < businesses.size(); i++) // ten lists
		{
			if (businesses.get(i).getRating() > 0) {
				avgRating += businesses.get(i).getRating(); // if the business' rating is greater than 0, add it to
															// avgRating
			} else {
				size -= 1; // if not greater than 0, decrease the size
			}
			if (i == size) {
				System.out.printf("%15s", avgRating / size); // print the average rating
				if (avgRating == 0) {
					System.out.printf("%15s", "0"); // once list is equal to size and avgRating is 0, print 0
				}
			}

		}

	}

	public static void foodTypeToNutritionFact(String foodType) {
		FoodLibrary foodLibrary = new FoodLibrary();
		ArrayList<Report> report = foodLibrary.getReports();

		//print saturated fat and protein values of foodType, if not found then prompt user to input agaib
		if (foodType == "Mexican food") {
			System.out.println(foodType + " will probably contain CHEESE. Here are some CHEESY facts!");
			System.out.println(report.get(135).getCategory() + " has "
					+ report.get(135).getData().getFat().getSaturatedFat() + " grams of saturated fat");
			System.out.println(report.get(135).getCategory() + " has " + report.get(135).getData().getProtein()
					+ " grams of protein");
			System.out.println();
		} else if (foodType == "Chinese food") {
			System.out.println(foodType + " sometimes serves DUCK as a delicacy. Here are some QUACKIN facts!");
			System.out.println(report.get(859).getCategory() + " has "
					+ report.get(859).getData().getFat().getSaturatedFat() + " grams of saturated fat");
			System.out.println(report.get(859).getCategory() + " has " + report.get(859).getData().getProtein()
					+ " grams of protein");
			System.out.println();
		} else if (foodType == "Italian food") {
			System.out.println(foodType + " will probably contain PEPPER. I'll SHAKE out some facts for you!");
			System.out.println(report.get(217).getCategory() + " has "
					+ report.get(217).getData().getFat().getSaturatedFat() + " grams of saturated fat");
			System.out.println(report.get(217).getCategory() + " has " + report.get(217).getData().getProtein()
					+ " grams of protein");
			System.out.println();
		} else if (foodType == "French Food") {
			System.out.println(foodType + " will probably contain EGGS. Lets LAY out some facts!");
			System.out.println(report.get(111).getCategory() + " has "
					+ report.get(111).getData().getFat().getSaturatedFat() + " grams of saturated fat");
			System.out.println(report.get(111).getCategory() + " has " + report.get(111).getData().getProtein()
					+ " grams of protein");
			System.out.println();
		} else if (foodType == "Fast Food") {
			System.out.println(foodType + " I like FRIED CHICKEN. Here are some sizzling facts!");
			System.out.println(report.get(739).getCategory() + " has "
					+ report.get(739).getData().getFat().getSaturatedFat() + " grams of saturated fat");
			System.out.println(report.get(739).getCategory() + " has " + report.get(739).getData().getProtein()
					+ " grams of protein");
			System.out.println();
		} else {
			System.out.println("We do not have any Nutrition Facts about that food type, please try again");
		}

	}

	public static String randomCity(String[] arr) {
		Random rand = new Random();

		return arr[rand.nextInt(arr.length)]; // convert randomCity array to individual strings

	}

	public static String randomKind(String[] arr) {
		Random rand = new Random();

		return arr[rand.nextInt(arr.length)]; // convert randomKind array to individual strings

	}

	public void foodType(String[] arr) {
		System.out.println(arr.toString()); // convert foodType array info to individual strings
	}

}
