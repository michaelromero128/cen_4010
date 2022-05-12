package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class EndpointController {
	

	@CrossOrigin(origins = "localhost")
	@RequestMapping(path = "/sendEmail/{email}", method= RequestMethod.POST)
	public String postComment(@PathVariable String email ,@RequestBody String text) {
		System.out.println(email);
		System.out.println(text);
		try {
			System.out.println("readStart");
			File reader = new File("data.csv");
			Scanner scan = new Scanner(reader);
			while(scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
			scan.close();
			FileWriter writer = new FileWriter("data.csv");
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "okay";
	}
	
	@GetMapping("/index/{email}")
	public ModelAndView renderIndex(@PathVariable String email) {
		ModelAndView model = new ModelAndView("resultsThyme");
		List<Intern> interns = new ArrayList<Intern>();
		try{
			interns= generateList("data.csv");
		}catch(Exception e) {
			e.printStackTrace();
		}
		Intern guy = null;
		Iterator<Intern> itt = interns.iterator();
		while(itt.hasNext()) {
			Intern inty = itt.next();
			if(inty.generateEmail().equals(email)) {
				guy = inty;
				break;
			}
		}
		
		model.addObject("username",email);
		if(guy != null) {
			model.addObject("results", guy.getClosestInterns(interns, 3));
		}else {
			model.addObject("results",new ArrayList<Intern>());
		}
		
		return model;
	}
	
	
	static ArrayList<Intern> generateList(String filePath) throws FileNotFoundException {
        ArrayList<Intern> interns = new ArrayList<Intern>();
        File csv = new File(filePath); //might have to change to filepath
        try {
            Scanner sc = new Scanner(csv);
            while (sc.hasNextLine()) {
                String curRow = sc.nextLine();
                String[] cols = curRow.split(",");
                Intern curIntern = new Intern(cols[0], Integer.parseInt(cols[1]), Integer.parseInt(cols[2]), Integer.parseInt(cols[3]), Integer.parseInt(cols[4]), Integer.parseInt(cols[5]),
                        Integer.parseInt(cols[6]), Integer.parseInt(cols[7]), Integer.parseInt(cols[8]), Integer.parseInt(cols[9]),
                        Integer.parseInt(cols[10]), Integer.parseInt(cols[11]), Integer.parseInt(cols[12]), Integer.parseInt(cols[13]),
                        Integer.parseInt(cols[14]), Integer.parseInt(cols[15]), Integer.parseInt(cols[16]), Integer.parseInt(cols[17]),
                        Integer.parseInt(cols[18]), Integer.parseInt(cols[19]));
                interns.add(curIntern);
                System.out.println(curIntern.getName() + " has been added!");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw e;
        }
        return interns;
    }
	
	
	
//	//Post mapping occurs when the form is submitted.  Return your results.
//	@GetMapping(path = "/")
//	public String getResultsSubmit(@ModelAttribute GetMyInfo userWantingInfo, Model model) {
//		//Do some logic here to get the results.
//		Results results = new Results();
//		results.setUsername("test");
//		ArrayList<Integer> testScores = new ArrayList<>();
//		testScores.add(99);
//		testScores.add(999);
//		results.setUserScores(testScores);
//
//
//
//
//		model.addAttribute("results", results)
//		return "resultsthyme"
//	}


}



